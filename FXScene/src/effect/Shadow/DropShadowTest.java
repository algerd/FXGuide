
package effect.Shadow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
The DropShadow effect draws a shadow (a blurred image) behind the input, so the input seems to be raised. It
gives the input a 3D look. The input can be a node or an effect in a chain of effects.
An instance of the DropShadow class represents a DropShadow effect. The size, location, color, and quality
of the effect are controlled by several properties of the DropShadow class:
    • offsetX
    • offsetY
    • color
    • blurType
    • radius
    • spread
    • width
    • height
    • input

The DropShadow class contains several constructors that let you specify the initial values for the
properties:
    • DropShadow()
    • DropShadow(BlurType blurType, Color color, double radius, double spread, double offsetX, double offsetY)
    • DropShadow(double radius, Color color)
    • DropShadow(double radius, double offsetX, double offsetY, Color color)

The offsetX and offsetY properties control the position of the shadow in pixels relative to the input.
By default, their values are zero. The positive values of offsetX and offsetY move the shadow in the positive
x axis and y axis directions, respectively. The negative values move the shadow in the reverse directions.

The color property specifies the color of the shadow. By default, it is Color.BLACK.

The blurring in the shadow can be achieved using different algorithms. The blurType property
specifies the type of blurring algorithm for the shadow. Its value is one of the following constants of the
BlurType enum:
    • ONE_PASS_BOX
    • TWO_PASS_BOX
    • THREE_PASS_BOX
    • GAUSSIAN

The ONE_PASS_BOX uses a single pass of the box filter to blur the shadow. The TWO_PASS_BOX uses two
passes of the box filter to blur the shadow. The THREE_PASS_BOX uses three passes of the box filter to blur
the shadow. The GAUSSIAN uses a Gaussian blur kernel to blur the shadow. The blur quality of the shadow
is the least in ONE_PASS_BOX and the best in GAUSSIAN. The default is THREE_PASS_BOX, which is very close to
GAUSSIAN in quality.

The radius property specifies the distance the shadow is spread on each side of the source pixel. If the
radius is zero, the shadow has sharp edges. Its value can be between 0 and 127. The default value is 10. The
blurring outside the shadow region is achieved by blending the shadow color and the background color. The
blur color fades out over the radius distance from the edges.

The spread property specifies the portion of the radius, which has the same color as the shadow.
The color for the remaining portion of the radius is determined by the blur algorithm. Its value is
between 0.0 and 1.0. The default is 0.0.
Suppose you have a DropShadow with a radius 10.0 and a spread value of 0.60 and the shadow color is
black. In this case, the blur color will be black up to 6px around the source pixel. It will start fading out from
the seventh pixel to the tenth pixel. If you specify the spread value as 1.0, there would be no blurring of the
shadow.

The width and height properties specify the horizontal and vertical distances, respectively, from the
source pixel up to where the shadow color is spread. Their values are between 0 and 255. Setting their values
is equivalent to setting the radius property, so they are equal to (2 * radius + 1). Their default value is 21.0.
When you change the radius, the width and height properties are adjusted using the formula if they are
not bound. However, setting the width and height changes the radius value, so the average of the width
and height is equal to (2 * radius + 1).
*/
public class DropShadowTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(100, 50, Color.GRAY);	
		DropShadow dsEffect = new DropShadow();
		rect.setEffect(dsEffect);

		GridPane controllsrPane = this.getControllerPane(dsEffect);
		BorderPane root = new BorderPane();
		root.setCenter(rect);
		root.setBottom(controllsrPane);
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
		stage.setTitle("Experimenting with DropShadow Effect");
		stage.show();
	}
	
	private GridPane getControllerPane(final DropShadow dsEffect) {
		Slider offsetXSlider = new Slider(-200, 200, 0);
		dsEffect.offsetXProperty().bind(offsetXSlider.valueProperty());

		Slider offsetYSlider = new Slider(-200, 200, 0);
		dsEffect.offsetYProperty().bind(offsetYSlider.valueProperty());

		Slider radiusSlider = new Slider(0, 127, 10);
		dsEffect.radiusProperty().bind(radiusSlider.valueProperty());

		Slider spreadSlider = new Slider(0.0, 1.0, 0);
		dsEffect.spreadProperty().bind(spreadSlider.valueProperty());

		ColorPicker colorPicker = new ColorPicker(Color.BLACK);
		dsEffect.colorProperty().bind(colorPicker.valueProperty());

		ComboBox<BlurType> blurTypeList = new ComboBox<>();
		blurTypeList.setValue(dsEffect.getBlurType());
		blurTypeList.getItems().addAll(
            BlurType.ONE_PASS_BOX, 
            BlurType.TWO_PASS_BOX, 
            BlurType.THREE_PASS_BOX, 
            BlurType.GAUSSIAN
        );
		dsEffect.blurTypeProperty().bind(blurTypeList.valueProperty());

		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(10);
		pane.addRow(0, new Label("OffsetX:"), offsetXSlider);
		pane.addRow(1, new Label("OffsetY:"), offsetYSlider);
		pane.addRow(2, new Label("Radius:"), radiusSlider, new Label("Spread:"), spreadSlider);
		pane.addRow(3, new Label("Color:"), colorPicker, new Label("Blur Type:"), blurTypeList);

		return pane;
	}
}
