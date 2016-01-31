
package shape;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

/*
Подробности смотреть в K.Sharan 774-780

The SVGPath class contains two properties.
    • content
    • fillRule

The content property defines the encoded string for the SVG path. The fillRule property specifies
the fill rule for the interior of the shape, which could be FillRule.NON_ZERO or FilleRule.EVEN_ODD. The
default value for the fillRule property is FillRule.NON_ZERO.

The content of a SVGPath is an encoded string following some rules:
    • The string consists of a series of commands.
    • Each command name is exactly one-letter long.
    • A command is followed by its parameters.
    • Parameter values for a command are separated by a comma or a space. For example,
    “M50, 0 L0, 50 L100, 50 Z” and “M50 0 L0 50 L100 50 Z” represent the same path.
    For readability, you will use a comma to separate two values.
    • You do not need to add spaces before or after the command character. For example,
    “M50 0 L0 50 L100 50 Z” can be rewritten as “M50 0L0 50L100 50Z”.
*/
public class SVGPathTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		
        SVGPath sp1 = new SVGPath();
        sp1.setContent("M50, 0 L0, 50 L100, 50 Z");
        sp1.setFill(Color.LIGHTGRAY);
        sp1.setStroke(Color.BLACK);
                
		HBox root = new HBox(sp1);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
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
		stage.setTitle("Using Different Stroke Types for Shapes");
		stage.show();
	}	
}
