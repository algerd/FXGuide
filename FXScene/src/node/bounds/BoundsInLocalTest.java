
package node.bounds;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
The boundsInLocal property is computed in the untransformed coordinate space of the node. It includes the
geometric properties of the node, effects, and clip. Transformations applied to a node are not included.

When do you use the boundsInLocal of a node? You would use boundsInLocal when you need to
include the effects and the clip of a node. Suppose you have a Text node with a reflection and you want
to center it vertically. If you use the layoutBounds of the Text node, it will only center the text portion of
the node and would not include the reflection. If you use the boundsInLocal, it will center the text with its
reflection. Another example would be checking for collisions of balls that have effects. If a collision between
two balls occurs when one ball moves inside the bounds of another ball that include their effects, use the
boundsInLocal for the balls. If a collision occurs only when they intersect their geometric boundaries, use
the layoutBounds.

Listing prints the layoutBounds and boundsInLocal of a button. The boundsInLocal property
includes the drop shadow effect around the button. Notice that the coordinates of the upper left corner of
the bounding box defined by the layoutBounds are (0.0, 0.0) and they are (-9.0, -9.0) for the boundsInLocal.
The output may be a bit different on different platforms as the size of nodes is computed automatically
based on the platform running the program.
*/
public class BoundsInLocalTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button b1 = new Button("Close");
		b1.setEffect(new DropShadow());
	
		VBox root = new VBox();
		root.getChildren().addAll(b1);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Testing LayoutBounds");
		stage.show();
		
		System.out.println("b1(layoutBounds)=" + b1.getLayoutBounds());
		System.out.println("b1(boundsInLocal)=" + b1.getBoundsInLocal());		
	}
}
