
package Dialog;

import java.util.Optional;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/*
Listing describes an application whose user interface consists of a text-input dialog followed by an alert dialog.

The text-input dialog is constructed by passing "Enter some text" to the TextInputDialog(String defaultValue) constructor. 
After setting the dialog's title, header text, and content text, the dialog is displayed and the result is returned. 
If a return value is present, an alert dialog is created with the result text built into the dialog's message. 
The alert dialog is then displayed.
*/
public class TextInputDemo extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}
    
    @Override
    public void start(Stage primaryStage) {
        
       TextInputDialog textInput = new TextInputDialog("Enter some text");
       textInput.setTitle("Enter some text");
       textInput.setHeaderText("Text Input");
       textInput.setContentText("Please enter something:");
       Optional<String> result = textInput.showAndWait();
       
       if (result.isPresent()) {
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setContentText("You entered: " + result.get());
           alert.showAndWait();
        }
    }
}