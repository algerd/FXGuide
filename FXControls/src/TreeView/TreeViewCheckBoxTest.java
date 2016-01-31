
package TreeView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
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

TreeView has a read-only editingItem property that contains the reference of the TreeItem being
edited. It is null when no TreeItem is being edited.

A CheckBoxTreeCell renders a check box in the cell. The following snippet of code sets the cell factory for a
TreeView to use CheckBoxTreeCell:
    TreeView<String> treeView = new TreeView<>();;
    // Set a cell factory to use TextFieldTreeCell
    treeView.setCellFactory(CheckBoxTreeCell.<String>forTreeView());

The above code will draw a check box in each cell of the TreeView, which can be selected and
unselected. However, there is no way to know whether the check box for a TreeItem is selected or unselected
because the TreeItem class does not provide access to the CheckBox state. CheckBoxTreeItem is a specialized
implementation of TreeItem that should be used when you want to use CheckBoxTreeCell. It provides
access to the selected state of the check box. It contains three boolean properties:
    • independent
    • indeterminate
    • selected

The independent property represents the independent state of the CheckBoxTreeItem. By default,
it is false. When a CheckBoxTreeItem is dependent, selecting or unselecting it affects the selected state
of its children and its parent. For example, selecting or unselecting a dependent parent node selects or
unselects all its children. If some, but not all, children are selected, a dependent parent node will be in an
indeterminate state. If all children are selected, a dependent parent node will be selected. If all children
are unselected, a dependent parent node will be unselected. The selected state of an independent
CheckBoxTreeItem does not affect its parent and children.

Tip: T he selection of an independent CheckBoxTreeItem does not affect is parent and children. However,
the reverse is not true. That is, if a dependent parent is selected or unselected, all its children, dependent as
well as independent, are selected or unselected.

The indeterminate property specifies the indeterminate state of the check box for the item. The
selected property specifies the selected property of the check box for the item. Use the isIndeterminate()
and isSelected() methods to determine the state of the check box of a CheckBoxTreeItem.

The program shows how to use a cell factory to render a check box in each cell with
a CheckBoxTreeItem. The initial part of the program is very similar to the first example you had for the
TreeView. The only difference is that, this time, you have used CheckBoxTreeItem instead of a TreeItem. You
have made the Claims item independent. That is, selecting and unselecting the Claims item does not affect
the state of its parent and children. Select and unselect different items to see this effect.
*/
public class TreeViewCheckBoxTest extends Application {	
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
		
		// Make the claimsDept item independent
		claimsDept.setIndependent(true);
		
		// Craete a TreeView with depts as its root item
		TreeView<String> treeView = new TreeView<>(depts);
		
		// Set the cell factory to draw a CheckBox in cells
		treeView.setCellFactory(CheckBoxTreeCell.<String>forTreeView());

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
