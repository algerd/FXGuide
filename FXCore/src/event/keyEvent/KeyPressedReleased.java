
package event.keyEvent;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
Key-pressed and key-released events are handled simply by adding the event filters and handlers to nodes
for the KEY_PRESED and KEY_RELEASED event types. Typically you use these events to know which keys were
pressed or released and to perform an action. For example, you can detect the F1 function key press and
display a custom Help window for the node in focus.

The program shows how to handle key-pressed and key-released events. It displays a
Label and a TextField. When you run the program, the TextField has focus. Notice the following points
when you use keystrokes while running this program:
    - Press and release some keys. Output will show the details of events as they occur.
        A key-released event does not occur for every key-pressed event.

    - The mapping between key-pressed and key-released events is not one-to-one. There
        may be no key-released event for a key-pressed event (refer to the next item). There
        may be one key-released event for several key-pressed events. This can happen when
        you keep a key pressed for a longer period. Sometimes you do it to type the same
        character multiple times. Press the A key and hold it for some time and then release
        it. This will generate several key-pressed events and only one key-released event.

    - Press the F1 key. It will display the Help window. Notice that pressing the F1 key
        does not generate an output for a key-released event, even after you release the key.
        Can you think of the reason for this? On the key-pressed event, the Help window is
        displayed, which grabs the focus. The TextField on the main window no longer has
        focus. Recall that the key events are delivered to the node that has focus, and only
        one node can have focus in a JavaFX application. Therefore, the key-released event is
        delivered to the Help window, not the TextField.
*/
public class KeyPressedReleased extends Application {
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

		// Add key pressed and released events to the TextField
		nameTfl.setOnKeyPressed(e -> handle(e));
		nameTfl.setOnKeyReleased(e -> handle(e));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Key Pressed and Released Events");
		stage.show();
	}

	public void handle(KeyEvent e) {
		String type = e.getEventType().getName();
		KeyCode keyCode = e.getCode();
		System.out.println(type + ": Key Code=" + keyCode.getName() + ", Text=" + e.getText());

		// Show the help window when the F1 key is pressed
		if (e.getEventType() == KEY_PRESSED && e.getCode() == KeyCode.F1) {
			displayHelp();
			e.consume();
		}
	}

	public void displayHelp() {
		Text helpText = new Text("Please enter a name.");
		HBox root = new HBox();
		root.setStyle("-fx-background-color: yellow;");
		root.getChildren().add(helpText);

		Scene scene = new Scene(root, 200, 100);
		Stage helpStage = new Stage();
		helpStage.setScene(scene);
		helpStage.setTitle("Help");
		helpStage.show();
	}
}
