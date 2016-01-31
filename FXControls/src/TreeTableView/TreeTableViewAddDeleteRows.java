
package TreeTableView;

import DatePicker.LocalDateStringConverter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import static javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/*
Selecting Cells and Rows in a TreeTableView:

TreeTableView has a selection model represented by its property called selectionModel. A selection
model is an instance of the TreeTableViewSelectionModel class, which is an inner static class of the
TreeTableView class. The selection model supports cell-level and row-level selection. It also supports two
selection modes: single and multiple. In the single selection mode, only one cell or row can be selected at a
time. In the multiple-selection mode, multiple cells or rows can be selected. By default, single row selection
is enabled. You can enable multirow selection using the following code:
    TreeTableView<Person> treeTable = ...
    // Turn on multiple-selection mode for the TreeTableView
    TreeTableViewSelectionModel<Person> tsm = treeTable.getSelectionModel();
    tsm.setSelectionMode(SelectionMode.MULTIPLE);

The cell-level selection can be enabled by setting the cellSelectionEnabled property of the selection
model to true, as shown in the following snippet of code. When the property is set to true, the TreeTableView
is put in cell-level selection mode and you cannot select an entire row. If multiple-selection mode is
enabled, you can still select all cells in a row. However, the row itself is not reported as selected because the
TreeTableView is in the cell-level selection mode. By default, cell-level selection mode is false.
    // Enable cell-level selection
    tsm.setCellSelectionEnabled(true);

The selection model provides information about the selected cells and rows. The isSelected(int
rowIndex) method returns true if the row at the specified rowIndex is selected. Use the isSelected(int
rowIndex, TableColumn<S,?> column) method to determine if a cell at the specified rowIndex and column
is selected. The getModelItem(int rowIndex) method returns the TreeItem for the specified rowIndex.

The selection model provides several methods to select cells and rows and get the report of selected
cells and rows. Please refer to the API documentation for the TreeTableViewSelectionModel class for
more details.

It is often a requirement to make some changes or take an action when a cell or row selection changes
in a TreeTableView. For example, a TreeTableView may act as a master list in a master-detail data view.
When the user selects a row in the master list, you want the data in the detail view to refresh. Several
methods of the TreeTableViewSelectionModel class return an ObservableList of selected indices and
items. If you are interested in handling the selection change event, you need to add a ListChangeListener
to one of those ObservableLists. The following snippet of code adds a ListChangeListener to the
ObservableList returned by the getSelectedIndices() method to track the row selection change in a
TreeTableView:
    TreeTableViewSelectionModel<Person> tsm = treeTable.getSelectionModel();
    ObservableList<Integer> list = tsm.getSelectedIndices();
    // Add a ListChangeListener
    list.addListener((ListChangeListener.Change<? extends Integer> change) -> {
        System.out.println("Row selection has changed");
    });

-------------------------------------------------------------------------

Each row in a TreeTableView is represented by a TreeItem in its model. Adding and deleting a row in a
TreeTableView is as simple as adding and deleting TreeItems in the model.

The program shows how to add and delete rows. It displays a prebuilt family hierarchy
in a TreeTableView along with Add and Delete buttons. Clicking the Add button
adds a new row as a child row for the selected row. If there is no row, a new root item is added to the tree.
The new row is selected, scrolled to the view, and put in editing mode. The addRow() method contains the
logic for adding a row. The Delete button deletes the selected row. Notice that all child rows of the selected
row are deleted.
*/
public class TreeTableViewAddDeleteRows  extends Application {
	private final TreeTableView<Person> treeTable = new TreeTableView<>();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		// Create the model
		TreeItem<Person> rootNode = TreeTableUtil.getModel();
		rootNode.setExpanded(true);
		treeTable.setRoot(rootNode);
		treeTable.setPrefWidth(400);
		treeTable.setEditable(true);
		treeTable.getSelectionModel().selectFirst();

