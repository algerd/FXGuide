
package shape.Text;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
A text node is an instance of the Text class that is used to render text. The Text class contains several
properties to customize the appearance of text. The Text class and all its related classes – for example, the
Font class, the TextAlignment enum, the FontWeight enum, etc. – are in the javafx.scene.text package.

The Text class inherits from the Shape class. That is, a Text is a Shape, which allows you to use all
properties and methods of the Shape class on a Text node. For example, you can apply a fill color and a
stroke to a Text node. Because Text is a node, you can use features of the Node class: for example, applying
effects and transformations. You can also set text alignment, font family, font size, text wrapping style, etc.,
on a Text node.

An instance of the Text class represents a Text node. A Text node contains text and properties to render the
text. You can create a Text node using one of the constructors of the Text class:
    • Text()
    • Text(String text)
    • Text(double x, double y, String text)
The no-args constructor creates a Text node with an empty string as its text. Other constructors let you
specify the text and position the node.

The text property of the Text class specifies the text (or content) of the Text node. The x and y
properties specify the x and y coordinates of the text origin, which are described in the next section.

Tip: The width and height of a text node are automatically determined by its font. By default, a Text node
uses a system default font to render its text.

The Text class contains two boolean properties to apply text decorations to its text:
    • strikethrough
    • underline

The Text class contains a fontSmoothingType property, which can be used to apply a gray or LCD font smoothing.
Its value is one of the constants of the FontSmoothingType enum: GRAY and LCD. The default-smoothing type is
fontSmoothingType.GRAY. The LCD smoothing type is used as a hint. The following snippet of code creates two
Text nodes: one uses LCD and one GRAY font-smoothing type.
*/
public class TextTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
 
	@Override
	public void start(Stage stage) {
		Text t1 = new Text("Hello Text Node!");
		
		Text t2 = new Text("Bold and Big");
		t2.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
		
		Text t3 = new Text("Reflection");
		t3.setEffect(new Reflection());
		t3.setStroke(Color.BLACK);
		t3.setFill(Color.WHITE);
		t3.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        HBox boxTest = new HBox(t1, t2, t3);
        boxTest.setSpacing(20);
        
        Text t4 = new Text("It uses the \nunderline decoaration.");
        t4.setUnderline(true);
        Text t5 = new Text("It uses the \nstrikethrough decoration.");
        t5.setStrikethrough(true);
      		 
        HBox boxDecoration = new HBox(t4, t5);
        boxDecoration.setSpacing(20);
        
        Text t6 = new Text("Hello world in LCD.");
        t6.setFontSmoothingType(FontSmoothingType.LCD);
        Text t7 = new Text("Hello world in GRAY.");
        t7.setFontSmoothingType(FontSmoothingType.GRAY);
        
        HBox boxSmoothing = new HBox(t6, t7);
        boxSmoothing.setSpacing(20);
        
        VBox root = new VBox(boxTest, boxDecoration, boxSmoothing);
		root.setSpacing(20);
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
		stage.setTitle("Using Text Nodes");
		stage.show();
	}
}
