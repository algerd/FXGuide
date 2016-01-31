
package transform;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

/*
A translation moves every point of a node by a fixed distance in a specified direction relative to its parent
coordinate system. It is achieved by shifting the origin of the local coordinate system of the node to a new
location. Computing the new locations of points is easy—just add a triplet of numbers to the coordinates of
each point in a 3D space. In a 2D space, add a pair of numbers to the coordinates of each point.

Suppose you want to apply translation to a 3D coordinate space by (tx, ty, tz). If a point had coordinates
(x, y, z) before the translation, after the translation its coordinates would be (x + tx, y + ty, z + tz).

An instance of the Translate class represents a translation. It contains three properties.
    • x
    • y
    • z

The properties specify the x, y, and z coordinates of the new origin of the local coordinate system of the
node after translation. The default values for the properties are 0.0.
The Translate class provides three constructors.
    • Translate()
    • Translate(double x, double y)
    • Translate(double x, double y, double z)
The no-args constructor creates a Translate object with the default values for the x, y, and z properties,
which, in essence, represents no translation.

Compare the use of the layoutX and layoutY properties of the Node class with the translateX and
translateY properties. The layoutX and layoutY properties position the node in its local coordinate system
without transforming the local coordinate system whereas the translateX and translateY properties
transform the local coordinate system of the node by shifting the origin. Typically, layoutX and layoutY are
used to place a node in a scene whereas translation is used for moving a node in an animation. If you set
both properties for a node, its local coordinate system will be transformed using the translation, and, then,
the node will be placed in the new coordinate system using its layoutX and layoutY properties.

The program creates three rectangles. By default, they are placed at (0, 0). It applies a
translation to the second and third rectangles.
*/
public class TranslateTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect1 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect1.setStroke(Color.BLACK);

		Rectangle rect2 = new Rectangle(100, 50, Color.YELLOW);
		rect2.setStroke(Color.BLACK);

		Rectangle rect3 = new Rectangle(100, 50, Color.STEELBLUE);
		rect3.setStroke(Color.BLACK);

		// Apply a translation on rect2 using the transforms sequence
		Translate translate1 = new Translate(50, 10);
		rect2.getTransforms().addAll(translate1);

		// Apply a translation on rect3 using the translateX and translateY proeprties
		rect3.setTranslateX(180);
		rect3.setTranslateY(20);

		Pane root = new Pane(rect1, rect2, rect3);
		root.setPrefSize(300, 80);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the Translation Transformation");
		stage.show();
	}
}
