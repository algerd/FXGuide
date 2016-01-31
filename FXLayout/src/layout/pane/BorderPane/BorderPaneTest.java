
package layout.pane.BorderPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
A BorderPane divides its layout area into five regions: top, right, bottom, left, and center. You can place at
most one node in each of the five regions. 
Any of the regions may be null. If a region is null, no space is allocated for it.

In a typical Windows application, a screen uses the five regions to places its content.
    • A menu or a toolbar at the top
    • A status bar at the bottom
    • A navigation panel on the left
    • Additional information on the right
    • Main content in the center

A BorderPane satisfies all the layout requirements for a typical Windows-based GUI screen. This is the
reason that a BorderPane is most often used as the root node for a scene. Typically, you have more than five
nodes in a window. If you have more than one node to place in one of the five regions of a BorderPane, add
the nodes to a layout pane: for example, an HBox, a VBox, etc., and then add the layout pane to the desired
region of the BorderPane.

A BorderPane uses the following resizing policies for its children:
    • The children in the top and bottom regions are resized to their preferred heights. Their
        widths are extended to fill the available extra horizontal space, provided the maximum
        widths of the children allow extending their widths beyond their preferred widths.
    • The children in the right and left regions are resized to their preferred widths. Their
        heights are extended to fill the extra vertical space, provided the maximum heights of
        the children allow extending their heights beyond their preferred heights.
    • The child node in the center will fill the rest of the available space in both directions.

Children in a BorderPane may overlap if it is resized to a smaller size than its preferred size. The
overlapping rule is based on the order in which the children are added. The children are drawn in the order
they are added. This means that a child node may overlap all child nodes added prior to it. Suppose regions
are populated in the order of right, center, and left. The left region may overlap the center and right regions,
and the center region may overlap the right region.

Tip: You can set the alignments for all children within their regions. You can set the margins for children.
As with all layout panes, you can also style a BorderPane with CSS.

The BorderPane class declares five properties named top, right, bottom, left, and center that store the
reference of five children in the five regions. Use the setters for these properties to add a child node to any
of the five regions. For example, use the setTop(Node topChild) method to add a child node to the top
region. To get the reference of the children in any of the five regions, use the getters for these properties.
For example, the getTop() method returns the reference of the child node in the top region.
    // Create an empty BorderPane and add a text node in each of the five regions
    BorderPane bpane = new BorderPane();
    bpane.setTop(new Text("Top"));
    bpane.setRight(new Text("Right"));
    bpane.setBottom(new Text("Bottom"));
    bpane.setLeft(new Text("Left"));
    bpane.setCenter(new Text("Center

Tip: Do not use the ObservableList<Node>, which is returned by the getChildren() method of the
BorderPane, to add children to a BorderPane. The children added to this list are ignored. Use the top, right,
bottom, left, and center properties instead.
*/
public class BorderPaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Set the top and left child nodes to null
		Node top = null;
		Node left = null;
		
		// Build the content nodes for the center region
		VBox center = getCenter();

		// Create the right child node
		Button okBtn = new Button("Ok");
		Button cancelBtn = new Button("Cancel");

		// Make the OK and cancel buttons the same size
		okBtn.setMaxWidth(Double.MAX_VALUE); 
		VBox right = new VBox(okBtn, cancelBtn);
		right.setStyle("-fx-padding: 10;");

		// Create the bottom child node
		Label statusLbl = new Label("Status: Ready");
		HBox bottom = new HBox(statusLbl); 
		BorderPane.setMargin(bottom, new Insets(10, 0, 0, 0));
		bottom.setStyle("-fx-background-color: lavender;" +
		                "-fx-font-size: 7pt;" +
		                "-fx-padding: 10 0 0 0;" );

		BorderPane root = new BorderPane(center, top, right, bottom, left);
		root.setStyle("-fx-background-color: lightgray;");
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using a BorderPane");
		stage.show();	
	}

	private VBox getCenter() {
		// A Label and a TextField in an HBox
		Label nameLbl = new Label("Name:");
		TextField nameFld = new TextField();
		HBox.setHgrow(nameFld, Priority.ALWAYS);
		HBox nameFields = new HBox(nameLbl, nameFld);

		// A Label and a TextArea
		Label descLbl = new Label("Description:");		
		TextArea descText = new TextArea();
		descText.setPrefColumnCount(20);
		descText.setPrefRowCount(5);
		VBox.setVgrow(descText, Priority.ALWAYS);
		
		// Box all controls in a VBox
		VBox center = new VBox(nameFields, descLbl, descText);

		return center;
	}
}
