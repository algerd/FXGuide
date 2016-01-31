
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
Смотреть описание в Constraints.txt

The program uses column and row constraints to customize columns and rows in a
GridPane.
The first column width is set to 100px fixed width. Each of the second and third columns is set to occupy
35% of the width. If the needed width (35% + 35% + 100px) is less than the available width, the extra width
will be left unused, as has been shown in the figure. The horizontal alignment for the first column is set to
center, so all buttons in the first column are horizontally aligned in the center. The buttons in the other two
columns use left as the horizontal alignment, which is the default setting. We have three rows. However,
the program adds constraints for only the first two rows. The constraints for the third row will be computed
based on its content.
*/
public class GridPaneColRowConstraints extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		GridPane root = new GridPane();
		root.setStyle("-fx-padding: 10;");
		root.setGridLinesVisible(true);

		// Add children
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				Button b = new Button(col + " " + row);
				root.add(b, col, row);
			}
		}

		// Set the fixed width for the first column to 100px
		ColumnConstraints cc1 = new ColumnConstraints(100);

		// Set the percent width for the second column to 30% and the horizontal alignment to center
		ColumnConstraints cc2 = new ColumnConstraints();
		cc2.setPercentWidth(35);
		cc2.setHalignment(HPos.CENTER);

		// Set the percent width for the third column to 50%
		ColumnConstraints cc3 = new ColumnConstraints();
		cc3.setPercentWidth(35);

		// Add all column constraints to the column constraints list
		root.getColumnConstraints().addAll(cc1, cc2, cc3);

		// Create two RowConstraints objects
		RowConstraints rc1 = new RowConstraints();
		rc1.setPercentHeight(35);
		rc1.setValignment(VPos.TOP);

		RowConstraints rc2 = new RowConstraints();
		rc2.setPercentHeight(35);
		rc2.setValignment(VPos.BOTTOM);

		// Add RowConstraints for the first two rows
		root.getRowConstraints().addAll(rc1, rc2);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Setting Column/Row Constraints");
		stage.show();
	}
}
