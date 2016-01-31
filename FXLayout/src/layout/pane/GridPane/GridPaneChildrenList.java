
package layout.pane.GridPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
Like most of the other layout panes, a GridPane stores its children in an ObservableList<Node> whose
reference is returned by the getChildren() method. You should not add children to the GridPane directly to
the list. Rather, you should use one of the convenience methods to add children to the GridPane. You should
specify constraints for children when you add them to a GridPane. The minimum constraints would be the
column and row indexes to identify the cell in which they are placed.

Let us first see the effect of adding the children directly to the observable list of the GridPane. Listing 10-28
contains the program that directly adds three buttons to the list of children of a GridPane. Figure 10-42 shows
the window. Notice that the buttons overlap. They are all placed in the same cell (c0, r0). They are drawn in the
order they are added to the list.

Tip: I n a GridPane, by default, all children are added in the first cell (c0, r0) spanning only one column and
one row, thus overlapping each other. They are drawn in the order they are added.

There are two ways of fixing the problem of overlapping children in Listing:
    • We can set the position in which they are placed, before or after adding them
    to the list.
    • We can use convenience methods of the GridPane class that allow specifying the
    positions, among other constraints, while adding children to the GridPane.
*/
public class GridPaneChildrenList extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button b1 = new Button("One One One One One");
		Button b2 = new Button("Two Two Two");
		Button b3 = new Button("Three");
		
		GridPane root = new GridPane();

		// Add three buttons to the list of children of the GridPane directly
		root.getChildren().addAll(b1, b2, b3);

		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Adding Children to a GridPane");
		stage.show();
	}
}
