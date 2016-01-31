
package animation.transition;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
An instance of the TranslateTransition class represents a translate transition for a node by gradually
changing the translateX, translateY, and translateZ properties of the node over the specified duration.
The class defines the following properties to specify the animation:
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
The node property specifies the node whose translateX, translateY, and translateZ properties
are changed.

The initial location of the node is defined by the (fromX, fromY, fromZ) value. If it is not specified, the
current (translateX, translateY, translateZ) value of the node is used as the initial location.
The (toX, toY, toZ) value specifies the end location.
The (byX, byY, byZ) value lets you specify the end location using the following formula:

    translateX_end_value = translateX_initial_value + byX
    translateY_end_value = translateY_initial_value + byY
    translateZ_end_value = translateZ_initial_value + byZ

If both (toX, toY, toZ) and (byX, byY, byZ) values are specified, the former is used.

The program creates a translate transition in an infinite loop for a Text object by scrolling
it across the width of the scene. The program in ScrollingText created the same animation using a Timeline
object with one difference. They use different interpolators. By default, timeline-based animations use the
Interpolator.LINEAR interpolator whereas transition-based animation uses the Interpolator.EASE_BOTH
interpolator. When you run the program, the text starts scrolling slow in the beginning and
end, whereas in ScrollingText, the text scrolls with a uniform speed all the time.
*/
public class TranslateTransitionTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text msg = new Text("JavaFX animation is cool!");
		msg.setTextOrigin(VPos.TOP);
		msg.setFont(Font.font(24));

		Pane root = new Pane(msg);
		root.setPrefSize(500, 70);
		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("Scrolling Text using a Translate Transition");
		stage.show();

		// Set up a translate transition for the Text object
		TranslateTransition tt = new TranslateTransition(Duration.seconds(2), msg);
		tt.setFromX(scene.getWidth());
		tt.setToX(-1.0 * msg.getLayoutBounds().getWidth());
		tt.setCycleCount(TranslateTransition.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
	}
}
