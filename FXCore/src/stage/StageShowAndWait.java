
package stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
You often want to display a dialog box and suspend further processing until it is closed. For example, you
may want to display a message box to the user with options to click yes and no buttons, and you want
different actions performed based on which button is clicked by the user. In this case, when the message box
is displayed to the user, the program must wait for it to close before it executes the next sequence of logic.
Consider the following pseudo-code:
    Option userSelection = messageBox("Close", "Do you want to exit?", YESNO);
    if (userSelection == YES) {
        stage.close();
    }
In this pseudo-code, when the messageBox() method is called, the program needs to wait to execute the
subsequent if statement until the message box is dismissed.
The show() method of the Window class returns immediately, making it useless to open a dialog box
in the above example. You need to use the showAndWait() method, which shows the stage and waits for
it to close before returning to the caller. The showAndWait() method stops processing the current event
temporarily and starts a nested event loop to process other events.

You can have multiple stages open using the showAndWait() method. Each call to the method starts a
new nested event loop. A specific call to the method returns to the caller when all nested event loops created
after this method call have terminated.
This rule may be confusing in the beginning. Let’s look at an example to explain this in detail. Suppose
you have three stages: s1, s2, and s3. Stage s1 is opened using the call s1.showAndWait(). From the code in
s1, stage s2 is opened using the call s2.showAndWait(). At this point, there are two nested event loops: one
created by s1.showAndWait() and another by s2.showAndWait(). The call to s1.showAndWait() will return
only after both s1 and s2 have been closed, irrespective of the order they were closed. The s2.showAndWait()
call will return after s2 has been closed.

Listing contains a program that will allow you to play with the showAndWait() method call using
multiple stages. The primary stage is opened with an Open button. Clicking the Open button opens a
secondary stage using the showAndWait() method. The secondary stage has two buttons—Say Hello and
Open—which will, respectively, will print a message on the console and open another secondary stage.
A message is printed on the console before and after the call to the showAndWait() method. You need to
open multiple secondary stages, print messages by clicking the Say Hello button, close them in any order
you want, and then look at the output on the console.
*/
public class StageShowAndWait extends Application {
    
	protected static int counter = 0;
	protected Stage lastOpenStage;
	
	public static void main(String[] args) {
		Application.launch(args); 
	}
		
	@Override
	public void start(Stage stage) {
        
		VBox root = new VBox(); 
		Button openButton = new Button("Open");
		openButton.setOnAction(e -> open(++counter));
		root.getChildren().add(openButton);
		Scene scene = new Scene(root, 400, 400);
		stage.setScene(scene);
		stage.setTitle("The Primary Stage");
		stage.show();
		
		this.lastOpenStage = stage;
	}
	
	private void open(int stageNumber) {
		Stage stage = new Stage();
		stage.setTitle("#" + stageNumber);

		Button sayHelloButton = new Button("Say Hello");
		sayHelloButton.setOnAction(e -> System.out.println("Hello from #" + stageNumber));

		Button openButton = new Button("Open"); 
		openButton.setOnAction(e -> open(++counter));
		
		VBox root = new VBox(); 
		root.getChildren().addAll(sayHelloButton, openButton);
		Scene scene = new Scene(root, 200, 200);
		stage.setScene(scene);
		stage.setX(this.lastOpenStage.getX() + 50);
		stage.setY(this.lastOpenStage.getY() + 50);
		this.lastOpenStage = stage;
		
		System.out.println("Before stage.showAndWait(): " + stageNumber);
		
		// Show the stage and wait for it to close
		stage.showAndWait();
		
		System.out.println("After stage.showAndWait(): " + stageNumber);
	}
}
