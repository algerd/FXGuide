
package TreeView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
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

A TextFieldTreeCell is rendered as a Label in nonediting mode and as a TextField in editing mode. Its
forTreeView() static method returns a cell factory. The method is overloaded. Use a string converter if the
item type is not String. The following snippet of code sets a cell factory for a TreeView that will use instances
of TextFieldTreeCell to render TreeItems. In editing mode, the TextField will display the item value.
    TreeView<String> treeView = new TreeView<>();
    treeView.setCellFactory(TextFieldTreeCell.forTreeView());

Clicking a selected cell or double-clicking an unselected cell puts the cell into editing mode, which
displays the cell data in a TextField. Once the cell is in editing mode, you need to click in the TextField
(one more click!) to put the caret in the TextField so you can make changes.

Tip: Double-clicking a cell representing a branch node will not put the cell in editing mode. Rather, the cell
is expanded or collapsed. The trick is to use two single clicks instead of a double-click on a branch node.
Both a double-click and two single clicks put a cell of a leaf node in editing mode.

If you are in the middle of editing a cell data, press the Esc key to cancel editing, which will return the
cell to nonediting mode and reverts to the old data in the cell. Pressing the Enter key commits the data to the
TreeItem for the cell.

If you are editing a cell using a TextFieldTreeCell, moving the focus to another cell, for example,
by clicking another cell, cancels the editing and puts the old value back in the cell. This is not what a user
expects. At present, there is no easy solution to this problem. You will have to create a subclass of TreeCell
and add a focus change listener so you can commit the data when the TextField loses focus.

The program shows how to use TextFieldTreeCell to edit cell data in a TreeView. 
Run the application and click a cell two times to put the cell in editing mode. A TextField will display the cell
data. Change the data and press the Enter key to commit the changes. The program adds edit-related event
handlers to the TreeView that prints a message on the standard output when the events occur. 
*/
public class TreeViewTextFieldData extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TreeView<String> treeView = TreeViewUtil.getTreeView();

		// Make the TreeView editable
		treeView.setEditable(true);
	
		// Set a cell factory to use TextFieldTreeCell
		treeView.setCellFactory(TextFieldTreeCell.forTreeView());
	
		// Set editing related event handlers
		treeView.setOnEditStart(this::editStart);
		treeView.setOnEditCommit(this::editCommit);
		treeView.setOnEditCancel(this::editCancel);
	
		HBox root = new HBox(treeView);
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
		stage.setTitle("A Editing Cells in a TreeView");
		stage.show();
	}

	public void editStart(TreeView.EditEvent<String> e) {
		System.out.println("Started editng: " + e.getTreeItem() );
	}

	public void editCommit(TreeView.EditEvent<String> e) {
		System.out.println(e.getTreeItem() +  " changed." + 
		                   " old = " + e.getOldValue() + 
		                   ", new = " + e.getNewValue());
	}

	public void editCancel(TreeView.EditEvent<String> e) {
		System.out.println("Cancelled editng: " + e.getTreeItem() );
	}
}
