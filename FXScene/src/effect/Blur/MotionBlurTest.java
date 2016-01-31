
package effect.Blur;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.MotionBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
The MotionBlur effect produces a blurring effect by motion. The input looks as if you are seeing it while it is
moving. A Gaussian convolution kernel is used with a specified angle to produce the effect. An instance of
the MotionBlur class represents a MotionBlur effect. The effect can be configured using the three properties
of the class:
    • radius
    • angle
    • input

The radius and input properties work the same as respective properties for the GaussianBlur class,
as described in the previous section. The angle property specifies the angle of the motion in degrees. By
default, the angle is zero.

The MotionBlur class contains two constructors:
    • MotionBlur()
    • MotionBlur(double angle, double radius)
The no-args constructor creates a MotionBlur object with a default radius of 10.0px and an angle of
0.0 degrees.
*/
public class MotionBlurTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text t1 = new Text("Motion Blur");
		t1.setFont(Font.font(null, FontWeight.BOLD, 36));
		MotionBlur mbEffect = new MotionBlur();
		t1.setEffect(mbEffect);

		Slider radiusSlider = new Slider(0.0, 63.0, 10.0);
		radiusSlider.setMajorTickUnit(10);
		radiusSlider.setShowTickLabels(true);
		mbEffect.radiusProperty().bind(radiusSlider.valueProperty());

		Slider angleSlider = new Slider(0.0, 360.0, 0);
		angleSlider.setMajorTickUnit(10);
		angleSlider.setShowTickLabels(true);
		mbEffect.angleProperty().bind(angleSlider.valueProperty());

		HBox pane = new HBox(10, new Label("Radius:"), radiusSlider, new Label("Angle:"), angleSlider);

		BorderPane root = new BorderPane();
		root.setCenter(t1);
		root.setBottom(pane);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using the MotionBlur Effect");
		stage.show();
	}
}
