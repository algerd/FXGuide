
package node.bounds.size;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
A resizable node can be resized by its parent during a layout. A nonresizable node is not resized by its
parent during a layout. If you want to resize a nonresizable node, you need to modify its properties that affect its size.
For example, to resize a rectangle, you need to change its width and height properties. Regions, Controls, and
WebView are examples of resizable nodes. Group, Text, and Shapes are examples of nonresizable nodes.

The actual size of a resizable node is determined by two things:
    • The sizing policy of the container in which the node is placed
    • The sizing range specified by the node itself
Each container has a resizing policy for its children. A resizable node may specify a range for its size (width and height), 
which should be taken into account by an honoring container for laying out the node. A resizable node specifies three types 
of sizes that constitute the range of its size:
    • Preferred size
    • Minimum size
    • Maximum size

The program shows the behavior of resizable and nonresizable nodes during a layout.
It adds a button and a rectangle to an HBox. After you run the program, make the stage shorter in width.
The button becomes smaller up to a point when it displays an ellipsis (...). The rectangle remains the same
size all the time.
*/
public class ResizableNode extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button btn = new Button("A big button");
		Rectangle rect = new Rectangle(100, 50);
		rect.setFill(Color.WHITE);
		rect.setStrokeWidth(1);
		rect.setStroke(Color.BLACK);

		HBox root = new HBox();
		root.setSpacing(20);
		root.getChildren().addAll(btn, rect);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Resizable Nodes");
		stage.show();

		System.out.println("btn.isResizable(): " + btn.isResizable());
		System.out.println("rect.isResizable(): " + rect.isResizable());
	}
}
