
package Slider;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SliderBindingTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
     
        Text text = new Text("Glow");
        text.setFill(Color.YELLOW);
        text.setFont(Font.font(null, FontWeight.BOLD, 24));
        Glow glow = new Glow();
        text.setEffect(glow);
        
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
        glow.levelProperty().bind(slider.valueProperty());
        
        Text textLevel = new Text();
        textLevel.textProperty().bind(glow.levelProperty().asString());
        
		VBox root = new VBox(pane, slider, textLevel);
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
