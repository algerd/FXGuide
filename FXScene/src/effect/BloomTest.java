
package effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
The Bloom effect adds a glow to the pixels of its input that have a luminosity greater than or equal to a
specified limit. Note that not all pixels in a Bloom effect are made to glow.
An instance of the Bloom class represents a Bloom effect. It contains two properties:
    • threshold
    • input

The threshold property is a number between 0.0 and 1.0. Its default value is 0.30. All pixels in the
input having a luminosity greater than or equal to the threshold property are made to glow. The brightness
of a pixel is determined by its luminosity. A pixel with a luminosity of 0.0 is not bright at all. A pixel with a
luminosity of 1.0 is 100% bright. By default, all pixels having a luminosity greater than or equal to 0.3 are
made to glow. A threshold of 0.0 makes all of the pixels glow. A threshold of 1.0 makes almost no pixels glow.

The Bloom class contains two constructors:
    • Bloom()
    • Bloom(double threshold)
The no-args constructor creates a Bloom object with a default threshold of 0.30. The other constructor
lets you specify the threshold value, as shown in the following code:

*/
public class BloomTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
     
        Text text = new Text("Bloom");
        text.setFill(Color.YELLOW);
        text.setFont(Font.font(null, FontWeight.BOLD, 24));
        Bloom bloom = new Bloom();
        text.setEffect(bloom);
        
        Rectangle rect = new Rectangle(200, 50, Color.GREEN);
        StackPane pane = new StackPane(rect, text);
    
        // Slider
        Slider slider = new Slider();
        slider.setMin(0.0);
        slider.setMax(1.0);
        slider.setValue(0.3);  
        slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
        slider.setMajorTickUnit(0.25);
        
        // Binding Text with Slider
        bloom.thresholdProperty().bind(slider.valueProperty());
        
        Text textBloom = new Text();
        textBloom.textProperty().bind(bloom.thresholdProperty().asString());
        
		VBox root = new VBox(pane, slider, textBloom);
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
		stage.setTitle("Using Bloom Effect");
		stage.show();
	}
}
