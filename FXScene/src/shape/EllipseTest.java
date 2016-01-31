
package shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

/*
An instance of the Ellipse class represents an ellipse node. The class uses four properties to define the ellipse.
    • centerX
    • centerY
    • radiusX
    • radiusY

The centerX and centerY properties are the x and y coordinates of the center of the circle in the local
coordinate system of the node. The radiusX and radiusY are the radii of the ellipse in the horizontal and
vertical directions. The default values for these properties are zero. A circle is a special case of an ellipse
when radiusX and radiusY are the same.

The Ellipse class contains several constructors.
    • Ellipse()
    • Ellipse(double radiusX, double radiusY)
    • Ellipse(double centerX, double centerY, double radiusX, double radiusY)

The program creates three instances of the Ellipse class. The third instance draws a
circle as the program sets the same value for the radiusX and radiusY properties.
*/
public class EllipseTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {	
		Ellipse e1 = new Ellipse(50, 30);
		e1.setFill(Color.LIGHTGRAY);

		Ellipse e2 = new Ellipse(60, 30);
		e2.setFill(Color.YELLOW);
		e2.setStroke(Color.BLACK);
		e2.setStrokeWidth(2.0);

		// Draw a circle using the Ellipse class (radiusX=radiusY=30)
		Ellipse e3 = new Ellipse(30, 30);
		e3.setFill(Color.YELLOW);
		e3.setStroke(Color.BLACK);
		e3.setStrokeWidth(2.0);

		HBox root = new HBox(e1, e2, e3);
		root.setSpacing(10);	
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Ellipses");
		stage.show();
	}
}
