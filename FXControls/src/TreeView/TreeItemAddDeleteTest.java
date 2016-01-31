
package TreeView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/*
TreeView uses a selection model to select one or multiple TreeItems. The selectionModel property
represents the selection model. The default selection model is an instance of the abstract class
MultipleSelectionModel. The following snippet of code enables multiple selection. Press the Ctrl or Shift
key while clicking a node to select multiple nodes.
    TreeView<String> treeView = new TreeView<>();;
    // Enable mutiple selection for the TreeView
    treeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

------------------------------------------------
Adding and removing TreeItems is as easy as adding or removing them in the children list of their parents.
Notice that the root node does not have a parent. To delete the root node, you need to set the root property of
the TreeView to null.

The program shows how to add and remove nodes in a TreeView. A TreeView with a
root node is displayed in the left side of the window. The right side displays a TextField and an Add button.
Enter a text and click the Add button; a new node will be added under the selected node. Click the Remove
Selected Item button to remove the selected node from the TreeView. At the bottom of the window,
a detailed message log is displayed in a TextArea. The program also shows how to handle TreeItem events.
*/
public class TreeItemAddDeleteTest  extends Application {	
	private final TreeView<String> treeView = new TreeView<>();   
	private final TextArea msgLogFld = new TextArea();
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Select the root node
		treeView.getSelectionModel().selectFirst();
		
		// Create the root node and adds event handler to it
		TreeItem<String> depts = new TreeItem<>("Departments");
		depts.addEventHandler(TreeItem.<String>branchExpandedEvent(), this::branchExpended);
		depts.addEventHandler(TreeItem.<String>branchCollapsedEvent(), this::branchCollapsed);
		depts.addEventHandler(TreeItem.<String>childrenModificationEvent(), this::childrenModification);
	
		// Set the root node for the TreeViww
		treeView.setRoot(depts);

		VBox rightPane = getRightPane();
		
		HBox root = new HBox(treeView, rightPane);
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
		stage.setTitle("Creating a TreeView");
		stage.show();	
	}

	public VBox getRightPane() {
		TextField itemFld = new TextField();
	
		Button addItemBtn = new Button("Add");
		addItemBtn.setOnAction(e -> this.addItem(itemFld.getText()));
	
		Button removeItemBtn = new Button("Remove Selected Item");
		removeItemBtn.setOnAction(e -> this.removeItem());
		
		msgLogFld.setPrefRowCount(15);
		msgLogFld.setPrefColumnCount(25);
		VBox box = new VBox(
            new Label("Select an item to add to or remove."),
            new HBox(new Label("Item:"), itemFld, addItemBtn), 
            removeItemBtn, 
            new Label("Message Log:"),
            msgLogFld
        );
		box.setSpacing(10);
		return box;
	}
	
	public void addItem(String value) {
		if (value == null || value.trim().equals("")) {
			this.logMsg("Item cannot be empty.");
			return;
		}
		
		TreeItem<String> parent = treeView.getSelectionModel().getSelectedItem();
		if (parent == null) {
			this.logMsg("Select a node to add this item to.");
			return;
		}
	
		// Check for duplicate
		for(TreeItem<String> child : parent.getChildren()) {
			if (child.getValue().equals(value)) {
				this.logMsg(value + " already exists under " + parent.getValue());
				return;
			}
		}
		
		TreeItem<String> newItem = new TreeItem<String>(value);
		parent.getChildren().add(newItem);
		if (!parent.isExpanded()) {
			parent.setExpanded(true);
		}
	}
	
	public void removeItem() {
		TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
		if (item == null) {
			this.logMsg("Select a node to remove.");
			return;
		}
		
		TreeItem<String> parent = item.getParent();
		if (parent == null ) {
			this.logMsg("Cannot remove the root node.");
		} else {
			parent.getChildren().remove(item);
		}

	}
	
	public void branchExpended(TreeItem.TreeModificationEvent<String> e) {
		String nodeValue = e.getSource().getValue();
		this.logMsg("Event: " + nodeValue + " expanded.");
	}
	
	public void branchCollapsed(TreeItem.TreeModificationEvent<String> e) {
		String nodeValue = e.getSource().getValue();
		this.logMsg("Event: " + nodeValue + " collapsed.");
	}
	
	public void childrenModification(TreeItem.TreeModificationEvent<String> e) {
		if (e.wasAdded()) {
			for(TreeItem<String> item : e.getAddedChildren()) {
				this.logMsg("Event: " + item.getValue() + " has been added.");
			}
		}
		
		if (e.wasRemoved()) {
			for(TreeItem<String> item : e.getRemovedChildren()) {
				this.logMsg("Event: " + item.getValue() + " has been removed.");
			}
		}
	}

	public void logMsg(String msg) {
		this.msgLogFld.appendText(msg + "\n");
	}
}
