
package ChoiceBox;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
Для сравнения смотреть ComboBoxPersonsWithConverter.java.

In the ChoiceBoxTest.java, you used String objects as items in the choice box. You can use any object type as
items. ChoiceBox calls the toString() method of every item and displays the returned value in the pop-up
list.

Typically, the toString() method of an object returns a String that represents the state of the object.
It is not meant to provide a customized string representation of the object to be displayed in a choice box.

The ChoiceBox class contains a converter property. It is an ObjectProperty of the StringConverter<T>
type. A StringConverter<T> object acts as a converter from the object type T to a string and vice versa.
The class is declared abstract, as in the following snippet of code:
    public abstract class StringConverter<T> {
        public abstract String toString(T object);
        public abstract T fromString(String string);
    }

The toString(T object) method converts the object of type T to a string. The fromString(String
string) method converts a string to a T object.

By default, the converter property in a choice box is null. If it is set, the toString(T object)
method of the converter is called to get the list of items instead of the toString() method of the class of the item. 
Notice that you are treating the argument string in the fromString() method as the name of a person
and trying to construct a Person object from it.  The ChoiceBox will use only the toString(Person p) method.

The following snippet of code uses a converter in a ChoiceBox to convert Person objects in its list of
items to strings.
*/
public class ChoiceBoxPersonsWithConverter extends Application {
    
    Label userSelectionMsgLbl = new Label("Your selection:");
	Label userSelectionDataLbl = new Label("");
    
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
        
        Label personLbl = new Label("Select/Enter Person:");      
        ChoiceBox<Person> persons = new ChoiceBox<>();
        // Set a converter to convert a Person object to a String object
        persons.setConverter(new PersonStringConverter());
        // Add five person objects to the ChoiceBox
        persons.getItems().addAll(
            new Person("John", "Jacobs", null),
            new Person("Donna", "Duncan", null),
            new Person("Layne", "Estes", null),
            new Person("Mason", "Boyd", null)
        );      
        // Select the first season from the list
		persons.getSelectionModel().selectFirst();
        	
        // Add ChangeListeners to the selectedItem and selectedIndex properties of the selection model
		persons.getSelectionModel().selectedItemProperty().addListener(this::personChanged);
		persons.getSelectionModel().selectedIndexProperty().addListener(this::indexChanged);
              
		// Display controls in a GridPane
		GridPane root = new GridPane();
		root.setVgap(10);
		root.setHgap(10);
		root.addRow(0, personLbl, persons);
        root.addRow(1, userSelectionMsgLbl, userSelectionDataLbl);
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
    	
	// A change listener to track the change in item selection
	public void personChanged(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {      
		System.out.println("Itemchanged: old = " + oldValue + ", new = " + newValue);       
		String name = newValue.getLastName() + ", " + newValue.getFirstName();
		userSelectionDataLbl.setText(name);
	}
	
	// A change listener to track the change in index selection
	public void indexChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		System.out.println("Indexchanged: old = " + oldValue + ", new = " + newValue);
	}

}
