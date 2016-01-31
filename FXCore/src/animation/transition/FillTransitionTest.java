
package animation.transition;

import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
An instance of the FillTransition class represents a fill transition for a shape by gradually transitioning
the fill property of the shape between the specified range and duration. The class defines the following
properties to specify the animation:
    • duration
    • shape
    • fromValue
    • toValue

The duration property specifies the duration for one cycle of the animation.
The shape property specifies the Shape whose fill property is changed.
The fromValue property specifies the initial fill color. If it is not specified, the current fill of the
shape is used.
The toValue property specifies the fill end value.

The fill of the shape is updated between the initial value and the toValue for one cycle of the
animation. The fill property in the Shape class is defined as a Paint. However, the fromValue and toValue
are of the type Color. That is, the fill transition works for two Colors, not two Paints.

The program creates a fill transition to change the fill color of a Rectangle from blue
violet to azure in 2 seconds in an infinite loop.
*/
public class FillTransitionTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(200, 50, Color.RED);
		HBox root = new HBox(rect);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Fill Transition");
		stage.show();

		// Set up a fill transition for the rectangle
		FillTransition fillTransition = new FillTransition(Duration.seconds(2), rect);
		fillTransition.setFromValue(Color.BLUEVIOLET);
		fillTransition.setToValue(Color.AZURE);
		fillTransition.setCycleCount(FillTransition.INDEFINITE);
		fillTransition.setAutoReverse(true);
		fillTransition.play();
	}
}
