// PolygonTest.java
package shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/*
An instance of the Polygon class represents a polygon node. The class does not define any public properties.
It lets you draw a polygon using an array of (x, y) coordinates defining the vertices of the polygon. Using the
Polygon class, you can draw any type of geometric shape that is created using connected lines
(triangles, pentagons, hexagons, parallelograms, etc.).

The Polygon class contains two constructors.
    • Polygon()
    • Polygon(double... points)

The no-args constructor creates an empty polygon. You need add the (x, y) coordinates of the vertices
of the shape. The polygon will draw a line from the first vertex to the second vertex, from the second to the
third, and so on. Finally, the shape is closed by drawing a line from the last vertex to the first vertex.

The Polygon class stores the coordinates of the vertices in an ObservableList<Double>. You can get the
reference of the observable list using the getPoints() method. Notice that it stores the coordinates in a list
of Double, which is simply a number. It is your job to pass the numbers in pairs, so they can be used as (x, y)
coordinates of vertices. If you pass an odd number of numbers, no shape is created. The following snippet of
code creates two triangles—one passes the coordinates of the vertices in the constructor and another adds
them to the observable list later. Both triangles are geometrically the same.
*/
public class PolygonTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Polygon triangle1 = new Polygon();
		triangle1.getPoints().addAll(
            50.0, 0.0,
            0.0, 50.0, 
            100.0, 50.0
        );
		triangle1.setFill(Color.WHITE);
		triangle1.setStroke(Color.RED);

		Polygon parallelogram = new Polygon();
		parallelogram.getPoints().addAll(
            30.0, 0.0,
            130.0, 0.0, 
            100.00, 50.0, 
            0.0, 50.0
        );
		parallelogram.setFill(Color.YELLOW);
		parallelogram.setStroke(Color.BLACK);

		Polygon hexagon = new Polygon(
            100.0, 0.0, 
            120.0, 20.0, 
            120.0, 40.0, 
            100.0, 60.0, 
            80.0, 40.0, 
            80.0, 20.0
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
		stage.setTitle("Using Polygons");
		stage.show();
	}
}
