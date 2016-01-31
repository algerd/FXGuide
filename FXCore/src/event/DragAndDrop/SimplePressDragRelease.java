
package event.DragAndDrop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
The simple press-drag-release gesture is the default drag gesture. It is used when the drag gesture involves
only one node—the node on which the gesture was initiated. During the drag gesture, all MouseDragEvent
types—mouse-drag entered, mouse-drag over, mouse-drag exited, mouse, and mouse-drag released—are
delivered only to the gesture source node. In this case, when the mouse button is pressed, the topmost node
is picked and all subsequent mouse events are delivered to that node until the mouse button is released.
When the mouse is dragged onto another node, the node on which the gesture was started is still under the
cursor and, therefore, no other nodes receive the events until the mouse button is released.

The program demonstrates a case of the simple press-drag-release gesture. It adds two
TextFields to a scene: one is called the source node and the other the target node. Event handlers are added
to both nodes. The target node adds MouseDragEvent handlers to detect any mouse-drag event on it. Run
the program, press the mouse button on the source node, drag it onto the target node, and, finally, release
the mouse button. The output that follows shows that the source node receives all mouse-drag events. The
target node does not receive any mouse-drag events. This is the case of a simple press-drag-release gesture
where the node initiating the drag gesture receives all mouse-drag events.

Note that the drag-detected event is generated once after the mouse is dragged. The MouseEvent object
has a dragDetect flag, which can be set in the mouse-pressed and mouse-dragged events. If it is set to true,
the subsequent event that is generated is the drag-detected event. The default is to generate it after the
mouse-dragged event. If you want to generate it after the mouse-pressed event, not the mouse-dragged
event, you need to modify the event handlers:
    sourceFld.setOnMousePressed(e -> {
        print("Source: Mouse pressed");
        // Generate drag detect event after the current mouse pressed event
        e.setDragDetect(true);
    });
    sourceFld.setOnMouseDragged(e -> {
        print("Source: Mouse dragged");
        // Suppress the drag detected default event generation after mouse dragged
        e.setDragDetect(false);
    });
*/
public class SimplePressDragRelease extends Application {
    
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
		stage.setTitle("A simple press-drag-release gesture");
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
		sourceFld.setOnMousePressed(e -> print("Source: pressed"));
		sourceFld.setOnMouseDragged(e -> print("Source: dragged"));
		sourceFld.setOnDragDetected(e -> print("Source: dragged detected"));
		sourceFld.setOnMouseReleased(e -> print("Source: released"));

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
