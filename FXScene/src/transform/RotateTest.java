
package transform;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/*
In a rotation transformation, the axes are rotated around a pivot point in the coordinate space and the
coordinates of points are mapped to the new axes.

An instance of the Rotate class represents a rotation transformation. It contains five properties to
describe the rotation:
    • angle
    • axis
    • pivotX
    • pivotY
    • pivotZ
The angle property specifies the angle of rotation in degrees. The default is 0.0 degrees. A positive value
for the angle is measured clockwise.

The axis property specifies the axis of rotation at the pivot point. Its value can be one of the constants,
X_AXIS, Y_AXIS, and Z_AXIS, defined in the Rotate class. The default axis of rotation is Rotate.Z_AXIS.

The pivotX, pivotY, and pivotZ properties are the x, y, and z coordinates of the pivot point. The default
values for the properties are 0.0.

The Rotate class contains several constructors:
    • Rotate()
    • Rotate(double angle)
    • Rotate(double angle, double pivotX, double pivotY)
    • Rotate(double angle, double pivotX, double pivotY, double pivotZ)
    • Rotate(double angle, double pivotX, double pivotY, double pivotZ,Point3D axis)
    • Rotate(double angle, Point3D axis)

The no-args constructor creates an identity rotation, which does not have any effect on the transformed
node. The other constructors let you specify the details.

The program creates two rectangles and places them at the same location. The opacity
of the second rectangle is set to 0.5, so we can see through it. The coordinate system of the second rectangle
is rotated by 30 degrees in the clockwise direction using the origin as the pivot point.
*/
public class RotateTest extends Application {
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

		// Apply a rotation on rect2. The rotation angle is 30 degree clockwise (0, 0) is the pivot point
		Rotate rotate = new Rotate(30, 0, 0);
        
        /*
        The coordinates of the upper-left of the rectangles are set to (0, 0). A point at (100, 0) is used as
        the pivot point to rotate the second rectangle. The pivot point is located on the x-axis of the rectangle. The
        coordinate system of the second rectangle is pinned at (100, 0), and then, rotated by 30 degree in the
        anticlockwise direction. Notice that the second rectangle maintains its location (0, 20) in the rotated
        coordinate space.
        */
        // Apply a rotation on rect2. The rotation angle is 30 degree anticlockwise (100, 0) is the pivot point.
        //Rotate rotate = new Rotate(-30, 100, 0);
		
        rect2.getTransforms().addAll(rotate);

		Pane root = new Pane(rect1, rect2);
		root.setPrefSize(300, 80);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the Rotation Transformation");
		stage.show();
	}
}
