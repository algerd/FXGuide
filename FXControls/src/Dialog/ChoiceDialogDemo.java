
package Dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;

/*
Listing describes an application whose user interface consists of a choice dialog followed by an alert dialog.

The choice dialog is constructed by passing "bananas" and fruits to the ChoiceDialog(T defaultChoice, Collection choices) constructor. 
After setting the dialog's title, header text, and content text, the dialog is displayed and the result is returned. 
If a return value is present, an alert dialog is created with the result text built into the dialog's message. 
The alert dialog is then displayed.
*/
public class ChoiceDialogDemo extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}
    
    @Override
    public void start(Stage primaryStage) {
        
        List<String> fruits = new ArrayList<>();
        fruits.add("apples");
        fruits.add("bananas");
        fruits.add("cherries");
        fruits.add("oranges");
        ChoiceDialog<String> choice = new ChoiceDialog<>("bananas", fruits);
        choice.setTitle("Fruits");
        choice.setHeaderText("Fruits");
        choice.setContentText("Choose your fruit:");
        Optional<String> result = choice.showAndWait();
        
        if (result.isPresent()) {
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setContentText(result.get() + " are great!");
           alert.showAndWait();
        }
    }
    
}