
package effect;

import java.net.URL;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
The ColorAdjust effect adjusts the hue, saturation, brightness, and contrast of pixels by the specified delta
amount. Typically, the effect is used on an ImageView node to adjust the color of an image.
An instance of the ColorAdjust class represents the ColorAdjust effect. The class contains five
properties that define the location, size, and the paint for the rectangular region:
    • hue
    • saturation
    • brightness
    • contrast
    • input

The hue, saturation, brightness, and contrast properties specify the delta amount by which these
components are adjusted for all pixels. They range from -1.0 to 1.0. Their default values are 0.0.

The program shows how to use the ColorAdjust effect on an image. It displays an image
and four sliders to change the properties of the ColorAdjust effect. Adjust their values using the sliders
to see the effects. If the program does not find the image, it prints a message and displays a Text node
overlaying a rectangle in a StackPane and the effect is applied to the StackPane.
*/
public class ColorAdjustTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ColorAdjust effect = new ColorAdjust();

		Node node = getImageNode();
		node.setEffect(effect);

		GridPane controller = getController(effect);

		BorderPane root = new BorderPane();
		root.setCenter(node);
		root.setBottom(controller);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the ColorAdjust Effect");
		stage.show();
	}

	private Node getImageNode() {
		Node node = null;
		String path = "\\Effect\\randomness.jpg";
		URL url = getClass().getClassLoader().getResource(path);

		if (url != null) {
			node = new ImageView(url.toExternalForm());
		} else {
			System.out.println("Missing image file " + path);
			node = new StackPane(new Rectangle(100, 50, Color.LIGHTGRAY), new Text("Color Adjust"));
		}
		return node;
	}

	private GridPane getController(ColorAdjust effect) {
		Slider hueSlider = new Slider(-1.0, 1.0, 0.0);
		effect.hueProperty().bind(hueSlider.valueProperty());

		Slider saturationSlider = new Slider(-1.0, 1.0, 0.0);
		effect.saturationProperty().bind(saturationSlider.valueProperty());

		Slider brightnessSlider = new Slider(-1.0, 1.0, 0.0);
		effect.brightnessProperty().bind(brightnessSlider.valueProperty());

		Slider contrastSlider = new Slider(-1.0, 1.0, 0.0);
		effect.contrastProperty().bind(contrastSlider.valueProperty());

		Slider[] sliders = new Slider[] {hueSlider, saturationSlider, 
		                                 brightnessSlider, contrastSlider};
		for (Slider s : sliders) {
			s.setPrefWidth(300);
			s.setMajorTickUnit(0.10);
			s.setShowTickMarks(true);
			s.setShowTickLabels(true);
		}

		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(10);
		pane.addRow(0, new Label("Hue:"), hueSlider);
		pane.addRow(1, new Label("Saturation:"), saturationSlider);
		pane.addRow(2, new Label("Brightness:"), brightnessSlider);
		pane.addRow(3, new Label("Contrast:"), contrastSlider);

		return pane;
	}
}
