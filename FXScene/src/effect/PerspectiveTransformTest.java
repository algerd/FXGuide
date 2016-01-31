
package effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
A PerspectiveTransform effect gives a 2D node a 3D look by mapping the corners to different locations.
The straight lines in the original nodes remain straight. However, parallel lines in the original nodes may not
necessarily remain parallel.

An instance of the PerspectiveTransform class represents a PerspectiveTransform effect. The class
contains eight properties to specify the x and y coordinates of four corners:
    • ulx
    • uly
    • urx
    • ury
    • lrx
    • lry
    • llx
    • lly
The first letter in the property names (u or l) indicates upper and lower. The second letter in the
property names (l or r) indicates left and right. The last letter in the property names (x or y) indicates the
x or y coordinate of a corner. For example, urx indicates the x coordinate of the upper right corner.

Tip The PerspectiveTransform class also contains an input property to specify the input effect to it in a
chain of effects.

The PerspectiveTransform class contains two constructors:
    • PerspectiveTransform()
    • PerspectiveTransform(double ulx, double uly, double urx, double ury, double lrx, double lry, double llx, double lly)
The no-args constructor creates a PerspectiveTransform object with all new corners at (0, 0). If you set
the object as an effect to a node, the node will be reduced to a point, and you will not be able to see the node.
The other constructor lets you specify the new coordinates for the four corners of the node.

The program creates two sets of a Text node and a rectangle. It adds two sets to two
different groups. It applies a PerspectiveTransform effect on the second group. 
The group on the left shows the original nodes; the group on the right has the effect applied to it.
*/
public class PerspectiveTransformTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create the efefct and set the mapping for the corners
		PerspectiveTransform effect = new PerspectiveTransform();
		effect.setUlx(0.0);
		effect.setUly(0.0);
		effect.setUrx(250.0);
		effect.setUry(20.0);
		effect.setLrx(310.0);
		effect.setLry(60.0);
		effect.setLlx(20.0);
		effect.setLly(60.0);

		// Create two rectangles and two Text nodes. Apply effects 
		// to one set and show another set without effect
		Rectangle rect1 = new Rectangle(200, 60, Color.LIGHTGRAY);
		Rectangle rect2 = new Rectangle(200, 60, Color.LIGHTGRAY);

		Text text1 = new Text();
		text1.setX(20);
		text1.setY(40);
		text1.setText("Welcome");
		text1.setFill(Color.RED);
		text1.setFont(Font.font(null, FontWeight.BOLD, 36));		
		
		System.out.println(text1.getLayoutBounds());
		
		Text text2 = new Text();
		text2.setX(20);
		text2.setY(40);
		text2.setText("Welcome");
		text2.setFill(Color.RED);
		text2.setFont(Font.font(null, FontWeight.BOLD, 36));

		// Group the original nodes
		Group group1 = new Group(rect1, text1);	
	    
		// Group the nodes with the effect
		Group group2 = new Group(rect2, text2);		
		group2.setEffect(effect);
		group2.setCache(true); // A hint to cache the bitmap for the group

		HBox root = new HBox(group1, group2);
		root.setSpacing(20);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root, 600, 100);
		stage.setScene(scene);
		stage.setTitle("Applying the PerspectiveTransform Effect");		
		stage.show();		
	}	
}
