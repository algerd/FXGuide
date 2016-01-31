
package TreeView;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
For FileSystemBrowser.java.

Listing has the code for this new class PathTreeItem, which inherits from TreeItem<Path>.
The class needs to override the getChildren() and isLeaf() methods of the TreeItem class. 
The three instance variables are used to cache the results of
the isLeaf() method call and flags indicating that the methods were called once. The constructor calls
the constructor of the TreeItem class and sets an icon for the node depending on whether it is a file or
a directory. The populateChildren() method contains the main logic for populating a node. The root
directories for the default file system are added as children for the root node. A nonroot node is populated
with its subdirectories and subfiles.
*/
public class PathTreeItem extends TreeItem<Path> {
    
	private boolean childrenLoaded = false;
	private boolean leafPropertyComputed = false;
	private boolean leafNode = false;

	public PathTreeItem(Path path) {
		super(path);	
		ImageView icon = null;
		if (Files.isDirectory(path)) {
			icon = getFolderIcon("folder.jpg");
		} else {
			icon = getFolderIcon("file.jpg");
		}
		this.setGraphic(icon);
	}

	@Override 
	public ObservableList<TreeItem<Path>> getChildren() {
		if (!childrenLoaded) {
			childrenLoaded = true;
			populateChildren(this);
		}
		return super.getChildren();
	}

	@Override 
	public boolean isLeaf() {
		if (!leafPropertyComputed) {
			leafPropertyComputed = true;
			Path path = this.getValue();
			leafNode = !Files.isDirectory(path);
		}
		return leafNode;
	}
	
	private void populateChildren(TreeItem<Path> item) {
		item.getChildren().clear();
		if (item.getParent() == null) {
			// Add root directories
			for (Path p : FileSystems.getDefault().getRootDirectories()) {
				item.getChildren().add(new PathTreeItem(p));
			}
		} else {
			Path path = item.getValue(); 
			// Populate sub-directories and files
			if (Files.isDirectory(path)) {
				try {
                    Files.list(path).forEach(p -> item.getChildren().add(new PathTreeItem(p)));
                }
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private ImageView getFolderIcon(String fileName) {
		ImageView imgView = null;
		try {
			String imagePath = "TreeView/" + fileName;			
			Image img = new Image(imagePath);
			imgView = new ImageView(img);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return imgView;
	}
}
