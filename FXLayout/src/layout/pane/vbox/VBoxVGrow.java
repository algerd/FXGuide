
package layout.pane.vbox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
VBox supports two types of constraints, vgrow and margin, that can be set on each child node individually.
The vgrow constraint specifies whether a child node expands vertically when additional space is available.
The margin constraint specifies space outside the edges of a child node. The VBox class provides setVgrow()
and setMargin() static methods to specify these constraints. You can use null with these methods to
remove the constraints individually. Use the clearConstraints(Node child) method to remove both
constraints for a child node at once.

By default, the children in a VBox get their preferred heights. If the VBox is expanded vertically, its children
may get the additional available space, provided their vgrow priority is set to grow. If a VBox is expanded
vertically and none of its children has its vgrow constraint set, the additional space is left unused.
The vgrow priority for a child node is set using the setVgrow() static method of the VBox class by
specifying the child node and the priority.
    
The program shows how to set the priority of a TextArea to Priority.ALWAYS, so it can
take all the additional vertical space when the VBox is expanded.  
Notice that the Label stays at its preferred height, after the window is expanded vertically.
*/
public class VBoxVGrow extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label descLbl = new Label("Descrption:");
		TextArea desc = new TextArea();
		desc.setPrefColumnCount(10);
		desc.setPrefRowCount(3);
		
		VBox root = new VBox(10); 
		root.getChildren().addAll(descLbl, desc);
		
		// Let the TextArea always grow vertically
		VBox.setVgrow(desc, Priority.ALWAYS);
        // Stop the TextArea from growing horizontally
        //VBox.setVgrow(desc, null);

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
