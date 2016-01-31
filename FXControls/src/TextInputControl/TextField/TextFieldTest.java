
package TextInputControl.TextField;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
TextField is a text input control. It inherits from the TextInputControl class. It lets the user enter a single
line of plain text. If you need a control to enter multiline text, use TextArea instead. Newline and tab
characters in the text are removed.

the text property of the TextField stores the textual content. If you are
interested in handling the changes in a TextField, you need to add a ChangeListener to its text property.
Most of the time you will be using its setText(String newText) method to set new text and the getText()
method to get the text from it. TextField adds the following properties:
    • alignment
    • onAction
    • prefColumnCount

The alignment property determines the alignment of the text within the TextField area when there is
empty space. Its default value is CENTER_LEFT if the node orientation is LEFT_TO_RIGHT and CENTER_RIGHT if
the node orientation is RIGHT_TO_LEFT. 
The onAction property is an ActionEvent handler, which is called when the Enter key is pressed in the TextField.
The prefColumnCount property determines the width of the control. By default, its value is 12. A column
is wide enough to display an uppercase letter W. If you set its value to 10, the TextField will be wide enough
to display ten letter Ws.

TextField provides a default context menu, as shown in Figure 12-39, that can be displayed by clicking
the right mouse button. Menu items are enabled or disabled based on the context. You can replace the
default context menu with a custom context menu. Currently, there is no way to customize the default
context menu.

The program shows how to use TextField controls. It displays two TextFields. It shows
adding ActionEvent handlers, a custom context menu, and ChangeListeners added to TextFields.
*/
public class TextFieldTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a TextFiled with an empty string as its initial text
		TextField firstNameFld = new TextField();
		TextField lastNameFld = new TextField();
	
		// Both fields should be wide enough to display 15 chars
		firstNameFld.setPrefColumnCount(15);
		lastNameFld.setPrefColumnCount(15);
		
		// Add a ChangeListener to the text property
		firstNameFld.textProperty().addListener(this::changed);
		lastNameFld.textProperty().addListener(this::changed);
		
		// Add a dummy custom context menu for the firstname field
		ContextMenu cm = new ContextMenu();
		MenuItem dummyItem = new MenuItem("Context menu is disabled");
		cm.getItems().add(dummyItem);
		firstNameFld.setContextMenu(cm);
		
		// Set ActionEvent handlers for both fields
		firstNameFld.setOnAction(e -> nameChanged("First Name"));
		lastNameFld.setOnAction(e -> nameChanged("Last Name"));
	
		GridPane root = new GridPane();
		root.setHgap(10);
		root.setVgap(5);
		root.addRow(0, new Label("First Name:"), firstNameFld);
		root.addRow(1, new Label("Last Name:"), lastNameFld);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using TextField Controls");
		stage.show();
	}
	
	public void nameChanged(String fieldName) {
		System.out.println("Action event fired on " + fieldName);
	}
	
	public void changed(ObservableValue<? extends String> prop, String oldValue, String newValue) {
		System.out.println("Old = " + oldValue + ", new = " + newValue);
	}
}
