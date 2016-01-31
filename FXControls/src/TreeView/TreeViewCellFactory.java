
package TreeView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
TreeView uses a TreeCell to render a TreeItem. A TreeCell is an IndexedCell. You can visualize items in
a TreeView from top to bottom arranged in rows. Each row has exactly one item. Each item is given a row
index. The first item, which is the root item, has the index of zero. The row indices are given only to the
visible items. TreeView contains a read-only expandedItemCount property that is the number of visible
items. Use the getExpandedItemCount() method to get the number of visible items. If a node above an item
is expanded or collapsed, the index of the item changes to reflect new visible items. The index of a TreeCell
in a TreeView and the row index of an item are the same. Use the getIndex() method of the TreeCell or the
getRow(TreeItem<T> item) method of the TreeView to get the row index of an item.

A TreeCell is a Labeled control. By default, it uses the following rules to render its TreeItem: If the
value in the TreeItem is an instance of the Node class, the value is displayed using the graphic property of
the cell. Otherwise, the toString() method of the value is called and the returned string is displayed using
the text property of the cell.

You can take full control of how a TreeCell renders its TreeItem by providing a cell factory for the
TreeView. The cellFactory property is a Callback instance, which takes the TreeView as an argument and
returns a TreeCell.
*/
public class TreeViewCellFactory extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TreeView<String> treeView = TreeViewUtil.getTreeView();

		// Set a cell factory to prepend the row index to the TreeItem value
		treeView.setCellFactory( (TreeView<String> tv) -> {
			TreeCell<String> cell = new TreeCell<String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						this.setText(null);
						this.setGraphic(null);
					}
					else {
						String value = this.getTreeItem().getValue();
						this.setText(this.getIndex() + ". " + value);
					}
				}};
			return cell;
		});
				
		HBox root = new HBox(treeView);
		root.setSpacing(20);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using a Cell Factory in a TreeView");
		stage.show();	
	}
}
