
package layout.pane.GridPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
The GridPane class contains the following convenience methods to add children with constraints.
    • void add(Node child, int columnIndex, int rowIndex)
    • void add(Node child, int columnIndex, int rowIndex, int colspan,int rowspan)
    • void addRow(int rowIndex, Node... children)
    • void addColumn(int columnIndex, Node... children)

The add() methods let you add a child node specifying the column index, row index, column span, and row span.

The addRow() method adds the specified children in a row identified by the specified rowIndex.
Children are added sequentially. If the row already contains children, the specified children are appended
sequentially. For example, if the GridPane has no children in the specified row, it will add the first child node
at column index 0, the second at column index 1, etc. Suppose the specified row already has two children
occupying column indexes 0 and 1. The addRow() method will add children starting at column index 2.

Tip: All children added using the addRow() method spans only one cell. Row and column spans for a
child node can be modified using the setRowSpan(Node child, Integer value) and setColumnSpan(Node
child, Integer value) static methods of the GridPane class. When you modify the row and column spans
for a child node, make sure to update row and column indexes of the affected children so they do not overlap.

The addColumn() method adds the specified children sequentially in a column identified by the
specified columnIndex. This method adds children to a column the same way the addRow() method adds
children to a row.
*/
public class GridPaneAdding extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		   
        // Add a child node at a time
        GridPane gpane1 = new GridPane();
        gpane1.add(new Button("One"), 0, 0);    // (c0, r0)
        gpane1.add(new Button("Two"), 1, 0);    // (c1, r0)
        gpane1.add(new Button("Three"), 0, 1);  // (c0, r1)
        gpane1.add(new Button("Four"), 1, 1);   // (c1, r1)
        
        // Add a row at a time
        GridPane gpane2 = new GridPane();
        gpane2.addRow(0, new Button("One"), new Button("Two"));
        gpane2.addRow(1, new Button("Three"), new Button("Four"));
        
        // Add a column at a time
        GridPane gpane3 = new GridPane();
        gpane3.addColumn(0, new Button("One"), new Button("Three"));
        gpane3.addColumn(1, new Button("Two"), new Button("Four"));
        
        
        VBox root = new VBox(20);
		root.getChildren().addAll(gpane1, gpane2, gpane3);

		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Adding Columns and Rows");
		stage.show();
	}
}
