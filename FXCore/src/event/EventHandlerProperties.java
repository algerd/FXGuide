
package event;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/*
The Node, Scene, and Window classes contain event properties to store event handlers of some selected
event types. The property names use the event type pattern. They are named as onXXX. For example, the
onMouseClicked property stores the event handler for the mouse-clicked event type; the onKeyTyped
property stores the event handler for the key-typed event, and so on. You can use the setOnXXX() methods
of these properties to register event handlers for a node. The setOnXXX() methods in various classes are known as
convenience methods for registering event handlers.

You need to remember some points about the onXXX convenience properties:
    - Event handlers, not event filters. If you need to register event filters, 
        use the addEventFilter() method.
    - They only support the registration of one event handler for a node. Multiple event
        handlers for a node may be registered using the addEventHandler() method.
    - These properties exist only for the commonly used events for a node type. For
        example, the onMouseClicked property exists in the Node and Scene classes, but not
        the Window class; the onShowing property exists in the Window class, but not in the
        Node and Scene classes.
    - Event handlers registered to a node using the addEventHandler() method are executed before the
        event handlers registered using the setOnXXX() convenience methods.

The convenience event properties do not provide a separate method to unregister the event handler.
Setting the property to null unregisters the event handler that has already been registered.

Classes that define the onXXX event properties also define getOnXXX() getter methods that return the
reference of the registered event handler. If no event handler is set, the getter method returns null.

The program works the same as the program EventRegistration.java. This time, you have used the
onMouseClicked property of the Node class to register the mouse-clicked event handler for the circle. Notice
that to register the event filter, you have to use the addEventFilter() method as before. 
*/
public class EventHandlerProperties extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Circle circle = new Circle (100, 100, 50);
		circle.setFill(Color.CORAL);

		HBox root = new HBox();
		root.getChildren().add(circle);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using convenience event handler properties");
		stage.show();
		stage.sizeToScene();

		// Create a MouseEvent filter
		EventHandler<MouseEvent> eventFilter = 
			e -> System.out.println("Mouse event filter has been called.");

		// Create a MouseEvent handler
		EventHandler<MouseEvent> eventHandler = 
			e -> System.out.println("Mouse event handler has been called.");

		// Register the filter using the addEventFilter() method
		circle.addEventFilter(MouseEvent.MOUSE_CLICKED, eventFilter);
		
		// Register the handler using the setter method for the onMouseCicked convenience event property 
		circle.setOnMouseClicked(eventHandler);
        
        // Later, when you are no longer interested in processing the mouse-clicked event, unregister it.
        //circle.setOnMouseClicked(null);
	}
}
