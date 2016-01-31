
package stage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/*
The Stage class has a fullScreen property that specified whether a stage should be displayed in full-screen
mode. The implementation of full-screen mode depends on the platform and profile. If the platform does
not support full-screen mode, the JavaFX runtime will simulate it by displaying the stage maximized and
undecorated. A stage may enter full-screen mode by calling the setFullScreen(true) method. When a
stage enters full-screen mode, a brief message is displayed about how to exit the full-screen mode: You will
need to press the ESC key to exit full-screen mode. You can exit full-screen mode programmatically
by calling the setFullScreen(false) method. Use the isFullScreen() method to check if a stage is in
full-screen mode.
*/
public class StageFullScreen extends Application {
    
	public static void main(String[] args) {
		Application.launch(args);
	}
    
	@Override
	public void start(Stage stage) {
            
        Button closeButton = new Button("Close");
		closeButton.setOnAction(e -> stage.close());       
        Group root = new Group(closeButton);
        Scene scene = new Scene(root);    
        stage.setTitle("Full-Screen Stage");
        stage.setScene(scene);
        if (!stage.isFullScreen()) {
            stage.setFullScreen(true);
        }        
        stage.show();
	}
}
