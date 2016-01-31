package effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
Sepia is a reddish-brown color. Sepia toning is performed on black-and-white photographic prints to give
them a warmer tone. An instance of the SepiaTone class represents a SepiaTone effect. It contains two
properties:
    • level
    • input
The level property specifies the intensity of the SepiaTone effect. It is a number between 0.0 and 1.0. Its
defaults value is 1.0. A level of 0.0 adds no sepia toning and a level of 1.0 adds the maximum sepia toning.

The SepiaTone class contains two constructors:
    • SepiaTone ()
    • SepiaTone (double level)
The no-args constructor creates a SepiaTone object with a default level of 1.0.
Notice that the higher the level value, the higher the sepia toning effect.
*/
public class SepiaToneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
        
        Text text = new Text("SepiaTone");
        text.setFill(Color.WHITE);
        text.setFont(Font.font(null, FontWeight.BOLD, 24));
        SepiaTone effect = new SepiaTone(0.50);      
        text.setEffect(effect);
        
        Rectangle rect = new Rectangle(150, 50, Color.BLACK);
        rect.setOpacity(0.50);
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
        effect.levelProperty().bind(slider.valueProperty());
        
        Text textLevel = new Text();
        textLevel.textProperty().bind(effect.levelProperty().asString());
        
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
		stage.setTitle("Using Sepia Tone Effect");
		stage.show();
	}
}
