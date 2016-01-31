
package layout.group;

import javafx.application.Application;
import javafx.beans.binding.NumberBinding;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/*
You can position child nodes in a Group by assigning them absolute positions using the layoutX and
layoutY properties of the nodes. Alternatively, you can use binding API to position them relative to other
nodes in the Group.
Listing shows how to use the absolute and relative positioning in a Group. 
The program adds two buttons (OK and Cancel) to the Group. The OK button uses absolute positioning; 
it is placed at (10, 10). The Cancel button is placed relative to the OK button; its vertical position
is the same as the OK button; its horizontal position is 20px after the right edge of the OK button. Notice the
use of the Fluent Binding API to accomplish the relative positioning for the Cancel button.
*/
public class NodesLayoutInGroup extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create two buttons
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");

		// Set the location of the OK button
		okBtn.setLayoutX(10);
		okBtn.setLayoutY(10);

		// Set the location of the Cancel botton relative to the OK button 		
		NumberBinding layoutXBinding = okBtn.layoutXProperty().add(okBtn.widthProperty().add(10));
		cancelBtn.layoutXProperty().bind(layoutXBinding);
		cancelBtn.layoutYProperty().bind(okBtn.layoutYProperty());

		Group root = new Group();		
		root.getChildren().addAll(okBtn, cancelBtn);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Positioning Nodes in a Group");
		stage.show();
	}
}
