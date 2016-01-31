
package Slider;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/*
A Slider lets the user select a numeric value from a numeric range graphically by sliding a thumb (or knob)
along a track. A slider can be horizontal or vertical.

A slider has minimum and maximum values that determine the range of the valid selectable values. The
thumb of the slider indicates its current value. You can slide the thumb along the track to change the current
value. Major and minor tick marks shows the location of values along the track. You can also show tick
labels. Custom labels are also supported.

The default orientation is horizontal.
orientation property specifies the orientation of the slider: 
vs.setOrientation(Orientation.VERTICAL);

Cjnstructors:
Slider s1 = new Slider();
Slider s2 = new Slider(min, max, value);

The following properties are related to the current value and the range of values:
    • min
    • max
    • value
    • valueChanging
    • snapToTicks
The min, max, and value properties are double properties, and they represent the minimum, maximum,
and current values, respectively, of the slider. The current value of the slider can be changed by dragging the
thumb on the track or using the setValue() method. The following snippet of code creates a slider and sets
its min, max, and value properties to 0, 10, and 3, respectively:
    Slider scoreSlider = new Slider();
    scoreSlider.setMin(0.0);
    scoreSlider.setMax(10.0);
    scoreSlider.setValue(3.0);

Typically, you want to perform an action when the value property of the slider changes. You will need to
add a ChangeListener to the value property.

The valueChanging property is a boolean property. It is set to true when the user presses the thumb
and is set to false when the thumb is released. As the user drags the thumb, the value keeps changing and
the valueChanging property is true. This property helps you avoid repeating an action if you want to take the
action only once when the value changes.

The snapToTicks property is a boolean property, which is false by default. It specifies whether the value
property of the slider is always aligned with the tick marks. If it is set to false, the value could be anywhere in
the min to max range.

Be careful in using the valueChanging property inside a ChangeListener. The listener may be called
several times for what the user sees as one change. Expecting that the ChangeListener will be notified when
the valueChanging property changes from true to false, you wrap the main logic for the action inside an if
statement:
    if (scoreSlider.isValueChanging()) {
    // Do not perform any action as the value changes
    } else {
    // Perform the action as the value has been changed
    }

The logic works fine when the snapToTicks property is set to true. The ChangeListener for the value
property is notified when the valueChanging property changes from true to false only when the snapToTicks
property is set to true. Therefore, do not write the above logic unless you have set the snapToTicks property
to true as well.

The following properties of the Slider class specify the tick spacing:
    • majorTickUnit
    • minorTickCount
    • blockIncrement
The majorTickUnit property is a double property. It specifies the unit of distance between two major
ticks. Suppose the min property is set to 0 and the majorTickUnit to 10. The slider will have major ticks
at 0, 10, 20, 30, and so forth. An out-of-range value for this property disables the major ticks. The default
value for the property is 25.

The minorTickCount property is an integer property. It specifies the number of minor ticks between two
major ticks. The default value for the property is 3.

You can change the thumb position by using keys, for example, using left and right arrow keys in a
horizontal slider and up and down arrow keys in a vertical slider. The blockIncrement property is a double
property. It specifies the amount by which the current value of the slider is adjusted when the thumb is
operating by using keys. The default value for the property is 10.
The following properties specify whether the tick marks and tick labels are shown; by default, they are
set to false:
    • showTickMarks
    • showTickLabels

The labelFormatter property is an object property of the StringConverter<Double> type. By default,
it is null and the slider uses a default StringConverter that displays the numeric values for the major ticks.
The values for the major ticks are passed to the toString() method and the method is supposed to return a
custom label for that value. See SliderStringConverter.java. 

The program shows how to use Slider controls. It adds a Rectangle, a Label, and three
Slider controls to a window. It adds a ChangeListener to the Sliders. Sliders represent red, green, and
blue components of a color. When you change the value for a slider, the new color is computed and set as the
fill color for the rectangle.

*/
public class SliderTest extends Application {
    
	Rectangle rect = new Rectangle(0, 0, 200, 50);
	Slider redSlider = getSlider();
	Slider greenSlider = getSlider();
	Slider blueSlider = getSlider();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Add a ChangeListener to all sliders
		redSlider.valueProperty().addListener(this::changed);
		greenSlider.valueProperty().addListener(this::changed);
		blueSlider.valueProperty().addListener(this::changed);

		GridPane root = new GridPane();
		root.setVgap(10);
		root.add(rect, 0, 0, 2, 1);
		root.add(new Label("Use sliders to change the fill color"), 0, 1, 2, 1);
		root.addRow(2, new Label("Red:"), redSlider);
		root.addRow(3, new Label("Green:"), greenSlider);
		root.addRow(4, new Label("Blue:"), blueSlider);

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
		stage.setTitle("Using Slider Controls");
		stage.show();
		
		// Adjust the fill color of the rectangle
		changeColor();
	}

	public Slider getSlider() {
		Slider slider = new Slider(0, 255, 125);		
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(85);
		slider.setMinorTickCount(10);
		slider.setBlockIncrement(20);
		slider.setSnapToTicks(true);
        
        // Set a custom major tick formatter
        slider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double value) {
                String label = "";
                if (value == 10) {
                    label = "F";
                } else if (value == 20) {
                    label = "C";
                } else if (value == 30) {
                    label = "B";
                } else if (value == 40) {
                    label = "A";
                }
                return label;
            }
            @Override
            public Double fromString(String string) {
                return null; // Not used
            }
        });
        
		return slider;
	}

	// A change listener to track the change in color
	public void changed(ObservableValue<? extends Number> prop, Number oldValue, Number newValue) {
		changeColor();
	}
	
	public void changeColor() {
		int r = (int)redSlider.getValue();
		int g = (int)greenSlider.getValue();
		int b = (int)blueSlider.getValue();
		Color fillColor = Color.rgb(r, g, b);
		rect.setFill(fillColor);
	}
}
