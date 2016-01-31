
package layout.pane.AnchorPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
The program adds a button to an AnchorPane and anchors it to the left and right edges
(opposite edges) using an anchor of 10px from each edge. This will make the button stretch when the
AnchorPane is resized to a width larger than its preferred width. The button is also anchored to the top edge.
*/
public class AnchorPaneStretching extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button leftRight = new Button("A button");		
		AnchorPane.setTopAnchor(leftRight, 10.0);
		AnchorPane.setLeftAnchor(leftRight, 10.0);
		AnchorPane.setRightAnchor(leftRight, 10.0);

		AnchorPane root = new AnchorPane();
		root.getChildren().addAll(leftRight);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Streching Children in an AnchorPane");
		stage.show();
	}
}
