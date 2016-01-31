
package ChoiceBox;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
ChoiceBox is used to let a user select an item from a small list of items. The items may be any type of objects.
ChoiceBox is a parameterized class. The parameter type is the type of the items in its list. If you want to store
mixed types of items in a ChoiceBox, you can use its raw type, as shown in the following code:
    ChoiceBox seasons = new ChoiceBox();
    ChoiceBox<String> seasons = new ChoiceBox<String>();

You can specify the list items while creating a ChoiceBox with the following code:
    ObservableList<String> seasonList = FXCollections.<String>observableArrayList("Spring", "Summer", "Fall", "Winter");
    ChoiceBox<String> seasons = new ChoiceBox<>(seasonList);

After you create a ChoiceBox, you can add items to its list of items using the items property, which is
of the ObjectProperty<ObservableList<T>> type in which T is the type parameter for the ChoiceBox.
The following code will accomplish this:
    ChoiceBox<String> seasons = new ChoiceBox<>();
    seasons.getItems().addAll("Spring", "Summer", "Fall", "Winter");

Properties Declared in the ChoiceBox Class:
-----------------------------------------------------------------------
Property        Type        Description
-----------------------------------------------------------------------
converter       ObjectProperty          It serves as a converter object whose toString()
                <StringConverter<T>>    method is called to get the string representation of
                                        the items in the list.

items           ObjectProperty          It is the list of choices to display in the ChoiceBox.
                <ObservableList<T>>

selectionModel  ObjectProperty              It serves as a selection model that keeps track of the
                <SingleSelectionModel<T>>   selections in a ChoiceBox.

showing         ReadOnlyBooleanProperty     Its true value indicates that the control is showing the
                                            list of choices to the user. Its false value indicates that
                                            the list of choices is collapsed.

value           ObjectProperty<T>           It is the selected item in the ChoiceBox.
---------------------------------------------------------------------------

Tip: You are not limited to showing the items list using the mouse or keyboard. You can show and hide the
list programmatically using the show() and hide() methods, respectively.

When you set a new value using the setValue() method, the ChoiceBox selects the specified value
in the control if the value exists in the list of items. It is possible to set a value that does not exist in the list
of items. In that case, the value property contains the newly set item, but the control does not show it. The
control keeps showing the previously selected item, if any. When the new item is later added to the list of
items, the control shows the item set in the value property.

The ChoiceBox needs to track the selected item and its index in the list of items. It uses a separate
object, called the selection model, for this purpose. The ChoiceBox class contains a selectionModel property
to store the item selection details. ChoiceBox uses an object of the SingleSelectionModel class as its
selection model, but you can use your own selection model. The default selection model works in almost all
cases. The selection model provides you selection-related functionality:
    • It lets you select an item using the index of the item in the list.
    • It lets you select the first, next, previous, or last item in the list.
    • It lets you clear the selection.
    • Its selectedIndex and selectedItem properties track the index and value of the
        selected item. You can add a ChangeListener to these properties to handle a change
        in selection in a ChoiceBox. When no item is selected, the selected index is -1 and the
        selected item is null.

The following snippet of code forces a value in a ChoiceBox by selecting the first item in the list by default:
    ChoiceBox<String> seasons = new ChoiceBox<>();
    seasons.getItems().addAll("Spring", "Summer", "Fall", "Winter", "Fall");
    // Select the first item in the list
    seasons.getSelectionModel().selectFirst();

Use the selectNext() method of the selection model to select the next item from the list. Calling the
selectNext() method when the last item is already selected has no effect. Use the selectPrevious() and
selectLast() methods to select the previous and the last item in the list, respectively. The select(int index)
and select(T item) methods select an item using the index and value of the item, respectively. Note that
you can also use the setValue() method of the ChoiceBox to select an item from the list by its value.
The clearSelection() method of the selection model clears the current selection, returning the ChoiceBox
to a state as if no item had been selected.

The program uses a ChoiceBox with a list of four seasons. 
By default, the program selects the first season from the list. The application forces the
user to select one season name by selecting one by default. It adds ChangeListeners to the selectedIndex
and selectedItem properties of the selection model. They print the details of the selection change on the
standard output. The current selection is shown in a Label control whose text property is bound to the
value property of the ChoiceBox. Select a different item from the list and watch the standard output and the
window for the details.

*/
public class ChoiceBoxTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		Label seasonLbl = new Label("Select a Season:");
		ChoiceBox<String> seasons = new ChoiceBox<>();	    
		seasons.getItems().addAll("Spring", "Summer", "Fall", "Winter");
		
		// Select the first season from the list
		seasons.getSelectionModel().selectFirst();
		
		// Add ChangeListeners to track change in selected index and item. Only 
		// one listener is necessary if you want to track change in selection
		seasons.getSelectionModel().selectedItemProperty().addListener(this::itemChanged);
		seasons.getSelectionModel().selectedIndexProperty().addListener(this::indexChanged);
		
		Label selectionMsgLbl = new Label("Your selection:");
		Label selectedValueLbl = new Label("None");

		// Bind the value property to the text property of the Label
		selectedValueLbl.textProperty().bind(seasons.valueProperty());

		// Display controls in a GridPane
		GridPane root = new GridPane();
		root.setVgap(10);
		root.setHgap(10);
		root.addRow(0, seasonLbl, seasons);
		root.addRow(1, selectionMsgLbl, selectedValueLbl);
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

	// A change listener to track the change in selected item
	public void itemChanged(ObservableValue<? extends String> observable, String oldValue, String newValue) { 		
		System.out.println("Itemchanged: old = " + oldValue + 
		                   ", new = " + newValue);
	}
	
	// A change listener to track the change in selected index
	public void indexChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {         
		System.out.println("Indexchanged: old = " + oldValue + ", new = " + newValue);
	}
}
