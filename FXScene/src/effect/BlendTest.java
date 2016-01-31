
package effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
Blending combines two pixels at the same location from two inputs to produce one composite pixel in the
output. The Blend effect takes two input effects and blends the overlapping pixels of the inputs to produce
an output. The blending of two inputs is controlled by a blending mode.
An instance of the Blend class represents the Blend effect. The class contains properties to specify the:
    • topInput
    • bottomInput
    • mode
    • opacity

The topInput and bottomInput properties specify the top and bottom effects, respectively. They are
null by default. The mode property specifies the blending mode, which is one of the constants defined in
the BlendMode enum. The default is BlendMode.SRC_OVER. JavaFX provides 17 predefined blending modes.
All blending modes use the SRC_OVER rules to blend the alpha components. The opacity property specifies the opacity to
be applied to the top input before the blending is applied. The opacity is 1.0 by default.

The Constants in the BlendMode Enum with Their Descriptions:
ADD         It adds the color (red, green, and blue) and alpha values for the pixels in the
            top and bottom inputs to get the new component value.
MULTIPLY    It multiplies the color components from two inputs.
DIFFERENCE  It subtracts the darker color components from any inputs from the lighter
            color components of the other input to get the resulting color components.
RED         It replaces the red component of the bottom input with the red component
            of the top input, leaving all other color components unaffected.
BLUE        It replaces the blue component of the bottom input with the blue
            component of the top input, leaving all other color components unaffected.
GREEN       It replaces the green component of the bottom input with the green
            component of the top input, leaving all other color components unaffected.
EXCLUSION   It multiplies the color components of the two inputs and doubles the
            result. The value thus obtained is subtracted from the sum of the color
            components of the bottom input to get the resulting color component.
COLOR_BURN  It divides the inverse of the bottom input color components by the top
            input color components and inverts the result.
COLOR_DODGE It divides the bottom input color components by the inverse of the top
            input color.
LIGHTEN     It uses the lighter of the color components from the two inputs.
DARKEN      It uses the darker of the color components from the two inputs.
SCREEN      It inverts the color components from both inputs, multiplies them, and
            inverts the result.
OVERLAY     Depending on the bottom input color, it multiplies or screens the input
            color components.
HARD_LIGHT  Depending on the top input color, it multiplies or screens the input color
            components.
SOFT_LIGHT  Depending on the top input color, it darkens or lightens the input color
            components.
SRC_ATOP    It keeps the bottom input for the nonoverlapping area and the top input for
            the overlapping area.
SRC_OVER    The top input is drawn over the bottom input. Therefore, the overlapping
            area shows the top input.

The program creates two ColorInput effects of the same size. Their x and y properties
are set in such a way that they overlap. These two effects are used as top and bottom inputs to the Blend
effect. A combo box and a slider are provided to select the blending mode and the opacity of the top input.
Run the program and try selecting different blending modes to see the Blend effect in action.
*/
public class BlendTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ColorInput topInput = new ColorInput(0, 0, 100, 50, Color.LIGHTGREEN);
		ColorInput bottomInput = new ColorInput(50, 25, 100, 50, Color.PURPLE);

		// Create the Blend effect 
		Blend effect = new Blend();
		effect.setTopInput(topInput);
		effect.setBottomInput(bottomInput);

		Rectangle rect = new Rectangle(150, 75);
		rect.setEffect(effect);

		GridPane controller = this.getController(effect);

		HBox root = new HBox(rect, controller);
		root.setSpacing(30);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" +
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the Blend Effect");
		stage.show();
	}

	private GridPane getController(Blend effect) {
		ComboBox<BlendMode> blendModeList = new ComboBox<>();
		blendModeList.setValue(effect.getMode());
		blendModeList.getItems().addAll(BlendMode.values());
		effect.modeProperty().bind(blendModeList.valueProperty());

		Slider opacitySlider = new Slider (0, 1.0, 1.0);
		opacitySlider.setMajorTickUnit(0.10);
		opacitySlider.setShowTickMarks(true);
		opacitySlider.setShowTickLabels(true);
		effect.opacityProperty().bind(opacitySlider.valueProperty());

		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(10);
		pane.addRow(0, new Label("Blend Mode:"), blendModeList);
		pane.addRow(1, new Label("Opacity:"), opacitySlider);

		return pane;
	}
}
