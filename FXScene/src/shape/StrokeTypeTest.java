
package shape;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

/*
Stroking is the process of painting the outline of a shape. Sometimes, the outline of a shape is also known as
stroke. The Shape class contains several properties to define the appearance of the stroke of a shape.
    • stroke
    • strokeWidth
    • strokeType
    • strokeLineCap
    • strokeLineJoin
    • strokeMiterLimit
    • strokeDashOffset

The stroke property specifies the color of the stroke. The default stroke is set to null for all shapes
except Line, Path and Polyline, which have Color.BLACK as their default stroke.
The strokeWidth property specifies the width of the stroke. It is 1.0px by default.

The stroke is painted along the boundary of a shape. The strokeType property specifies the distribution
of the width of the stroke on the boundary. Its value is one of the three constants, CENTERED, INSIDE, and
OUTSIDE, the StrokeType enum. The default value is CENTERED. The CENTERED stroke type draws a half of
the stroke width outside and half inside the boundary. The INSIDE stroke type draws the stroke inside
the boundary. The OUTSIDE stroke draws the stroke outside the boundary. The stroke width of a shape is
included in its layout bounds.

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

The program in Listing 17-15 creates four rectangles . All rectangles have the same width and height (50px and 50px). 
The first rectangle, counting from the left, has no stroke and it has layout bounds of 50px X 50px. 
The second rectangle uses a stroke of width 4px and an INSIDE stroke type.
The INSIDE stroke type is drawn inside the width and height boundary, the rectangle has the layout bounds
of 50px X 50px. The third rectangle uses a stroke width 4px and a CENTERED stroke type, which is the default.
The stroke is drawn 2px inside the boundary and 2px outside the boundary. The 2px outside stroke is added
to the dimensions of all four making the layout bounds to 54px X 54px. The fourth rectangle uses a 4px stroke
width and an OUTSIDE stroke type. The entire stroke width falls outside the width and height of the rectangle
making the layouts to 58px X 58px.
*/
public class StrokeTypeTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle r1 = new Rectangle(50, 50);
		r1.setFill(Color.LIGHTGRAY);
		
		Rectangle r2 = new Rectangle(50, 50);
		r2.setFill(Color.LIGHTGRAY);
		r2.setStroke(Color.BLACK);
		r2.setStrokeWidth(4);
		r2.setStrokeType(StrokeType.INSIDE);

		Rectangle r3 = new Rectangle(50, 50);
		r3.setFill(Color.LIGHTGRAY);
		r3.setStroke(Color.BLACK);
		r3.setStrokeWidth(4);

		Rectangle r4 = new Rectangle(50, 50);
		r4.setFill(Color.LIGHTGRAY);
		r4.setStroke(Color.BLACK);
		r4.setStrokeWidth(4);
		r4.setStrokeType(StrokeType.OUTSIDE);

		HBox root = new HBox(r1, r2, r3, r4);
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
