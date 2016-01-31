
package layout.pane.StackPane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
The StackPane class has an alignment property of the ObjectProperty<Pos> type. The property defines
the default alignment of all children within the content area of the StackPane. By default, its value is set to
Pos.CENTER, which means that all children, by default, are aligned in the center of the content area of the
StackPane. This is what we have seen in our previous examples. If you do not want the default alignment for
all children, you can change it to any other alignment value. Note that changing the value of the alignment
property sets the default alignment for all children.

Individual children may override the default alignment by setting its alignment constraint. We will
discuss how to set the alignment constraint on a child node in the next section.

StackPane has several other uses besides overlaying nodes. Whenever you have a requirement to align
a node or a collection of nodes in a specific position, try using a StackPane. For example, if you want to
display text in the center of your screen, use a StackPane with a Text node as the root node of the scene. The
StackPane takes care of keeping the text in the center as the window is resized. Without a StackPane, you will
need to use binding to keep the text positioned in the center of the window.

The program uses five StackPanes in an HBox. Each StackPane has a Rectangle overlaid with a Text. 
The alignment for the StackPane, and hence for all its children, is used as the text for the Text
node. Notice that the Rectangles in StackPanes are bigger than the Texts.
Therefore, the Rectangles occupy the entire content area of the StackPanes and they seem not to be affected
by the alignment property.

*/
public class StackPaneAlignment extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		StackPane topLeft = createStackPane(Pos.TOP_LEFT);
		StackPane topRight = createStackPane(Pos.TOP_RIGHT);
		StackPane bottomLeft = createStackPane(Pos.BOTTOM_LEFT);
		StackPane bottomRight = createStackPane(Pos.BOTTOM_RIGHT);
		StackPane center = createStackPane(Pos.CENTER);

		double spacing = 10.0;
		HBox root = new HBox(
            spacing, 
            topLeft, 
            topRight, 
            bottomLeft, 
            bottomRight, 
            center);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using StackPane");
		stage.show();
	}

	public StackPane createStackPane(Pos alignment) {
		Rectangle rect = new Rectangle(80, 50);
		rect.setFill(Color.LAVENDER);

		Text text = new Text(alignment.toString());
		text.setStyle("-fx-font-size: 7pt;");

		StackPane spane = new StackPane(rect, text);
		spane.setAlignment(alignment);
	 	spane.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;");
		return spane;
	}
}
