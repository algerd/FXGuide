
package TreeTableView;

import DatePicker.LocalDateStringConverter;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
A cell in a TreeTableView can be editable. An editable cell switches between editing and nonediting
modes. In editing mode, cell data can be modified by the user. In order for a cell to enter editing mode, the
TreeTableView, TreeTableColumn, and TreeTableCell must be editable. All three of them have an editable
property, which can be set to true using the setEditable(true) method. By default, TreeTableColumn
and TreeTableCell are editable. To make cells editable in a TreeTableView, you need to make the
TreeTableView editable, as shown in the following code:
    TreeTableView<Person> treeTable = ...
    treeTable.setEditable(true);

The TreeTableColumn class supports three types of events:
    • onEditStart
    • onEditCommit
    • onEditCancel

The onEditStart event is fired when a cell in the column enters editing mode. The onEditCommit
event is fired when the user successfully commits the editing, for example, by pressing the Enter key in a
TextField. The onEditCancel event is fired when the user cancels the editing, for example, by pressing the
Esc key in a TextField. The events are represented by an object of the TreeTableColumn.CellEditEvent
class. The event object encapsulates the old and new values in the cell, the TreeItem of the model being
edited, TreeTableColumn, the TreeTablePosition indicating the cell position where the editing is
happening, and the reference of the TreeTableView. Use the methods of the CellEditEvent class to get
these values.

Making a TreeTableView editable does not let you edit its cell data. Cell editing capability is provided through specialized
implementations of the TreeTableCell class. JavaFX library provides a few of these implementations. Set the
cell factory for a column to use one of the following implementations of the TreeTableCell to edit cell data:
    • CheckBoxTreeTableCell
    • ChoiceBoxTreeTableCell
    • ComboBoxTreeTableCell
    • TextFieldTreeTableCell

Please refer to the corresponding section for the TableView control 
for an in-depth discussion of editing data using various controls and handling editing related events.
The only difference between editing cells in TableView and TreeTableView
is the cell classes you will need to use: TableView uses subclasses of TableCell that are named as
XxxTableCell; TreeTableView uses subclasses of TreeTableCell that are named as XxxTreeTableCell.

The program shows how to make cells in a TreeTableView editable. Run the program
and click a cell twice (using two single clicks) to start editing data. 
When you are done editing, press the Enter key to commit the changes or press the Esc key to cancel editing.
*/
public class TreeTableViewEditing extends Application {		
	public static void main(String[] args) {		
		Application.launch(args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		// Create the model
		TreeItem<Person> rootNode = TreeTableUtil.getModel();
		rootNode.setExpanded(true);
		
		// Create a TreeTableView with a model
		TreeTableView<Person> treeTable = new TreeTableView<>(rootNode);	
		treeTable.setPrefWidth(400);
		
		// Must make the TreeTableView editable
		treeTable.setEditable(true);

		// Set appropariate cell factories for 
		TreeTableColumn<Person, String> fNameCol = TreeTableUtil.getFirstNameColumn();
		fNameCol.setCellFactory(TextFieldTreeTableCell.<Person>forTreeTableColumn());

		TreeTableColumn<Person, String> lNameCol =	TreeTableUtil.getLastNameColumn();
		lNameCol.setCellFactory(TextFieldTreeTableCell.<Person>forTreeTableColumn());

        /*
        When editing nonstring data in cell, you need to provide a StringConverter. The following snippet
        of code sets a cell factory for a Birth Date column with a StringConverter, which converts a String to
        a LocalDate and vice versa. The column type is LocalDate. By default, the LocalDateStringConverter
        assumes a date format of mm/dd/yyyy:
        */
		TreeTableColumn<Person, LocalDate> birthDateCol = TreeTableUtil.getBirthDateColumn();
		LocalDateStringConverter converter = new LocalDateStringConverter();
		birthDateCol.setCellFactory(TextFieldTreeTableCell.<Person, LocalDate>forTreeTableColumn(converter));

		// Add Columns
		treeTable.getColumns().addAll(fNameCol, lNameCol, birthDateCol);
		
		HBox root = new HBox(treeTable);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;");
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Editing Data in a TreeTableView");
		stage.show();	
	}
}
