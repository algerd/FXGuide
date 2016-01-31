
package effect.Shadow;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
The InnerShadow effect works very similar to the DropShadow effect. It draws a shadow (a blurred image)
of an input inside the edges of the input, so the input seems to have depth or a 3D look. The input can be a
node or an effect in a chain of effects.
An instance of the InnerShadow class represents an InnerShadow effect. The size, location, color, and
quality of the effect are controlled by several properties of the InnerShadow class:
    • offsetX
    • offsetY
    • color
    • blurType
    • radius
    • choke
    • width
    • height
    • input

The number of properties of the InnerShadow class is equal to that for the DropShadow class. The
spread property in the DropShadow class is replaced by the choke property in the InnerShadow class, which
works similar to the spread property in the DropShadow class. Please refer to DropShadowTest.java.
The DropShadow class contains several constructors that let you specify the initial values for the
properties:
    • InnerShadow()
    • InnerShadow(BlurType blurType, Color color, double radius, double choke, double offsetX, double offsetY)
    • InnerShadow(double radius, Color color)
    • InnerShadow(double radius, double offsetX, double offsetY, Color color)
*/
public class InnerShadowTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		InnerShadow is1 = new InnerShadow();
		is1.setOffsetX(3);
		is1.setOffsetY(6);

		Text t1 = new Text("Inner Shadow");
		t1.setEffect(is1);
		t1.setFill(Color.RED);
		t1.setFont(Font.font(null, FontWeight.BOLD, 36));

		InnerShadow is2 = new InnerShadow();
		is2.setOffsetX(3);
		is2.setOffsetY(3);
		is2.setColor(Color.GRAY);
		Rectangle rect1 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect1.setEffect(is2);

		InnerShadow is3 = new InnerShadow();
		is3.setOffsetX(-3);
		is3.setOffsetY(-3);
		is3.setColor(Color.GRAY);
		Rectangle rect2 = new Rectangle(100, 50, Color.LIGHTGRAY);
		rect2.setEffect(is3);

		HBox root = new HBox(wrap(t1, is1),  wrap(rect1, is2), wrap(rect2, is3));
		root.setSpacing(10);	
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying InnerShadow Effect");
		stage.show();
	}
	
	private VBox wrap(Shape s, InnerShadow in) {
		Text t = new Text ("offsetX=" + in.getOffsetX() + "\n" +  
		                   "offsetY=" + in.getOffsetY());
		t.setFont(Font.font(10));

		VBox box =  new VBox(10, s, t);	
		box.setAlignment(Pos.CENTER);
		return box;
	}
}
