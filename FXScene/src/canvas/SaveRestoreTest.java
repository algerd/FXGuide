
package canvas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
The current settings for the GraphicsContext are used for all subsequent drawing. For example, if you set the
line width to 5px, all subsequent strokes will be 5px in width. Sometimes you may want to modify the state of
the graphics context temporarily, and after some time, restore the state that existed before the modification.

The save() method stores the current state of the GraphicsContext on a stack. The restore() method
restores the state of the GraphicsContext to the last saved state.
*/
public class SaveRestoreTest extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Canvas canvas = new Canvas(400, 100);
		GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.strokeRect(10, 10, 50, 20);
        // Save the current state
        gc.save();
        // Modify the current state to add an effect and darw the text
        gc.setEffect(new Reflection());
        gc.strokeText("Chatar", 70, 20);
        // Restore the state what it was when the last save() was called and draw the second rectangle
        gc.restore();
        gc.strokeRect(120, 10, 50, 20);
        
		Pane root = new Pane(canvas);	
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Drawing on a Canvas");
		stage.show();
        
	}
		
	
}
