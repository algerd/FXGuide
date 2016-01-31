
package layout.pane.AnchorPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
The program adds two buttons to an AnchorPane. One button has a long label and
another has a short label. The button with the long label is added first, and hence, it is drawn first. The
second button is drawn second, which overlays the first button. The figure
shows two views of the window: one when the program is run and another when the window is resized.
Both buttons are placed at (0, 0). This program does not take advantage of the anchoring features of the
AnchorPane.
*/
public class AnchorPaneDefaults extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button bigBtn = new Button("This is a big button.");
		Button smallBtn = new Button("Small button");

		// Create an AnchorPane with two buttons
		AnchorPane root = new AnchorPane(bigBtn, smallBtn);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Defaults in AnchorPane");
		stage.show();
	}
}
