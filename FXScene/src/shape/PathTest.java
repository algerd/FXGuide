
package shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

/*
An instance of the Path class defines the path (outline) of a shape. A path consists of one or more subpaths. 
A subpath consists of one or more path elements. Each subpath has a starting point and an ending point.
A path element is an instance of the PathElement abstract class. The following subclasses of the
PathElement class exist to represent specific type of path elements:
    • MoveTo
    • LineTo
    • HLineTo
    • VLineTo
    • ArcTo
    • QuadCurveTo
    • CubicCurveTo
    • ClosePath

Before you see an example, let us outline the process of creating a shape using the Path class. The
process is similar to drawing a shape on a paper with a pencil. First, you place the pencil on the paper. You
can restate it, “You move the pencil to a point on the paper.” Regardless of what shape you want to draw,
moving the pencil to a point must be the first step. Now, you start moving your pencil to draw a path element
(e.g., a horizontal line). The starting point of the current path element is the same as the ending point of the
previous path element. Keep drawing as many path elements as needed (e.g., a vertical line, an arc, and a
quadratic Bezier curve). At the end, you can end the last path element at the same point where you started or
somewhere else.

The coordinates defining a PathElement can be absolute or relative. By default, coordinates are
absolute. It is specified by the absolute property of the PathElement class. If it is true, which is the default,
the coordinates are absolute. If it is false, the coordinates are relative. The absolute coordinates are
measured relative to the local coordinate system of the node. Relative coordinates are measured treating the
ending point of the previous PathElement as the origin.

The Path class contains three constructors.
    • Path()
    • Path(Collection<? extends PathElement> elements)
    • Path(PathElement... elements)

A Path stores path elements in an ObservableList<PathElement>. You can get the reference
of the list using the getElements() method. You can modify the list of path elements to modify the shape.
*/
public class PathTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Path triangle = new Path(
            new MoveTo(0, 0), 
            new LineTo(0, 50),
            new LineTo(50, 50), 
            new ClosePath()
        );

		Path star = new Path(); 
		star.getElements().addAll(
            new MoveTo(30, 0), 
            new LineTo(0, 30),
            new LineTo(60, 30), 
            new ClosePath(),/* new LineTo(30, 0), */
            new MoveTo(0, 10),
            new LineTo(60, 10),
            new LineTo(30, 40),
            new ClosePath() /*new LineTo(0, 10)*/
        );

		HBox root = new HBox(triangle, star);
		root.setSpacing(10);	
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Paths");
		stage.show();
	}
}
