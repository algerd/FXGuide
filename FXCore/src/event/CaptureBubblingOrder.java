
package event;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

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

Listing demonstrates the execution order of the event filters and handlers for different nodes. The
program adds a Circle and a Rectangle to an HBox. The HBox is added to the Scene. An event filter and
an event handler are added to the Stage, Scene, HBox, and Circle for the mouse-clicked event. Run the
program and click anywhere inside the circle. The output shows the order in which filters and handlers are
called. The output contains the event phase, type, target, source, and location. Notice that the source of the
event changes as the event travels from one node to another. The location is relative to the event source.
Because every node uses its own local coordinate system, the same point, where the mouse is clicked, has
different values for (x, y) coordinates relative to different nodes.

If you click the rectangle, you will notice that the output shows the same path for the event through
its parents as it did for the circle. The event still passes through the rectangle, which is the event target.
However, you do not see any output, because you have not registered any event filters or handlers for the
rectangle to output any message. You can click at any point outside the circle and rectangle to see the event
target and the event path.
*/
public class CaptureBubblingOrder extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Circle circle = new Circle (50, 50, 50);
		circle.setFill(Color.CORAL);
		
		Rectangle rect = new Rectangle(100, 100);
		rect.setFill(Color.TAN);

		HBox root = new HBox();
		root.setPadding(new Insets(20));
		root.setSpacing(20);
		root.getChildren().addAll(circle, rect);
		
		Scene scene = new Scene(root);
		
		// Create two EventHandlders
		EventHandler<MouseEvent> filter = e -> handleEvent("Capture", e);
		EventHandler<MouseEvent> handler = e -> handleEvent("Bubbling", e);

		// Register filters
		stage.addEventFilter(MOUSE_CLICKED, filter);
		scene.addEventFilter(MOUSE_CLICKED, filter);
		root.addEventFilter(MOUSE_CLICKED, filter);
		circle.addEventFilter(MOUSE_CLICKED, filter);

		// Register handlers
		stage.addEventHandler(MOUSE_CLICKED, handler);
		scene.addEventHandler(MOUSE_CLICKED, handler);
		root.addEventHandler(MOUSE_CLICKED, handler);
		circle.addEventHandler(MOUSE_CLICKED, handler);

		stage.setScene(scene);
		stage.setTitle("Event Capture and Bubbling Execution Order");
		stage.show();
	}
	
	public void handleEvent(String phase, MouseEvent e) {
		String type = e.getEventType().getName();
		String source = e.getSource().getClass().getSimpleName();
		String target = e.getTarget().getClass().getSimpleName();
		
		// Get coordinates of the mouse cursor relative to the event source
		double x = e.getX();
		double y = e.getY();

		System.out.println(phase + ": Type=" + type + 
		                   ", Target=" + target + ", Source=" +  source + 
		                   ", location(" + x + ", " + y + ")");
	}
}
