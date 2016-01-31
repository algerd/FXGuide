
package shape;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

/*
The strokeLineCap property specifies the ending decoration of a stroke for unclosed subpaths and dash
segments. Its value is one of the constants of the StrokeLineCap enum: BUTT, SQUARE, and ROUND. The default
is BUTT. The BUTT line cap adds no decoration to the end of a subpath; the stroke starts and ends exactly at
the starting and ending points. The SQUARE line cap extends the end by half the stroke width. The ROUND line
cap adds a round cap to the end. The round cap uses a radius equal to half the stroke width.

The strokeLineJoin property specifies how two successive path elements of a subpath are joined.
Its value is one of the constants of the StrokeLineJoin enum: BEVEL, MITER, and ROUND. The default is
MITER. The BEVEL line join connects the outer corners of path elements by a straight line. The MITER line
join extends the outer edges of two path elements until they meet. The ROUND line join connects two path
elements by rounding their corners by half the stroke width.
*/
public class StrokePropertiesTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
        
        /*
        The following snippet of code creates two triangles. Both use MITER line join by
        default. The first triangle uses 2.0 as the miter limit. The second triangle uses the default miter limit, which
        is 10.0. The stroke width is 10px. The first triangle tries to join the corners by extending two lines up to 20px,
        which is computed by multiplying the 10px stroke width by the miter limit of 2.0. The corners cannot be
        joined using the MITER join within 20px, so a BEVEL join is used.
        */
		SVGPath t1 = new SVGPath();
        t1.setContent("M50, 0L0, 50 L100, 50 Z");
        t1.setStrokeWidth(10);
        t1.setFill(null);
        t1.setStroke(Color.BLACK);
        t1.setStrokeMiterLimit(2.0);
        
        SVGPath t2 = new SVGPath();
        t2.setContent("M50, 0L0, 50 L100, 50 Z");
        t2.setStrokeWidth(10);
        t2.setFill(null);
        t2.setStroke(Color.BLACK);
        
        /*
        A MITER line join joins two path elements by extending their outer edges. If the path elements meet at
        a smaller angle, the length of the join may become very big. You can limit the length of the join using the
        strokeMiterLimit property. It specifies the ratio of the miter length and the stroke width. The miter length
        is the distance between the most inside point and the most outside point of the join. If the two path elements
        cannot meet by extending their outer edges within this limit, a BEVEL join is used instead. The default value
        is 10.0. That is, by default, the miter length may be up to ten times the stroke width.

        By default, the stroke draws a solid outline. You can also have a dashed outline. You need to provide
        a dashing pattern and a dash offset. The dashing pattern is an array of double that is stored in an
        ObservableList<Double>. You can get the reference of the list using the getStrokeDashArray() method of
        the Shape class. The elements of the list specify a pattern of dashes and gaps. The first element is the dash
        length, the second gap, the third dash length, the fourth gap, and so on. The dashing pattern is repeated to
        draw the outline. The strokeDashOffset property specifies the offset in the dashing pattern where the
        stroke begins.
        */
        Polygon p1 = new Polygon(0, 0, 100, 0, 100, 50, 0, 50, 0, 0);
        p1.setFill(null);
        p1.setStroke(Color.BLACK);
        p1.getStrokeDashArray().addAll(15.0, 5.0, 5.0, 5.0);
        
        Polygon p2 = new Polygon(0, 0, 100, 0, 100, 50, 0, 50, 0, 0);
        p2.setFill(null);
        p2.setStroke(Color.BLACK);
        p2.getStrokeDashArray().addAll(15.0, 5.0, 5.0, 5.0);
        p2.setStrokeDashOffset(20.0);

		HBox root = new HBox(t1, t2, p1, p2);
		root.setAlignment(Pos.CENTER);
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
		stage.setTitle("Using Different Stroke Types for Shapes");
		stage.show();
	}	
}
