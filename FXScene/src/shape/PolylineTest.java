
package shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

/*
A polyline is similar to a polygon, except that it does not draw a line between the last and first points.
That is, a polyline is an open polygon. However, the fill color is used to fill the entire shape as if the shape
was closed.

An instance of the Polyline class represents a polyline node. The class does not define any public
properties. It lets you draw a polyline using an array of (x, y) coordinates defining the vertices of the polyline.
Using the Polyline class, you can draw any type of geometric shape that is created using connected lines
(triangles, pentagons, hexagons, parallelograms, etc.).

The Polyline class contains two constructors.
    • Polyline()
    • Polyline(double... points)

The no-args constructor creates an empty polyline. You need add (x, y) coordinates of the vertices of the
shape. The polygon will draw a line from the first vertex to the second vertex, from the second to the third,
and so on. Unlike a Polygon, the shape is not closed automatically. If you want to close the shape, you need
to add the coordinates of the first vertex as the last pair of numbers.

If you want to add coordinates of vertices later, add them to the ObservableList<Double> returned by
the getPoints() method of the Polyline class.
*/
public class PolylineTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Polyline triangle1 = new Polyline();
		triangle1.getPoints().addAll(
            50.0, 0.0,
            0.0, 50.0, 
            100.0, 50.0, 
            50.0, 0.0
        );
		triangle1.setFill(Color.WHITE);
		triangle1.setStroke(Color.RED);

		// Create an open parallelogram
		Polyline parallelogram = new Polyline();
		parallelogram.getPoints().addAll(
            30.0, 0.0,
            130.0, 0.0, 
            100.00, 50.0, 
            0.0, 50.0
        );
		parallelogram.setFill(Color.YELLOW);
		parallelogram.setStroke(Color.BLACK);

		Polyline hexagon = new Polyline(
            100.0, 0.0, 
            120.0, 20.0, 
            120.0, 40.0, 
            100.0, 60.0, 
            80.0, 40.0, 
            80.0, 20.0,
            100.0, 0.0
        );
		hexagon.setFill(Color.WHITE);
		hexagon.setStroke(Color.BLACK);

		HBox root = new HBox(triangle1, parallelogram, hexagon);
		root.setSpacing(10);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" +
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;"
        );
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Polylines");
		stage.show();
	}
}
