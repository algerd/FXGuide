
package shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/*
The Shape class provides three static methods that let you perform union, intersection and subtraction
of shapes.
    • union(Shape shape1, Shape shape2)
    • intersect(Shape shape1, Shape shape2)
    • subtract(Shape shape1, Shape shape2)

The methods return a new Shape instance. They operate on the areas of the input shapes. If a shape
does not have a fill and a stroke, its area is zero. The new shape has a stroke and a fill. The union() method
combines the areas of two shapes. The intersect() method uses the common areas between the shapes
to create the new shape. The subtract() method creates a new shape by subtracting the specified second
shape from the first shape.
*/
public class CombiningShapesTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		Circle c1 = new Circle (0, 0, 20);
		Circle c2 = new Circle (15, 0, 20);
	    
		Shape union = Shape.union(c1, c2);
		union.setStroke(Color.BLACK);
		union.setFill(Color.LIGHTGRAY);
		
	    Shape intersection = Shape.intersect(c1, c2);
	    intersection.setStroke(Color.BLACK);
	    intersection.setFill(Color.LIGHTGRAY);
		
		Shape subtraction = Shape.subtract(c1, c2);
		subtraction.setStroke(Color.BLACK);
		subtraction.setFill(Color.LIGHTGRAY);

		HBox root = new HBox(union, intersection, subtraction);
		root.setSpacing(20);
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
		stage.setTitle("Combining Shapes");
		stage.show();
	}	
}
