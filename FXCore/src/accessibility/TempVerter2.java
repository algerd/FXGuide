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
See TempVerter1
*/
public class TempVerter2 extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}
    
    @Override
    public void start(Stage primaryStage) {
        
        TextField txtDegrees = new TextField();
        Label lblDegrees = new Label("Degrees:");
        lblDegrees.setLabelFor(txtDegrees);
        lblDegrees.setAccessibleText("Enter temperature in degrees.");
        
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
                   new Alert(AlertType.ERROR, 
                             "Valid number expected").showAndWait();
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