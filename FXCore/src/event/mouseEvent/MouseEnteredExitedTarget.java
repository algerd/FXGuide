
package event.mouseEvent;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_ENTERED;
import static javafx.scene.input.MouseEvent.MOUSE_EXITED;
import static javafx.scene.input.MouseEvent.MOUSE_ENTERED_TARGET;
import static javafx.scene.input.MouseEvent.MOUSE_EXITED_TARGET;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/*
The MOUSE_ENTERED and MOUSE_EXITED event types provide the functionality needed in most cases.
Sometimes you need these events to go through the normal capture and bubbling phases, so parent nodes
can apply filters and provide default responses. The MOUSE_ENTERED_TARGET and MOUSE_EXITED_TARGET
event types provide these features. They participate in the event capture and bubbling phases.

The MOUSE_ENTERED and MOUSE_EXITED event types are subtypes of the MOUSE_ENTERED_TARGET and
MOUSE_EXITED_TARGET event types. A node interested in the mouse-entered event of its children should
add event filters and handlers for the MOUSE_ENTERED_TARGET type. The child node can add MOUSE_ENTERED,
MOUSE_ENTERED_TARGET, or both event filters and handlers. When the mouse enters the child node, parent
nodes receive the MOUSE_ENTERED_TARGET event. Before the event is delivered to the child node, which is
the target node of the event, the event type is changed to the MOUSE_ENTERED type. Therefore, in the same
event processing, the target node receives the MOUSE_ENTERED event, whereas all its parent nodes receive the
MOUSE_ENTERED_TARGET event. Because the MOUSE_ENTERED event type is a subtype of the MOUSE_ENTERED_
TARGET type, either type of event handler on the target can handle this event. The same would apply to the
mouse-exited event and its corresponding event types.

Sometimes, inside the parent event handler, it is necessary to distinguish the node that fires the
MOUSE_ENTERED_TARGET event. A parent node receives this event when the mouse enters the parent node
itself or any of its child nodes. You can check the target node reference, using the getTarget() method of the
Event class, for equality with the reference of the parent node, inside the event filters and handlers, to know
whether or not the event was fired by the parent.

The program shows how to use the mouse-entered-target and mouse-exited-target events.
It adds a Circle and a CheckBox to an HBox. The HBox is added to the Scene. It adds the mouse-entered-target
and mouse-exited-target event filters to the HBox and event handlers to the Circle. It also adds mouse-entered
and mouse-exited event handlers to the Circle. When the check box is selected, events are consumed by the
HBox, so they do not reach the Circle. Below are a few observations when you run the program:
    • With the check box unselected, when the mouse enters or leaves the Circle,
        the HBox receives the MOUSE_ENTERED_TARGET and MOUSE_EXITED_TARGET events.
        The Circle receives the MOUSE_ENTERED and MOUSE_EXITED events.
    • With the check box selected, the HBox receives the MOUSE_ENTERED_TARGET and
        MOUSE_EXITED_TARGET events and consumes them. The Circle does not receive any events.
    • When the mouse enters or leaves the HBox, the white area in the window, the HBox
        receives the MOUSE_ENTERED and MOUSE_EXITED events, because the HBox is the target
        of the event.
*/
public class MouseEnteredExitedTarget extends Application {
    
	private CheckBox consumeCbx = new CheckBox("Consume Events");

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Circle circle = new Circle(50, 50, 50);
		circle.setFill(Color.GRAY);

		HBox root = new HBox();
		root.setPadding(new Insets(20));
		root.setSpacing(20);
		root.getChildren().addAll(circle, consumeCbx);

		// Create mouse event handlers
		EventHandler<MouseEvent> circleHandler = e -> handleCircle(e);
		EventHandler<MouseEvent> circleTargetHandler = e -> handleCircleTarget(e);
		EventHandler<MouseEvent> hBoxTargetHandler = e -> handleHBoxTarget(e);

		// Add mouse-entered-target and mouse-exited-target event handlers to HBox
		root.addEventFilter(MOUSE_ENTERED_TARGET, hBoxTargetHandler);
		root.addEventFilter(MOUSE_EXITED_TARGET, hBoxTargetHandler);

		// Add mouse-entered-target and mouse-exited-target event handlers to the Circle
		circle.addEventHandler(MOUSE_ENTERED_TARGET, circleTargetHandler);
		circle.addEventHandler(MOUSE_EXITED_TARGET, circleTargetHandler);

		// Add mouse-entered and mouse-exited event handlers to the Circle	
		circle.addEventHandler(MOUSE_ENTERED, circleHandler);
		circle.addEventHandler(MOUSE_EXITED, circleHandler);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Mouse Entered Target and Exited Target Events");
		stage.show();
	}

	public void handleCircle(MouseEvent e) {
		print(e, "Circle Handler");
	}

	public void handleCircleTarget(MouseEvent e) {
		print(e, "Circle Target Handler");
	}

	public void handleHBoxTarget(MouseEvent e) {
		print(e, "HBox Target Filter");
		if (consumeCbx.isSelected()) {
			e.consume();
			System.out.println("HBox consumed the " + e.getEventType() + " event");
		}
	}

	public void print(MouseEvent e, String msg) {
		String type = e.getEventType().getName();
		String source = e.getSource().getClass().getSimpleName();
		String target = e.getTarget().getClass().getSimpleName();
		System.out.println(msg + ": Type=" + type +
		                   ", Target=" + target + 
		                   ", Source=" + source);
	}
}
