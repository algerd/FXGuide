
package layout.pane.GridPane;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/*
The halignment and valignment constraints specify the alignment of a child node within its layout area.
They default to HPos.LEFT and VPos.CENTER. They can be set on column/row affecting all children. Children
may set them individually. The final value applicable to a child node depends of some rules:
    • When they are not set for column/row and not for the child node, the child node will
    use the default values.
    • When they are set for column/row and not for the child node, the child node will use
    the value set for the column/row.
    • When they are set for column/row and for the child node, the child node will use the
    value set for it, not the value set for the column/row. In essence, a child node can
    override the default value or the value set for the column/row for these constraints.

The program demonstrates the rules mentioned above.The program adds three buttons to a column. 
The column constraints override the default value of HPos.LEFT
for the halignment constraints for the children and set it to HPos.RIGHT. The button labeled “Two” overrides
this setting to HPos.CENTER. Therefore, all buttons in the column are horizontally aligned to the right, except
the button labeled “Two,” which is aligned to the center. We set constraints for all three rows. The first and
the second rows set valignment to VPos.TOP. The third row leaves the valignment to the default that is
VPos.CENTER. The button with the label “One” overrides the valignment constraint set on the first row to set
it to VPos.BOTTOM. Notice that all children follow the above thee rules to use the valignment and halignment
constraints.
*/
public class GridPaneHValignment extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		GridPane root = new GridPane();
		root.setStyle("-fx-padding: 10;");
		root.setGridLinesVisible(true);

		// Add three buttons to a column
		Button b1 = new Button("One");
		Button b2 = new Button("Two");
		Button b3 = new Button("Three");
		root.addColumn(0, b1, b2, b3);

		// Set the column constraints
		ColumnConstraints cc1 = new ColumnConstraints(100);
		cc1.setHalignment(HPos.RIGHT);
		root.getColumnConstraints().add(cc1);

		// Set the row constraints
		RowConstraints rc1 = new RowConstraints(40);
		rc1.setValignment(VPos.TOP);

		RowConstraints rc2 = new RowConstraints(40);
		rc2.setValignment(VPos.TOP);

		RowConstraints rc3 = new RowConstraints(40);
		root.getRowConstraints().addAll(rc1, rc2, rc3);

		// Override the halignment for b2 set in the column
		GridPane.setHalignment(b2, HPos.CENTER);

		// Override the valignment for b1 set in the row
		GridPane.setValignment(b1, VPos.BOTTOM);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("halignemnt and valignment Constraints");
		stage.show();
	}
}
