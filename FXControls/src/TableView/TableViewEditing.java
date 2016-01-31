
package TableView;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.util.converter.LocalDateStringConverter;

/*
A cell in a TableView can be edited. An editable cell switches between editing and nonediting modes.
In editing mode, cell data can be modified by the user. For a cell to enter editing mode, the TableView,
TableColumn, and TableCell must be editable. All three of them have an editable property, which can be
set to true using the setEditable(true) method. By default, TableColumn and TableCell are editable.
To make cells editable in a TableView, you need make the TableView editable:
    TableView<Person> table = ...
    table.setEditable(true);

The TableColumn class supports three types of events:
    • onEditStart
    • onEditCommit
    • onEditCancel
The onStartEdit event is fired when a cell in the column enters editing mode. The onEditCommit
event is fired when the user successfully commits the editing, for example, by pressing the Enter key in a
TextField. The onEditCancel event is fired when the user cancels the editing, for example, by pressing the
Esc key in a TextField.
The events are represented by an object of the TableColumn.CellEditEvent class. The event object
encapsulates the old and new values in the cell, the row object from the items list of the TableView,
TableColumn, TablePosition indicating the cell position where the editing is happening, and the reference
of the TableView. Use the methods of the CellEditEvent class to get these values.

Making a TableView editable does not let you edit its cell data. You need to do a little more plumbing
before you can edit data in cells. Cell-editing capability is provided through specialized implementation of
the TableCell class. the JavaFX library provides a few of these implementations. Set the cell factory for a
column to use one of the following implementations of the TableCell to edit cell data:
    • CheckBoxTableCell
    • ChoiceBoxTableCell
    • ComboBoxTableCell
    • TextFieldTableCell
Реализации и пояснения смотреть в коде.

The program shows how to edit data in a TableView using different types of controls.
The TableView contains Id, First Name, Last Name, Birth Date, Baby, and Gender columns. The Id column
is noneditable. The First Name, Last Name, and Birth Date columns use TextFieldTableCell, so they can
be edited using a TextField. The Baby column is a noneditable computed field and is not backed by the
data model. It uses CheckBoxTableCell to render its values. The Gender column is an editable computed
field. It is not backed by the data model. It uses a ComboBoxTableCell that presents the user a list of values
(Male and Female) in editing model. When the user selects a value, the value is not saved to the data model.
It stays in the cell. An onEditCommit event handler is added that prints the gender selection on the standard
output. The Birth Date value for the fifth row is being edited.
*/
public class TableViewEditing extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TableView<Person> table = new TableView<>(PersonTableUtil.getPersonList());
		
		// Make the TableView editable
		table.setEditable(true);
		
		// Add columns with appropriate editing features
		addIdColumn(table);
		addFirstNameColumn(table);
		addLastNameColumn(table);
		addBirthDateColumn(table);
		addBabyColumn(table);
		addGenderColumn(table);
		
		HBox root = new HBox(table);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;"
        );
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Editing Data in a TableView");
		stage.show();
	}
	
	public void addIdColumn(TableView<Person> table) {
		// Id column is non-editable
		table.getColumns().add(PersonTableUtil.getIdColumn());
	}
	
    /*
    A TextFieldTableCell renders a TextField inside the cell when the cell is being edited where the user can
    modify the data. It renders the cell data in a Label when the cell is not being edited.
    
    Clicking a selected cell or double-clicking an unselected cell puts the cell into editing mode, which
    displays the cell data in a TextField. Once the cell is in editing mode, you need to click in the TextField
    (one more click!) to put the caret in the TextField so you can make changes. Notice that you need a
    minimum of three clicks to edit a cell.
    
    If you are in the middle of editing a cell data, press the Esc key to cancel editing, which will return the
    cell to nonediting mode and reverts to the old data in the cell. Pressing the Enter key commits the data to the
    underlying data model if the TableColumn is based on a Writable ObservableValue.
    
    If you are editing a cell using a TextFieldTableCell, moving the focus to another cell, for example,
    by clicking another cell, cancels the editing and puts the old value back in the cell. This is not what a user
    expects. At present, there is no easy solution for this problem. You will have to create a subclass of TableCell
    and add a focus change listener, so you can commit the data when the TextField loses focus.
    
    Use the forTableColumn() static method of the TextFieldTableCell class to get a cell factory that
    uses a TextField to edit cell data.
    */
	public void addFirstNameColumn(TableView<Person> table) {
		// First Name is a String, editable column
		TableColumn<Person, String> fNameCol = PersonTableUtil.getFirstNameColumn();
		
		// Use a TextFieldTableCell, so it can be edited
		fNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
		
		table.getColumns().add(fNameCol);
	}
	
	public void addLastNameColumn(TableView<Person> table) {
		// Last Name is a String, editable column
		TableColumn<Person, String> lNameCol = PersonTableUtil.getLastNameColumn();
		
		// Use a TextFieldTableCell, so it can be edited
		lNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
		
		table.getColumns().add(lNameCol);
	}
	
    /*
    Sometimes you need to edit nonstring data using a TextField, for example, for a date. The date may be
    represented as an object of the LocalDate class in the model. You may want to display it in a TextField as
    a formatted string. When the user edits the date, you want to commit the data to the model as a LocalDate.
    The TextFieldTableCell class supports this kind of object-to-string and vice versa conversion through
    a StringConverter. The following snippet of code sets a cell factory for a Birth Date column with a
    StringConverter, which converts a string to a LocalDate and vice versa. The column type is LocalDate.
    By default, the LocalDateStringConverter assumes a date format of mm/dd/yyyy.
    */
	public void addBirthDateColumn(TableView<Person> table) {
		// Birth Date is a LocalDate, editable column
		TableColumn<Person, LocalDate> birthDateCol = PersonTableUtil.getBirthDateColumn();

		// Use a TextFieldTableCell, so it can be edited
		LocalDateStringConverter converter = new LocalDateStringConverter();
		birthDateCol.setCellFactory(TextFieldTableCell.<Person, LocalDate>forTableColumn(converter));
		
		table.getColumns().add(birthDateCol);
	}
	
    /*
    A CheckBoxTableCell renders a check box inside the cell. Typically it is used to represent a boolean value in
    a column. The class provides a way to map other types of values to a boolean value using a Callback object.
    The check box is selected if the value is true. Otherwise, it is unselected. Bidirectional binding is used to
    bind the selected property of the check box and the underlying ObservableValue. If the user changes the
    selection, the underlying data are updated and vice versa.
    
    You do not have a boolean property in the Person class. You must create a boolean column by providing
    a cell value factory, as shown in the following code. If a Person is a baby, the cell value factory returns true.
    Otherwise, it returns false.
    
    A CheckBoxTableCell does not fire the cell-editing events. The selected property of the check box is
    bound to the ObservableValue representing the data in the cell. If you are interested in tracking the selection
    change event, you need to add a ChangeListener to the data for the cell.    
    */
	public void addBabyColumn(TableView<Person> table) {
		// Baby? is a Boolean, non-editable column
		TableColumn<Person, Boolean> babyCol = new TableColumn<>("Baby?");
		babyCol.setEditable(false);
		
		// Set a cell value factory
		babyCol.setCellValueFactory(cellData -> {
	        Person p = cellData.getValue();
			Boolean v =  (p.getAgeCategory() == Person.AgeCategory.BABY);
			return new ReadOnlyBooleanWrapper(v);
		});
        
		// Use a CheckBoxTableCell to display the boolean value
		babyCol.setCellFactory(CheckBoxTableCell.<Person>forTableColumn(babyCol));

		table.getColumns().add(babyCol);
	}
	
    /*
    A ChoiceBoxTableCell(ComboBoxTableCell) renders a choice box with a specified list of values inside the cell. The type of values
    in the list must match the type of the TableColumn. The data in a ChoiceBoxTableCell are displayed in a
    Label when the cell is not being edited. A ChoiceBox is used when the cell is being edited.
    
    The Person class does not have a gender property. You want to add a Gender column to a
    TableView<Person>, which can be edited using a choice box. The following snippet of code creates the
    TableColumn and sets a cell value factory, which sets all cells to an empty string. You would set the cell
    value factory to use the gender property of the Person class if you had one.
    
    A ComboBoxTableCell renders a combo box with a specified list of values inside the cells. It works similar to a
    ChoiceBoxTableCell.
    
    Clicking a selected cell puts the cell into editing mode. Double-clicking an unselected cell puts the cell
    into editing mode. Changing the focus to another cell or selecting an item from the list puts the editing
    cell into nonediting mode and the current value is displayed in a Label.
    */
	public void addGenderColumn(TableView<Person> table) {
		// Gender is a String, editable, ComboBox column
		TableColumn<Person, String> genderCol = new TableColumn<>("Gender");
		genderCol.setMinWidth(80);

		// By default, all cells are have null values
		genderCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(null));
		
        /*You can create a cell factory that uses a choice box for editing data in cells using the forTableColumn()
        static method of the ChoiceBoxTableCell class. You need to specify the list of items to be displayed in the
        choice box.*/
		// Set a ComboBoxTableCell, so we can selects a value from a list
		genderCol.setCellFactory(ComboBoxTableCell.<Person, String>forTableColumn("Male", "Female"));
		
		// Add an event handler to handle the edit commit event. It displays the selected value on the standard output
		genderCol.setOnEditCommit(e -> {
			int row = e.getTablePosition().getRow();		    
			Person person = e.getRowValue();
			System.out.println(
                "Gender changed for " + 
                person.getFirstName() + " " + person.getLastName() + 
               " at row " + (row + 1) + " to " + e.getNewValue());
		});

		table.getColumns().add(genderCol);
	}
}
