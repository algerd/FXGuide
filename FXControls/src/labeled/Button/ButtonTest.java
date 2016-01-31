
package labeled.Button;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
All types of buttons support the ActionEvent. Buttons trigger an ActionEvent when they are activated. A button
can be activated in different ways, for example, by using a mouse, a mnemonic, an accelerator key, or other
key combinations.

All buttons are labeled controls. Therefore, they can have a textual content, a graphic, or both. All types
of buttons are capable of firing an ActionEvent.

The mnemonicParsing property for the Button class is set to true by default.
A Button can be in one of three modes:
    • A normal button
    • A default button
    • A cancel button
For a normal button, its ActionEvent is fired when the button is activated. For a default button, the
ActionEvent is fired when the Enter key is pressed and no other node in the scene consumes the key press.
For a cancel button, the ActionEvent is fired when the Esc key is pressed and no other node in the scene
consumes the key press.

By default, a Button is a normal button. The default and cancel modes are represented by the
defaultButton and cancelButton properties. You would set one of these properties to true to make a button
a default or cancel button. By default, both properties are set to false.

The program creates a normal button, a default button, and a cancel button. It adds
an ActionEvent listener to all three buttons. Notice that all buttons have a mnemonic (e.g., N for the New
button). When the buttons are activated, a message is displayed in a Label. You can activate the buttons by
different means:
    • Clicking on buttons
    • Setting focus to the buttons using the Tab key and pressing the spacebar
    • Pressing Alt key and their mnemonics
    • Pressing the Enter key to activate the Save button
    • Pressing Esc key to activate the Cancel button

No matter how you activate the buttons, their ActionEvent handler is called. Typically, the ActionEvent
handler for a button contains the command for the button.

Tip: It is possible to set more than one button in a scene as a default or cancel button. However, only the
first one is used. It is poor designing to declare multiple buttons as default and cancel buttons in a scene.
By default, JavaFX highlights the default button with a light shade of color to give it a unique look. You can
customize the appearance of default and cancel buttons using CSS styles. Setting the same button as a default
button and a cancel button is also allowed, but it is a sign of bad design when this is done.

The Button class supports two CSS pseudo-classes: default and cancel. 
You can use these pseudo-classes to customize the look for default and cancel buttons:
    .button:default {}
    .button:cancel {}
*/
public class ButtonTest extends Application {
	Label msgLbl = new Label("Press Enter or Esc key to see the message");
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// A normal button with N as its mnemonic 
		Button newBtn = new Button("_New");
		newBtn.setOnAction(e -> newDocument());
		
		// A default button with S as its mnemonic
		Button saveBtn = new Button("_Save");
		saveBtn.setDefaultButton(true);		
		saveBtn.setOnAction( e -> save());
		
		// A cancel button with C as its mnemonic
		Button cancelBtn = new Button("_Cancel");
		cancelBtn.setCancelButton(true);		
		cancelBtn.setOnAction(e -> cancel());
		
		HBox buttonBox = new HBox(newBtn, saveBtn, cancelBtn);
		buttonBox.setSpacing(15);
		VBox root = new VBox(msgLbl, buttonBox); 
		root.setSpacing(15);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Command Buttons");
		stage.show();
	}
	
	public void newDocument() {
		msgLbl.setText("Creating a new document...");
	}
	
	public void save() {
		msgLbl.setText("Saving...");
	}
	
	public void cancel() {
		msgLbl.setText("Cancelling...");
	}
}
