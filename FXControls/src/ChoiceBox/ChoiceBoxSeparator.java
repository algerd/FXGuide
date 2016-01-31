
package ChoiceBox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*

*/
public class ChoiceBoxSeparator extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
        
        Label lbl = new Label("Select a Fruits:");
        
        ChoiceBox<Object> breakfasts = new ChoiceBox<>();
        breakfasts.getItems().addAll(
            "Apple", "Banana", "Strawberry",                    
            new Separator(),
            "Apple Pie", "Donut", "Hash Brown");
      
        // Select the first season from the list
		breakfasts.getSelectionModel().selectFirst();
        	
		// Display controls in a GridPane
		GridPane root = new GridPane();
		root.setVgap(10);
		root.setHgap(10);
		root.addRow(0, lbl, breakfasts);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using ChoiceBox Controls");
		stage.show();
	}

}
