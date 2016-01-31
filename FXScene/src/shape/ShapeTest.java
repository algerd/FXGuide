
package shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/*
A shape has a size and a position, which are defined by their properties. For example, the width and
height properties define the size of a rectangle; the radius property defines the size of a circle, the x and y
properties define the position of the upper-left corner of a rectangle, the centerX and centerY properties
define the center of a circle, etc.

Shapes are not resized by their parents during layout. The size of a shape changes only when its
size-related properties are changed. You may find a phrase like “JavaFX shapes are non-resizable.”
It means shapes are non-resizable by their parent during layout. They can be resized only by changing
their properties.

Shapes have an interior and a stroke. The properties for defining the interior and stroke of a shape are
declared in the Shape class. The fill property specifies the color to fill the interior of the shape. The default
fill is Color.BLACK. The stroke property specifies the color for the outline stroke, which is null by default,
except for Line, Polyline, and Path, which have Color.BLACK as the default stroke. The strokeWidth
property specifies the width of the outline, which is 1.0px by default. The Shape class contains other
stroke-related properties.

The Shape class contains a smooth property, which is true by default. Its true value indicates that an
antialiasing hint should be used to render the shape. If it is set to false, the antialiasing hint will not be used,
which may result in the edges of shapes being not crisp.
*/
public class ShapeTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a circle with a light gray fill and no stroke
		Circle c1 = new Circle(40, 40, 40);	 
		c1.setFill(Color.LIGHTGRAY);

		// Create a circle with an yellow fill and a black stroke of 2.0px
		Circle c2 = new Circle(40, 40, 40);	
		c2.setFill(Color.YELLOW);
		c2.setStroke(Color.BLACK);
		c2.setStrokeWidth(2.0);

		HBox root = new HBox(c1, c2);	
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
		stage.setTitle("Using Shapes");
		stage.show();
	}
}
