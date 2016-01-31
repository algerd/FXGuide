
package shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;

/*
An instance of the CubicCurve class represents a cubic Bezier curve segment intersecting two
specified points using two specified Bezier control points. Please refer to the Wikipedia article at
http://en.wikipedia.org/wiki/Bezier_curves for a detailed explanation and demonstration of Bezier
curves. The CubicCurve class contains eight properties to specify the four points.
    • startX
    • startY
    • controlX1
    • controlY1
    • controlX2
    • controlY2
    • endX
    • endY

The CubicCurve class contains two constructors.
    • CubicCurve()
    • CubicCurve(double startX, double startY, double controlX1, double controlY1, double controlX2, double controlY2, double endX, double endY)
*/
public class CubicCurveTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		CubicCurve cc1 = new CubicCurve(0, 50, 20, 0, 50, 80, 50, 0);
		cc1.setFill(Color.TRANSPARENT);
		cc1.setStroke(Color.BLACK);

		CubicCurve cc2 = new CubicCurve(0, 50, 20, 0, 50, 80, 50, 0);
		cc2.setFill(Color.LIGHTGRAY);   
		
		HBox root = new HBox(cc1, cc2);
		root.setSpacing(10);	
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using CubicCurves");
		stage.show();
	}
}
