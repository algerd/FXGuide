
package layout.pane.StackPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
A StackPane allows you to set alignment and margin constraints on individual children. The alignment for a
child node is defined relative to the content area of the StackPane.
You should be able to differentiate between the alignment property of a StackPane and the alignment
constraint on its children. The alignment property affects all children. Its value is used to align children by
default. The alignment constraint on a child node overrides the default alignment value set by the alignment
property. The alignment constraint on a child node affects the alignment of only that child node, whereas
the alignment property affects all child nodes. When a child node is drawn, JavaFX uses the alignment
constraint of the child node for aligning it within the content area of the StackPane. If its alignment
constraint is not set, the alignment property of the StackPane is used.

Tip: The default value for the alignment property of StackPane is Pos.CENTER. The default value for the
alignment constraint for children is null.

Use the setAlignment(Node child, Pos value) static method of the StackPane class to set the
alignment constraints for children. The getAlignment(Node child) static method returns the alignment for
a child node.

Use the setMargin(Node child, Insets value) static method of the StackPane class to set the margin
for children. The getMargin(Node child) static method returns the margin for a child node.
    // Set 10px margin around the topLeft child node
    StackPane.setMargin(topLeft, new Insets(10));
    ...
    // Get the margin of the topLeft child node
    Insets margin = StackPane.getMargin(topLeft);

Use null to reset the constraints to the default value. Use the clearConstraints(Node child) static
method of the StackPane to reset all constraints for a child at once.
    // Clear the alignment and margin constraints for the topLeft child node
    StackPane.clearConstraints(topLeft);

After you clear all constraints for a child node, it will use the current value of the alignment property of
the StackPane as its alignment and 0px as the margins.
*/
public class StackPaneAlignmentMarginConstraints extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(200, 60);
		rect.setFill(Color.LAVENDER);

		// Create a Text node with the default CENTER alignment
		Text center = new Text("Center"); 

		// Create a Text node with a TOP_LEFT alignemnt constraint
		Text topLeft = new Text("top-left");
		StackPane.setAlignment(topLeft, Pos.TOP_LEFT);
        StackPane.setMargin(topLeft, new Insets(5));
		
		// Create a Text node with a BOTTOM_LEFT alignemnt constraint
		Text bottomRight = new Text("bottom-right");
		StackPane.setAlignment(bottomRight, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(bottomRight, new Insets(25));
        
        // Clear the alignment and margin constraints for the topLeft child node
        //StackPane.clearConstraints(topLeft);

		StackPane root = new StackPane(rect, center, topLeft, bottomRight);
        root.setPrefSize(300, 200);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("StackPane Alignment Constraint");
		stage.show();
	}
}
