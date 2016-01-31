
package event.mouseEvent;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
The Node class has a mouseTransparent property to control whether or not a node and its children receive
mouse events. Contrast the pickOnBounds and mouseTransparent properties: The former determines the
area of a node that generates mouse events, and the latter determines whether or not a node and its children
generate mouse events, irrespective of the value of the former. The former affects only the node on which it
is set; the latter affects the node on which it is set and all its children.

The code in shows the effects of the mouseTransparent property of a Circle. This is a variant
of the program in PickOnBounds.java. It displays a window that is very similar to the one shown in Figure 9-9. When
the check box MouseTransparency is selected, it sets the mouseTransparent property of the circle to true.
When the check box is unselected, it sets the mouseTransparent property of the circle to false.

Click the circle, in the gray area, when the check box is selected and all mouse-clicked events will be
delivered to the rectangle. This is because the circle is mouse transparent and it lets the mouse events pass
through. Unselect the check box, and all mouse-clicks in the gray area are delivered to the circle. Note that
clicking the red area always delivers the event to the rectangle, because the pickOnBounds property for the
circle is set to false by default. The output shows the node that receives the mouse-clicked events.
*/
public class MouseTransparency extends Application {
    
	private CheckBox mouseTransparentCbx = new CheckBox("Mouse Transparent");
	Circle circle = new Circle(50, 50, 50, Color.LIGHTGRAY);

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Rectangle rect = new Rectangle(100, 100);
		rect.setFill(Color.RED);

		Group group = new Group();
		group.getChildren().addAll(rect, circle);
		
		HBox root = new HBox();
		root.setPadding(new Insets(20));
		root.setSpacing(20);
		root.getChildren().addAll(group, mouseTransparentCbx);

		// Add MOUSE_CLICKED event handlers to the circle and rectangle
		circle.setOnMouseClicked(e -> handleMouseClicked(e));
		rect.setOnMouseClicked(e -> handleMouseClicked(e));

		// Add an Action handler to the checkbox
		mouseTransparentCbx.setOnAction(e -> handleActionEvent(e));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Mouse Transparency");
		stage.show();
	}

	public void handleMouseClicked(MouseEvent e) {
		String target = e.getTarget().getClass().getSimpleName();
		String type = e.getEventType().getName();
		System.out.println(type + " on " + target);
	}

	public void handleActionEvent(ActionEvent e) {
		if (mouseTransparentCbx.isSelected()) {
			circle.setMouseTransparent(true);
		} else {
			circle.setMouseTransparent(false);
		}
	}
} 
