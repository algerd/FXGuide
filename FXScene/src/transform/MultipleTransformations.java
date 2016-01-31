
package transform;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

/*
You can apply multiple transformations to a node. As mentioned previously, the transformations in the
transforms sequence are applied before the transformation set on the properties of the node. When
properties of the Node class are used, translation, rotation, and scale are applied in sequence. When the
transforms sequence is used, transformations are applied in the order they are stored in the sequence.

The program creates three rectangles and positions them at the same location. It applies
multiple transformations to the second and third rectangles in different order.
The first rectangle is shown at its original position, as we did not apply any transformation to it. Notice that
two rectangles ended up at different locations. If you change the order of the transformation for the third
rectangle as shown next, both rectangles will overlap.
*/
public class MultipleTransformations extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect1 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect1.setStroke(Color.BLACK);

		Rectangle rect2 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect2.setStroke(Color.BLACK);
		rect2.setOpacity(0.5);

		Rectangle rect3 = new Rectangle(100, 50, Color.LIGHTCYAN);
		rect3.setStroke(Color.BLACK);
		rect3.setOpacity(0.5);

		// apply transformations to rect2
		rect2.setTranslateX(100);
		rect2.setTranslateY(0);
		rect2.setRotate(30);
		rect2.setScaleX(1.2);
		rect2.setScaleY(1.2);

		// Apply the same transformation as on rect2, but in a different order
		rect3.getTransforms().addAll(
            new Scale(1.2, 1.2, 50, 25),  
            new Rotate(30, 50, 25),
            new Translate(100, 0)
        );
		Group root = new Group(rect1, rect2, rect3);		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying Multiple Transformations");
		stage.show();
	}
}
