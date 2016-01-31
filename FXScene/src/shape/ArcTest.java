
package shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

/*
An instance of the Arc class represents a sector of an ellipse. The class uses seven properties to define
the ellipse.
    • centerX
    • centerY
    • radiusX
    • radiusY
    • startAngle
    • length
    • type

The first four properties define an ellipse. Please refer to the section “Drawing Ellipses” for how to
define an ellipse. The last three properties define a sector of the ellipse that is the Arc node. The startAngle
property specifies the start angle of the section in degrees measured counterclockwise from the positive
x-axis. It defines the beginning of the arc. The length is an angle in degrees measured counterclockwise
from the start angle to define the end of the sector. If the length property is set to 360, the Arc is a full ellipse.

The type property specifies the way the Arc is closed. It is one of the constants, OPEN, CHORD, and ROUND,
defined in the ArcType enum.
• The ArcType.OPEN does not close the arc.
• The ArcType.CHORD closes the arc by joining the starting and ending points by a
straight line.
• The ArcType.ROUND closes the arc by joining the starting and ending point to the
center of the ellipse.
The default type for an Arc is ArcType.OPEN. 
If you do not apply a stroke to an Arc, both ArcType.OPEN and ArcType.CHORD look the same.

The Arc class contains two constructors:
    • Arc()
    • Arc(double centerX, double centerY, double radiusX, double radiusY, double startAngle, double length)
*/
public class ArcTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// An OPEN arc with a fill
		Arc arc1 = new Arc(0, 0, 50, 100, 0, 90);
		arc1.setFill(Color.LIGHTGRAY);

		// An OPEN arc with no fill and a stroke
		Arc arc2 = new Arc(0, 0, 50, 100, 0, 90);
		arc2.setFill(Color.TRANSPARENT);
		arc2.setStroke(Color.BLACK);

		// A CHORD arc with no fill and a stroke
		Arc arc3 = new Arc(0, 0, 50, 100, 0, 90);
		arc3.setFill(Color.TRANSPARENT);
		arc3.setStroke(Color.BLACK);
		arc3.setType(ArcType.CHORD);

		// A ROUND arc with no fill and a stroke
		Arc arc4 = new Arc(0, 0, 50, 100, 0, 90);
		arc4.setFill(Color.TRANSPARENT);
		arc4.setStroke(Color.BLACK);
		arc4.setType(ArcType.ROUND);

		// A ROUND arc with a gray fill and a stroke
		Arc arc5 = new Arc(0, 0, 50, 100, 0, 90);
		arc5.setFill(Color.GRAY);
		arc5.setStroke(Color.BLACK);
		arc5.setType(ArcType.ROUND);

		HBox root = new HBox(arc1, arc2, arc3, arc4, arc5);
		root.setSpacing(10);	
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Arcs");
		stage.show();
	}
}
