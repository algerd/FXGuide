
package effect.Blur;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
The GaussianBlur effect uses a Gaussian convolution kernel to produce a blurring effect. An instance of the
GaussianBlur class represents a GaussianBlur effect. The effect can be configured using two properties of
the class:
    • radius
    • input

The radius property controls the distribution of the blur in pixels from the source pixel. The greater this
value, the more the blur effect. Its value can be between 0.0 and 63.0. The default value is 10.0. A radius of
zero pixels produces no blur effect.

The GaussianBlur class contains two constructors:
    • GaussianBlur()
    • GaussianBlur(double radius)
The no-args constructor creates a GaussianBlur object with a default radius of 10.0px.

The following snippet of code creates four Text nodes and applies GaussianBlur effects of different
Notice that the last Text node does not have any blur effect as the radius property is set to zero.
*/
public class GaussianBlurTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
        
		Text t1 = new Text("Gaussian Blur");
        t1.setFont(Font.font(24));
        t1.setEffect(new GaussianBlur(5));
        
        Text t2 = new Text("Gaussian Blur");
        t2.setFont(Font.font(24));
        t2.setEffect(new GaussianBlur(10));
        
        Text t3 = new Text("Gaussian Blur");
        t3.setFont(Font.font(24));
        t3.setEffect(new GaussianBlur(15));
        
        Text t4 = new Text("Gaussian Blur");
        t4.setFont(Font.font(24));
        t4.setEffect(new GaussianBlur(0)); // radius = 0 means no blur

		HBox root = new HBox(t1, t2, t3, t4);
		root.setSpacing(20);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;"
        );
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Shadow Effect");
		stage.show();
	}
}
