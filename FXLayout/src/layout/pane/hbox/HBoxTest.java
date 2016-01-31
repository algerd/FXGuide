
package layout.pane.hbox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
An HBox lays out its children in a single horizontal row. It lets you set the horizontal spacing between adjacent
children, margins for any children, resizing behavior of children, etc. It uses 0px as the default spacing
between adjacent children. The default width of the content area and HBox is wide enough to display all its
children at their preferred widths, and the default height is the largest of the heights of all its children.

You cannot set the locations for children in an HBox. They are automatically computed by the HBox.
You can control the locations of children to some extent by customizing the properties of the HBox and
setting constraints on the children.

Constructors of the HBox class let you create HBox objects with or without specifying the spacing and initial
set of children.
    // Create an empty HBox with the default spacing (0px)
    HBox hbox1 = new HBox();
    // Create an empty HBox with a 10px spacing
    HBox hbox2 = new HBox(10);
    // Create an HBox with two Buttons and a 10px spacing
    Button okBtn = new Button("OK");
    Button cancelBtn = new Button("Cancel");
    HBox hbox3 = new HBox(10, okBtn, cancelBtn);

The program shows how to use an HBox. It adds a Label, a TextField, and two Buttons to
an HBox. Spacing between adjacent children is set to 10px. A padding of 10px is used to maintain a distance
between the edges of the HBox and the edges of its children.
*/
public class HBoxTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label nameLbl = new Label("Name:");
		TextField nameFld = new TextField();
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");

		HBox root = new HBox(10); // 10px spacing
		root.getChildren().addAll(nameLbl, nameFld, okBtn, cancelBtn);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" + 
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using HBox");
		stage.show();
	}
}
