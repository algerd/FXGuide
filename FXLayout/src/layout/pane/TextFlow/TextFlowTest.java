
package layout.pane.TextFlow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/*
A TextFlow layout pane is designed to display rich text. The rich text is composed of multiple Text nodes.
The TextFlow combines the text in all Text nodes to display in a single text flow. A new line character ('\n')
in the text of the Text child nodes indicates the start of a new paragraph. The text is wrapped at the width of
the TextFlow.

A Text node has its position, size, and wrapping width. However, when it is added to a TextFlow pane,
these properties are ignored. Text nodes are placed one after another wrapping them when necessary.
A Text node in e TextFlow may span multiple lines in a TextFlow, whereas in a Text node it is displayed in
only one line.

The TextFlow is especially designed to display rich text using multiple Text nodes. However, you are not
limited to adding only Text nodes to a TextFlow. You can add any other nodes to it, for example: Buttons,
TextFields, etc. Nodes other than Text nodes are displayed using their preferred sizes.

Tip: Y ou can think of a TextFlow very similar to a FlowPane. Like a FlowPane, a TextFlow lays out its
children in a flow from one end to another by treating Text nodes differently. When a Text node is encountered
past its width boundary, it breaks the text of the Text node at its width and displays the remaining text in the
next line.

The program shows how to use a TextFlow. It adds three Text nodes to a TextFlow. The
text in the third Text node starts with a newline character (\n), which starts a new paragraph. The program
sets the preferred width of the TextFlow to 300px and the line spacing to 5px. 
When you resize the window, the TextFlow redraws the text wrapping, if necessary, at the new width.
*/
public class TextFlowTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create three Text nodes
		Text tx1 = new Text("TextFlow layout pane is cool! ");
		tx1.setFill(Color.RED);
		tx1.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		
		Text tx2 = new Text("It supports rich text display.");
		tx2.setFill(Color.BLUE);
		
		Text tx3 = new Text("\nThis is a new paragraph, which was " + 
		                    "created using the \\n newline character.");
		
		// Create a TextFlow object with the three Text nodes
		TextFlow root = new TextFlow(tx1, tx2, tx3);
		
		// Set the preferred width and line spacing 
		root.setPrefWidth(300);
		root.setLineSpacing(5);
		
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
	
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using TextFlow");
		stage.show();
	}
}
