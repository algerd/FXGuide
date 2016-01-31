
package stage;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/*
A Stage object must be created and modified on the JavaFX Application Thread. Recall that the start()
method of the Application class is called on the JavaFX Application Thread, and a primary Stage is created
and passed to this method. Note that the primary stage that is passed the start() method is not shown.
You need to call the show() method to show it.

Если у Stage stage не был вызван метод show(), то вызов у неё close() не остановит программу.
В этом случае необходимо принудительно останавливать программу с помощью Ctrl + Alt + Del 
или Ctrl + C() (If you are using the command prompt) или Platform.exit();

The close() method of the Stage class has the same effect as calling the hide() method of the
Window class. The JavaFX API documentation does not mention that attempting to close a not showing window
has no effect.
*/
public class StageRunClose extends Application {
    
	public static void main(String[] args) {
		Application.launch(args);
	}
    
	@Override
	public void start(Stage stage) {
        
        stage.show(); // First show the stage
        stage.close(); // Now close it
        
		// Platform.exit(); // Exit the application
	}
}
