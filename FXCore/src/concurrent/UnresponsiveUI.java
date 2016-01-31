
package concurrent;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
The program hastwo problems.
Click the Start button and immediately try to click the Exit button. Clicking the Exit button has no effect
until the task finishes. Once you click the Start button, you cannot do anything else on the window, except to
wait for 10 seconds for the task to finish. That is, the application becomes unresponsive for 10 seconds. This
is the reason you named the class UnresponsiveUI.

Inside the loop in the runTask() method, the program prints the status of the task on the standard
output and displays the same in the Label in the window. You see the status updated on the standard
output, but not in the Label.

It is repeated to emphasize that all UI event handlers in JavaFX run on a single thread, which is
the JavaFX Application Thread. When the Start button is clicked, the runTask() method is executed in the
JavaFX Application Thread. When the Exit button is clicked while the task is running, an ActionEvent event
for the Exit button is generated and queued on the JavaFX Application Thread. The ActionEvent handler for
the Exit button is run on the same thread after the thread is done running the runTask() method as part of
the ActionEvent handler for the Start button.

A pulse event is generated when the scene graph is updated. The pulse event handler is also run on the
JavaFX Application Thread. Inside the loop, the text property of the Label was updated ten times, which
generated the pulse events. However, the scene graph was not refreshed to show the latest text for the Label,
as the JavaFX Application Thread was busy running the task and it did not run the pulse event handlers.

Both problems arise because there is only one thread to process all UI event handlers and you ran a
long-running task in the ActionEvent handler for the Start button.
*/
public class UnresponsiveUI extends Application {
    
	Label statusLbl = new Label("Not Started...");
	Button startBtn = new Button("Start");
	Button exitBtn = new Button("Exit");

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Add event handlders to the buttons
		startBtn.setOnAction(e -> runTask());
		exitBtn.setOnAction(e -> stage.close());

		HBox buttonBox = new HBox(5, startBtn, exitBtn);
		VBox root = new VBox(10, statusLbl, buttonBox);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("An Unresponsive UI");
		stage.show();
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
