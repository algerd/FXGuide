
package Slider;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/*
The labelFormatter property is an object property of the StringConverter<Double> type. By default,
it is null and the slider uses a default StringConverter that displays the numeric values for the major ticks.
The values for the major ticks are passed to the toString() method and the method is supposed to return a
custom label for that value.
*/
public class SliderStringConverter extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}
    
	@Override
	public void start(Stage stage) {
		
        Slider scoreSlider = new Slider();
        scoreSlider.setShowTickLabels(true);
        scoreSlider.setShowTickMarks(true);
        scoreSlider.setMajorTickUnit(10);
        scoreSlider.setMinorTickCount(3);
        scoreSlider.setBlockIncrement(20);
        scoreSlider.setSnapToTicks(true);
        
        // Set a custom major tick formatter
        scoreSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double value) {
                String label = "";
                if (value == 40) {
                    label = "F";
                } else if (value == 70) {
                    label = "C";
                } else if (value == 80) {
                    label = "B";
                } else if (value == 90) {
                    label = "A";
                }
            return label;
            }
            @Override
            public Double fromString(String string) {
                return null; // Not used
            }
        });
        
        Pane pane = new Pane(scoreSlider);        
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Using Slider Controls");
		stage.show();		
	}	
}
