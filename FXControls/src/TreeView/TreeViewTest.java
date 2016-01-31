
package TreeView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
See TreeViewUtil.java!

The program shows a TreeView control in a window. When you run the program, all
nodes are collapsed, which is the default behavior for a TreeView. You will need to click the disclosure node
(the triangle) for the root node to expand it and view its children. Repeat this to expand other nodes. Clicking
the disclosure node for an expanded node hides its children.

Tip: By default, a node is in the collapsed state. Call the setExpanded(true) method of a TreeItem to
expand a node.
*/
public class TreeViewTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TreeView<String> treeView = TreeViewUtil.getTreeView();
      	
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
		stage.setTitle("Hiding Root Item");
		stage.show();	
	}
}
