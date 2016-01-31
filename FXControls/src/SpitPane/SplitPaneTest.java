
package SpitPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
SplitPane arranges multiple nodes by placing them horizontally or vertically separated by a divider. The
divider can be dragged by the user, so the node on one side of the divider expands and the node on the other
side shrinks by the same amount. Typically, each node in a SplitPane is a layout pane containing some
controls. However, you can use any node, for example, a Button. If you have used Windows Explorer, you
are already familiar with using a SplitPane. In a Windows Explorer, the divider separates the tree view and
the list view. Using the divider, you can resize the width of the tree view and the width of the list view resizes
with the equal amount in the opposite direction.

The getItems() method of the SplitPane class returns the ObservableList<Node> that stores the list of
nodes in a SplitPane. Add all your nodes to this list.

By default, SplitPane places its nodes horizontally. Its orientation property can be used to specify the
orientation:
    // Place nodes vertically
    sp.setOrientation(Orientation.VERTICAL);

A divider can be moved between the leftmost and rightmost edges or topmost and bottommost edges
provided it does not overlap any other divider. The divider position can be set between 0 and 1. The position
0 means topmost or leftmost. The position 1 means bottommost or rightmost. By default, a divider is placed
in the middle with its position set to 0.5. Use either of the following two methods to set the position of a
divider:
    • setDividerPositions(double... positions)
    • setDividerPosition(int dividerIndex, double position)

The setDividerPositions() method takes the positions of multiple dividers. You must provide
positions for all dividers from starting up to the one you want to set the positions.

If you want to set the position for a specific divider, use the setDividerPosition() method. The first
divider has the index 0. Positions passed in for an index outside the range are ignored.

The getDividerPositions() method returns the positions of all dividers. It returns a double array.
The index of dividers matches the index of the array elements.

By default, SplitPane resizes its nodes when it is resized. You can prevent a specific node from resizing
with the SplitPane using the setResizableWithParent() static method:
    // Make node1 non-resizable
    SplitPane.setResizableWithParent(node1, false);

The program shows how to use SplitPane. Run the program and use the mouse to drag the divider 
to the left or right to adjust the spacing for the left and right nodes.
*/
public class SplitPaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) { 
		TextArea desc1 = new TextArea();
		desc1.setPrefColumnCount(10);
		desc1.setPrefRowCount(4);

		TextArea desc2 = new TextArea();
		desc2.setPrefColumnCount(10);
		desc2.setPrefRowCount(4);

		VBox vb1 = new VBox(new Label("Description1"), desc1);
		VBox vb2 = new VBox(new Label("Description2"), desc2); 

		SplitPane sp = new SplitPane();
		sp.getItems().addAll(vb1, vb2);

		HBox root = new HBox(sp);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);      
		stage.setTitle("Using SplitPane Controls");
		stage.show();
	}
}
