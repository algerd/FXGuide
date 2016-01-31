
package layout.pane.AnchorPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
The program adds two Buttons to an AnchorPane. The first button has its top and left anchors set. 
The second button has its bottom and right anchors set. The initial size of the
window is not wide enough to display both buttons, so the buttons overlap. The JavaFX runtime computes
the width of the content area of the window based on the preferred size of the bottom-right button, which
has the maximum preferred width, and its right anchor value. The figure also shows the window after it
is resized. You need to set a sensible preferred size for an AnchorPane, so all children are visible without
overlapping.
*/
public class AnchorPaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button topLeft = new Button("Top Left");
		AnchorPane.setTopAnchor(topLeft, 10.0);
		AnchorPane.setLeftAnchor(topLeft, 10.0);

		Button bottomRight = new Button("Botton Right");
		AnchorPane.setBottomAnchor(bottomRight, 10.0);
		AnchorPane.setRightAnchor(bottomRight, 10.0);

		AnchorPane root = new AnchorPane();
		root.getChildren().addAll(topLeft, bottomRight);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using an AnchorPane");
		stage.show();
	}
}
