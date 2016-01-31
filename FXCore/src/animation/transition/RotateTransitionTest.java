
package animation.transition;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
An instance of the RotateTransition class represents a rotation transition for a node by gradually changing
its rotate property over the specified duration. The rotation is performed around the center of the node
along the specified axis. The class defines the following properties to specify the animation:
    • duration
    • node
    • axis
    • fromAngle
    • toAngle
    • byAngle
The duration property specifies the duration for one cycle of the animation.
The node property specifies the node whose rotate property is changed.
The axis property specifies the axis of rotation. If it is unspecified, the value for the rotationAxis
property, which defaults to Rotate.Z_AXIS, for the node is used. The possible values are Rotate.X_AXIS,
Rotate.Y_AXIS, and Rotate.Z_AXIS.

The initial angle for the rotation is specified by fromAngle property. If it is unspecified, the value for the
rotate property of the node is used as the initial angle.
The toAngle specifies the end rotation angle.

The byAngle lets you specify the end rotation angle using the following formula:
    rotation_end_value = rotation_initial_value + byAngle

If both toAngle and byAngle values are specified, the former is used. All angles are specified in degrees.
Zero degrees correspond to the 3 o’clock position. Positive values for angles are measured clockwise.
*/
public class RotateTransitionTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(50, 50, Color.RED);
		HBox.setMargin(rect, new Insets(20));
		HBox root = new HBox(rect);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Rotate Transition");
		stage.show();

		// Set up a rotate transition the rectangle
		RotateTransition rt = new RotateTransition(Duration.seconds(2), rect);
		rt.setFromAngle(0.0);
		rt.setToAngle(360.0);
		rt.setCycleCount(RotateTransition.INDEFINITE);
		rt.setAutoReverse(true);
		rt.play();
	}
}
