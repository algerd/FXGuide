
package transform;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
See RotateTest.java.

You can also apply a rotation to a node using the rotate and rotationAxis properties of the Node class.
The rotate property specifies the angle of rotation in degrees. The rotationAxis property specifies the axis
of rotation. The center of the untransformed layout bounds of the node is used as the pivot point.

Tip: The default pivot point used in a transforms sequence is the origin of the local coordinate system of
the node whereas the rotate property of the Node class uses the center of the untransformed layout bounds of
the node as the pivot point.

The program creates two rectangles similar to RotateTest.java. It uses the rotate
property of the Node class to rotate the rectangle by 30 degrees. 
The former uses the origin of the local coordinate system as the pivot point 
and the latter uses the center of the rectangle as the pivot point.
*/
public class RotatePropertyTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect1 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect1.setStroke(Color.BLACK);

		Rectangle rect2 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect2.setStroke(Color.BLACK);
		rect2.setOpacity(0.5);

		// Use the rotate proeprty of the node class
		rect2.setRotate(30);

		Pane root = new Pane(rect1, rect2);
		root.setPrefSize(300, 80);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the Rotation Transformation");
		stage.show();
	}
}
