
package stage;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*
You can set whether a user can or cannot resize a stage by using its setResizable(boolean resizable)
method. Note that a call to the setResizable() method is a hint to the implementation to make the stage
resizable. By default, a stage is resizable. Sometimes, you may want to restrict the use to resize a stage within
a range of width and height. The setMinWidth(), setMinHeight(), setMaxWidth(), and setMaxHeight()
methods of the Stage class let you set the range within which the user can resize a stage.

Tip: Calling the setResizable(false) method on a Stage object prevents the user from resizing the stage.
You can still resize the stage programmatically.

It is often required to open a window that takes up the entire screen space. To achieve this, you need to
set the position and size of the window to the available visual bounds of the screen. Listing 4-8 provides the
program to illustrate this. It opens an empty stage, which takes up the entire visual area of the screen.
*/
public class StageResizeMax extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setScene(new Scene(new Group()));
		stage.setTitle("A Maximized Stage");

		// Set the position and size of the stage equal to the position and size of the screen
		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
		stage.setX(visualBounds.getMinX());
		stage.setY(visualBounds.getMinY());
		stage.setWidth(visualBounds.getWidth());
		stage.setHeight(visualBounds.getHeight());

		// Show the stage		
		stage.show();
	}
}
