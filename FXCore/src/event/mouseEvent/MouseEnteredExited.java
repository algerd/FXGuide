
package event.mouseEvent;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import static javafx.scene.input.MouseEvent.MOUSE_ENTERED; 
import static javafx.scene.input.MouseEvent.MOUSE_EXITED; 

/*
Four mouse event types deal with events when the mouse enters or exits a node:
    • MOUSE_ENTERED
    • MOUSE_EXITED
    • MOUSE_ENTERED_TARGET
    • MOUSE_EXITED_TARGET

When the mouse enters a node, a MOUSE_ENTERED event is generated. When the mouse leaves a node, a
MOUSE_EXITED event is generated. These events do not go through the capture and bubbling phases. That is,
they are delivered directly to the target node, not to any of its parent nodes.

The program in Listing shows how mouse-entered and mouse-exited events are delivered. It shows a circle with gray fill inside an HBox. 
Event handlers for mouse-entered and mouse-exited events are added to the HBox and the Circle. Run the
program and move the mouse in and out of the circle. When the mouse enters the white area in the window,
its MOUSE_ENTERED event is delivered to the HBox. When you move the mouse in and out of the circle, the
output shows that the MOUSE_ENTERED and MOUSE_EXITED events are delivered only to the Circle, not to the
HBox. Notice that in the output the source and target of these events are always the same, proving that the
capture and bubbling phases do not occur for these events. When you move the mouse in and out of the
circle, keeping it in the white area, the MOUSE_EXITED event for the HBox does not fire, as the mouse stays on
the HBox. To fire the MOUSE_EXITED event on the HBox, you will need to move the mouse outside the scene
area, for example, outside the window or over the title bar of the window.
*/
public class MouseEnteredExited  extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		Circle circle = new Circle (50, 50, 50);
		circle.setFill(Color.GRAY);

		HBox root = new HBox();		
		root.setPadding(new Insets(20));
		root.setSpacing(20);		
		root.getChildren().addAll(circle);

		// Create a mouse event handler
		EventHandler<MouseEvent> handler = e -> handle(e);  

		// Add mouse-entered and mouse-exited event handlers to the HBox
		root.addEventHandler(MOUSE_ENTERED, handler);
		root.addEventHandler(MOUSE_EXITED, handler);

		// Add mouse-entered and mouse-exited event handlers to the Circle
		circle.addEventHandler(MOUSE_ENTERED, handler);
		circle.addEventHandler(MOUSE_EXITED, handler);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Mouse Entered and Exited Events");
		stage.show();
	}
	
	public void handle(MouseEvent e) {
		String type = e.getEventType().getName();
		String source = e.getSource().getClass().getSimpleName();
		String target = e.getTarget().getClass().getSimpleName();
		System.out.println("Type=" + type + ", Target=" + target + ", Source=" +  source);
	}
}
