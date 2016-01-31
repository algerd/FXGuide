
package transform;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

/*
See ScaleTest.java.

If the pivot point is not the center of the node, the scale transformation may move the node. The
program creates two rectangles. Both are placed at the same location. One of them is scaled
and the other not. The opacity of the not scaled rectangle is set to 0.5, so we can see through it. Figure 21-9
shows the rectangles. The scaled rectangle is smaller. A Scale object with the transforms sequence is used
to apply the transformation, which uses the upper-left corner of the rectangle as the pivot point making
the rectangle shrink, but moving it to the left to keep the coordinates of its upper-left corner the same
(150, 0) in the transformed coordinate system. The scaled rectangles shrinks by half (scale factor = 0.50) in
both directions and moves half the distance to the left.
*/
public class ScalePivotPointTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect1 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect1.setX(150);
		rect1.setStroke(Color.BLACK);
		rect1.setOpacity(0.5);

		Rectangle rect2 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect2.setX(150);
		rect2.setStroke(Color.BLACK);

		// Apply a scale on rect2. The origin of the local coordinate system
		// of rect4 is the pivot point
		Scale scale = new Scale(0.5, 0.5);
		rect2.getTransforms().addAll(scale);

		Pane root = new Pane(rect1, rect2);
		root.setPrefSize(300, 60);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the Scale Transformation");
		stage.show();
	}
}
