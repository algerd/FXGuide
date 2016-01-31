
package effect.Lighting;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

/*
The Lighting effect, as the name suggests, simulates a light source shining on a specified node in a scene
to give the node a 3D look. A Lighting effect uses a light source, which is an instance of the Light class, to
produce the effect. Different types of configurable lights are available. If you do not specify a light source, the
effect uses a default light source.

An instance of the Lighting class represents a Lighting effect. The class contains two constructors:
    • Lighting()
    • Lighting(Light light)

Lighting class contains several properties to configure the effect:
    • contentInput
    • surfaceScale
    • bumpInput
    • diffuseConstant
    • specularConstant
    • specularExponent
    • light
If you use a chain of effects, the contentInput property specifies the input effect to the Lighting effect.

The surfaceScale and bumpInput properties are used to provide texture to a 2D surface to make it look like
a 3D surface. Pixels, based on their opacity, look high or low to give the surface a texture. Transparent pixels
appear low and opaque pixels appear raised.

The surfaceScale property lets you control the surface roughness. Its value ranges from 0.0 to 10.0. The
default is 1.5. For a higher surfaceScale, the surface appears rougher, giving it a more 3D look.

You can pass an Effect as an input to the Lighting effect using its bumpInput property. The opacity
of the pixels in the bumpInput is used to obtain the height of the pixels of the lighted surface, and then the
surfaceScale is applied to increase the roughness. If bumpInput is null, the opacity of the pixels from the
node on which the effect is applied is used to generate the roughness of the surface. By default, a Shadow
effect with a radius of 10 is used as the bumpInput. You can use an ImageInput, a blur effect, or any other
effect as the bumpInput for a Lighting effect.

The program in Listing 20-11 displays a Text node with a Lighting effect. The bumpInput is set to null.
It provides a check box to set a GaussianBlur effect as the bumpInput and a slider to adjust the surfaceScale
value.
*/
public class SurfaceTexture extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text text = new Text();
		text.setText("Texture");
		text.setFill(Color.RED);
		text.setFont(Font.font(null, FontWeight.BOLD, 72));
		text.setBoundsType(TextBoundsType.VISUAL);

		Lighting effect = new Lighting();
		effect.setBumpInput(null); // Remove the default bumpInput
		text.setEffect(effect);

		// Let the user choose to use a bumpInput
		CheckBox bumpCbx = new CheckBox("Use a GaussianBlur Bump Input?");
		bumpCbx.selectedProperty().addListener((prop, oldValue,newValue) -> {
			if (newValue) {
				effect.setBumpInput(new GaussianBlur(20));
			}
			else {
				effect.setBumpInput(null);
			}
		});

		// Let the user select a surfaceScale
		Slider scaleSlider = new Slider(0.0, 10.0, 1.5);
		effect.surfaceScaleProperty().bind(scaleSlider.valueProperty());
		scaleSlider.setShowTickLabels(true);
		scaleSlider.setMajorTickUnit(2.0);
		scaleSlider.setShowTickMarks(true);

		VBox root = new VBox(10, text, bumpCbx, scaleSlider);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Surface Scale and Bump Input");
		stage.show();
	}
}
