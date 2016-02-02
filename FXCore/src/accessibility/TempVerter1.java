package accessibility;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
Listing created a TempVerter application for converting a temperature to Fahrenheit or Celsius

Before running the application, you need to install a screen reader. 
For my 64-bit Windows 7 platform, I'm using Microsoft's pre-installed Narrator software. 
To run this program, I enter narrator in the Start menu's Search programs and files text field. 
I also hear a feminine voice describing the window and its contents.

Because of 
    lblDegrees.setLabelFor(txtDegrees);
, Narrator speaks "Degrees, editable text" instead of "editable text" when the text field has the focus. 
Comment out this code and listen to the result. You should not hear the computer voice utter "Degrees".

While experimenting, insert the following line of code after lblDegrees.setLabelFor(txtDegrees); (see TempVerter2.java):
    lblDegrees.setAccessibleText("Enter temperature in degrees.");

This time, you should hear "Enter temperature in degrees" instead of "Degrees".
*/
public class TempVerter1 extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}
    
    
   @Override
   public void start(Stage primaryStage) {
       
        TextField txtDegrees = new TextField();
        Label lblDegrees = new Label("Degrees:");
        lblDegrees.setLabelFor(txtDegrees);
        
        Button btnToC = new Button("To C");
        btnToC.setOnAction(
            ae -> {
                try {
                   String input = txtDegrees.getText();
                   double deg = Double.parseDouble(input);
                   txtDegrees.setText("" + ((deg - 32) * 5 / 9.0));
                }
                catch (NumberFormatException nfe) {
                   new Alert(AlertType.ERROR, "Valid number expected").showAndWait();
                }
            }
        );
        
        Button btnToF = new Button("To F");
        btnToF.setOnAction(
            ae -> {
                try {
                   String input = txtDegrees.getText();
                   double deg = Double.parseDouble(input);
                   txtDegrees.setText("" + (deg * 9 / 5.0 + 32));
                }
                catch (NumberFormatException nfe) {
                   new Alert(AlertType.ERROR, "Valid number expected").showAndWait();
                }
            }
        );
        
        HBox hboxForm = new HBox(10);
        hboxForm.setPadding(new Insets(10, 10, 10, 10));
        hboxForm.getChildren().addAll(lblDegrees, txtDegrees, btnToC, btnToF);
        Scene scene = new Scene(hboxForm);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("TempVerter");
        primaryStage.show();
    }
}