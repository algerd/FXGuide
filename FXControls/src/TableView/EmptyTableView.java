
package TableView;

import javafx.application.Application;
import javafx.beans.binding.When;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
TableView displays a placeholder when it does not have any visible leaf columns or content.
*/
@SuppressWarnings("unchecked")
public class EmptyTableView extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create empty TableView
		TableView<Person> table = new TableView<>();
        table.getColumns().addAll(
            PersonTableUtil.getIdColumn(),
            PersonTableUtil.getFirstNameColumn(),
            PersonTableUtil.getLastNameColumn(),
            PersonTableUtil.getBirthDateColumn()
        );
        // You can replace the built-in placeholder using the placeholder property of the TableView. The value
        // for the property is an instance of the Node class. 
        table.setPlaceholder(new Label("No visible columns and/or data exist."));
         
        //You can set a custom placeholder to inform the user of the specific condition that resulted in showing no data in the TableView.
        /*table.placeholderProperty().bind(
            new When(new SimpleIntegerProperty(0).isEqualTo(table.getVisibleLeafColumns().size()))
                .then(new When(new SimpleIntegerProperty(0).isEqualTo(table.getItems().size()))
                    .then(new Label("No columns and data exist."))
                    .otherwise(new Label("No columns exist.")))
                .otherwise(new When(new SimpleIntegerProperty(0).isEqualTo(table.getItems().size()))
                    .then(new Label("No data exist."))
                    .otherwise((Label)null))
        );*/
        
		VBox root = new VBox(table);
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
		stage.setTitle("Simplest TableView");
		stage.show();
	}
}
