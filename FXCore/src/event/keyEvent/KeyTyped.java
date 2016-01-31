
package event.keyEvent;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
The typical use of the key-typed event is to detect specific keystrokes to prevent some characters from being
entered. For example, you may allow users to only enter letters in a name field. You can do so by consuming
all key-typed events for the field associated with all nonletters.

The program shows a Label and a TextField. It adds a key-typed event handler to the TextField, 
which consumes the event if the character typed is not a letter. Otherwise, it prints the character
typed on the standard output. Run the program. You should be able to enter letters in the TextField. 
When you press any nonletter keys, for example, 1, 2, 3, nothing happens.

This example is not a correct solution to stop users from entering nonletter characters. For example,
users can still paste nonletters using the context menu (right-click on Windows) or using the keyboard
shortcut Ctrl + V. The correct solution lies in detecting and handling the event on the TextField that is
generated, irrespective of the method used.
*/
public class KeyTyped extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label nameLbl = new Label("Name:");
		TextField nameTfl = new TextField();

		HBox root = new HBox();
		root.setPadding(new Insets(20));
		root.setSpacing(20);
		root.getChildren().addAll(nameLbl, nameTfl);

		// Add key-typed event to the TextField
		nameTfl.setOnKeyTyped(e -> handle(e));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Key Typed Event");
		stage.show();
	}

	public void handle(KeyEvent e) {
		// Consume the event if it is not a letter
		String str = e.getCharacter();
		int len = str.length();
		for(int i = 0; i < len; i++) {
			Character c = str.charAt(i);
			if (!Character.isLetter(c)) {
				e.consume();
			}
		}
		
		// Print the details if it is not consumed
		if (!e.isConsumed()) {
			String type = e.getEventType().getName();
			System.out.println(type + ": Character=" + e.getCharacter());		    
		}
	}
}
