
package event.DragAndDrop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
When the source node of a drag gesture receives the drag-detected event, you can start a full press-dragrelease
gesture by calling the startFullDrag() method on the source node. The startFullDrag() method
exists in both Node and Scene classes, allowing you to start a full press-drag-release gesture for a node and a
scene. I will simply use only the term node during this discussion.

Tip: The startFullDrag() method can only be called from the drag-detected event handler. Calling this
method from any other place throws an IllegalStateException.

You need to do one more set up to see the full press-drag-release gesture in action. The source node of
the drag gesture will still receive all mouse-drag events as it is under the cursor when a drag is happening.
You need to set the mouseTransparent property of the gesture source to false so the node below it will be
picked and mouse-drag events will be delivered to that node. Set this property to true in the mouse-pressed
event and set it back to false in the mouse-released event.

The program demonstrates a full press-drag-release gesture. The program is similar to
the one show in SimplePressDragRelease.java, except for the following:
    • In the mouse-pressed event handler for the source node, the mouseTransparent
    property for the source node is set to false. It is set back to true in the mouse-released
    event handler.
    • In the drag-detected event handler, the startFullDrag() method is called on the
    source node.

Run the program, press the mouse button on the source node, drag it onto the target node, and, finally,
release the mouse button. The output that follows shows that the target node receives mouse-drag events as
the mouse is dragged inside its bounds. This is the case of a full press-drag-release gesture where the node
over which the mouse drag takes place receives the mouse-drag events.
*/
public class FullPressDragRelease extends Application {
	TextField sourceFld = new TextField("Source Node");
	TextField targetFld = new TextField("Target node");

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Build the UI
		GridPane root = getUI();

		// Add event handlers
		this.addEventHanders();

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A full press-drag-release gesture");
		stage.show();
	}

	private GridPane getUI() {
		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(20);
		pane.addRow(0, new Label("Source Node:"), sourceFld);
		pane.addRow(1, new Label("Target Node:"), targetFld);
		return pane;
	}

	private void addEventHanders() {
		// Add mouse event handlers for the source
		sourceFld.setOnMousePressed(e -> {
			// Make sure the node is not picked
			sourceFld.setMouseTransparent(true);
			print("Source: Mouse pressed");
		});
		
		sourceFld.setOnMouseDragged(e -> print("Source: Mouse dragged"));
		
		sourceFld.setOnDragDetected(e -> {
			// Start a full press-drag-release gesture
			sourceFld.startFullDrag();
			print("Source: Mouse dragged detected");
		});	

		sourceFld.setOnMouseReleased(e -> {
			// Make sure the node is picked
			sourceFld.setMouseTransparent(false);
			print("Source: Mouse released");
		});

		// Add mouse event handlers for the target
		targetFld.setOnMouseDragEntered(e -> print("Target: drag entered"));
		targetFld.setOnMouseDragOver(e -> print("Target: drag over"));
		targetFld.setOnMouseDragReleased(e -> print("Target: drag released"));
		targetFld.setOnMouseDragExited(e -> print("Target: drag exited"));
	}

	private void print(String msg) {
		System.out.println(msg);
	}
}
