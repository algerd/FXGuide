
package application;

import javafx.application.Application;
import static javafx.application.ConditionalFeature.SCENE3D;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
The Platform class in the javafx.application package is a utility class used to support platform-related
functionalities.

The runLater() method is used to submit a Runnable task to an event queue, so it is executed on
the JavaFX Application Thread. JavaFX allow developers to execute some of the code only on the JavaFX
Application Thread. Listing creates a task in the init() method that is called on the JavaFX Launcher
Thread. It uses the Platform.runLater() method to submit the task to be executed on the JavaFX
Application Thread later.
*/
public class PlatformRunLater extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() {
		System.out.println("init(): " + Thread.currentThread().getName());

		// Create a Runnable task
		Runnable task = () -> System.out.println("Running the task on the "
				+ Thread.currentThread().getName());

		// Submit the task to be run on the JavaFX Aplication Thread
		Platform.runLater(task);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(new Group(), 400, 100));
		stage.setTitle("Using Platform.runLater() Method");
		stage.show();
        
        /*
        Some features in a JavaFX implementation are optional (or conditional). They may not be available
        on all platforms. Using an optional feature on a platform that does not support the feature does not result
        in an error; the optional feature is simply ignored. Optional features are defined as enum constants in the
        ConditionalFeature enum in the javafx.application package:
            EFFECT          Indicates the availability of filter effects, for example, reflection, shadow, etc.
            INPUT_METHOD    Indicates the availability of text input method.
            SCENE3D         Indicates the availability of 3D features.
            SHAPE_CLIP      Indicates the availability of clipping of a node against an arbitrary shape.
            TRANSPARENT_WINDOW  Indicates the availability of the full window transparency.        
        */
        if (Platform.isSupported(SCENE3D)) {
            System.out.println("3D features are available");
        }
        else {
            System.out.println("3D features are not available");
        }
	}
}
