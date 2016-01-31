
package concurrent;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
Продолжение(правильная реализация) BadUI и UnresponsiveUI.

The program replaces the statement
    statusLbl.setText(status);

in the BadUI class with the statement
    // Update the Label on the JavaFx Application Thread
    Platform.runLater(() -> statusLbl.setText(status));

Now, setting the text property for the Label takes place on the JavaFX Application Thread. The
ActionEvent handler of the Start button runs the task in a background thread, thus freeing up the JavaFX
Application Thread to handle user actions. The status of the task is updated in the Label regularly. You can
click the Exit button while the task is being processed.

Did you overcome the restrictions imposed by the event-dispatching threading model of the JavaFX?
The answer is yes and no. You used a trivial example to demonstrate the problem. You have solved the trivial
problem. However, in a real world, performing a long-running task in a GUI application is not so trivial. For
example, your task-running logic and the UI are tightly coupled as you are referencing the Label inside the
runTask() method, which is not desirable in a real world. Your task does not return a result, nor does it have
a reliable mechanism to handle errors that may occur. Your task cannot be reliably cancelled, restarted, or
scheduled to be run at a future time.
*/
public class ResponsiveUI extends Application {
    
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
		stage.setTitle("A Responsive UI");
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

				// Update the Label on the JavaFx Application Thread
				Platform.runLater(() -> statusLbl.setText(status));
			
				System.out.println(status);
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
