
package animation.transition;

import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javafx.animation.PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT;

/*
An instance of the SequentialTransition class represents a sequential transition. It executes a list of animations
in sequential order. The list of animation may contain timeline-based animations, transition-based
animations, or both.

The SequentialTransition class contains a node property that is used as the node for animations in the
list if the animation does not specify a node. If all animations specify a node, this property is not used.
A SequentialTransition maintains the animations in an ObservableList<Animation>. The getChildren()
method returns the reference of the list.

Tip: The SequentialTransition class contains constructors that let you specify the list of animations
and node.

The program in creates a scale transition, a fill transition, a pause transition, and a path
transition, which are added to a sequential transition. The sequential transition runs in an infinite loop.
When the program runs
    • It scales up the rectangle to double its size, and then down to the original size.
    • It changes the fill color of the rectangle from red to blue, and then, back to red.
    • It pauses for 200 milliseconds, and then, prints a message on the standard output.
    • It moves the rectangle along the outline of a circle.
    • The foregoing sequence of animations is repeated indefinitely.
---------------------------------------------------

Pause Transition:

An instance of the PauseTransition class represents a pause transition. It causes a delay of the specified
duration. Its use is not obvious. It is not used alone. Typically, it is used in a sequential transition to insert a
pause between two transitions. It defines a duration property to specify the duration of the delay.
A pause transition is also useful if you want to execute an ActionEvent handler after a specified
duration when a transition is finished. You can achieve this by setting its onFinished property, which is
defined in the Animation class.
    // Create a pause transition of 400 milliseconds that is the default duration
    PauseTransition pt1 = new PauseTransition();
    // Change the duration to 10 seconds
    pt1.setDuration(Duration.seconds(10));
    // Create a pause transition of 5 seconds
    PauseTransition pt2 = new PauseTransition(Duration.seconds(5));

If you change the duration of a running pause transition, you need to stop and restart the transition to
pick up the new duration.
*/
public class SequentialTransitionTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		// Create the node to be animated
		Rectangle rect = new Rectangle(20, 10, Color.RED);

		// Create the path
		Circle path = new Circle(100, 100, 75);
		path.setFill(null);
		path.setStroke(Color.BLACK);

		Pane root = new Pane(rect, path);
		root.setPrefSize(200, 200);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Sequential Transition");
		stage.show();

		// Set up a scale transition
		ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1));
		scaleTransition.setFromX(1.0);
		scaleTransition.setToX(2.0);
		scaleTransition.setFromY(1.0);
		scaleTransition.setToY(2.0);
		scaleTransition.setCycleCount(2);
		scaleTransition.setAutoReverse(true);

		// Set up a fill transition
		FillTransition fillTransition = new FillTransition(Duration.seconds(1));
		fillTransition.setFromValue(Color.RED);
		fillTransition.setToValue(Color.BLUE);
		fillTransition.setCycleCount(2);
		fillTransition.setAutoReverse(true);

		// Set up a pause transition
		PauseTransition pauseTransition = new PauseTransition(Duration.millis(200));
		pauseTransition.setOnFinished(e -> System.out.println("Ready to circle..."));
		
		// Set up a path transition
		PathTransition pathTransition = new PathTransition(Duration.seconds(2), path);
		pathTransition.setOrientation(ORTHOGONAL_TO_TANGENT);

		// Create a sequential transition
		SequentialTransition st = new SequentialTransition();
		
		// Rectangle is the node for all animations
		st.setNode(rect);
		
		// Add animations to the list
		st.getChildren().addAll(
            scaleTransition, 
            fillTransition, 
            pauseTransition, 
            pathTransition
        );
		st.setCycleCount(PathTransition.INDEFINITE);
		st.play();
	}
}
