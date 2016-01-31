
package shape.Text;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/*
A Text node is capable of displaying multiple lines of text. It creates a new line in two cases:
    • A newline character ‘\n’ in the text creates a new line causing the characters
    following the newline to wrap to the next line.
    • The Text class contains a wrappingWidth property, which is 0.0 by default. Its value
    is specified in pixels, not characters. If it is greater than zero, the text in each line is
    wrapped to at the specified value.

The lineSpacing property specifies the vertical spacing in pixels between two lines. It is 0.0 by default.
The textAlignment property specifies the horizontal alignment of the text lines in the bounding box. The
widest line defines the width of the bounding box. Its value has no effect in a single line Text node. Its value
can be one of the constants of the TextAlignment enum: LEFT, RIGHT< CENTER, and JUSTIFY. The default is
TextAlignment.LEFT.

The program creates three multiline Text nodes. The text
for all nodes is the same. The text contains three newline characters. The first node uses the default LEFT
text alignment and a line spacing of 5px. The second node uses RIGHT text alignment with the default line
spacing of 0px. The third node uses a wrappingWidth of 100px. A new line is created at 100px as well as a
newline character ‘\n’.
*/
public class MultilineText extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		String text = 
            "Strange fits of passion have I known: \n" + 
            "And I will dare to tell, \n" + 
            "But in the lover's ear alone, \n" + 
            "What once to me befell.";

		Text t1 = new Text(text);
		t1.setLineSpacing(5);

		Text t2 = new Text(text);
		t2.setTextAlignment(TextAlignment.RIGHT);
	
		Text t3 = new Text(text);
		t3.setWrappingWidth(100);

		HBox root = new HBox(t1, t2, t3); 
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
		stage.setTitle("Using Multiline Text Nodes");
		stage.show();
	}
}
