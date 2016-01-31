
package layout.pane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
Pane is a subclass class of the Region class. It exposes the getChildren() method of the Parent class, which
is the superclass of the Region class, This means that instances of the Pane class and its subclasses can add
any children.
    A Pane provides the following layout features:
    • It can be used when absolute positioning is needed. By default, it positions all its
    children at (0, 0). You need to set the positions of the children explicitly.
    • It resizes all resizable children to their preferred sizes.

By default, a Pane has minimum, preferred, and maximum sizes. Its minimum width is the sum of the
left and right insets; its minimum height is the sum of the top and bottom insets. Its preferred width is the
width required to display all its children at their current x location with their preferred widths; its preferred
height is the height required to display all its children at their current y location with their preferred heights.
Its maximum width and height are set to Double.MAX_VALUE.

You can tell the Pane to compute its preferred size based on its children sizes by resetting its preferred
width and height to the computed width and height:
    Pane root = new Pane();
    // Set the preferred size to 300px wide and 200px tall
    root.setPrefSize(300, 200);
    // Do some processing...
    // Set the default preferred size
    root.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

Tip: A Pane does not clip its content; its children may be displayed outside its bounds.

The program shows how to create a Pane, add two Buttons to it, and how to position the
Buttons. The Pane uses a border to show the area it occupies in the screen. 
Try resizing the window, and you will find that the Pane shrinks and expands.
*/
public class PaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");
		okBtn.relocate(10, 10);
		cancelBtn.relocate(60, 10);

		Pane root = new Pane();
		root.getChildren().addAll(okBtn, cancelBtn);
		root.setStyle("-fx-border-style: solid inside;" + 
		              "-fx-border-width: 3;" + 
		              "-fx-border-color: red;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Panes");
		stage.show();
	}
}
