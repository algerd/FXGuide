
package ListView;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/*
The CheckBoxListCell class provides the ability to edit a ListCell using a check box. It draws a check box
in the cell, which can be selected or deselected. Note that the third state, the indeterminate state, of the
check box is not available for selection while using a check box to edit ListView items.

Using a check box to edit ListView items is a little different. You need to provide the CheckBoxListCell
class with an ObservableValue<Boolean> object for each item in the ListView. Internally, the observable
value is bound bidirectionally to the selected state of the check box. When the user selects or deselects an
item in the ListView using the check box, the corresponding ObservableValue object is updated with a
true or false value. If you want to know which item is selected, you will need to keep the reference of the
ObservableValue object.

The following snippet of code creates a Callback object. Its call() method returns the
ObservableValue object for the specified item passed to the call() method. The CheckBoxListCell class
will call the call() method of this object automatically:
    Callback<String, ObservableValue<Boolean>> itemToBoolean = (String item) -> map.get(item);

Now it is time to create and set a cell factory for the ListView. The forListView() static method of the
CheckBoxListCell class takes a Callback object as an argument. If your ListView contains domain objects,
you can also provide a StringConverter to this method, using the following code:
    // Set the cell factory
    breakfasts.setCellFactory(CheckBoxListCell.forListView(itemToBoolean));

When the user selects or deselects an item using the check box, the corresponding ObservableValue in
the map will be updated. To know whether an item in the ListView is selected, you need to look at the value
in the ObservableValue object for that item.

The program shows how to use a check box to edit items in a ListView.  
Select items using a mouse. Pressing the Print Selection button prints the selected items on the standard output.
*/
public class ListViewCheckBoxEditing extends Application {
    
	Map<String, ObservableValue<Boolean>> map = new HashMap<>();
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Populate the map with ListView items as its keys and their selected state as the value 
		map.put("Apple", new SimpleBooleanProperty(false));
		map.put("Banana", new SimpleBooleanProperty(false));
		map.put("Donut", new SimpleBooleanProperty(false));
		map.put("Hash Brown", new SimpleBooleanProperty(false));
		
		ListView<String> breakfasts = new ListView<>();
		breakfasts.setPrefSize(200, 120);
		breakfasts.setEditable(true);
		breakfasts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		// Add all keys from the map as items to the ListView		
		breakfasts.getItems().addAll(map.keySet());
		
		// Create a Callback object
		Callback<String, ObservableValue<Boolean>> itemToBoolean = (String item) -> map.get(item);
		
		// Set the cell factory
		breakfasts.setCellFactory(CheckBoxListCell.forListView(itemToBoolean));
		
		Button printBtn = new Button("Print Selection");
		printBtn.setOnAction(e -> printSelection());
		
		VBox root = new VBox(new Label("Breakfasts:"), breakfasts, printBtn);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
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
	
	public void printSelection() {
		System.out.println("Selected items: ");
		for(String key: map.keySet()) {
			ObservableValue<Boolean> value = map.get(key);
			if (value.getValue()) {
				System.out.println(key);		
			}
		}
		System.out.println();
	}
}
