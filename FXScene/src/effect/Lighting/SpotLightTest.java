
package effect.Lighting;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

/*
See Lighting_Light.txt

An instance of the Light.Spot class represents a spot light source. The class inherits from the Light.Point
class. The inherited properties (x, y, and z) from the Light.Point class specify the location of the light
source, which coincides with the vertex of the cone. The Light.Spot class contains four properties to specify
the position of the light source in space:
    • pointsAtX
    • pointsAtY
    • pointsAtZ
    • specularExponent

The pointsAtX, pointsAtY, and pointsAtY properties specify a point in the space to set the direction of
the light. A line starting from (x, y, z) and going toward (pointsAtX, pointsAtY, pointsAtZ) is the cone axis,
which is also the direction of the light. By default, they are set to 0.0. The specularExponent property defines
the focus of the light (the width of the cone), which ranges from 0.0 to 4.0. The default is 1.0. The higher the
value for the specularExponent, the narrower the cone is and the more focused light will be on the scene.

The Light.Spot class contains two constructers:
    • Light.Spot()
    • Light.Spot(double x, double y, double z, double specularExponent,
Color color)

The no-args constructor places the light at (0, 0, 0) and uses a Color.WHITE color for the light. Because
the default values for pointsAtX, pointsAtY, and pointsAtZ are 0.0, the light does not have a direction. The
other constructor lets you specify the location and the color of the light source. The cone axis will pass from
the specified (x, y, x) to (0, 0, 0).

The program shows how to use a Light.Spot light. It displays a window that lets you
configure the location, direction, and focus of the light using sliders at the bottom.
*/
public class SpotLightTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a light source and position it in the space
		Light.Spot light = new Light.Spot(150.0, 50.0, 50.0, 1.0, Color.WHITE);
		
		// Create a Lighting effect with the light source
		Lighting effect = new Lighting();
		effect.setLight(light);
		effect.setSurfaceScale(8.0);

		Text text = new Text();
		text.setText("Spot");
		text.setFill(Color.RED);
		text.setFont(Font.font("null", FontWeight.BOLD, 72));		
		text.setBoundsType(TextBoundsType.VISUAL);

		Rectangle rect = new Rectangle(300, 100);
		rect.setFill(Color.LIGHTGRAY);

		// Set the same Lighting effect to both Rectangle and Text nodes
		text.setEffect(effect);
		rect.setEffect(effect);

		StackPane sp = new StackPane(rect, text);
		BorderPane.setMargin(sp, new Insets(5));
		GridPane lightDirectionController = this.getPointLightUI(light);
		GridPane controllsrPane = LightingUtil.getPropertyControllers(effect);
		
		BorderPane root = new BorderPane();
		root.setCenter(sp);
		root.setRight(controllsrPane);
		root.setBottom(lightDirectionController);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Configuring a Spot Light");
		stage.show();
	}
	
	private GridPane getPointLightUI(Light.Spot light) {
		Slider xSlider = LightingUtil.getSlider(-200.0, 200.0, light.getX(), light.xProperty());
		Slider ySlider = LightingUtil.getSlider(-200.0, 200.0, light.getY(), light.yProperty());
		Slider zSlider = LightingUtil.getSlider(-200.0, 200.0, light.getZ(), light.zProperty());

		Slider pointsAtXSlider = LightingUtil.getSlider(-200.0, 200.0, light.getPointsAtX(), light.pointsAtXProperty());
		Slider pointsAtYSlider = LightingUtil.getSlider(-200.0, 200.0, light.getPointsAtY(), light.pointsAtYProperty());
		Slider pointsAtZSlider = LightingUtil.getSlider(-200.0, 200.0, light.getPointsAtZ(), light.pointsAtZProperty());

		Slider focusSlider = LightingUtil.getSlider(0.0, 4.0, light.getSpecularExponent(), light.specularExponentProperty());

		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(5);
		pane.addRow(0, new Label("x:"), xSlider);
		pane.addRow(1, new Label("y:"), ySlider);
		pane.addRow(2, new Label("z:"), zSlider);
		pane.addRow(3, new Label("PointsAtX:"), pointsAtXSlider);
		pane.addRow(4, new Label("PointsAtY:"), pointsAtYSlider);
		pane.addRow(5, new Label("PointsAtZ:"), pointsAtZSlider);
		pane.addRow(6, new Label("Focus:"), focusSlider);

		return pane;
	}
}
