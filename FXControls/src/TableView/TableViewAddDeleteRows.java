
package TableView;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Arrays;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import static javafx.scene.control.TableView.TableViewSelectionModel;

/*
-----------------------------------------
Selecting Cells and Rows in a TableView:
-----------------------------------------
TableView has a selection model represented by its property selectionModel. A selection model is an
instance of the TableViewSelectionModel class, which is an inner static class of the TableView class. The
selection model supports cell-level and row-level selection. It also supports two selection modes: single
and multiple. In the single-selection mode, only one cell or row can be selected at a time. In the multipleselection
mode, multiple cells or rows can be selected. By default, single-row selection is enabled.
    TableView<Person> table = ...
    // Turn on multiple-selection mode for the TableView
    TableViewSelectionModel<Person> tsm = table.getSelectionModel();
    tsm.setSelectionMode(SelectionMode.MULTIPLE);

The cell-level selection can be enabled by setting the cellSelectionEnabled property of the selection
model to true, as in the following snippet of code. When the property is set to true, the TableView is put in
cell-level selection mode and you cannot select an entire row. If multiple-selection mode is enabled, you
can still select all cells in a row. However, the row itself is not reported as selected as the TableView is in
the cell-level selection mode. By default, cell-level selection mode is false.
    // Enable cell-level selection
    tsm.setCellSelectionEnabled(true);

The selection model provides information about the selected cells and rows. The isSelected(int
rowIndex) method returns true if the row at the specified rowIndex is selected. Use the isSelected(int
rowIndex, TableColumn<S,?> column) method to know if a cell at the specified rowIndex and column
is selected. The selection model provides several methods to select cells and rows and get the report of
selected cells and rows:
    • The selectAll() method selects all cells or rows.
    • The select() method is overloaded. It selects a row, a row for an item, and a cell.
    • The isEmpty() method returns true if there is no selection. Otherwise, it returns
    false.
    • The getSelectedCells() method returns a read-only
    ObservableList<TablePosition> that is the list of currently selected cells. The list
    changes as the selection in the TableView changes.
    • The getSelectedIndices() method returns a read-only ObservableList<Integer>
    that is the list of currently selected indices. The list changes as the selection in the
    TableView changes. If row-level selection is enabled, an item in the list is the row
    index of the selected row. If cell-level selection is enabled, an item in the list is the
    row index of the row in which one or more cells are selected.
    • The getSelectedItems() method returns a read-only ObservableList<S> where
    S is the generic type of the TableView. The list contains all items for which the
    corresponding row or cells have been selected.
    • The clearAndSelect() method is overloaded. It lets you clear all selections before
    selecting a row or a cell.
    • The clearSelection() method is overloaded. It lets you clear selections for a row, a
    cell, or the entire TableView.

It is often a requirement to make some changes or take an action when a cell or row selection changes
in a TableView. For example, a TableView may act as a master list in a master-detail data view. When the
user selects a row in the master list, you want to refresh the data in the detail view. If you are interested in
handling the selection change event, you need to add a ListChangeListener to one of the ObservableLists
returned by the above listed methods that reports on the selected cells or rows. The following snippet of
code adds a ListChangeListener to the ObservableList returned by the getSelectedIndices() method to
track the row selection change in a TableView:

    TableView<Person> table = ...
    TableViewSelectionModel<Person> tsm = table.getSelectionModel();
    ObservableList<Integer> list = tsm.getSelectedIndices();
    // Add a ListChangeListener
    list.addListener((ListChangeListener.Change<? extends Integer> change) -> {
        System.out.println("Row selection has changed");
    });

---------------------------------------------------

Adding and deleting rows in a TableView are easy. Note that each row in a TableView is backed by an item
in the items list. Adding a row is as simple as adding an item in the items list. When you add an item to the
items list, a new row appears in the TableView at the same index as the index of the added item in the items
list. If the TableView is sorted, it may need to be resorted after adding a new row. Call the sort() method of
the TableView to resort the rows after adding a new row.

You can delete a row by removing its item from the items list. An application provides a way for the
user to indicate the rows that should be deleted. Typically, the user selects one or more rows to delete.
Other options are to add a Delete button to each row or to provide a Delete check box to each row. Clicking
the Delete button should delete the row. Selecting the Delete check box for a row indicates that the row is
marked for deletion.

The program shows how to add and delete rows to a TableView. It displays a window with three sections:
    • The Add Person form at the top has three fields to add person details and an Add
    button. Enter the details for a person and click the Add button to add a record to the
    TableView. Error checking is skipped in the code.
    • In the middle, you have two buttons. One button is used to restore the default rows in
    the TableView. Another button deletes the selected rows.
    • At the bottom, a TableView is displayed with some rows. The multirow selection is
    enabled. Use the Ctrl or Shift key with the mouse to select multiple rows.
*/
public class TableViewAddDeleteRows extends Application {
	// Fields to add Person details
	private final TextField fNameField = new TextField();
	private final TextField lNameField = new TextField();
	private final DatePicker dobField = new DatePicker();
	
