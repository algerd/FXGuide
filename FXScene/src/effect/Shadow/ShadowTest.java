
package effect.Shadow;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
The Shadow effect creates a shadow with blurry edges of its input. Unlike DropShadow and InnerShadow,
it modifies the original input itself to convert it into a shadow. Typically, a Shadow effect is combined with the
original input to create a higher-level shadowing effect:
    • You can apply a Shadow effect with a light color to a node and superimpose it on a
    duplicate of the original node to create a glow effect.
    • You can create a Shadow effect with a dark color and place it behind the original node
    to create a DropShadow effect.

An instance of the Shadow class represents a Shadow effect. The size, color, and quality of the effect are
controlled by several properties of the Shadow class:
    • color
    • blurType
    • radius
    • width
    • height
    • input
These properties work the same way they work in the DropShadow. Please refer to DropShadowTest.java.

The Shadow class contains several constructors that let you specify the initial values for the properties:
    • Shadow()
    • Shadow(BlurType blurType, Color color, double radius)
    • Shadow(double radius, Color color)
*/
public class ShadowTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a Shadow of a Text node
		Text t1 = new Text("Shadow");
		t1.setFont(Font.font(36));
		t1.setEffect(new Shadow());

		// Create a Glow effect using a Shadow
		Text t2Original = new Text("Glow");
		t2Original.setFont(Font.font(36));
		Text t2 = new Text("Glow");
		t2.setFont(Font.font(36));
		Shadow s2 = new Shadow();
		s2.setColor(Color.YELLOW);
		t2.setEffect(s2);		
		StackPane glow = new StackPane(t2Original, t2);

		// Create a DropShadow effect using a Shadow 
		Text t3Original = new Text("DropShadow");
		t3Original.setFont(Font.font(36));
		Text t3 = new Text("DropShadow");
		t3.setFont(Font.font(36));
		Shadow s3 = new Shadow();
		t3.setEffect(s3);
		StackPane dropShadow = new StackPane(t3, t3Original);

		HBox root = new HBox(t1, glow, dropShadow);
		root.setSpacing(20);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Shadow Effect");
		stage.show();
	}
}
