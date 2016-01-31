
package transform;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;

/*
A shear transformation rotates axes of the local coordinate system of the node around a pivot point, so the
axes are no longer perpendicular. A rectangular node becomes a parallelogram after the transformation.
An instance of the Shear class represents a shear transformation. It contains four properties to describe
the transformation.
    • x
    • y
    • pivotX
    • pivotY

The x property specifies a multiplier by which the coordinates of points are shifted along the positive
x-axis by a factor of the y coordinate of the point. The default is 0.0.

The y property specifies a multiplier by which the coordinates of points are shifted along the positive
y-axis by a factor of the x coordinate of the point. The default is 0.0.

The pivotX, and pivotY properties are the x and y coordinates of the pivot point about which the shear
occurs. The default values for them are 0.0. The pivot point is not shifted by the shear. By default, the pivot
point is the origin of the untransformed coordinate system.

Suppose you have a point (x1, y1) inside a node, and by the shear transformation, the point is shifted to
(x2, y2). You can use the following formula to compute (x2, y2):
    x2 = pivotX + (x1 - pivotX) + x * (y1 - pivotY)
    y2 = pivotY + (y1 - pivotY) + y * (x1 - pivotX)
All coordinates (x1, y1, x2, and y2) in the previous formula are in the untransformed local coordinate
system of the node. Notice that if (x1, y1) is the pivot point, the foregoing formula computes the shifted point
(x2, y2), which is the same as (x1, y1). That is, the pivot point is not shifted.

The Shear class contains several constructors.
    • Shear()
    • Shear(double x, double y)
    • Shear(double x, double y, double pivotX, double pivotY)
The no-args constructor creates an identity shear transformation, which does not have any effect on the
transformed node.

Tip: You can apply a shear transformation to a node using only a Shear object in the transforms sequence.
Unlike for other types of transformations, the Node class does not contain a property allowing you to apply shear
transformation.

The program applies a Shear to a rectangle. The original rectangle is also shown. 
A multiplier of 0.5 is used along both axes. Note that the pivot point is (0, 0), which is the default.
*/
public class ShearTest extends Application {
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

        // Apply a shear on rect2. The x and y multipliers are 0.5 and (0, 0) is the pivot point.
		Shear shear = new Shear(0.5, 0.5);
        /*
        The upper-left corners of the rectangles are placed at (100, 0), so we can see the sheared rectangle fully. 
        We have used (100, 50), which is the lower-left corner of the rectangle, as the pivot point. 
        Notice that the transformation did not shift the pivot point.
        */
        // Apply a shear on rect2. The x and y multipliers are 0.5 and (100, 50) is the pivot point.
        //Shear shear = new Shear(0.5, 0.5, 100, 50);
       	
		rect2.getTransforms().addAll(shear);

		Group root = new Group(rect1, rect2);		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the Shear Transformation");
		stage.show();
	}
}
