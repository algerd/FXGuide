
package Dialog;

import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
Listing describes an application whose user interface consists of eight buttons in a horizontal line. 
Clicking each button demonstrates either the confirmation, error, information, or warning dialog with custom or default header text.

The Alert(Alert.AlertType alertType) constructor is called to initialize an Alert dialog object to a specific alertType. 
The dialog's title is specified by calling void setTitle(String title), its header text is specified by calling void setHeaderText(String headerText), 
and its content text is set by calling void setContentText(String contentText).

The Optional<R> showAndWait() method presents the dialog and waits for a user response before dismissing the dialog. 
The returned java.util.Optional object is a container that may or may not contain a non-null result value.
*/
public class AlertDemo extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}
    
   @Override
   public void start(Stage primaryStage) {
       
        // 1. Confirmation dialog with header message 
        Button btn1 = new Button("Demo 1");
        btn1.setOnAction(
            ae -> {          
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Exit application");
                alert.setContentText("Do you want to exit the application?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Platform.exit();
                }   
            }
        );
        
        // 2. Confirmation dialog without header message
        Button btn2 = new Button("Demo 2");
        btn2.setOnAction(
            ae -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("Do you want to exit the application?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                   Platform.exit();
                }
            }
        );
        
        // 3. Error dialog with header message
        Button btn3 = new Button("Demo 3");
        btn3.setOnAction(
            ae -> {                     
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Printer error");
                alert.setContentText("Paper not loaded");
                alert.showAndWait();
            }
        );
        
        // 4. Error dialog without header message
        Button btn4 = new Button("Demo 4");
        btn4.setOnAction(
            ae -> {                        
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Paper not loaded");
                alert.showAndWait();
            }
        );
        
        // 5. Information dialog with header message
        Button btn5 = new Button("Demo 5");
        btn5.setOnAction(
            ae -> {                         
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Time status");
                alert.setContentText("The time is currently 10PM.");
                alert.showAndWait();
            }
        );
        
        // 6. Information dialog without header message
        Button btn6 = new Button("Demo 6");
        btn6.setOnAction(
            ae -> {                        
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setContentText("The time is currently 10PM.");
                alert.showAndWait();
            }
        );
        
        // 7. Warning dialog with header message
        Button btn7 = new Button("Demo 7");
        btn7.setOnAction(
            ae -> {                        
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Battery status");
                alert.setContentText("The battery charge is low.");
                alert.showAndWait();
            }
        );
        
        // 8. Warning dialog without header message
        Button btn8 = new Button("Demo 8");
        btn8.setOnAction(
            ae -> {                                       
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("The battery charge is low.");
                alert.showAndWait();
            }
        );
        
        HBox hboxForm = new HBox(10);
        hboxForm.setPadding(new Insets(10, 10, 10, 10));
        hboxForm.getChildren().addAll(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8);
        Scene scene = new Scene(hboxForm);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Alert Demo");
        primaryStage.show();
    }
}
