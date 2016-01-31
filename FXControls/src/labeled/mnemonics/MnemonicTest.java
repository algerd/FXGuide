
package labeled.mnemonics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
Labeled controls support keyboard mnemonics, which is also known as a keyboard shortcut or keyboard
indicator. A mnemonic is a key that sends an ActionEvent to the control. The mnemonic key is often pressed
in combination with a modifier key such as an Alt key. The modifier key is platform dependent; however,
it is usually an Alt key.

Setting a mnemonic key for a labeled control is easy. You need to precede
the mnemonic character with an underscore in the text content and make sure that the mnemonicParsing
property for the control is set to true. The first underscore is removed and the character following it is set as
the mnemonic for the control. For some labeled controls, the mnemonic parsing is set to true by default,
and for others, you will need to set it.

The following statement will set the C key as the mnemonic for the Close button:
    // For Button, mnemonic parsing is true by default
    Button closeBtn = new Button("_Close");

When you press the Alt key, the mnemonic characters for all controls are underlined and pressing the
mnemonic character for any controls will set focus to the control and send it an ActionEvent.
JavaFX provides the following four classes in the javafx.scene.input package to set mnemonics for all
types of controls programmatically:
    • Mnemonic
    • KeyCombination
    • KeyCharacterCombination
    • KeyCodeCombination

An object of the Mnemonic class represents a mnemonic. An object of the KeyCombination class, which
is declared abstract, represents the key combination for a mnemonic. The KeyCharacterCombination and
KeyCodeCombination classes are subclasses of the KeyCombination class. Use the former to construct a
key combination using a character; use the latter to construct a key combination using a key code. Note
that not all keys on the keyboard represent characters. The KeyCodeCombination class lets you create a key
combination for any key on the keyboard.

The Mnemonic object is created for a node and is added to a Scene. When the Scene receives an
unconsumed key event for the key combination, it sends an ActionEvent to the target node.
The following snippet of code achieves the same result that was achieved using one statement in the
above example:
    Button closeBtn = new Button("Close");
    // Create a KeyCombination for Alt + C
    KeyCombination kc = new KeyCodeCombination(KeyCode.C, KeyCombination.ALT_DOWN);
    // Create a Mnemonic object for closeBtn
    Mnemonic mnemonic = new Mnemonic(closeBtn, kc);
    Scene scene = create a scene...;
    scene.addMnemonic(mnemonic); // Add the mnemonic to the scene

The KeyCharacterCombination class can also be used to create a key combination for Alt + C:
    KeyCombination kc = new KeyCharacterCombination("C", KeyCombination.ALT_DOWN);


The program shows how to use mnemonics and accelerator keys. Press Alt + 1 and
Alt + 2 to activate Button 1 and Button 2, respectively. Pressing these buttons changes the text for the Label.
Pressing the shortcut key + X will close the window.
*/
public class MnemonicTest  extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		VBox root = new VBox();
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		Label msg = new Label("Press Ctrl + X on Windows \nand " +
		                      "\nMeta + X on Mac to close the window");
		Label lbl = new Label("Press Alt + 1 or Alt + 2");

		// Use Alt + 1 as the mnemonic for Button 1
		Button btn1 = new Button("Button _1");
		btn1.setOnAction(e -> lbl.setText("Button 1 clicked!"));

		// Use Alt + 2 as the mnemonic key for Button 2
		Button btn2 = new Button("Button 2");
		btn2.setOnAction(e -> lbl.setText("Button 2 clicked!"));
		KeyCombination kc = new KeyCodeCombination(KeyCode.DIGIT2, KeyCombination.ALT_DOWN);
		Mnemonic mnemonic = new Mnemonic(btn2, kc);
		scene.addMnemonic(mnemonic);

        /*
        The Scene class supports accelerator keys. An accelerator key, when pressed, executes a Runnable task.
        Notice the difference between mnemonics and accelerator keys. A mnemonic is associated with a control,
        and pressing its key combination sends an ActionEvent to the control. An accelerator key is not associated
        with a control, but rather to a task. The Scene class maintains an ObservableMap<KeyCombination,
        Runnable>, whose reference can be obtained using the getAccelerators() method.
        */
		// Add an accelarator key to the scene
		KeyCombination kc4= new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN);
		Runnable task = () -> scene.getWindow().hide();
		scene.getAccelerators().put(kc4, task);
		
		// Add all children to the VBox
		root.getChildren().addAll(msg, lbl, btn1, btn2);
		
		stage.setScene(scene);
		stage.setTitle("Using Mnemonics and Accelerators");
		stage.show();
	}
}
