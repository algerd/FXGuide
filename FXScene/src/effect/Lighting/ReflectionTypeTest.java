
package effect.Lighting;

import javafx.application.Application;
import javafx.scene.Scene;
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
When light falls on an opaque surface, part of light is absorbed, part is transmitted, and some is reflected.
A 3D look is achieved by showing part of the surface brighter and part shadowy. You see the reflected light
from the surface. The 3D look varies depending on the light source and the way the node surface reflects
the light. The structure of the surface at the microscopic level defines the details of the reflection, such as
the intensity and directions. Among several reflection types, two types are worth mentioning at this point:
diffuse reflection and specular reflection.

In a diffuse reflection, the surface reflects an incident ray of light at many angles. That is, a diffuse
reflection scatters a ray of light by reflecting it in all directions. A perfect diffuse reflection reflects light
equally in all directions. The surface using a diffuse reflection appears to be equally bright from all
directions. This does not mean that the entire diffuse surface is visible. The visibility of an area on a diffuse
surface depends on the direction of the light and the orientation of the surface. The brightness of the surface
depends on the surface type itself and the intensity of the light. Typically, a rough surface, for example,
clothing, paper, or plastered walls, reflects light using a diffuse reflection. Surfaces may appear smooth to
the eyes, for example, paper or clothing, but they are rough at the microscopic level, and they reflect light
diffusively.

Three properties of the Lighting class are used to control the size and intensity of the reflection:
    • diffuseConstant
    • specularConstant
    • specularExponent

The properties are of the double type. The diffuseConstant is used for diffuse reflection. The
specularConstant and specularExponent are used for specular reflection. The diffuseConstant property
specifies a multiplier for the diffuse reflection intensity. Its value ranges from 0.0 to 2.0 with a default of 1.0.
A higher value makes the surface brighter. The specularConstant property specifies the fraction of the light
to which the specular reflection applies. Its value ranges from 0.0 to 2.0 with a default value of 0.30. A higher
value means a bigger-sized specular highlight. The specularExponent specifies the shininess of the surface.
A higher value means a more intense reflection and the surface looks shinier. The specularExponent ranges
from 0.0 to 40.0 with a default value of 20.0.

In a specular reflection, the surface reflects a ray of light in exactly one direction. That is, there is a single
reflected ray for one incident ray. A smooth surface at the microscopic level, for example, mirrors or polished
marbles, produces a specular reflection. Some smooth surfaces may not be 100% smooth at the microscopic
level, and they may reflect part of the light diffusively as well. Specular reflection produces a brighter surface
compared to diffuse reflection. Figure 20-25 depicts the ways light is reflected in diffuse and specular
reflections.

The program uses the utility class to bind the properties of a Lighting effect to UI controls.
*/
public class ReflectionTypeTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text text = new Text();
		text.setText("Chatar");
		text.setFill(Color.RED);
		text.setFont(Font.font("null", FontWeight.BOLD, 72));
		text.setBoundsType(TextBoundsType.VISUAL);

		Rectangle rect = new Rectangle(300, 100);
		rect.setFill(Color.LIGHTGRAY);

		// Set the same Lighting effect to both Rectangle and Text nodes
		Lighting effect = new Lighting();
		text.setEffect(effect);
		rect.setEffect(effect);

		StackPane sp = new StackPane(rect, text);

		GridPane controllsrPane = LightingUtil.getPropertyControllers(effect);
		BorderPane root = new BorderPane();
		root.setCenter(sp);
		root.setRight(controllsrPane);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Controlling Reflection Details");
		stage.show();
	}
}
