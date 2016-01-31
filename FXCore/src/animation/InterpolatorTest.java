package animation;

import javafx.animation.Interpolator;
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
An interpolator is an instance of the abstract Interpolator class. An interpolator plays an important role
in an animation. Its job is to compute the key values for the intermediate key frames during animation.
Implementing a custom interpolator is easy. You need to subclass the Interpolator class and override
its curve() method. The curve() method is passed the time elapsed for the current interval. The
time is normalized between 0.0 and 1.0. The start and end of the interval have the value of 0.0 and 1.0,
respectively. The value passed to the method would be 0.50 when half of the interval time has elapsed.
The return value of the method indicates the fraction of change in the animated property.

The following interpolator is known as a linear interpolator whose curve() method returns the passed
in argument value:
    Interpolator linearInterpolator = new Interpolator() {
        @Override
        protected double curve(double timeFraction) {
            return timeFraction;
        }
    };

The linear interpolator mandates that the percentage of change in the animated property is the same
as the progression of the time for the interval.

Once you have a custom interpolator, you can use it in constructing key values for key frames in a
timeline-based animation. For a transition-based animation, you can use it as the interpolator property
of the transition classes.

The animation API calls the interpolate() method of the Interpolator. If the animated property is
an instance of Number, it returns
    startValue + (endValue - startValue) * curve(timeFraction)

Otherwise, if the animated property is an instance of the Interpolatable, it delegates the
interpolation work to the interpolate() method of the Interpolatable. Otherwise, the interpolator
defaults to a discrete interpolator by returning 1.0 when the time fraction is 1.0, and 0.0 otherwise.

JavaFX provides some standard interpolators that are commonly used in animations. They are available
as constants in the Interpolator class or as its static methods.
    • Linear interpolator
    • Discrete interpolator
    • Ease-in interpolator
    • Ease-out interpolator
    • Ease-both interpolator
    • Spline interpolator
    • Tangent interpolator

The Interpolator.LINEAR constant represents a linear interpolator. It interpolates the value of the animated
property of a node linearly with time. The percentage change in the property for an interval is the same as
the percentage of the time passed.

The Interpolator.DISCRETE constant represents a discrete interpolator. A discrete interpolator jumps from
one key frame to the next, providing no intermediate key frame. The curve() method of the interpolator
returns 1.0 when the time fraction is 1.0, and 0.0 otherwise. That is, the animated property value stays at its
initial value for the entire duration of the interval. It jumps to the end value at the end of the interval. The
program in ScrollingTest.java uses discrete interpolators for all key frames. When you run the program, it moves
text jumping from key frame to another. Compare this example with the scrolling text example, which used a
linear interpolator. The scrolling text example moved the text smoothly whereas this example created a jerk
in the movement.

The Interpolator.EASE_IN constant represents an ease-in interpolator. It starts the animation slowly for the
first 20% of the time interval and accelerates afterward.

The Interpolator.EASE_OUT constant represents an ease-out interpolator. It plays animation at a constant
speed up to 80% of the time interval and slows down afterwards.

The Interpolator.EASE_BOTH constant represents an ease-both interpolator. Its plays the animation slower
in the first 20% and the last 20% of the time interval and maintains a constant speed otherwise.

The Interpolator.SPLINE(double x1, double y1, double x2, double y2) static method returns a spline
interpolator. It uses a cubic spline shape to compute the speed of the animation at any point in the interval.
The parameters (x1, y1) and (x2, y2) define the control points of the cubic spline shape with (0, 0) and (1, 1)
as implicit anchor points. The values of the parameters are between 0.0 and 1.0.

The slope at a given point on the cubic spline shape defines the acceleration at that point. A slope
approaching the horizontal line indicates deceleration whereas a slope approaching the vertical line
indicates acceleration. For example, using (0, 0, 1, 1) as the parameters to the SPLINE method creates an
interpolator with a constant speed whereas the parameters (0.5, 0, 0.5, 1.0) will create an interpolator that
accelerates in the first half and decelerates in the second half. Please refer to http://www.w3.org/TR/SMIL/
smil-animation.html#animationNS-OverviewSpline for more details.

The Interpolator.TANGENT static method returns a tangent interpolator, which defines the behavior of an
animation before and after a key frame. All other interpolators interpolate data between two key frames.
If you specify a tangent interpolator for a key frame, it is used to interpolate data before and after the key
frame. The animation curve is defined in terms of a tangent, which is known as in-tangent, at a specified
duration before the key frame and a tangent, which is called an out-tangent, at a specified duration after the
key frame. This interpolator is used only in timeline-based animations as it affects two intervals.
The TANGENT static method is overloaded.
    • Interpolator TANGENT(Duration t1, double v1, Duration t2, double v2)
    • Interpolator TANGENT(Duration t, double v)
In the first version, the parameters t1 and t2 are the duration before and after the key frame,
respectively. The parameters v1 and v2 are the in-tangent and out-tangent values. That is, v1 is the tangent
value at duration t1 and v2 is the tangent value at duration t2. The second version specifies the same value
for both pairs.
*/
public class InterpolatorTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text msg = new Text("Hopping text!");
		msg.setTextOrigin(VPos.TOP);
		msg.setFont(Font.font(24));
		
		Pane root = new Pane(msg);
		root.setPrefSize(500, 70);
		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setTitle("Hopping Text");
		stage.show();
	
		// Setup a Timeline animation
		double start = scene.getWidth();
		double end = -1.0 * msg.getLayoutBounds().getWidth();

		KeyFrame[] frame = new KeyFrame[11];
		for(int i = 0; i <= 10; i++) {
			double pos = start - (start - end) * i / 10.0;

			// Set 2.0 seconds as the cycle duration
			double duration = i/5.0;
			
			// Use a discrete interpolator (Change interpolator for testing)
			KeyValue keyValue = new KeyValue(
                msg.translateXProperty(), 
                pos , 
                Interpolator.DISCRETE
            );
			frame[i] = new KeyFrame(Duration.seconds(duration), keyValue);
		}

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
	}
}
