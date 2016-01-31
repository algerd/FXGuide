
package effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
The Reflection effect adds a reflection of the input below the input. An instance of the Reflection class
represents a reflection effect. The position, size, and opacity of the reflection are controlled by various
properties:
    • topOffset
    • fraction
    • topOpacity
    • bottomOpacity
    • input

The topOffset specifies the distance in pixels between the bottom of the input and the top of the
reflection. By default, it is 0.0. The fraction property specifies the faction of the input height that is visible
in the reflection. It is measured from the bottom. Its value can be between 0.0 and 1.0. A value of 0.0 means
no reflection. A value of 1.0 means the entire input is visible in the reflection. A value of 0.25 means 25%
of the input from the bottom is visible in the reflection. The default value is 0.75. The topOpacity and
bottomOpacity properties specify the opacity of the reflection at its top and bottom extremes. Their values
can be between 0.0 and 1.0. The default value is 0.50 for the topOpacity and 0.0 for the bottomOpacity.

The Reflection class contains two constructors:
    • Reflection()
    • Reflection(double topOffset, double fraction, double topOpacity, double bottomOpacity)
*/
public class ReflectionTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
        
        Text text = new Text("Chatar");
        text.setFont(Font.font(null, FontWeight.BOLD, 24));
        Reflection reflect = new Reflection(0.0, 1.0, 1.0, 1.0);
        text.setEffect(reflect);       
        StackPane sp = new StackPane(text);
        sp.setPrefHeight(100);
             
		GridPane controllsrPane = getControllerPane(reflect);
		VBox root = new VBox(sp, controllsrPane);
		root.setStyle(
            "-fx-spacing: 30;" +    
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;"
        );
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Experimenting Reflection Effect");
		stage.show();
	}
	
	private GridPane getControllerPane(final Reflection effect) {
		Slider topOffsetSlider = new Slider(0.0, 1.0, 0);
		effect.topOffsetProperty().bind(topOffsetSlider.valueProperty());

		Slider fractionSlider = new Slider(0.0, 1.0, 1.0);
		effect.fractionProperty().bind(fractionSlider.valueProperty());

		Slider topOpacitySlider = new Slider(0.0, 1.0, 1.0);
		effect.topOpacityProperty().bind(topOpacitySlider.valueProperty());

		Slider bottomOpacitySlider = new Slider(0.0, 1.0, 1.0);
		effect.bottomOpacityProperty().bind(bottomOpacitySlider.valueProperty());

		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(10);
		pane.addRow(0, new Label("topOffset:"), topOffsetSlider);
		pane.addRow(1, new Label("fraction:"), fractionSlider);
		pane.addRow(2, new Label("topOpacity:"), topOpacitySlider);
		pane.addRow(3, new Label("bottomOpacity:"), bottomOpacitySlider);

		return pane;
	}
}
