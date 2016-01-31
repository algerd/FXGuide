
package ListView;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
ListView has a selection model that stores the selected state of its items. Its selectionModel property
stores the reference of the selection model. By default, it uses an instance of the MultipleSelectionModel
class. You can use a custom selection model, however, that is rarely needed. The selection model can be
configured to work in two modes:
    • Single selection mode
    • Multiple selection mode

In single selection mode, only one item can be selected at a time. If an item is selected, the previously
selected item is deselected. By default, a ListView supports single selection mode. An item can be selected
using a mouse or a keyboard. You can select an item using a mouse-click. Using a keyboard to select an item
requires that the ListView has focus. You can use the up/down arrow in a vertical ListView and the left/
right arrow in a horizontal ListView to select items.

In multiple selection mode, multiple items can be selected at a time. Using only a mouse lets you select
only one item at a time. Clicking an item selects the item. Clicking an item with the Shift key pressed selects
all contiguous items. Clicking an item with the Ctrl key pressed selects a deselected item and deselects a
selected item. You can use the up/down or left/right arrow key to navigate and the Ctrl key with the spacebar
or Shift key with the spacebar to select multiple items. If you want a ListView to operate in multiple
selection mode, you need to set the selectionMode property of its selection model, as in the following code:
    // Use multiple selection mode
    seasons.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    // Set it back to single selection mode, which is the default for a ListView
    seasons.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

The MultipleSelectionModel class inherits from the SelectionModel class, which contains
selectedIndex and selectedItem properties.

The selectedIndex property is -1 if there is no selection. In single selection mode, it is the index of
the currently selected item. In multiple selection mode, it is the index of the last selected item. In multiple
selection mode, use the getSelectedIndices() method that returns a read-only ObservableList<Integer>
containing the indices of all selected items. If you are interested in listening for selection change in a
ListView, you can add a ChangeListener to the selectedIndex property or a ListChangeListener to the
ObservableList returned by the getSelectedIndices() method.

The selectedItem property is null if there is no selection. In single selection mode, it is the currently
selected item. In multiple selection mode, it is the last selected item. In multiple selection mode, use
the getSelectedItems() method that returns a read-only ObservableList<T> containing all selected
items. If you are interested in listening for selection change in a ListView, you can add a ChangeListener
to the selectedItem property or a ListChangeListener to the ObservableList<T> returned by the
getSelectedItems() method.

The selection model of ListView contains several methods to select items in different ways:
    • The selectAll() method selects all items.
    • The selectFirst() and selectLast() methods select the first item and the last
    item, respectively.
    • The selectIndices(int index, int... indices) method selects items at the
    specified indices. Indices outside the valid range are ignored.
    • The selectRange(int start, int end) method selects all indices from the start
    index (inclusive) to the end index (exclusive).
    • The clearSelection() and clearSelection(int index) methods clear all selection
    and the selection at the specified index, respectively.

The program idemonstrates how to use the selection model of a ListView for making
selections and listening for selection change events.  Run the application and use a mouse 
or buttons on the window to select items in the ListView. The selection details are displayed at the bottom.
*/
public class ListViewSelectionModel extends Application {
	private ListView<String> seasons;
	private final Label selectedItemsLbl = new Label("[None]");
	private final Label lastSelectedItemLbl = new Label("[None]");
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label seasonsLbl = new Label("Select Seasons:");
		seasons = new ListView<>();
		seasons.setPrefSize(120, 120);
		seasons.getItems().addAll("Spring", "Summer", "Fall", "Winter");

		// Enable multiple selection
		seasons.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Add a selection change listener
		seasons.getSelectionModel().selectedItemProperty().addListener(this::selectionChanged);

		// Add some buttons to assist in selection
		Button selectAllBtn = new Button("Select All");
		selectAllBtn.setOnAction(e -> seasons.getSelectionModel().selectAll()); 
 
		Button clearAllBtn = new Button("Clear All");
		clearAllBtn.setOnAction(e -> seasons.getSelectionModel().clearSelection());

		Button selectFirstBtn = new Button("Select First");
		selectFirstBtn.setOnAction(e -> seasons.getSelectionModel().selectFirst());

		Button selectLastBtn = new Button("Select Last");
		selectLastBtn.setOnAction(e -> seasons.getSelectionModel().selectLast());

		Button selectNextBtn = new Button("Select Next");
		selectNextBtn.setOnAction(e -> seasons.getSelectionModel().selectNext());
		
		Button selectPreviousBtn = new Button("Select Previous");
		selectPreviousBtn.setOnAction(e -> seasons.getSelectionModel().selectPrevious());

		// Let all buttons expand as needed
		selectAllBtn.setMaxWidth(Double.MAX_VALUE);
		clearAllBtn.setMaxWidth(Double.MAX_VALUE);
		selectFirstBtn.setMaxWidth(Double.MAX_VALUE);
		selectLastBtn.setMaxWidth(Double.MAX_VALUE);
		selectNextBtn.setMaxWidth(Double.MAX_VALUE);
		selectPreviousBtn.setMaxWidth(Double.MAX_VALUE);

		// Display controls in a GridPane
		GridPane root = new GridPane();
		root.setHgap(10);
		root.setVgap(5);
		
		// Add buttons to two VBox objects
		VBox singleSelectionBtns = new VBox(selectFirstBtn, selectNextBtn, selectPreviousBtn, selectLastBtn);
		VBox allSelectionBtns = new VBox(selectAllBtn, clearAllBtn);
		root.addColumn(0, seasonsLbl, seasons);
		root.add(singleSelectionBtns, 1, 1, 1, 1);
		root.add(allSelectionBtns, 2, 1, 1, 1);
		
		// Add controls to display the user selection
		Label selectionLbl = new Label("Your selection:");	    
		root.add(selectionLbl, 0, 2);
		root.add(selectedItemsLbl, 1, 2, 2, 1);
		
		Label lastSelectionLbl = new Label("Last selection:");      
		root.add(lastSelectionLbl, 0, 3);
		root.add(lastSelectedItemLbl, 1, 3, 2, 1);

		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);	    
		stage.setScene(scene);		
		stage.setTitle("Using ListView Selection Model");
		stage.show();
	}

	// A change listener to track the change in item selection
	public void selectionChanged(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		String lastItem = (newValue == null)?"[None]":"[" + newValue + "]";
		lastSelectedItemLbl.setText(lastItem);	
		
		ObservableList<String> selectedItems = seasons.getSelectionModel().getSelectedItems();
		String selectedValues = (selectedItems.isEmpty())?"[None]":selectedItems.toString();
		this.selectedItemsLbl.setText(selectedValues);
	}
}
