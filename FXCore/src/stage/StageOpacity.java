
package stage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/*
The opacity of a stage determines how much you can see through the stage. You can set the opacity of a stage
using the setOpacity(double opacity) method of the Window class. Use the getOpacity() method to get
the current opacity of a stage.
The opacity value ranges from 0.0 to 1.0. Opacity of 0.0 means the stage is fully translucent; opacity of 1.0
means the stage is fully opaque. Opacity affects the entire area of a stage, including its decorations. Not all
JavaFX runtime platforms are required to support opacity. Setting opacity on the JavaFX platforms that do
not support opacity has no effect. The following snippet of code sets the opacity of a state to half-translucent:
*/
public class StageOpacity extends Application {
    
	public static void main(String[] args) {
		Application.launch(args);
	}
    
	@Override
	public void start(Stage stage) {
       
        stage.setTitle("Stage Opacity");
        Group root = new Group(new Button("Hello"));
        Scene scene = new Scene(root, 300, 100);
        stage.setScene(scene);
        // A half-translucent stage
        stage.setOpacity(0.5); 
        stage.show();
	}
}
