
package concurrent;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
Продолжение UnresponsiveUI.

You cannot change the single-threaded model for handling the UI events. 
You must not run long-running tasks in the event handlers. Sometimes, it is a
business need to process a big job as part of a user action. The solution is to run the long-running tasks in
one or more background threads, instead of in the JavaFX Application Thread.

The program is your first, incorrect attempt to provide a solution. The ActionEvent
handler for the Start button calls the startTask() method, which creates a new thread and runs the
runTask() method in the new thread.

The following statement in the runTask() method generated the exception
    statusLbl.setText(status);

The JavaFX runtime checks that a live scene must be accessed from the JavaFX Application Thread. The
runTask() method is run on a new thread, named Thread-4 as shown in the stack trace, which is not the
JavaFX Application Thread. The foregoing statement sets the text property for the Label, which is part of a
live scene graph, from the thread other than the JavaFX Application Thread, which is not permissible.
*/
public class BadUI extends Application {
    
	Label statusLbl = new Label("Not Started...");
	Button startBtn = new Button("Start");	
	Button exitBtn = new Button("Exit");

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Add event handlders to the buttons
		startBtn.setOnAction(e -> startTask());
		exitBtn.setOnAction(e -> stage.close());

		HBox buttonBox = new HBox(5, startBtn, exitBtn);
		VBox root = new VBox(10, statusLbl, buttonBox);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Bad UI");
		stage.show();
	}

	public void startTask() {
		// Create a Runnable
		Runnable task = () -> runTask();

		// Run the task in a background thread
		Thread backgroundThread = new Thread(task);

		// Terminate the running thread if the application exits
		backgroundThread.setDaemon(true);

		// Start the thread
		backgroundThread.start();
	}

	public void runTask() {
		for(int i = 1; i <= 10; i++) {
			try {
				String status = "Processing " + i + " of " + 10;
				statusLbl.setText(status);
				System.out.println(status);
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
