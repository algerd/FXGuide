
package effect.Blur;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
The BoxBlur effect uses a box filter kernel to produce a blurring effect. An instance of the BoxBlur class
represents a BoxBlur effect. The size and quality of the effect can be configured using these properties of
the class:
    • width
    • height
    • iterations
    • input

The width and height properties specify the horizontal and vertical size of the effect, respectively.
Imagine a box defined by the width and height centered on a pixel of the input. The color information of the
pixel is spread within the box during the blurring process. The values of these properties are between 5.0 and
255.0. The default values are 5.0. A value of less than or equal to 1.0 does not produce the blurring effect in
the corresponding direction.
The iterations property specifies the number of times the blurring effect is applied. A higher value
produces a better quality blur. Its value can be between 0 and 3. The default is 1. The value of 3 produces the
blur quality comparable to the Gaussian blur, discussed in the next section. The value of zero produces no
blur at all.

The BoxBlur class contains two constructors:
    • BoxBlur()
    • BoxBlur(double width, double height, int iterations)
The no-args constructor creates a BoxBlur object with the width and height of 5.0 pixels and
iterations of 1.

The following snippet of code creates four Text nodes and applies BoxBlur effects of various qualities.
Notice that the last Text node does not have any blur effect as the iterations property is set to zero.
*/
public class BoxBlurTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
        
		Text t1 = new Text("Box Blur");
        t1.setFont(Font.font(24));
        t1.setEffect(new BoxBlur(5, 10, 1));
        
        Text t2 = new Text("Box Blur");
        t2.setFont(Font.font(24));
        t2.setEffect(new BoxBlur(10, 5, 2));
        
        Text t3 = new Text("Box Blur");
        t3.setFont(Font.font(24));
        t3.setEffect(new BoxBlur(5, 5, 3));
        
        Text t4 = new Text("Box Blur");
        t4.setFont(Font.font(24));
        t4.setEffect(new BoxBlur(5, 5, 0)); // Zero iterations = No blurring

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
