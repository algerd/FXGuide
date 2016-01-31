
package layout.pane.StackPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
A StackPane lays out its children in a stack of nodes. It is simple to use. However, it provides a powerful
means to overlay nodes. Children are drawn in the order they are added. That is, the first child node is drawn
first; the second child node is drawn next, etc. For example, overlaying text on a shape is as easy as using a
StackPane: add the shape as the first child node and the text as the second child node. The shape will be
drawn first followed by the text, which makes it seem as if the text is a part of the shape.

Tip: You can create very appealing GUI using StackPanes by overlaying different types of nodes. You can
overlay text on an image to get an effect as if the text were part of the image. And you can overlay different
types of shapes to create a complex shape. Remember that the node that overlays other nodes is added last to
the StackPane.

The preferred width of a StackPane is the width of its widest children. Its preferred height is the height of
its tallest children. StackPane does clip its content. Therefore, its children may be drawn outside its bounds.

A StackPane resizes its resizable children to fill its content area, provided their maximum size allows
them to expand beyond their preferred size. By default, a StackPane aligns all its children to the center of its
content area. You can change the alignment for a child node individually or for all children to use the same
alignment.

You must add the children to a StackPane in a specific order to create the desired overlay. Children are
drawn in the order they exist in the list.
*/
public class StackPaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a Rectangle and a Text
		Rectangle rect = new Rectangle(200, 50);
		rect.setStyle("-fx-fill: lavender;" +
		              "-fx-stroke-type: inside;" +
		              "-fx-stroke-dash-array: 5 5;" +
		              "-fx-stroke-width: 1;" +
		              "-fx-stroke: black;" +
		              "-fx-stroke-radius: 5;");

		Text text = new Text("A Rectangle");

		// Create a StackPane with a Rectangle and a Text
		StackPane root = new StackPane(rect, text);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using StackPane");
		stage.show();
	}
}
