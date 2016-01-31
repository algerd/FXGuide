
package event.windowEvent;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST;

/*
Constants in the WindowEvent Class to Represent Window Event Types:
    ANY                 It is the supertype of all other window event types.
    WINDOW_SHOWING      It occurs just before the window is shown.
    WINDOW_SHOWN        It occurs just after the window is shown.
    WINDOW_HIDING       It occurs just before the window is hidden.
    WINDOW_HIDDEN       It occurs just after the window is hidden.
    WINDOW_CLOSE_REQUEST It occurs when there is an external request to close this window.

The program shows how to use all window events. You may get a different output than
that shown below the code. It adds a check box and two buttons to the primary stage. If the check box is
unselected, external requests to close the window are consumed, thus preventing the window from closing.
The Close button closes the window. The Hide button hides the primary window and opens a new window,
so the user can show the primary window again.

The program adds event handlers to the primary stage for window event types. When the show() method
on the stage is called, the window-showing and window-shown events are generated. When you click the
Hide button, the window-hiding and window-hidden events are generated. When you click the button on the
pop-up window to show the primary window, the window-showing and window-shown events are generated
again. Try clicking the Close icon on the title bar to generate the window-close-request event. If the Can Close
Window check box is not selected, the window is not closed. When you use the Close button to close the
window, the window-hiding and window-hidden events are generated, but not the window-close-request
event, as it is not an external request to close the window.
*/
public class WindowEventApp  extends Application {
	private CheckBox canCloseCbx = new CheckBox("Can Close Window");
		
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button closeBtn = new Button("Close");
		closeBtn.setOnAction(e -> stage.close());
		
		Button hideBtn = new Button("Hide");
		hideBtn.setOnAction(e -> {showDialog(stage); stage.hide(); });

		HBox root = new HBox();
		root.setPadding(new Insets(20));
		root.setSpacing(20); 
		root.getChildren().addAll(canCloseCbx, closeBtn, hideBtn);
		
		// Add window event handlers to the stage
		stage.setOnShowing(e -> handle(e));
		stage.setOnShown(e -> handle(e));
		stage.setOnHiding(e -> handle(e));
		stage.setOnHidden(e -> handle(e));
		stage.setOnCloseRequest(e -> handle(e));
	
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Window Events");
		stage.show();
	}

	public void handle(WindowEvent e) {
		// Consume the event if the CheckBox is not selected
		// thus preventing the user from closing the window
		EventType<WindowEvent> type = e.getEventType();
		if (type == WINDOW_CLOSE_REQUEST && !canCloseCbx.isSelected()) {
			e.consume();
		}		
		System.out.println(type + ": Consumed=" + e.isConsumed());	
	}
	 
	public void showDialog(Stage mainWindow) {
		Stage popup = new Stage();

		Button closeBtn = new Button("Click to Show Main Window");
		closeBtn.setOnAction(e -> { popup.close(); mainWindow.show();});

		HBox root = new HBox();
		root.setPadding(new Insets(20));
		root.setSpacing(20);
		root.getChildren().addAll(closeBtn);

		Scene scene = new Scene(root);
		popup.setScene(scene);
		popup.setTitle("Popup");
		popup.show();
	}
}
