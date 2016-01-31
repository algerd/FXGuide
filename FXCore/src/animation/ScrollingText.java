
package animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


/*
Порядок создания анимации:
    - создать KeyValue объекты со значениями свойств для определённых моментов времени
    - создать KeyFrame объекты и передать в них KeyValue объекты и Duration объекты, соответствующих KeyValue объектам
    - создать Timeline объект и передать в него KeyFrame объекты
    - Timeline::play() - начать анимацию

------------------------------------------------
Understating the KeyValue Class:

An instance of the KeyValue class represents a key value that is interpolated for a particular interval during
animation. It encapsulates three things.
    • A target
    • An end value for the target
    • An interpolator
The target is a WritableValue, which qualifies all JavaFX properties to be a target. The end value is
the value for the target at the end of the interval. The interpolator is used to compute the intermediate
key frames.

The KeyValue class is immutable. It provides two constructors.
    • KeyValue(WritableValue<T> target, T endValue)
    • KeyValue(WritableValue<T> target, T endValue, Interpolator interpolator)

The Interpolator.LINEAR is used as the default interpolator that interpolates the animated property
linearly with time. I will discuss different types of interpolators later.

The following snippet of code creates a Text object and two KeyValue objects. The translateX property
is the target. 0 and 100 are the end values for the target. The default interpolator is used.
    Text msg = new Text("JavaFX animation is cool!");
    KeyValue initKeyValue = new KeyValue(msg.translateXProperty(), 0.0);
    KeyValue endKeyValue = new KeyValue(msg.translateXProperty(), 100.0);

The following snippet of code is similar to the one shown above. It uses the Interpolator.EASE_BOTH
interpolator, which slows down the animation in the start and toward the end.
    KeyValue initKeyValue = new KeyValue(msg.translateXProperty(), 0.0, Interpolator.EASE_BOTH);
    KeyValue endKeyValue = new KeyValue(msg.translateXProperty(), 100.0, Interpolator.EASE_BOTH);
----------------------------------------------

Understanding the KeyFrame Class:

A key frame defines the target state of a node at a specified point on the timeline. The target state is defined
by the key values associated with the key frame.
A key frame encapsulates four things.
    • An instant on the timeline
    • A set of KeyValues
    • A name
    • An ActionEvent handler
The instant on the timeline with which the key frame is associated is defined by a Duration, which is an
offset of the key frame on the timeline.
    • The set of KeyValues defines the end value of the target for the key frame.
A key frame may optionally have a name that can be used as a cue point to jump to the instant
defined by it during an animation. The getCuePoints() method of the Animation class returns a Map of
cue points on the Timeline.
The ActionEvent handler is called when the time for the key frame arrives during animation.

An instance of the KeyFrame class represents a key frame. The class provides several constructors:
    • KeyFrame(Duration time, EventHandler<ActionEvent> onFinished, KeyValue... values)
    • KeyFrame(Duration time, KeyValue... values)
    • KeyFrame(Duration time, String name, EventHandler<ActionEvent> onFinished, Collection<KeyValue> values)
    • KeyFrame(Duration time, String name, EventHandler<ActionEvent> onFinished, KeyValue... values)
    • KeyFrame(Duration time, String name, KeyValue... values)

The following snippet of code creates two instances of KeyFrame that specify the translateX property of
a Text node at 0 seconds and 3 seconds on a timeline:
    Text msg = new Text("JavaFX animation is cool!");
    KeyValue initKeyValue = new KeyValue(msg.translateXProperty(), 0.0);
    KeyValue endKeyValue = new KeyValue(msg.translateXProperty(), 100.0);
    KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);
    KeyFrame endFrame = new KeyFrame(Duration.seconds(3), endKeyValue);
----------------------------------------------

Understating the Timeline Animation:

A timeline animation is used for animating any properties of a node. An instance of the Timeline class
represents a timeline animation. Using a timeline animation involves the following steps:
    • Construct key frames
    • Create a Timeline object with key frames
    • Set the animation properties
    • Use the play() method to run the animation

You can add key frames to a Timeline at the time of creating it or after. The Timeline instance keeps all
key frames in an ObservableList<KeyFrame> object. The getKeyFrames() method returns the list. You can
modify the list of key frames at any time. If the timeline animation is already running, you need to stop and
restart it to pick up the modified list of key frames.

The Timeline class contains several constructors.
    • Timeline()
    • Timeline(double targetFramerate)
    • Timeline(double targetFramerate, KeyFrame... keyFrames)
    • Timeline(KeyFrame... keyFrames)
The no-args constructor creates a Timeline with no key frames with animation running at the optimum
rate.

Note that the order in which the key frames are added to a Timeline is not important. Timeline will
order them based on their time offset.
----------------------------------------------------

It is possible to create a Timeline animation with only one key frame. The key frame is treated as the last
key frame. The Timeline synthesizes an initial key frame (for time = 0 seconds) using the current values for
the WritableValue being animated. To see the effect, let us replace the statement
    Timeline timeline = new Timeline(endFrame);

The Timeline will create an initial key frame with the current value of translateX property of the Text
object, which is 0.0. This time, the Text scrolls differently. The scrolling starts by placing the Text at 0.0 and
scrolling it to the left, so it goes beyond the scene.
*/
public class ScrollingText extends Application {
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
		stage.setTitle("Scrolling Text");
		stage.show();

		/* Set up a Timeline animation */
		// Get the scene width and the text width
		double sceneWidth = scene.getWidth();
		double msgWidth = msg.getLayoutBounds().getWidth();

		// Create the initial and final key frames
		KeyValue initKeyValue = new KeyValue(msg.translateXProperty(), sceneWidth);
		KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);

		KeyValue endKeyValue = new KeyValue(msg.translateXProperty(), -1.0 * msgWidth);
		KeyFrame endFrame = new KeyFrame(Duration.seconds(3), endKeyValue);

		// Create a Timeline object
		Timeline timeline = new Timeline(initFrame, endFrame);

		// Let the animation run forever
		timeline.setCycleCount(Timeline.INDEFINITE);
        
        // Start the animation
		timeline.play();
        
        /*
        The scrolling the text does not update its initial horizontal position when
        the width of the scene changes. You can rectify this problem by updating the initial key frame whenever
        the scene width changes.
        It adds a ChangeListener for the scene width that updates key frames and restarts the animation.
        */
        scene.widthProperty().addListener( (prop, oldValue , newValue) -> {
            KeyValue kv = new KeyValue(msg.translateXProperty(), scene.getWidth());
            KeyFrame kf = new KeyFrame(Duration.ZERO, kv);
            timeline.stop();
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().addAll(kf, endFrame);
            timeline.play();
        });
	
	}
}
