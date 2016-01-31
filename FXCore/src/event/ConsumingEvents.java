
package event;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
An event is consumed by calling its consume() method. The event class contains the method and it is
inherited by all event classes. Typically, the consume() method is called inside the handle() method of the
event filters and handlers.

Consuming an event indicates to the event dispatcher that the event processing is complete and that
the event should not travel any farther in the event dispatch chain. If an event is consumed in an event filter
of a node, the event does not travel to any child node. If an event is consumed in an event handler of a node,
the event does not travel to any parent node.

All event filters or handlers for the consuming node are called, irrespective of which filter or handler
consumes the event. Suppose you have registered three event handlers for a node and the event handler, which
is called first, consumes the event. In this case, the other two event handlers for the node are still called.

If a parent node does not want its child nodes to respond to an event, it can consume the event in its
event filter. If a parent node provides a default response to an event in an event handler, a child node can
provide a specific response and consume the event, thus suppressing the default response of the parent.

Typically, nodes consume most input events after providing a default response. The rule is that all event
filters and handlers of a node are called, even if one of them consumes the event. This makes it possible for
developers to execute their event filters and handlers for a node even if the node consumes the event.

The program adds a Circle, a Rectangle, and a CheckBox to an HBox. The HBox is added to the scene
as the root node. An event handler is added to the Stage, Scene, HBox, and Circle. Notice that you have a
different event handler for the Circle, just to keep the program logic simple. When the check box is selected,
the event handler for the circle consumes the mouse-clicked event, thus preventing the event from traveling
up to the HBox, Scene, and Stage. If the check box is not selected, the mouse-clicked event on the circle
travels from the Circle to the HBox, Scene, and Stage. Run the program and, using the mouse, click the
different areas of the scene to see the effect. Notice that the mouse-clicked event handler for the HBox, Scene,
and Stage are executed, even if you click a point outside the circle, because they are in the event dispatch
chain of the clicked nodes.

Clicking the check box does not execute the mouse-clicked event handlers for the HBox, Scene, and
Stage, whereas clicking the rectangle does. Can you think of a reason for this behavior? The reason is simple.
The check box has a default event handler that takes a default action and consumes the event, preventing it
from traveling up the event dispatch chain. The rectangle does not consume the event, allowing it to travel
up the event dispatch chain.

Tip: Consuming an event by the event target in an event filter has no effect on the execution of any other
event filters. However, it prevents the event bubbling phase from happening. Consuming an event in the
event handlers of the topmost node, which is the head of the event dispatch chain, has no effect on the event
processing
at all.
*/
public class ConsumingEvents extends Application {	
    
	private CheckBox consumeEventCbx = new CheckBox("Consume Mouse Click at Circle");
	
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
		root.getChildren().addAll(circle, rect, consumeEventCbx);

		Scene scene = new Scene(root);		

		// Register mouse-clicked event handlers to all nodes, except the rectangle and checkbox	
		EventHandler<MouseEvent> handler = e -> handleEvent(e);
		EventHandler<MouseEvent> circleMeHandler = e -> handleEventforCircle(e);

		stage.addEventHandler(MOUSE_CLICKED, handler);
		scene.addEventHandler(MOUSE_CLICKED, handler);
		root.addEventHandler(MOUSE_CLICKED, handler);
		circle.addEventHandler(MOUSE_CLICKED, circleMeHandler);

		stage.setScene(scene);
		stage.setTitle("Consuming Events");
		stage.show();
	}
	
	public void handleEvent(MouseEvent e) {
		print(e);	
	}
	
	public void handleEventforCircle(MouseEvent e) {
		print(e);
		if (consumeEventCbx.isSelected()) {
			e.consume();
		}
	}

	public void print(MouseEvent e) {
		String type = e.getEventType().getName();
		String source = e.getSource().getClass().getSimpleName();
		String target = e.getTarget().getClass().getSimpleName();
		
		// Get coordinates of the mouse cursor relative to the event source
		double x = e.getX();
		double y = e.getY();

		System.out.println("Type=" + type + ", Target=" + target + 
		                   ", Source=" +  source + 
		                   ", location(" + x + ", " + y + ")");
	}
}
