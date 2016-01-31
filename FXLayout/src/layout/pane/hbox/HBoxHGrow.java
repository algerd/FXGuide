
package layout.pane.hbox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/*
HBox supports two types of constraints, hgrow and margin, which can be set on each child node individually.
The hgrow constraint specifies whether a child node expands horizontally when additional space is
available. The margin constraint specifies space outside the edges of a child node. The HBox class provides
setHgrow() and setMargin() static methods to specify these constraints. You can use null with these
methods to remove the constraints individually. Use the clearConstraints(Node child) method to remove
both constraints for a child node at once.

By default, the children in an HBox get their preferred widths. If the HBox is expanded horizontally, its children
may get the additional available space, provided their hgrow priority is set to grow. If an HBox is expanded
horizontally and none of its children has its hgrow constraint set, the additional space is left unused.

The hgrow priority for a child node is set using the setHgrow() static method of the HBox class by
specifying the child node and the priority.

The program is hows how to set the priority of a TextField to Priority.ALWAYS, so it
can take all the additional horizontal space when the HBox is expanded. 
Notice that all controls, except the TextField, stayed at their preferred widths, after the
window is expanded horizontally.
*/
public class HBoxHGrow extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label nameLbl = new Label("Name:");
		TextField nameFld = new TextField();

		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");

		HBox root = new HBox(10); 
		root.getChildren().addAll(nameLbl, nameFld, okBtn, cancelBtn);
		
		// Let the TextField always grow horizontally
		HBox.setHgrow(nameFld, Priority.ALWAYS);
        // Stop the TextField from growing horizontally
        //HBox.setHgrow(nameFld, null);
		
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Horizontal Grow Priority in an HBox");
		stage.show();
	}
}
