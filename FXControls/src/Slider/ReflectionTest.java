
package Slider;

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
Простой пример использования слайдера
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
