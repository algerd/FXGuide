
package layout.pane.hbox;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
HBox supports two types of constraints, hgrow and margin, which can be set on each child node individually.
The hgrow constraint specifies whether a child node expands horizontally when additional space is
available. The margin constraint specifies space outside the edges of a child node. The HBox class provides
setHgrow() and setMargin() static methods to specify these constraints. You can use null with these
methods to remove the constraints individually. Use the clearConstraints(Node child) method to remove
both constraints for a child node at once.

Tip: Be careful when using the spacing property of the HBox and the margin constraint on its children.
Both will add to the horizontal gap between adjacent children. If you want margins applied, keep the horizontal
spacing between children uniform, and set the right and left margins for children to zero.
*/
public class HBoxMargin extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		     
        Label nameLbl = new Label("Name:");
        TextField nameFld = new TextField();
        Button okBtn = new Button("OK");
        Button cancelBtn = new Button("Cancel");
        HBox root = new HBox(nameLbl, nameFld, okBtn, cancelBtn);
        
        // Set a spacing between adjacent children. 
        // Overall horizontal gap between adjacent children is spacing + margin.
        //root.setSpacing(20);
       
        // Set a margin for all children: 10px top, 2px right, 10px bottom, and 2px left
        Insets margin = new Insets(10, 2, 10, 2);
        HBox.setMargin(nameLbl, margin);
        HBox.setMargin(nameFld, margin);
        HBox.setMargin(okBtn, margin);
        HBox.setMargin(cancelBtn, margin);
             
        // Remove morgins for okBtn
        //HBox.setMargin(okBtn, null);
              
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
