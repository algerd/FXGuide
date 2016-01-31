
package event;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/*
There are some execution order rules for event filters and handlers for both similar and different nodes:
    - Event filters are called before event handlers. Event filters are executed from the
        topmost parent to the event target in the parent-child order. Event handlers are
        executed in the reverse order of the event filters. That is, the execution of the event
        handlers starts at the event target and moves up in the child-parent order.
    - For the same node, event filters and handlers for a specific event type are called
        before the event filters and handlers for generic types. Suppose you have registered
        event handlers to a node for MouseEvent.ANY and MouseEvent.MOUSE_CLICKED. Event
        handlers for both event types are capable of handling mouse-clicked events. When the
        mouse is clicked on the node, the event handler for the MouseEvent.MOUSE_CLICKED
        event type is called before the event handler for the MouseEvent.ANY event type. Note
        that a mouse-pressed event and a mouse-released event occur before a mouse-clicked
        event occurs. In our example, these events will be handled by the event handler for the
        MouseEvent.ANY event type.
    - The order in which the event filters and handlers for the same event type for a node
        are executed is not specified. There is one exception to this rule. Event handlers
        registered to a node using the addEventHandler() method are executed before the
        event handlers registered using the setOnXXX() convenience methods.

Listing demonstrates the execution order of event handlers for a node. It displays a circle. 
It registers three event handlers for the circle:
    • One for the MouseEvent.ANY event type
    • One for the MouseEvent.MOUSE_CLICKED event type using the addEventHandler() method
    • One for the MouseEvent.MOUSE_CLICKED event type using the setOnMouseClicked() method

Run the program and click inside the circle. The output shows the order in which three event handlers
are called. The order will be similar to that presented in the discussion at the beginning of the section.
*/
public class HandlersOrder extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Circle circle = new Circle(50, 50, 50);
		circle.setFill(Color.CORAL);

		HBox root = new HBox();
		root.getChildren().addAll(circle);
		Scene scene = new Scene(root);

		/* Register three handlers for the circle that can handle mouse-clicked events */
		// This will be called last
		circle.addEventHandler(MouseEvent.ANY, e -> handleAnyMouseEvent(e));

		// This will be called first
		circle.addEventHandler(MouseEvent.MOUSE_CLICKED,
				e -> handleMouseClicked("addEventHandler()", e));

		// This will be called second
		circle.setOnMouseClicked(e -> handleMouseClicked("setOnMouseClicked()", e));

		stage.setScene(scene);
		stage.setTitle("Execution Order of Event Handlers of a Node");
		stage.show();
	}

	public void handleMouseClicked(String registrationMethod, MouseEvent e) {
		System.out.println(registrationMethod + ": MOUSE_CLICKED handler detected a mouse click.");
	}

	public void handleAnyMouseEvent(MouseEvent e) {
		// Print a message only for mouse-clicked events, ignoring
		// other mouse events such as mouse-pressed, mouse-released, etc.
		if (e.getEventType() == MouseEvent.MOUSE_CLICKED) {
			System.out.println("MouseEvent.ANY handler detected a mouse click.");
		}
	}
}
