
package TableView;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

/*
For CustomTableCellTest.java

You can subclass TableCell to use any control to edit cell data. For
example, you may want to use a DatePicker to select a date in cells of a date column or RadioButtons to
select from multiple options. The possibilities are endless.
You need to override four methods of the TableCell class:
    • startEdit()
        The startEdit() method for the cell transitions from nonediting mode to editing mode. Typically, you
        set the control of your choice in the graphic property of the cell with the current data.
    • commitEdit()
        The commitEdit() method is called when the user action, for example, pressing the Enter key in a
        TextField, indicates that the user is done modifying the cell data and the data need to be saved in the
        underlying data model. Typically, you do not need to override this method as the modified data are
        committed to the data model if the TableColumn is based on a Writable ObservableValue.
    • cancelEdit()
        The cancelEdit() method is called when the user action, for example, pressing the Esc key in a
        TextField, indicates that the user wants to cancel the editing process. When the editing process is canceled,
        the cell returns to nonediting mode. You need to override this method and revert the cell data to their old
        values.
    • updateItem()
        The updateItem() method is called when the cell needs to be rendered again. Depending on the editing
        mode, you need to set the text and graphic properties of the cell appropriately.

The DatePickerTableCell class supports a StringConverter and the editable property value for
the DatePicker. You can pass them to the constructors or the forTableColumn() methods. It creates a
DatePicker control when the startEdit() method is called for the first time. A ChangeListener is added
that commits the data when a new date is entered or selected. Several versions of the forTableColumn()
static methods are provided that return cell factories. The following snippet of code shows how to use the
DatePickerTableCell class:

    TableColumn<Person, LocalDate> birthDateCol = ...

    // Set a cell factory for birthDateCol. The date format is mm/dd/yyyy and the DatePicker is editable.
    birthDateCol.setCellFactory(DatePickerTableCell.<Person>forTableColumn());

    // Set a cell factory for birthDateCol. The date format is "Month day, year" and and the DatePicker is non-editable
    StringConverter converter = new LocalDateStringConverter("MMMM dd, yyyy");

    birthDateCol.setCellFactory(DatePickerTableCell.<Person>forTableColumn(converter, false));

DatePickerTableCell class is used in CustomTableCellTest.java.
*/
@SuppressWarnings("unchecked")
public class DatePickerTableCell<S, T> extends TableCell<S, java.time.LocalDate> {
    
	private DatePicker datePicker;
	private StringConverter converter = null;
	private boolean datePickerEditable = true;
	
	public DatePickerTableCell() {
		this.converter = new LocalDateStringConverter();
	}
	
	public DatePickerTableCell(boolean datePickerEditable) {
	    this.converter = new LocalDateStringConverter();
		this.datePickerEditable = datePickerEditable;
	}

	public DatePickerTableCell(StringConverter<java.time.LocalDate> converter) {
		this.converter = converter;
	}

	public DatePickerTableCell(StringConverter<java.time.LocalDate> converter, boolean datePickerEditable) {
		this.converter = converter;
		this.datePickerEditable = datePickerEditable;
	}
	
	@Override
	public void startEdit() {
		// Make sure the cell is editable
		if (!isEditable() || 
		    !getTableView().isEditable() || 
		    !getTableColumn().isEditable()) {
			return;
		}
		
		// Let the ancestor do the plumbing job
		super.startEdit();
		
		// Create a DatePicker, if needed, and set it as the graphic for the cell
		if (datePicker == null) {
			this.createDatePicker();
		}
		
		this.setGraphic(datePicker);
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		this.setText(converter.toString(this.getItem()));
		this.setGraphic(null);
	}

	@Override
	public void updateItem(java.time.LocalDate item, boolean empty) {
		super.updateItem(item, empty);
		
		// Take actions based on whether the cell is being edited or not
		if (empty) {
			this.setText(null);
			this.setGraphic(null);
		}
		else {
			if (this.isEditing()) {
				if (datePicker != null) {
					datePicker.setValue((java.time.LocalDate)item);
				}
				this.setText(null);
				this.setGraphic(datePicker);
			}
			else {
				this.setText(converter.toString(item));
				this.setGraphic(null);
			}
		}
	}

	private void createDatePicker() {
		datePicker = new DatePicker();
		datePicker.setConverter(converter);
		
		// Set the current value in the cell to the DatePicker
		datePicker.setValue((java.time.LocalDate)this.getItem());
		
		// Configure the DatePicker properties
		datePicker.setPrefWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		datePicker.setEditable(this.datePickerEditable);
		
		// Commit the new value when the user selects or enters a date
		datePicker.valueProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue prop, Object oldValue, Object newValue) {
				if (DatePickerTableCell.this.isEditing()) {
					DatePickerTableCell.this.commitEdit((java.time.LocalDate)newValue);
				}
			}
		});
	}
	
	public static <S> Callback<TableColumn<S, java.time.LocalDate>, TableCell<S, java.time.LocalDate>> 
                forTableColumn() {
		return forTableColumn(true);
	}
	
	public static <S> Callback<TableColumn<S, java.time.LocalDate>, TableCell<S, java.time.LocalDate>> 
				forTableColumn(boolean datePickerEditable) {
		return (col -> new DatePickerTableCell<>(datePickerEditable));
	}
	
	public static <S> Callback<TableColumn<S, java.time.LocalDate>, TableCell<S, java.time.LocalDate>> 
                forTableColumn(StringConverter<java.time.LocalDate> converter) {
		return forTableColumn(converter, true);
	}
	
	public static <S> Callback<TableColumn<S, java.time.LocalDate>, TableCell<S, java.time.LocalDate>> 
                forTableColumn(StringConverter<java.time.LocalDate> converter, boolean datePickerEditable) {
		return (col -> new DatePickerTableCell<>(converter, datePickerEditable));
	}
}
