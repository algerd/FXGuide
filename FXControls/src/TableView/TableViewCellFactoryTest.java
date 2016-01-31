
package TableView;

import javafx.application.Application;
import java.time.LocalDate;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.scene.control.cell.CheckBoxTableCell;

/*
A cell in a TableColumn is an instance of the TableCell class, which displays the data in the cell. A TableCell
is a Labeled control, which is capable of displaying text, a graphic, or both.

You can specify a cell factory for a TableColumn. The job of a cell factory is to render the data in the cell.
The TableColumn class contains a cellFactory property, which is a Callback object. Its call() method is
passed in the reference of the TableColumn to which the cell belongs. The method returns an instance of
TableCell. The updateItem() method of the TableCell is overridden to provide the custom rendering of
the cell data.

TableColumn uses a default cell factory if its cellFactory property is not specified. The default cell
factory displays the cell data depending on the type of the data. If the cell data comprise a node, the data are
displayed in the graphic property of the cell. Otherwise, the toString() method of the cell data is called and
the retuned string is displayed in the text property of the cell.

You can also display images in cells. In the updateItem() method, create an
ImageView object for the image and display it using the setGraphic() method of the TableCell. TableCell
contains tableColumn, tableRow, and tableView properties that store the references of its TableColumn,
TableRow, and TableView, respectively. These properties are useful to access the item in the data model that
represents the row for the cell.

The following are subclasses of TableCell that render cell data in different ways. For example, a
CheckBoxTableCell renders cell data in a check box and a ProgressBarTableCell renders a number using
a progress bar:
    • CheckBoxTableCell
    • ChoiceBoxTableCell
    • ComboBoxTableCell
    • ProgressBarTableCell
    • TextFieldTableCell
*/
public class TableViewCellFactoryTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		TableView<Person> table = new TableView<>(PersonTableUtil.getPersonList());

        /*
        The Birth Date column is formatted as yyyy-mm-dd, which is the default
        ISO date format return by the toString() method of the LocalDate class. If you would like to format
        birth dates in the mm/dd/yyyy format, you can achieve this by setting a custom cell factory for the Birth
        Date column:
        */
		// Create the birth date column
		TableColumn<Person, LocalDate> birthDateCol = PersonTableUtil.getBirthDateColumn();

		// Set a custom cell factory for Birth Date column
		birthDateCol.setCellFactory(col -> {
			TableCell<Person, LocalDate> cell = new TableCell<Person, LocalDate>() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    // Cleanup the cell before populating it
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        String formattedDob = DateTimeFormatter.ofPattern("MM/dd/yyyy").format(item);
                        this.setText(formattedDob);
                    }
                }
            };
			return cell;
		});

        /*
        The following snippet of code creates a column labeled Baby? and sets a cell factory to display the
        value in a CheckBoxTableCell. The forTableColumn(TableColumn<S, Boolean> col) method of the
        CheckBoxTableCell class returns a Callback object that is used as a cell factory:
        */
        // Create a "Baby?" column
		TableColumn<Person, Boolean> babyCol = new TableColumn<>("Baby?");
		babyCol.setCellValueFactory(
            cellData -> {
                Person p = cellData.getValue();
                Boolean v = (p.getAgeCategory() == Person.AgeCategory.BABY);
                return new ReadOnlyBooleanWrapper(v);
            });
        
		// Set a custom cell factory for the baby column
		babyCol.setCellFactory(CheckBoxTableCell.<Person>forTableColumn(babyCol));

		// Add columns to the table
		table.getColumns().addAll(
            PersonTableUtil.getIdColumn(),
            PersonTableUtil.getFirstNameColumn(),
            PersonTableUtil.getLastNameColumn(),
            birthDateCol,
            babyCol
        );

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
		stage.setTitle("Using a Custom Cell Factory for a TableColumn");
		stage.show();
	}
}
