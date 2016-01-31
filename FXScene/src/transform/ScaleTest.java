
package transform;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

/*
A scale transformation scales the unit of measurement along axes of a coordinate system by a scale factor.
This causes the dimensions of a node to change (stretch or shrink) by the specified scale factors along axes.
The dimension along an axis is multiplied by the scale factor along that axis. The transformation is applied at
a pivot point whose coordinates remain the same after the transformation.

An instance of the Scale class represents a scale transformation. It contains the following six properties
to describe the transformation:
    • x
    • y
    • z
    • pivotX
    • pivotY
    • pivotZ
The x, y, and z properties specify the scale factors long the x-axis, y-axis, and z-axis. They are 1.0 by default.
The pivotX, pivotY, and pivotZ properties are the x, y, and z coordinates of the pivot point. The default
values for the properties are 0.0.

The Scale class contains several constructors.
    • Scale()
    • Scale(double x, double y)
    • Scale(double x, double y, double z)
    • Scale(double x, double y, double pivotX, double pivotY)
    • Scale(double x, double y, double z, double pivotX, double pivotY, double pivotZ)

The no-args constructor creates an identity scale transformation, which does not have any effect on the
transformed node. The other constructors let you specify the scale factors and the pivot point.

You can use an object of the Scale class or the scaleX, scaleY, and scaleX properties of the Node
class to apply a scale transformation. By default, the pivot point used by the Scale class is at (0, 0, 0). The
properties of the Node class use the center of the node as the pivot point.

The program creates two rectangles. Both are placed at the same location. One of them
is scaled and the other not. The opacity of the not scaled rectangle is set to 0.5, so we can see through it.
The scaled rectangle is smaller. The coordinate system of the second
rectangle is scaled by 0.5 along the x-axis and 0.50 along the y-axis. The scaleX and scaleY properties are
used to apply the transformation, which uses the center of the rectangles as the pivot point making the
rectangles shrunk, but keeping it at the same location.
*/
public class ScaleTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect1 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect1.setStroke(Color.BLACK);
		rect1.setOpacity(0.5);

		Rectangle rect2 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect2.setStroke(Color.BLACK);

		// Apply a scale on rect2. Center of the Rectangle is the pivot point.
		//rect2.setScaleX(0.5);
		//rect2.setScaleY(0.5);
        
        //  Apply a scale on rect2. The origin of the local coordinate system
        // of rect2 is the pivot point
        Scale scale = new Scale(0.5, 0.5);
        rect2.getTransforms().addAll(scale);

		Pane root = new Pane(rect1, rect2);
		root.setPrefSize(150, 60);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the Scale Transformation");
		stage.show();
	}
}
