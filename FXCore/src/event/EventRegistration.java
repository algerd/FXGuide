
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
You can use the addEventFilter() and addEventHandler() methods to register event filters and handlers to
nodes, respectively. These methods are defined in the Node class, Scene class, and Window class. Some classes
(e.g., MenuItem and TreeItem) can be event targets; however, they are not inherited from the Node class. The
classes provide only the addEventHandler() method for event handlers registration, such as:
    • <T extends Event> void addEventFilter(EventType<T> eventType, EventHandler<? super T> eventFilter)
    • <T extends Event> void addEventHandler(EventType<T> eventType, EventHandler<? super T> eventHandler)

These methods have two parameters. The first parameter is the event type and the second is an object
of the EventHandler interface.
Registering the same EventHandler object as event filters as well as handlers is allowed.

Tip: You can add multiple event filters and events for a node using the addEventFilter() and
addEventHandler() methods. You need to call these methods once for every instance of the event filters
and handlers that you want to add.

To unregister an event filter and an event handler, you need to call the removeEventFilter() and
removeEventHandler() methods, respectively:
    • <T extends Event> void removeEventFilter(EventType<T> eventType, EventHandler<? super T> eventFilter)
    • <T extends Event> void removeEventHandler(EventType<T> eventType, EventHandler<? super T> eventHandler)

Note that once an EventHandler is removed from a node, its handle() method is not called when the event occurs

Listing has the complete program to demonstrate the handling of the mouse-clicked events of a
Circle object. It uses an event filter and an event handler. Run the program and click inside the circle. When
the circle is clicked, the event filter is called first, followed by the event handler. This is evident from the
output. The mouse-clicked event occurs every time you click any point inside the circle. If you click outside
the circle, the mouse-clicked event still occurs; however, you do not see any output because you have not
registered event filters or handlers on the HBox, Scene, and Stage.

EventType<T> eventType - это константы в классах событий, которые указывают типы событий для данного общего класса события.
Напр., для MouseEvent это DRAG_DETECTED, MOUSE_CLICKED, MOUSE_ENTERED и т.д. Смотреть UML Event.cdg или конкретные классы 
событий.
*/
public class EventRegistration extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Circle circle = new Circle (100, 100, 50);
		circle.setFill(Color.CORAL);

		// Create a MouseEvent filter
		EventHandler<MouseEvent> mouseEventFilter = 
			e -> System.out.println("Mouse event filter has been called.");

		// Create a MouseEvent handler
		EventHandler<MouseEvent> mouseEventHandler = 
			e -> System.out.println("Mouse event handler has been called.");

		// Register the MouseEvent filter and handler to the Circle for mouse-clicked events
		circle.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventFilter);
		circle.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandler);
        
        // Unregister the MouseEvent filter and handler to the Circle for mouse-clicked events
        //circle.removeEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventFilter);
        //circle.removeEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandler);

		HBox root = new HBox();
		root.getChildren().add(circle);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Registering Event Filters and Handlers");
		stage.show();
		stage.sizeToScene();
	}
}
