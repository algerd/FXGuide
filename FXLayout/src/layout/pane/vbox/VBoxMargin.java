
package layout.pane.vbox;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
VBox supports two types of constraints, vgrow and margin, that can be set on each child node individually.
The vgrow constraint specifies whether a child node expands vertically when additional space is available.
The margin constraint specifies space outside the edges of a child node. The VBox class provides setVgrow()
and setMargin() static methods to specify these constraints. You can use null with these methods to
remove the constraints individually. Use the clearConstraints(Node child) method to remove both
constraints for a child node at once.

Tip: Be careful when using the spacing property of the VBox and the margin constraint on its children.
Both will add to the vertical gap between adjacent children. If you want margins applied, keep the vertical
spacing between children uniform, and set the top and bottom margins for children to zero.
*/
public class VBoxMargin extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
        
		Label nameLbl = new Label("Name:");
        TextField nameFld = new TextField();
        Button okBtn = new Button("OK");
        Button cancelBtn = new Button("Cancel");
        VBox root = new VBox(nameLbl, nameFld, okBtn, cancelBtn);
        
        // Set a spacing between adjacent children. 
        // Overall vertical gap between adjacent children is spacing + margin.
        //root.setSpacing(20);
       
        // Set a margin for all children: 5px top, 10px right, 5px bottom, and 10px left
        Insets margin = new Insets(5, 10, 5, 10);
        VBox.setMargin(nameLbl, margin);
        VBox.setMargin(nameFld, margin);
        VBox.setMargin(okBtn, margin);
        VBox.setMargin(cancelBtn, margin);
             
        // Remove morgins for okBtn
        //VBox.setMargin(okBtn, null);

		root.setStyle("-fx-padding: 10;" + 
					  "-fx-border-style: solid inside;" + 
					  "-fx-border-width: 2;" +
					  "-fx-border-insets: 5;" + 
					  "-fx-border-radius: 5;" + 
					  "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Vertical Grow Priority in a VBox");
		stage.show();
	}
}
