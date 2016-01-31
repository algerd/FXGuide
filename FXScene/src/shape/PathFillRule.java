
package shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.stage.Stage;

/*
Для подробного рассмотрения применения свойства fillRule смотреть K.Sharan 771-772

A Path can be used to draw very complex shapes. Sometimes, it is hard to determine whether a point
is inside or outside the shape. The Path class contains a fillRule property that is used to determine
whether a point is inside a shape. Its value could be one of the constants of the FillRule enum: NON_ZERO
and EVEN_ODD. If a point is inside the shape, it will be rendered using the fill color.

The direction of the stroke is the vital factor in determining whether a point is inside a shape.

The draws four paths: the first two (counting from the left) with NON_ZERO fill rules and the last two with EVEN_ODD fill
rules. The first and third paths use a counterclockwise stroke for drawing both triangular subpaths. 
The second and fourth paths are drawn using a counterclockwise stroke for one triangle
and a clockwise stroke for another.
*/
public class PathFillRule extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Both triangles use a couterclockwise stroke
		PathElement[] pathEleemnts1 = {
            new MoveTo(50, 0), 
            new LineTo(0, 50), 
            new LineTo(100, 50),
            new LineTo(50, 0), 
            new MoveTo(90, 15),
            new LineTo(40, 65), 
            new LineTo(140, 65),
            new LineTo(90, 15)
        };

		// One traingle uses a clockwise stroke and another uses a couterclockwise stroke
		PathElement[] pathEleemnts2 = {
            new MoveTo(50, 0), 
            new LineTo(0, 50), 
            new LineTo(100, 50),	
            new LineTo(50, 0), 
            new MoveTo(90, 15),
            new LineTo(140, 65),
            new LineTo(40, 65),
            new LineTo(90, 15)
        };

		/* Using the NON-ZERO fill rule by default */
		Path p1 = new Path(pathEleemnts1);
		p1.setFill(Color.LIGHTGRAY);

		Path p2 = new Path(pathEleemnts2);
		p2.setFill(Color.LIGHTGRAY);

		/* Using the EVEN_ODD fill rule */
		Path p3 = new Path(pathEleemnts1);
		p3.setFill(Color.LIGHTGRAY);
		p3.setFillRule(FillRule.EVEN_ODD);

		Path p4 = new Path(pathEleemnts2);
		p4.setFill(Color.LIGHTGRAY);
		p4.setFillRule(FillRule.EVEN_ODD);

		HBox root = new HBox(p1, p2, p3, p4);
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
		stage.setTitle("Using Fill Rules for Paths");
		stage.show();
	}
}
