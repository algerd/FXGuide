
package animation.transition;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
An instance of the ScaleTransition class represents a scale transition for a node by gradually changing its
scaleX, scaleY, and scaleZ properties over the specified duration. The class defines the following properties
to specify the animation:
    • duration
    • node
    • fromX
    • fromY
    • fromZ
    • toX
    • toY
    • toZ
    • byX
    • byY
    • byZ

The duration property specifies the duration for one cycle of the animation.
The node property specifies the node whose scaleX, scaleY, and scaleZ properties are changed.
The initial scale of the node is defined by the (fromX, fromY, fromZ) value. If it is not specified, the
current (scaleX, scaleY, scaleZ) value of the node is used as the initial scale.
The (toX, toY, toZ) value specifies the end scale.
The (byX, byY, byZ) value lets you specify the end scale using the following formula:

    scaleX_end_value = scaleX_initial_value + byX
    scaleY_end_value = scaleY_initial_value + byY
    scaleZ_end_value = scaleZ_initial_value + byZ

If both (toX, toY, toZ) and (byX, byY, byZ) values are specified, the former is used.
The program creates a scale transition in an infinite loop for a Rectangle by changing its
width and height between 100% and 20% of their original values in 2 seconds.
*/
public class ScaleTransitionTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(200, 50, Color.RED);
		HBox root = new HBox(rect);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Scale Transition");
		stage.show();

		// Set up a scale transition for the rectangle
		ScaleTransition st = new ScaleTransition(Duration.seconds(2), rect);
		st.setFromX(1.0);
		st.setToX(0.20);
		st.setFromY(1.0);
		st.setToY(0.20);
		st.setCycleCount(ScaleTransition.INDEFINITE);
		st.setAutoReverse(true);
		st.play();
	}
}
