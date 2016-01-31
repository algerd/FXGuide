
package ListView;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
Для лучшего понимания смотреть ListViewEditing.java. 

An instance of the ChoiceBoxListCell is a ListCell that displays an item in a Label when the item is not
being edited and in a ChoiceBox when the item is being edited. If you want to edit a domain object to a
ListView, you will need to use a StringConverter to facilitate two-way conversion. You need to supply the
list of items to show in the choice box. Use the forListView() static method of the ChoiceBoxListCell
class to create a cell factory. The following snippet of code shows how to set a choice box as the cell editor
for a ListView:

    ListView<String> breakfasts = new ListView<>();
    ...
    breakfasts.setEditable(true);
    // Set a cell factory to use a ChoiceBox for editing
    ObservableList<String> items = FXCollections.<String>observableArrayList("Apple", "Banana", "Donut", "Hash Brown");
    breakfasts.setCellFactory(ChoiceBoxListCell.forListView(items));

The program uses a choice box to edit items in a ListView. Double-click an item in a
cell to start editing. In edit mode, the cell becomes a choice box. Click the arrow to show the list of items to
select. Using a combo box for editing is similar to using a choice box.
*/
public class ListViewChoiceBoxEditing extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ListView<String> breakfasts = new ListView<>();
		breakfasts.setPrefSize(200, 120);
		breakfasts.setEditable(true);
		breakfasts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		// Let the user select a maximum of four breakfast items
		breakfasts.getItems().addAll(
            "[Double click to select]", 
            "[Double click to select]", 
            "[Double click to select]", 
            "[Double click to select]");

		// The breakfast items to select from
		ObservableList<String> items = FXCollections.<String>observableArrayList("Apple", "Banana", "Donut", "Hash Brown");
		
		// Set a ChoiceBox cell factory for editing
		breakfasts.setCellFactory(ChoiceBoxListCell.forListView(items));
		
		VBox root = new VBox(new Label("Double click an item to select."), new Label("Breakfasts:"), breakfasts);
		root.setSpacing(10);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using ListView Cell Factory");
		stage.show();
	}
}
