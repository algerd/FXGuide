
package layout.pane.TextFlow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/*
A TextFlow also lets you embed nodes other than Text nodes. You can create a form to display
text mixed with other types of nodes that users can use. The program embeds a pair of
RadioButtons, a TextField, and a Button to a TextFlow to create an online form with text. Users can use
these nodes to interact with the form.

At the time of testing this example, the RadioButtons and TextField
nodes did not gain focus using the mouse. Use the Tab key to navigate to these nodes and the spacebar to
select a RadioButton.
*/
public class TextFlowEmbeddingNodes extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text tx1 = new Text("I, ");

		RadioButton rb1 = new RadioButton("Mr.");
		RadioButton rb2 = new RadioButton("Ms.");
		rb1.setSelected(true);

		ToggleGroup group = new ToggleGroup();
		rb1.setToggleGroup(group);
		rb2.setToggleGroup(group);

		TextField nameFld = new TextField();
		nameFld.setPromptText("Your Name");

		Text tx2 = new Text(", acknowledge the receipt of this letter...\n\n" +
		                    "Sincerely,\n\n");

		Button submitFormBtn = new Button("Submit Form");

		// Create a TextFlow object with all nodes
		TextFlow root = new TextFlow(tx1, rb1, rb2, nameFld, tx2, submitFormBtn);

		// Set the preferred width and line spacing
		root.setPrefWidth(350);
		root.setLineSpacing(5);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
// Set the textAlignment to CENTER
root.setTextAlignment(TextAlignment.CENTER);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Creating Forms Using TextFlow");
		stage.show();
	}
}
