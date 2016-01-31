
package layout.pane.TilePane;

import java.time.Month;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/*
A TilePane lays out its children in a grid of uniformly sized cells, known as tiles. TilePanes work similar
to FlowPanes with one difference: In a FlowPane, rows and columns can be of different heights and widths,
whereas in a TilePane, all rows have the same heights and all columns have the same widths. The width of
the widest child node and the height of the tallest child node are the default widths and heights of all tiles in
a TilePane.

The orientation of a TilePane, which can be set to horizontal or vertical, determines the direction of
the flow for its content. By default, a TilePane has a horizontal orientation. In a horizontal TilePane, the
content flows in rows. The content in rows may flow from left to right (the default) or from right to left. In a
vertical TilePane, the content flow in columns.

You can customize the layout in a TilePane using its properties or setting constraints on individual
children:
    • You can override the default size of tiles.
    • You can customize the alignment of the entire content of a TilePane within its
    content area, which defaults to Pos.TOP_LEFT.
    • You can also customize the alignment of each child node within its tile, which
    defaults to Pos.CENTER.
    • You specify the spacing between adjacent rows and columns, which defaults to 0px.
    • You can specify the preferred number of columns in a horizontal TilePane and the
    preferred number of rows in a vertical TilePane. The default values for the preferred
    number of rows and columns are five.

The TilePane class provides several constructors to create TilePane objects with a specified orientation
(horizontal or vertical), a specified horizontal and vertical spacing between children, and a specified initial
list of children.

The orientation property specifies the flow of content in a TilePane. If it is set to Orientation.HORIZONTAL,
which is the default value, the content flows in rows. If it is set to Orientation.VERTICAL, the content flows
in columns. You can specify the orientation in the constructors or using the setter method.
*/
public class TilePaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) { 
		double hgap = 5.0;
		double vgap = 5.0;
		TilePane root = new TilePane(hgap, vgap);
        //TilePane root = new TilePane(Orientation.VERTICAL, hgap, vgap);
		root.setPrefColumns(5);
        		
		// Add 12 Buttons - each having the name of the 12 months
		for(Month month: Month.values()) {
			Button b = new Button(month.toString());
			b.setMaxHeight(Double.MAX_VALUE);
			b.setMaxWidth(Double.MAX_VALUE);
			root.getChildren().add(b);
		}
		
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Horizontal TilePane");
		stage.show();
	}
}
