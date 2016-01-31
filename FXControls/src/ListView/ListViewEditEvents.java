
package ListView;

import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
An editable ListView fires three kinds of events:
    • An editStart event when the editing starts
    • An editCommit event when the edited value is committed
    • An editcancel event when the editing is cancelled

The ListView class defines a ListView.EditEvent<T> static inner class to represent edit-related event
objects. Its getIndex() method returns the index of the item that is edited. The getNewValue() method
returns the new input value. The getSource() method returns the reference of the ListView firing the event.
The ListView class provides onEditStart, onEditCommit, and onEditCancel properties to set the event
handlers for these methods.

The following snippet of code adds an editStart event hander to a ListView. The handler prints the
index that is being edited and the new item value:
    ListView<String> breakfasts = new ListView<>();
    ...
    breakfasts.setEditable(true);
    breakfasts.setCellFactory(TextFieldListCell.forListView());
    // Add an editStart event handler to the ListView
    breakfasts.setOnEditStart(
        e ->System.out.println("Edit Start: Index=" + e.getIndex() + ", item = " + e.getNewValue()));

Listing ontains a complete program to show how to handle edit-related events in a ListView.
Run the program and double-click an item to start editing. After changing the value, press Enter to commit
editing or Esc to cancel editing. Edit-related event handlers print messages on the standard output.

*/
public class ListViewEditEvents extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ListView<String> breakfasts = new ListView<>();
		breakfasts.setPrefSize(200, 120);
		breakfasts.getItems().addAll("Apple", "Banana", "Donut", "Hash Brown"); 
		breakfasts.setEditable(true);
		breakfasts.setCellFactory(TextFieldListCell.forListView());
	
		// Add Edit-related event handlers
		breakfasts.setOnEditStart(this::editStart);
		breakfasts.setOnEditCommit(this::editCommit);
		breakfasts.setOnEditCancel(this::editCancel);

		HBox root = new HBox(new Label("Breakfast:"), breakfasts);
		root.setSpacing(20);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);	    
		stage.setScene(scene);		
		stage.setTitle("Using ListView Edit Events");
		stage.show();	
	}	
	
	public void editStart(ListView.EditEvent<String> e) {
		System.out.println("Edit Start: Index=" + e.getIndex() + ", Item=" + e.getNewValue());
	}
	
	public void editCommit(ListView.EditEvent<String> e) {		
		System.out.println("Edit Commit: Index=" + e.getIndex() + ", Item=" + e.getNewValue());
	}   
	
	public void editCancel(ListView.EditEvent<String> e) {
		System.out.println("Edit Cancel: Index=" + e.getIndex() + ", Item=" + e.getNewValue());
	}
}
