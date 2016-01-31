
package ColorPicker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
ColorPicker is a combo box–style control that is especially designed for users to select a color from a
standard color palette or create a color using a built-in color dialog. The ColorPicker class inherits from
the ComboBoxBase<Color> class. Therefore, all properties declared in the ComboBoxBase class apply to the
ColorPicker control as well. I have discussed several of these properties earlier in the “Understanding the
ComboBox Control” section. If you want to know more about those properties, please refer to that section.
For example, the editable, onAction, showing, and value properties work the same way in a ColorPicker as
they do in a combo box. A ColorPicker has three parts:
    • ColorPicker control
    • Color palette
    • Custom color dialog

The ColorPicker control supports three looks: combo-box look, button look, and split-button look.
Combo-box look is the default look.The ColorPicker class contains two string contents that are the CSS style-class name 
for the button and split-button looks. The constants are:
    • STYLE_CLASS_BUTTON
    • STYLE_CLASS_SPLIT_BUTTON

Tip: It is possible to add both STYLE_CLASS_BUTTON and STYLE_CLASS_SPLIT_BUTTON as style classes for
a ColorPicker. In such a case, the STYLE_CLASS_BUTTON is used.

*/
public class ColorPickerTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ColorPicker bgColor = new ColorPicker(Color.RED);
        // Change the look to button
        //bgColor.getStyleClass().add(ColorPicker.STYLE_CLASS_BUTTON);
        // Change the look to split-button
        //bgColor.getStyleClass().add(ColorPicker.STYLE_CLASS_SPLIT_BUTTON);

		// A Rectangle to show the selected color from the color picker
		Rectangle rect = new Rectangle(0, 0, 100, 50);
		rect.setFill(bgColor.getValue());
		rect.setStyle("-fx-stroke-width: 2; -fx-stroke: black;");
		
		// Add an ActionEvent handler to the ColorPicker, so we change
		// the fill color for the rectangle when we pick a new color
		bgColor.setOnAction(e -> rect.setFill(bgColor.getValue()));
	
		HBox root = new HBox(new Label("Color:"), bgColor, rect);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);      
		stage.setScene(scene);      
		stage.setTitle("Using ColorPicker Controls");
		stage.show();
	}
}
