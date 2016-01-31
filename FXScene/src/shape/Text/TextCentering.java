
package shape.Text;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
Apart from the local and parent coordinate system, a Text node has an additional coordinate system.
It is the coordinate system used for drawing the text. Three properties of the Text class define the text
coordinate system:
    • x
    • y
    • textOrigin

The x and y properties define the x and y coordinates of the text origin. The textOrigin property is of
type VPos. Its value could be VPos.BASELINE, VPos.TOP, VPos.CENTER, and VPos.BOTTOM. The default is
VPos.BASELINE. It defines where the x-axis of the text coordinate system lies within the text height. Figure 18-2
shows the local and text coordinate systems of a text node. The local coordinate axes are in solid lines. The
text coordinate axes are in dashed lines.

When the textOrigin is VPos.TOP, the x-axis of the text coordinate system is aligned with the top of the
text. That is, the y property of the Text node is the distance between the x-axis of the local coordinate system
and the top of the displayed text. A font places its characters on a line called the baseline. The VPos.BASELINE
aligns the x-axis of the text coordinate system with the baseline of the font. Note that some characters
(e.g., g, y, j, p, etc.) are extended below the baseline. The VPos.BOTTOM aligns the x-axis of the text coordinate
system with the bottom of the displayed text accounting for the descent for the font. The VPos.CENTER
(not shown in the figure) aligns the x-axis of the text coordinate system in the middle of the displayed text,
accounting for the ascent and descent for the font.

Tip: The Text class contains a read-only baselineOffset property. Its value is the vertical distance
between the top and baseline of the text. It is equal to the max ascent of the font.

Most of the time, you need not worry about the textOrigin property of the Text node, except when you
need to align it vertically relative to another node. Listing shows how to center a Text node horizontally
and vertically in a scene. To center the node vertically, you must set the textOrigin property to VPos.TOP.
If you do not set the textOrigin property, its y-axis is aligned with its baseline and it appears 
above the centerline of the scene.
*/
public class TextCentering extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text msg = new Text("A Centered Text Node");
		
		// Must set the textOrigian to VPos.TOP to center the text node vertcially within the scene
		msg.setTextOrigin(VPos.TOP);

		Group root = new Group();
		root.getChildren().addAll(msg);
		Scene scene = new Scene(root, 200, 50);
		msg.layoutXProperty().bind(
			scene.widthProperty().subtract(msg.layoutBoundsProperty().get().getWidth()).divide(2)
        );
		msg.layoutYProperty().bind(
			scene.heightProperty().subtract(msg.layoutBoundsProperty().get().getHeight()).divide(2)
        );

		stage.setTitle("Centering a Text Node in a Scene");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
}
