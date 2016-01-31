
package shape.Text;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
An instance of the Font class represents a font. The Font class provides two constructors:
    • Font(double size)
    • Font(String name, double size)

The first constructor creates a Font object of the specified size that belongs to the “System” font family.
The second one creates a Font object of the specified full name of the font and the specified size. The size of
the font is specified in points. 

The getFamily(), getName(), and getSize() methods of the Font class return the family name, full name,
and size of the font, respectively.

If the full font name is not found, the default “System” font will be created. It is hard to remember or
know the full names for all variants of a font. To address this, the Font class provides factory methods to
create fonts using a font family name, styles, and size:
    • font(double size)
    • font(String family)
    • font(String family, double size)
    • font(String family, FontPosture posture, double size)
    • font(String family, FontWeight weight, double size)
    • font(String family, FontWeight weight, FontPosture posture, double size)

The font()methods let you specify the family name, font weight, font posture, and font size. If only
the family name is provided, the default font size is used, which depends on the platform and the desktop
setting of the user.

The font weight specifies how bold the font is. Its value is one of the constants of the FontWeight
enum: THIN, EXTRA_LIGHT, LIGHT, NORMAL, MEDIUM, SEMI_BOLD, BOLD, EXTRA_BOLD, BLACK. The constant THIN
represents the thinnest font and the constant BLOCK the thickest font.

The posture of a font specifies whether it is italicized. It is represented by one of the two constants of the
FontPosture enum: REGULAR and ITALIC.

Tip: Use the getDefault() static method of the Font class to get the system default font.

The program creates Text nodes and sets their font property. The first Text node uses
the default font. The text for the Text nodes is the String returned from
the toString() method of their Font objects.
*/
public class TextFontTest extends Application {	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text t1 = new Text();
		t1.setText(t1.getFont().toString());

		Text t2 = new Text();
		t2.setFont(Font.font("Arial", 12));
		t2.setText(t2.getFont().toString());

		Text t3 = new Text();
		t3.setFont(Font.font("Arial", FontWeight.BLACK, 12));
		t3.setText(t2.getFont().toString());

		Text t4 = new Text();
		t4.setFont(Font.font("Arial", FontWeight.THIN, FontPosture.ITALIC, 12));
		t4.setText(t2.getFont().toString());

		VBox root = new VBox(t1, t2, t3, t4);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Setting Fonts for Text Nodes");
		stage.show();
	}
}
