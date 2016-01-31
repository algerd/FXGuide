
package layout.pane.FlowPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/*
A FlowPane is a simple layout pane that lays out its children in rows or columns wrapping at a specified
width or height. It lets its children flow horizontally or vertically, and hence the name “flow pane.” You
can specify a preferred wrap length, which is the preferred width for a horizontal flow and the preferred
height for a vertical flow, where the content is wrapped. A FlowPane is used in situations where the relative
locations of children are not important: for example, displaying a series of pictures or buttons. A FlowPane
gives all its children their preferred sizes. Rows and columns may be of different heights and widths. You can
customize the vertical alignments of children in rows and the horizontal alignments of children in columns.

Tip: Children in a horizontal FlowPane may be arranged in rows from left to right or right to left, which is
controlled by the nodeOrientation property declared in the Node class. The default value for this property
is set to NodeOrientation.LEFT_TO_RIGHT. If you want the children to flow right to left, set the property
to NodeOrientation.RIGHT_TO_LEFT. This applies to all layout panes that arrange children in rows
(e.g., HBox, TilePane, etc.).

The orientation of a FlowPane, which can be set to horizontal or vertical, determines the direction of the
flow for its content. In a horizontal FlowPane, the content flows in rows. In a vertical FlowPane, the content
flows in columns.

The orientation property specifies the flow of content in a FlowPane. If it is set to Orientation.HORIZONTAL,
which is the default value, the content flows in rows. If it is set to Orientation.VERTICAL, the content flows
in columns. You can specify the orientation in the constructors or using the setter method.
    // Create a horizontal FlowPane
    FlowPane fpane = new FlowPane();
    ...
    // Change the orientation of the FlowPane to vertical
    fpane.setOrientation(Orientation.VERTICAL);

*/
public class FlowPaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {     
		double hgap = 5;
		double vgap = 10;
		FlowPane root = new FlowPane(hgap, vgap);

		// Add ten buttons to the flow pane
		for(int i = 1; i <= 10; i++) {
			root.getChildren().add(new Button("Button " + i));
		}
		
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Horizontal FlowPane");
		stage.show();
	}
}
