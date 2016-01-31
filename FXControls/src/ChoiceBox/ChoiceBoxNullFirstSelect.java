
package ChoiceBox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/*
Sometimes a choice box may allow the user to select null as a valid choice.

It is often required that the null choice be shown as a custom string, for example, "[None]". This can
be accomplished using a converter. In the previous section, you used a converter to customize the choices
for Person objects. Here you will use the converter to customize the choice item for null. You can do both
in one converter as well.

Для более глубокого понимания конвертера смотреть ChoiceBoxPersons.java
*/
public class ChoiceBoxNullFirstSelect extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
        
        Label seasonLbl = new Label("Select a Season:");
        
        ChoiceBox<String> seasons = new ChoiceBox<>();
        seasons.getItems().addAll(null, "Spring", "Summer", "Fall", "Winter");

        // Use a converter to convert null to "[None]"
        seasons.setConverter(new StringConverter<String>() {           
            @Override
            public String toString(String string) {
                return (string == null) ? "[None]" : string;
            }
            @Override
            public String fromString(String string) {
                return string;
            }
        });
        
        // Select the first season from the list
		seasons.getSelectionModel().selectFirst();
        	
		// Display controls in a GridPane
		GridPane root = new GridPane();
		root.setVgap(10);
		root.setHgap(10);
		root.addRow(0, seasonLbl, seasons);
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
