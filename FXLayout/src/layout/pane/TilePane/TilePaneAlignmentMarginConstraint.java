
package layout.pane.TilePane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/*
A TilePane allows you to set alignment and margin constraints on individual children. The alignment for a
child node is defined within the tile that contains the child node.
You should be able to differentiate between the three:
    • The alignment property of a TilePane
        The alignment property is used to align the content (all children) within the content area of the
        TilePane. It affects the content of TilePane as a whole.
    • The tileAlignment property of the TilePane
        The tileAlignment property is used to align all children within their tiles by default. Modifying this
        property affects all children.
    • The alignment constraint on individual children of the TilePane
        The alignment constraint on a child node is used to align the child node within its tile. It affects only the
        child node on which it is set. It overrides the default alignment value for the child node that is set using the
        tileAlignment property of the TilePane.

Tip: The default value for the tileAlignment property of a TilePane is Pos.CENTER. The default value for
the alignment constraint for children is null.

Use the setAlignment(Node child, Pos value) static method of the TilePane class to set the
alignment constraints for the children. The getAlignment(Node child) static method returns the alignment
for a child node.

Use the setMargin(Node child, Insets value) static method of the TilePane class to set the margin
for children. The getMargin(Node child) static method returns the margin for a child node.

Use null to reset the constraints to the default value. Use the clearConstraints(Node child) static
method of the TilePane to reset all constraints for a child at once.

The program adds five buttons to a TilePane. The button labeled “Three” uses a custom
tile alignment constraint of Pos.BOTTOM_RIGHT. All other buttons use the default tile alignment, which is
Pos.CENTER.
*/
public class TilePaneAlignmentMarginConstraint extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button b12 = new Button("One\nTwo");
		Button b3 = new Button("Three");
		Button b4 = new Button("Four");
		Button b5 = new Button("Five");
		Button b6 = new Button("Six");

		// Set the tile alignment constraint on b3 to BOTTOM_RIGHT
		TilePane.setAlignment(b3, Pos.BOTTOM_RIGHT);
        // Set 10px margin around the topLeft child node
        TilePane.setMargin(b5, new Insets(5));
        // Get the margin of the topLeft child node
        Insets margin = TilePane.getMargin(b5);
        
        // Clear the tile alignment and margin constraints for the topLeft child node
        //TilePane.clearConstraints(b3);
        
		TilePane root = new TilePane(b12, b3, b4, b5, b6);
		root.setPrefColumns(3);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Alignment Constraints in TilePane");
		stage.show();
	}
}