		// Set appropariate cell factories for columns
		TreeTableColumn<Person, String> fNameCol = TreeTableUtil.getFirstNameColumn();
		fNameCol.setCellFactory(TextFieldTreeTableCell.<Person>forTreeTableColumn());

		TreeTableColumn<Person, String> lNameCol = 
				TreeTableUtil.getLastNameColumn();
		lNameCol.setCellFactory(TextFieldTreeTableCell.<Person>forTreeTableColumn());

		TreeTableColumn<Person, LocalDate> birthDateCol = TreeTableUtil.getBirthDateColumn();
		LocalDateStringConverter converter = new LocalDateStringConverter();
		birthDateCol.setCellFactory(
		TextFieldTreeTableCell.<Person, LocalDate>forTreeTableColumn(converter));
	
		// Add Columns
		treeTable.getColumns().addAll(fNameCol, lNameCol, birthDateCol);

		// Add a placeholder to the TreeTableView.
		// It is displayed when the root node is deleted.
		treeTable.setPlaceholder(new Label("Click the Add button to add a row."));
	
		Label msgLbl = new Label("Please select a row to add/delete.");
		HBox buttons = this.getButtons();
		VBox root = new VBox(msgLbl, buttons, treeTable);
		root.setSpacing(10);
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
		stage.setTitle("Adding/Deleting Rows in a TreeTableView");
		stage.show();
	}

	private HBox getButtons() {
		Button addBtn = new Button("Add");
		addBtn.setOnAction(e -> addRow());
		
		Button deleteBtn = new Button("Delete");
		deleteBtn.setOnAction(e -> deleteRow());

		return new HBox(20, addBtn, deleteBtn);
	}

	private void addRow() {
		if (treeTable.getExpandedItemCount() == 0 ) {	
			// There is no row in teh TreeTableView
			addNewRootItem();
		} else if (treeTable.getSelectionModel().isEmpty()) {
			System.out.println("Select a row to add.");
			return;
		} else {
			addNewChildItem();
		}
	}

	private void addNewRootItem() {
		// Add a root Item
		TreeItem<Person> item = new TreeItem<>(new Person("New", "New", null));
		treeTable.setRoot(item);
	
		// Edit the item
		this.editItem(item);
	}

	private void addNewChildItem() {
		// Prepare a new TreeItem with a new Person object
		TreeItem<Person> item = new TreeItem<>(new Person("New", "New", null));

		// Get the selection model
		TreeTableViewSelectionModel<Person> sm = treeTable.getSelectionModel();

		// Get the selected row index
		int rowIndex = sm.getSelectedIndex();

		// Get the selected TreeItem
		TreeItem<Person> selectedItem = sm.getModelItem(rowIndex);

		// Add the new item as children to the selected item
		selectedItem.getChildren().add(item);

		// Make sure the new item is visible
		selectedItem.setExpanded(true);

		// Edit the item
		this.editItem(item);
	}

	private void editItem(TreeItem<Person> item) {
		// Scroll to the new item
		int newRowIndex = treeTable.getRow(item);
		treeTable.scrollTo(newRowIndex);

		// Put the first column in editing mode
		TreeTableColumn<Person, ?> firstCol = treeTable.getColumns().get(0);
		treeTable.getSelectionModel().select(item);
		treeTable.getFocusModel().focus(newRowIndex, firstCol);
		treeTable.edit(newRowIndex, firstCol);
	}

	private void deleteRow() {
		// Get the selection model
		TreeTableViewSelectionModel<Person> sm = treeTable.getSelectionModel();
		if (sm.isEmpty()) {
			System.out.println("Select a row to delete.");
			return;
		}

		int rowIndex = sm.getSelectedIndex();
		TreeItem<Person> selectedItem = sm.getModelItem(rowIndex);
	
		TreeItem<Person> parent = selectedItem.getParent();	
		if (parent != null) {
			parent.getChildren().remove(selectedItem);
		} else {
			// Must be deleting the root item
			treeTable.setRoot(null);
		}
	}
}
