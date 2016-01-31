
package layout.pane.vbox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
A VBox lays out its children in a single vertical column. It lets you set the vertical spacing between adjacent
children, margins for any children, resizing behavior of children, etc. It uses 0px as the default spacing
between adjacent children. The default height of the content area of a VBox is tall enough to display all its
children at their preferred heights, and the default width is the largest of the widths of all its children.
You cannot set the locations for children in a VBox. They are automatically computed by the VBox. You
can control the locations of children to some extent by customizing the properties of the VBox and setting
constraints on the children.

Working with a VBox is similar to working with an HBox with a difference that they work in opposite
directions. For example, in an HBox, the children fills the height of the content area by default, and in a VBox,
children fill the width of the content by default; an HBox lets you set hgrow constraints on a child node and a
VBox lets you set the vgrow constraint.

You cannot set the locations for children in an VBox. They are automatically computed by the VBox.
You can control the locations of children to some extent by customizing the properties of the VBox and
setting constraints on the children.

Constructors of the VBox class let you create VBox objects with or without specifying the spacing and initial
set of children.
    // Create an empty VBox with the default spacing (0px)
    VBox vbox1 = new VBox();
    // Create an empty VBox with a 10px spacing
    VBox vbox2 = new VBox(10);
    // Create a VBox with two Buttons and a 10px spacing
    Button okBtn = new Button("OK");
    Button cancelBtn = new Button("Cancel");
    VBox vbox3 = new VBox(10, okBtn, cancelBtn);

The program shows how to use a VBox. It adds a Label, a TextField, and two Buttons
to a VBox. Spacing between adjacent children is set to 10px. A padding of 10px is used to maintain a distance
between the edges of the VBox and the edges of its children.
*/
public class VBoxTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label nameLbl = new Label("Name:");
		TextField nameFld = new TextField();
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");

		VBox root = new VBox(10); // 10px spacing
		root.getChildren().addAll(nameLbl, nameFld, okBtn, cancelBtn);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" + 
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using VBox");
		stage.show();
	}
}
