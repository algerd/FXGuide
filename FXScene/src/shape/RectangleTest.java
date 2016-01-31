
package shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
An instance of the Rectangle class represents a rectangle node. The class uses six properties to define
the rectangle.
    • x
    • y
    • width
    • height
    • arcWidth
    • arcHeight

The x and y properties are the x and y coordinates of the upper-left corner of the rectangle in the local
coordinate system of the node. The width and height properties are the width and height of the rectangle,
respectively. Specify the same width and height to draw a square.

By default, the corners of a rectangle are sharp. A rectangle can have rounded corners by specifying
the arcWidth and arcHeight properties. You can think of one of the quadrants of an ellipse positioned
at the four corners to make them round. The arcWidth and arcHeight properties are the horizontal and
vertical diameters of the ellipse. By default, their values are zero, which makes a rectangle have sharp
corners.

The Rectangle class contains several constructors. They take various properties as arguments. The
default values for x, y, width, height, arcWidth, and arcHeight properties are zero. The constructors are
    • Rectangle()
    • Rectangle(double width, double height)
    • Rectangle(double x, double y, double width, double height)
    • Rectangle(double width, double height, Paint fill)

You will not see effects of specifying the values for the x and y properties for a Rectangle when you add
it to most of the layout panes as they place their children at (0, 0). A Pane uses these properties. The program
in Listing adds two rectangles to a Pane. The first rectangle uses the default values of zero for the x and y
properties. The second rectangle specifies 120 for the x property and 20 for the y property. 
Notice that the upper-left corner of the second rectangle (on the right) is at (120, 20).
*/
public class RectangleTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// x=0, y=0, width=100, height=50, fill=LIGHTGRAY, stroke=null
		Rectangle rect1 = new Rectangle(100, 50, Color.LIGHTGRAY);

		// x=120, y=20, width=100, height=50, fill=WHITE, stroke=BLACK 
		Rectangle rect2 = new Rectangle(120, 20, 100, 50);
		rect2.setFill(Color.WHITE);
		rect2.setStroke(Color.BLACK);
		rect2.setArcWidth(10);
		rect2.setArcHeight(10);
	
		Pane root = new Pane();
		root.getChildren().addAll(rect1, rect2);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Rectangles");
		stage.show();
	}
}
