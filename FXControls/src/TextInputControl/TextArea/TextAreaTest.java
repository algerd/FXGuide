
package TextInputControl.TextArea;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
TextArea is a text input control. It inherits from the TextInputControl class. It lets the user enter multiline
plain text. If you need a control to enter a single line of plain text, use TextField instead. If you want to
use rich text, use the HTMLEditor control. Unlike the TextField, newline and tab characters in the text are
preserved. A newline character starts a new paragraph in a TextArea.

the text property of the TextArea stores the textual
content. If you are interested in handling the changes in a TextArea, you need to add a ChangeListener to its
text property. Most of the time, you will be using its setText(String newText) method to set new text and
its getText() method to get the text from it.
TextArea adds the following properties:
    • prefColumnCount
    • prefRowCount
    • scrollLeft
    • scrollTop
    • wrapText

The prefColumnCount property determines the width of the control. By default, its value is 32. A column
is wide enough to display an uppercase letter W. If you set its value to 80, the TextArea will be wide enough
to display 80 letter Ws.

If the text exceeds the number of columns and rows, the horizontal and vertical scroll panes are
automatically displayed.
Like TextField, TextArea provides a default context menu (TextFieldTest.java).

By default, TextArea starts a new line when it encounters a newline character in its text. A newline
character also creates a new paragraph except for the first paragraph. By default, the text is not wrapped to
the next line if it exceeds the width of the control. The wrapText property determines whether the text is
wrapped to another line when its run exceeds the width of the control. By default, its value is false.
The following code would set the default to true:
    resume.setWrapText(true);

The getParagraphs() method of the TextArea class returns an unmodifiable list of all paragraphs in its
text. Each element in the list is a paragraph, which is an instance of CharSequence. The returned paragraph
does not contain the newline characters.
*/
public class TextAreaTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TextField title = new TextField("Luci");
		title.setPromptText("Your poem title goes here");
		
		TextArea poem = new TextArea();
		poem.setPromptText("Your poem goes here");
		poem.setPrefColumnCount(20);
		poem.setPrefRowCount(10);
		poem.appendText("I told her this: her laughter light\n" + 
		                "Is ringing in my ears:\n" + 
		                "And when I think upon that night\n" + 
		                "My eyes are dim with tears.");
	    
		Button printBtn = new Button("Print Poem Details");
		printBtn.setOnAction(e -> print(poem));

		VBox root = new VBox(new Label("Title:"), title, new Label("Poem:"), poem, printBtn);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);      
		stage.setScene(scene);      
		stage.setTitle("Using TextArea Controls");
		stage.show();
	}
	
	public void print(TextArea poem) {		
		System.out.println("Poem Length: " + poem.getLength());
		System.out.println("Poem Text:\n" + poem.getText());
		System.out.println();
		
		ObservableList<CharSequence> list = poem.getParagraphs();
		int size = list.size();
		System.out.println("Paragraph Count:" + size);
		for(int i = 0; i < size; i++) {
			CharSequence cs = list.get(i);
	        System.out.println("Paragraph #" + (i + 1) + ", Characters="  + cs.length());
			System.out.println(cs);
		}
	}
}
