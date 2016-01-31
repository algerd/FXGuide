
package animation.transition;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
An instance of the ParallelTransition class represents a parallel transition. It executes a list of animations
simultaneously. The list of animations may contain timeline-based animations, transition-based
animations, or both.

The ParallelTransition class contains a node property that is used as the node for animations in the
list if the animation does not specify a node. If all animations specify a node, this property is not used.
A ParallelTransition maintains the animations in an ObservableList<Animation>. The getChildren()
method returns the reference of the list.

The following snippet of code creates a fade transition and a path transition. They transitions are added
to a parallel transition. When the sequential transition is played, it will apply the fading effect and move the
node at the same time.
    FadeTransition fadeTransition = ...
    PathTransition pathTransition = ...
    ParallelTransition pt = new ParallelTransition();
    pt.getChildren().addAll(fadeTransition, pathTransition);
    pt.play();

Tip: The ParallelTransition class contains constructors that let you specify the list of animations and node.

The program creates a fade transition and a rotate transition. It adds them to a parallel
transition. When the program is run, the rectangle rotates and fades in/out at the same time.
*/
public class ParallelTransitionTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(100, 100, Color.RED);
		HBox.setMargin(rect, new Insets(20));

		HBox root = new HBox(rect);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Parallel Transition");
		stage.show();

		// Set up a fade transition
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1));
		fadeTransition.setFromValue(0.20);
		fadeTransition.setToValue(1.0);
		fadeTransition.setCycleCount(2);
		fadeTransition.setAutoReverse(true);

		// Set up a rotate transition
		RotateTransition rotateTransition = new RotateTransition(Duration.seconds(2));
		rotateTransition.setFromAngle(0.0);
		rotateTransition.setToAngle(360.0);
		rotateTransition.setCycleCount(2);
		rotateTransition.setAutoReverse(true);

		// Create and start a sequential transition
		ParallelTransition pt = new ParallelTransition();

		// Rectangle is the node for all animations
		pt.setNode(rect); 
		pt.getChildren().addAll(fadeTransition, rotateTransition);
		pt.setCycleCount(PathTransition.INDEFINITE);
		pt.play();
	}
}
