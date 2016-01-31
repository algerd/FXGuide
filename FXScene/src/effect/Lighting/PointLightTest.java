
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

An instance of the Light.Point class represents a point light source. The class contains three properties
to specify the position of the light source in space: x, y, and z. The x, y, and z properties are the x, y, and z
coordinates of point where the point light is located in the space. If you set the z property to 0.0, the light
source will be in the plane of the scene showing as a very tiny bright point lighting a very small area. As the z
value increases, the light source moves away from the scene plane, lighting more area on the scene.
A negative value of z will move the light source behind the scene, leaving it with no light, and the scene will
look completely dark.

The Light.Point class contains two constructers:
    • Light.Point()
    • Light.Point(double x, double y, double z, Color color)
The no-args constructor places the point light at (0, 0, 0) and uses a Color.WHITE color for the light.
The other constructor lets you specify the location and the color of the light source.

The program shows how to use a Light.Point light. It displays a window with sliders
at the bottom to change the location of the point light source. As the point light source moves away from the
scene, some area on the scene will be brighter than the other area.
*/
public class PointLightTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a light source and position it in the space
		Light.Point light = new Light.Point(150.0, 50.0, 50.0, Color.WHITE);
		
		// Create a Lighting effect with the light source
		Lighting effect = new Lighting();
		effect.setLight(light);
		effect.setSurfaceScale(8.0);

		Text text = new Text();
		text.setText("Point");
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
		stage.setTitle("Configuring a Point Light");
		stage.show();
	}
	
	private GridPane getPointLightUI(Light.Point light) {
		Slider xSlider = LightingUtil.getSlider(-200.0, 200.0, 
			light.getX(), light.xProperty());
		Slider ySlider = LightingUtil.getSlider(-200.0, 200.0, 
			light.getY(), light.yProperty());
		Slider zSlider = LightingUtil.getSlider(-200.0, 200.0, 
			light.getZ(), light.zProperty());

		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(5);
		pane.addRow(0, new Label("x:"), xSlider);
		pane.addRow(1, new Label("y:"), ySlider);
		pane.addRow(2, new Label("z:"), zSlider);

		return pane;
	}
}
