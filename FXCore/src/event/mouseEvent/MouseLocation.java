
package event.mouseEvent;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
The MouseEvent class contains methods to give you the location of the mouse when a mouse event occurs.
You can obtain the mouse location relative to the coordinate systems of the event source node, the scene,
and the screen. The getX() and getY() methods give the (x, y) coordinates of the mouse relative to the event
source node. The getSceneX() and getSceneY() methods give the (x, y) coordinates of the mouse relative to
the scene to which the node is added. The getScreenX() and getScreenY() methods give the
(x, y) coordinates of the mouse relative to the screen to which the node is added.

Listing contains the program to show how to use the methods in the MouseEvent class to know
the mouse location. It adds a MOUSE_CLICKED event handler to the stage, and the stage can receive the
notification when the mouse is clicked anywhere in its area. Run the program and click anywhere in
the stage, excluding its title bar if you are running it on the desktop. Each mouse click prints a message
describing the source, target, and location of the mouse relative to the source, scene, and screen.
*/
public class MouseLocation extends Application {
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

		// Add a MOUSE_CLICKED event handler to the stage
		stage.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handleMouseMove(e));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Mouse Location");
		stage.show();
	}
	
	public void handleMouseMove(MouseEvent e) {
		String source = e.getSource().getClass().getSimpleName();
		String target = e.getTarget().getClass().getSimpleName();

		// Mouse location relative to the event source
		double sourceX = e.getX();
		double sourceY = e.getY();

		// Mouse location relative to the scene
		double sceneX = e.getSceneX();
		double sceneY = e.getSceneY();
		
		// Mouse location relative to the screen
		double screenX = e.getScreenX();
		double screenY = e.getScreenY();
		
		System.out.println("Source=" +  source + ", Target=" + target +  
		                   ", Location:" + 
		                   " source(" + sourceX + ", " + sourceY + ")" + 
		                   ", scene(" + sceneX + ", " + sceneY + ")" + 
		                   ", screen(" + screenX + ", " + screenY + ")");
	}	
}
