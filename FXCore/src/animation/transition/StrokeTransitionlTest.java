
package animation.transition;

import javafx.animation.FillTransition;
import javafx.animation.StrokeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
An instance of the StrokeTransition class represents a stroke transition for a shape by gradually
transitioning the stroke property of the shape between the specified range and duration. The stroke
transition works the same as the fill transition, except that it interpolates the stroke property of the
shape rather than the fill property. The StrokeTransition class contains the same properties as the
FillTransition class.
Please refer to the section FillTransitionTest.java for more details.

The following snippet of code starts animating the stroke of a Rectangle in an infinite loop. The stroke
changes from red to blue in a cycle duration of 2 seconds.
*/
public class StrokeTransitionlTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(200, 50, Color.RED);
		HBox root = new HBox(rect);
        root.setPadding(new Insets(10));
        rect.setStrokeWidth(5);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Fill Transition");
		stage.show();

		// Set up a fill transition for the rectangle
		StrokeTransition strokeTransition = new StrokeTransition(Duration.seconds(2), rect);
		strokeTransition.setFromValue(Color.YELLOW);
		strokeTransition.setToValue(Color.GREEN);
		strokeTransition.setCycleCount(FillTransition.INDEFINITE);
		strokeTransition.setAutoReverse(true);
		strokeTransition.play();
	}
}
