
package layout.pane.GridPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
Индекс ячейки является абсолютным и он игнорирует все колспаны и роуспаны других элементов,
затрагивающих ячейку.

A child node may span more than one row and column, which can be specified using the rowSpan and
colSpan constraints. By default, a child node spans one column and one row. These constraints can be
specified while adding the child node or later using any of the following methods in the GridPane class.
    • void add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan)
    • static void setColumnSpan(Node child, Integer value)
    • static void setConstraints(Node child, int columnIndex, int rowIndex, int columnspan, int rowspan)

The setConstraints() method is overloaded. Other versions of the method also let you specify the
column/row span.

The GridPane class defines a constant named REMAINING that is used for specifying the column/row
span. It means that the child node spans the remaining columns or remaining rows.

The following snippet of code adds a two Labels and a two TextFields to the first row. 
It adds a TextArea to the first column of the second row with its colSpan as REMAINING. 
This will make the TextArea occupy four columns as we set its colSpan as REMAINING.
*/
public class GridPaneChildrenSpan extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
        
		// Create a GridPane and set its background color to lightgray
        GridPane root = new GridPane();
        root.setGridLinesVisible(true);
        root.setStyle("-fx-background-color: lightgray;");
        
        // Add a Label and a TextField to the first row
        root.addRow(0, new Label("First Name:"), new TextField());
        // Add a Label and a TextField to the first row
        root.addRow(0, new Label("Last Name:"), new TextField());
        
        // Add a TextArea in the second row to span all columns in row 2
        TextArea ta = new TextArea();
        ta.setPromptText("Enter your resume here");
        ta.setPrefColumnCount(10);
        ta.setPrefRowCount(3);
        root.add(ta, 0, 1, GridPane.REMAINING, 1);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Adding Children to a GridPane");
		stage.show();
	}
}
