
package animation.transition;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
An instance of the FadeTransition class represents a fade-in or fade-out effect for a node by gradually
increasing or decreasing the opacity of the node over the specified duration. The class defines the following
properties to specify the animation:
    • duration
    • node
    • fromValue
    • toValue
    • byValue

The duration property specifies the duration for one cycle of the animation.
The node property specifies the node whose opacity property is changed.
The fromValue property specifies the initial value for the opacity. If it is not specified, the current
opacity of the node is used.
The toValue property specifies the opacity end value. The opacity of the node is updated between the
initial value and the toValue for one cycle of the animation.

The byValue property lets you specify the opacity end value differently using the formula
    opacity_end_value = opacity_initial_value + byValue

The byValue lets you set the opacity end value by incrementing or decrementing the initial value by an
offset. If both toValue and byValue are specified, the toValue is used.

Suppose you want to set the initial and end opacity of a node between 1.0 and 0.5 in an animation. You
can achieve it by setting the fromValue and toValue to 1.0 and 0.50 or by setting fromValue and byValue to
1.0 and -0.50.

The valid opacity value for a node is between 0.0 and 1.0. It is possible to set FadeTransition
properties to exceed the range. The transition takes care of clamping the actual value in the range.

The following snippet of code sets up a fade-out animation for a Rectangle by changing its opacity
from 1.0 to 0.20 in 2 seconds:
*/
public class FadeTransitionTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(200, 50, Color.RED);
		HBox root = new HBox(rect);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Fade-in and Fade-out");
		stage.show();

		// Set up a fade-in and fade-out animation for the rectangle
		FadeTransition fadeInOut = new FadeTransition(Duration.seconds(2), rect);
		fadeInOut.setFromValue(1.0);
		fadeInOut.setToValue(.20);
		fadeInOut.setCycleCount(FadeTransition.INDEFINITE);
		fadeInOut.setAutoReverse(true);
		fadeInOut.play();
	}
}
