// GridPaneForm.java
package layout.pane.GridPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/*
All children in the first row span only one cell. The “Description” Label in the second row spans two
columns (c0 and c1) and the Cancel button only one column. The TextArea in the third row spans two
columns (c0 and c1). The Label in the fourth row spans three columns (c0, c1, and c1). The following
snippet of code places all children in the grid.

If we add the GridPane to a scene, it will give us the desired look of the form, but not the desired resizing
behavior. The children will not resize correctly on resizing the window. We need to specify the correct
resizing behavior for some of the children.
    • The OK and Cancel buttons should be of the same size.
    • The TextField to enter name should expand horizontally.
    • The TextArea to enter the description should expand horizontally and vertically.
    • The Label used as the status bar at the bottom should expand horizontally.

Making the OK and Cancel buttons the same size is easy. By default, a GridPane resizes its children to
fill their cells, provided the maximum size of the children allows it. The maximum size of a Button is
clamped to its preferred size. We need to set the maximum size of the OK button big enough, so it can
expand to fill the width of its cell, which would be the same as the preferred width of the widest node in its
column (the Cancel button).

By default, the rows and columns in a GridPane stay at their preferred size when the GridPane is resized.
Their horizontal and vertical grow constraints specify how they grow when additional space is available. To
let the name, description, and status bar fields grow when the GridPane is expanded, we will set their hgrow
and vgrow constraints appropriately.

When the GridPane is expanded horizontally, the second column, occupied by the name field, grows by
taking the extra available width. It makes the description and status bar fields fill the extra width generated
in the second column.
When the GridPane is expanded vertically, the third row, occupied by the description field, grows by
taking the extra available height. The maximum size of a TextArea is unbounded. That is, it can grow to fill
the available space in both directions.
*/
public class GridPaneForm extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// A Label and a TextField 
		Label nameLbl = new Label("Name:");
		TextField nameFld = new TextField();

		// A Label and a TextArea
		Label descLbl = new Label("Description:");
		TextArea descText = new TextArea();
		descText.setPrefColumnCount(20);
		descText.setPrefRowCount(5);

		// Two buttons
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");
		
		// A Label used as a status bar
		Label statusBar = new Label("Status: Ready");
		statusBar.setStyle("-fx-background-color: lavender;" +
		                   "-fx-font-size: 7pt;" +
		                   "-fx-padding: 10 0 0 0;");
		
		// Create a GridPane and set its background color to lightgray
		GridPane root = new GridPane();
		root.setStyle("-fx-background-color: lightgray;");

		// Add children to the GridPane
		root.add(nameLbl,   0, 0, 1, 1); // (c0, r0, colspan=1, rowspan=1)
		root.add(nameFld,   1, 0, 1, 1); // (c1, r0, colspan=1, rowspan=1)
        root.add(okBtn,     2, 0, 1, 1); // (c2, r0, colspan=1, rowspan=1)
		root.add(descLbl,   0, 1, 3, 1); // (c0, r1, colspan=3, rowspan=1)
        // Несмотря что для descLbl colspan=3, кнопка cancelBtn станет в позицию c2, r1, проигнорировав колспаны
        root.add(cancelBtn, 2, 1, 1, 1); // (c2, r1, colspan=1, rowspan=1)      
		root.add(descText,  0, 2, 2, 1); // (c0, r2, colspan=2, rowspan=1)			
		root.add(statusBar, 0, 3, GridPane.REMAINING, 1); 

		/* Set constraints for children to customize their resizing behavior */

		// The max width of the OK button should be big enough, so it can fill the width of its cell
		okBtn.setMaxWidth(Double.MAX_VALUE);
		
		// The name field in the first row should grow horizontally
		GridPane.setHgrow(nameFld, Priority.ALWAYS);

		// The description field in the third row should grow vertically
		GridPane.setVgrow(descText, Priority.ALWAYS);

		// The status bar in the last should fill its cell 
		statusBar.setMaxWidth(Double.MAX_VALUE);
        /* 
        Debug:
        Sometimes, you may want to show the grid without showing the children to get an idea on how the grid
        is formed. You can do so by making all children invisible. The GridPane computes the size of the grid for all
        managed children irrespective of their visibility.
        */
        /*
        // Make the grid lines visible
        root.setGridLinesVisible(true);
        // Make all children invisible to see only grid lines
        nameLbl.setVisible(false);
        nameFld.setVisible(false);
        okBtn.setVisible(false);
        descLbl.setVisible(false);
        cancelBtn.setVisible(false);
        descText.setVisible(false);
        statusBar.setVisible(false);
        */
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Creating Forms Using a GridPane");
		stage.show();
	}
}
