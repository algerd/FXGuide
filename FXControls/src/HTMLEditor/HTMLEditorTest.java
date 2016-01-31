
package HTMLEditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

/*
The HTMLEditor control provides a rich text editing capability to JavaFX application. It uses HTML as its data
model. That is, the formatted text in HTMLEditor is stored in HTML format.

An HTMLEditor displays formatting toolbars with it. You cannot hide the toolbars. They can be styled
using a CSS. Using the toolbars, you can:
    • Copy, cut, and paste text using the system clipboard
    • Apply text alignment
    • Indent text
    • Apply bulleted list and numbered list styles
    • Set foreground and background colors
    • Apply paragraph and heading styles with font family and font size
    • Apply formatting styles such as bold, italic, underline, and strikethrough
    • Add horizontal rulers

The control supports HTML5. Note that the toolbars do not allow you to apply all kinds of HTML.
However, if you load a document that uses those styles, it allows you to edit them. For example, you cannot
create an HTML table directly in the control. However, if you load HTML content having HTML tables into
the control, you will be able to edit the data in the tables.

The HTMLEditor does not provide API to load HTML content from a file to save its content to a file.
You will have to write your own code to accomplish this.

The HTMLEditor class has a very simple API that consists of only three methods:
    • getHtmlText()
    • setHtmlText(String htmlText)
    • print(PrinterJob job)
The getHTMLText() method returns the HTML content as a string. The setHTMLText() method sets the
content of the control to the specified HTML string. The print() method prints the content of the control.
*/
public class HTMLEditorTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		HTMLEditor editor = new HTMLEditor();
		editor.setPrefSize(600, 300);
		
		TextArea html = new TextArea();
		html.setPrefSize(600, 300);
		html.setStyle("-fx-font-size:10pt; -fx-font-family: \"Courier New\";");
		
		Button htmlToText = new Button("Convert HTML to Text");
		Button textToHtml = new Button("Convert Text to HTML");
		htmlToText.setOnAction(e -> editor.setHtmlText(html.getText()));
		textToHtml.setOnAction(e -> html.setText(editor.getHtmlText()));

		HBox buttons = new HBox(htmlToText, textToHtml);
		buttons.setSpacing(10);
		
		VBox root = new VBox(editor, buttons, html);
		root.setSpacing(10);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using an HTMLEditor");
		stage.show();
	}
}
