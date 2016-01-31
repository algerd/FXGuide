
package shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;

/*
Bezier curves are used in computer graphics to draw smooth curves. An instance of the QuadCurve class
represents a quadratic Bezier curve segment intersecting two specified points using a specified Bezier
control point. The QuadCurve class contains six properties to specify the three points.
    • startX
    • startY
    • controlX
    • controlY
    • endX
    • endY

The QuadCurve class contains two constructors.
    • QuadCurve()
    • QuadCurve(double startX, double startY, double controlX, double controlY, double endX, double endY)
*/
public class QuadcurveTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		QuadCurve qc1 = new QuadCurve(0, 100, 20, 0, 150, 100);
		qc1.setFill(Color.TRANSPARENT);
		qc1.setStroke(Color.BLACK);

		QuadCurve qc2 = new QuadCurve(0, 100, 20, 0, 150, 100);
		qc2.setFill(Color.LIGHTGRAY);   

		HBox root = new HBox(qc1, qc2);
		root.setSpacing(10);	
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using QuadCurves");
		stage.show();
	}
}
