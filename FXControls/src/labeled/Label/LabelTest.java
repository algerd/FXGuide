
package labeled.Label;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
An instance of the Label class represents a label control. As the name suggest, a Label is simply a label that is
used to identify or describe another component on a screen. It can display a text, an icon, or both. Typically,
a Label is placed next to (to the right or left) or at the top of the node it describes.

A Label is not focus traversable. That is, you cannot set the focus to a Label using the Tab key. A Label
control does not generate any interesting events that are typically used in an application.

A Label control can also be used to display text in situations where it is acceptable to truncate the
text if enough space is not available to display the entire text. Please refer to the API documentation on the
textOverrun and ellipsisString properties of the Labeled class for more details on how to control the text
truncation behavior in a Label control.

The Label class has a very useful labelFor property of ObjectProperty<Node> type. It is set to another
node in the scene graph. A Label control can have a mnemonic. Mnemonic parsing for Label controls is
set to false by default. When you press the mnemonic key for a Label, the focus is set to the labelFor node
for that Label. The following snippet of code creates a TextField and a Label. The Label sets a mnemonic,
enables mnemonic parsing, and sets the TextField as its labelFor property. When the Alt + F keys are
pressed, focus is moved to the TextField.

Press Alt + F and Alt + L to shift focus between the two TextField controls.
*/
public class LabelTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TextField fNameFld = new TextField();
		Label fNameLbl = new Label("_First Name:");
		fNameLbl.setLabelFor(fNameFld);
		fNameLbl.setMnemonicParsing(true);

		TextField lNameFld = new TextField();
		Label lNameLbl = new Label("_Last Name:");
		lNameLbl.setLabelFor(lNameFld);
		lNameLbl.setMnemonicParsing(true);

		GridPane root = new GridPane();
		root.addRow(0, fNameLbl, fNameFld);
		root.addRow(1, lNameLbl, lNameFld);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Labels");
		stage.show();
	}
}
