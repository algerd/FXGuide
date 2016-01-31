
package animation.transition;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
An instance of the PathTransition class represents a path transition for a node by gradually changing its
translateX and translateY properties to move it along a path over the specified duration. The path is
defined by the outline of a Shape. The class defines the following properties to specify the animation:
    • duration
    • node
    • path
    • orientation

The duration property specifies the duration for one cycle of the animation.
The node property specifies the node whose rotate property is changed.
The path property defines the path along which the node is moved. It is a Shape. You can use an Arc, a
Circle, a Rectangle, an Ellipse, a Path, a SVGPath, and so on as the path.

The moving node may maintain the same upright position or it may be rotated to keep it perpendicular
to the tangent of the path at any point along the path. The orientation property specifies the upright position
of the node along the path. Its value is one of the constants (NONE and ORTHOGONAL_TO_TANGENT) of the
PathTransition.OrientationType enum. The default is NONE, which maintains the same upright position.
The ORTHOGONAL_TO_TANGENT value keeps the node perpendicular to the tangent of the path at any point.

You can specify the duration, path, and node for the path transition using the properties of the
PathTransition class or in the constructors. The class contains the following constructors:
    • PathTransition()
    • PathTransition(Duration duration, Shape path)
    • PathTransition(Duration duration, Shape path, Node node)
*/
public class PathTransitionTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create the node
		Rectangle rect = new Rectangle(20, 10, Color.RED);

		// Create the path
		Circle path = new Circle(100, 100, 100);
		path.setFill(null);
		path.setStroke(Color.BLACK);

		Group root = new Group(rect, path);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Path Transition");
		stage.show();

		// Set up a path transition for the rectangle
		PathTransition pt = new PathTransition(Duration.seconds(2), path, rect);
		pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pt.setCycleCount(PathTransition.INDEFINITE);
		pt.setAutoReverse(true);
		pt.play();
	}
}
