
package TreeView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.ChoiceBoxTreeCell;
import javafx.scene.control.cell.ComboBoxTreeCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
A cell in a TreeView can be editable. An editable cell may switch between editing and nonediting mode.
In editing mode, cell data can be modified by the user. For a cell to enter editing mode, the TreeView must be
editable. TreeView has an editable property, which can be set to true using the setEditable(true) method,
as shown in the following code. By default, TreeView is not editable.
    TreeView<Stirng> treeView = ...
    treeView.setEditable(true);

TreeView supports three types of events:
    • onEditStart
    • onEditCommit
    • onEditCancel
The onEditStart event is fired when a cell enters editing mode. The onEditCommit event is fired
when the user successfully commits the editing, for example, by pressing the Enter key in a TextField.

The onEditCancel event is fired when the user cancels the editing, for example, by pressing the Esc key
in a TextField. The events are represented by an object of the TreeView.EditEvent class. The event
object encapsulates the old and new values in the cell, the TreeItem being edited, and the reference of the
TreeView. Use one of the methods of the EditEvent class to get these values.

Creating a TreeView does not let you edit its cells. Cell-editing capability is provided through specialized
implementations of the TreeCell class. The JavaFX library provides some of these implementations. Set the
cell factory for a TreeView, which is a Callback object, to use one of the following implementations of the
TreeCell to make cells in a TreeView editable:
    • CheckBoxTreeCell
    • ChoiceBoxTreeCell
    • ComboBoxTreeCell
    • TextFieldTreeCell
-----------------------------------------------------------

A ChoiceBoxTreeCell is rendered as a Label in nonediting mode and as a choice box in editing mode. Its
forTreeView() static method returns a cell factory. The method is overloaded. You need to pass the list of
items to be shown in the choice box. If the toString() method of the item does not return a user-friendly
string for the user, use a string converter. The following snippet of code sets a cell factory for a TreeView that
will use instances of ChoiceBoxTreeCell to render TreeItems. In editing mode, the choice box will display
three items: Item-1, Item-2, and Item-3.

Clicking a selected cell puts the cell into editing mode. Double-clicking an unselected cell puts the cell
into editing mode. Changing the focus to another cell or selecting an item from the list puts the editing cell
into nonediting mode and the current value is displayed in a Label.

Editing data using a СomboBoxTreeCell works similar to the method used for a ChoiceBoxTreeCell.
*/
public class TreeViewChoiceComboBoxesTest extends Application {	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		CheckBoxTreeItem<String> depts = new CheckBoxTreeItem<>("Departments");
		
		// Add items to depts
		CheckBoxTreeItem<String> isDept = new CheckBoxTreeItem<>("IS");
		CheckBoxTreeItem<String> claimsDept = new CheckBoxTreeItem<>("Claims");
		CheckBoxTreeItem<String> underwritingDept = new CheckBoxTreeItem<>("Underwriting");
		depts.getChildren().addAll(isDept, claimsDept, underwritingDept);
		
		// Add employees for each dept
		isDept.getChildren().addAll(
            new CheckBoxTreeItem<String>("Doug Dyer"),
            new CheckBoxTreeItem<String>("Jim Beeson"),
            new CheckBoxTreeItem<String>("Simon Ng")
        );
		
		claimsDept.getChildren().addAll(
            new CheckBoxTreeItem<String>("Lael Boyd"),
            new CheckBoxTreeItem<String>("Janet Biddle")
        );
		
		underwritingDept.getChildren().addAll(
            new CheckBoxTreeItem<String>("Ken McEwen"),
            new CheckBoxTreeItem<String>("Ken Mann"),
            new CheckBoxTreeItem<String>("Lola Ng")      
        );	
				
		// Craete a TreeView with depts as its root item
		TreeView<String> treeView = new TreeView<>(depts);
        treeView.setEditable(true);
		
		// Set the cell factory to draw a ChoiceBox in cells
        treeView.setCellFactory(ChoiceBoxTreeCell.<String>forTreeView("Item-1", "Item-2", "Item-3"));
        // Editing data using a СomboBoxTreeCell works similar to the method used for a ChoiceBoxTreeCell.
        //treeView.setCellFactory(ComboBoxTreeCell.<String>forTreeView("Item-1", "Item-2", "Item-3"));

        /*
        When an item is selected in the choice box, the item is set to the TreeItem of the cell. You can set an
        onEditCommit event handler that is fired when the user selects an item. The following snippet of code adds
        such a handler for the TreeView that prints a message on the standard output:
        */
        // Add an onEditCommit handler
        treeView.setOnEditCommit(e -> {
            System.out.println(
                e.getTreeItem() + " changed." +
                " old = " + e.getOldValue() +
                ", new = " + e.getNewValue()
            );
        });
        
		HBox root = new HBox(treeView);
		root.setSpacing(20);
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
		stage.setTitle("Using CheckBoxTreeItem");
		stage.show();	
	}
}
