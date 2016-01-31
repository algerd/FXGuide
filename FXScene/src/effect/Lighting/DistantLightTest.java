
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

An instance of the Light.Distant class represents a distant light source. The class contains two properties to
specify the direction of the light source:
    • azimuth
    • elevation

Both properties are of the double type. Their values are specified in degrees. Both properties are
used together to position the light source in a 3D space in a specific direction. By default, their values are
45 degrees. They do not have maximum and minimum values. Their values are computed using modulo 360.
For example, an azimuth value of 400 is effectively 40 (400 modulo 360 = 40).

The azimuth property specifies the direction angle in the XY plane. A positive value is measured
clockwise and a negative value is measured counterclockwise. A 0 value for the azimuth is located at the
3 o’clock position, 90 at 6 o’clock, 180 at 9 o’clock, 270 at 12 o’clock, and 360 at 3 o’clock. An azimuth of -90
will be located at 12 o’clock.

The elevation property specifies the direction angle of the light source in the YZ plane. The elevation
property values of 0 and 180 make the light source stay on the XY plane. An elevation of 90 puts the light
source in front of the scene and the entire scene is lighted. An elevation greater than 180 and less than 360
puts the light source behind the scene making it appear dark (without light).

The Light.Distant class contains two constructers:
    • Light.Distant()
    • Light.Distant(double azimuth, double elevation, Color color)
The no-args constructor uses 45.0 degrees for azimuth and elevation and Color.WHITE as the light
color. The other constructor lets you specify these properties.

The program shows how to use a Light.Distant light. It displays a window that lets you
set the direction for a distant light shining on a rectangle and a Text node.
*/
public class DistantLightTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a light source and position it in the space
		Light.Distant light = new Light.Distant(45.0, 60.0, Color.WHITE);	

		// Create a Lighting effect with the light source
		Lighting effect = new Lighting();
		effect.setLight(light);
		effect.setSurfaceScale(8.0);

		Text text = new Text();
		text.setText("Distant");
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
		GridPane lightDirectionController = this.getDistantLightUI(light);	
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
		stage.setTitle("Configuring a Distant Light");
		stage.show();
	}
	
	private GridPane getDistantLightUI(Light.Distant light) {
		Slider azimuthSlider = LightingUtil.getSlider(0.0, 360.0, 
				light.getAzimuth(), light.azimuthProperty());	    
		Slider elevationSlider = LightingUtil.getSlider(0.0, 360.0, 
				light.getElevation(), light.elevationProperty());

		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(5);
		pane.addRow(0, new Label("Azimuth:"), azimuthSlider);
		pane.addRow(1, new Label("Elevation:"), elevationSlider);

		return pane;
	}
}
