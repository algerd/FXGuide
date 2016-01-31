
package Progress;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
When you have a long running task, you need to provide a visual feedback to the user about the progress of
the task for a better user experience. JavaFX offers two controls to show the progress:
    • ProgressIndicator
    • ProgressBar

They differ in the ways they display the progress. The ProgressBar class inherits from the
ProgressIndicator class. ProgressIndicator displays the progress in a circular control, whereas
ProgressBar uses a horizontal bar. The ProgressBar class does not add any properties or methods. It
just uses a different shape for the control.

The current progress of a task may be determined or not. If the progress cannot be determined, it is
said to be in an indeterminate state. If the progress is known, it is said to be in a determinate state. The
ProgressIndicator class declares two properties:
    • indeterminate
    • progress

The indeterminate property is a read-only boolean property. If it returns true, it means it is not
possible to determine the progress. A ProgressIndicator in this state is rendered with some kind of
repeated animation. The progress property is a double property. Its value indicates the progress between
0% and 100%. A negative value indicates that the progress is indeterminate. A value between 0 and 1.0
indicates a determinate state with a progress between 0% and 100%. A value greater than 1.0 is treated as 1.0
(i.e., 100% progress).

The program shows how to use ProgressIndicator and ProgressBar controls. Clicking
the Make Progress button increases the progress by 10%. Clicking the Complete Task button completes the
indeterminate tasks by setting their progress to 100%. Typically, the progress properties of these controls
are updated by a long running task when the task progresses to a milestone. You used a button to update the
progress property to keep the program logic simple.

*/
public class ProgressTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		ProgressIndicator indeterminateInd = new ProgressIndicator();
		ProgressIndicator determinateInd = new ProgressIndicator(0);

		ProgressBar indeterminateBar = new ProgressBar();
		ProgressBar determinateBar = new ProgressBar(0);

		Button completeIndBtn = new Button("Complete Task");
		completeIndBtn.setOnAction(e -> indeterminateInd.setProgress(1.0));

		Button completeBarBtn = new Button("Complete Task");
		completeBarBtn.setOnAction(e -> indeterminateBar.setProgress(1.0));

		Button makeProgresstIndBtn = new Button("Make Progress");
		makeProgresstIndBtn.setOnAction(e -> makeProgress(determinateInd));

		Button makeProgresstBarBtn = new Button("Make Progress");
		makeProgresstBarBtn.setOnAction(e -> makeProgress(determinateBar));

		GridPane root = new GridPane();
		root.setHgap(10);
		root.setVgap(5);
		root.addRow(0, new Label("Indeterminate Progress:"), indeterminateInd, completeIndBtn);
		root.addRow(1, new Label("Determinate Progress:"), determinateInd, makeProgresstIndBtn);
		root.addRow(2, new Label("Indeterminate Progress:"), indeterminateBar, completeBarBtn);
		root.addRow(3, new Label("Determinate Progress:"), determinateBar, makeProgresstBarBtn);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;"
        );
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using ProgressIndicator and ProgressBar Controls");
		stage.show();
	}
	
	public void makeProgress(ProgressIndicator p) {		
		double progress = p.getProgress();
		if (progress <= 0) {
			progress = 0.1;
		} else {
			progress = progress + 0.1;
			if (progress >= 1.0) {
				progress = 1.0;
			}
		}
		p.setProgress(progress);
	}
}