	// The TableView
	TableView<Person> table = new TableView<>(PersonTableUtil.getPersonList());
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		// Turn on multi-row selection for the TableView
		TableViewSelectionModel<Person> tsm = table.getSelectionModel();
		tsm.setSelectionMode(SelectionMode.MULTIPLE);
		
		// Add columns to the TableView
		table.getColumns().addAll(
            PersonTableUtil.getIdColumn(), 
            PersonTableUtil.getFirstNameColumn(), 
            PersonTableUtil.getLastNameColumn(), 
            PersonTableUtil.getBirthDateColumn()
        );	
		GridPane newDataPane  = this.getNewPersonDataPane();
		
		Button restoreBtn = new Button("Restore Rows");
		restoreBtn.setOnAction(e -> restoreRows());  

		Button deleteBtn = new Button("Delete Selected Rows");
		deleteBtn.setOnAction(e -> deleteSelectedRows());  
		
		VBox root = new VBox(newDataPane, new HBox(restoreBtn, deleteBtn), table);
		root.setSpacing(5);
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
		stage.setTitle("Adding/Deleting Rows in a TableViews");
		stage.show();	
	}
	
	public GridPane getNewPersonDataPane() {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(5);
		pane.addRow(0, new Label("First Name:"), fNameField);
		pane.addRow(1, new Label("Last Name:"), lNameField);
		pane.addRow(2, new Label("Birth Date:"), dobField);
		
		Button addBtn = new Button("Add");
		addBtn.setOnAction(e -> addPerson());
		
		// Add the "Add" button
		pane.add(addBtn, 2, 0);
		
		return pane;
	}
	
    /*
    The deleteSelectedRows() method implements the logic to
    delete the selected rows. When you remove an item from the items list, the selection model does not remove
    its index. Suppose the first row is selected. If you remove the first item from the items list, the second row,
    which becomes the first row, is selected. To make sure that this does not happen, you clear the selection
    for the row before you remove it from the items list. You delete rows from last to first (higher index to lower
    index) because when you delete an item from the list, all of the items after the deleted items will have
    different indices. Suppose you have selected rows at indices 1 and 2. Deleting a row at index 1 first changes
    the index of the index 2 to 1. Performing deletion from last to first takes care of this issue.
    */
	public void deleteSelectedRows() {	
		TableViewSelectionModel<Person> tsm = table.getSelectionModel();
		if (tsm.isEmpty()) {
			System.out.println("Please select a row to delete.");
			return;
		}
		
		// Get all selected row indices in an array
		ObservableList<Integer> list = tsm.getSelectedIndices();
		Integer[] selectedIndices = new Integer[list.size()];
		selectedIndices = list.toArray(selectedIndices);

		// Sort the array
		Arrays.sort(selectedIndices);
		
		// Delete rows (last to first)
		for(int i = selectedIndices.length - 1; i >= 0; i--) {
			tsm.clearSelection(selectedIndices[i].intValue());
			table.getItems().remove(selectedIndices[i].intValue());
		}
	}
	
	public void restoreRows() {
		table.getItems().clear();
		table.getItems().addAll(PersonTableUtil.getPersonList());
	}
	
	public Person getPerson() {
		return new Person(fNameField.getText(), lNameField.getText(), dobField.getValue());
	}
	
	public void addPerson() {
		Person p = getPerson();
		table.getItems().add(p);		
		clearFields();
	}

	public void clearFields() {
		fNameField.setText(null);
		lNameField.setText(null);
		dobField.setValue(null);
	}
}
