
package ComboBox;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
Для более глубокого понимания смотреть ChoiceBoxPersonsWithConverter.java.

In an editable ComboBox<T> where T is something other than String, you must set the converter property to
a valid StringConverter<T>. Its toString(T object) method is used to convert the item object to a string
to show it in the pop-up list. Its fromString(String s) method is called to convert the entered string to an
item object. The value property is updated with the item object converted from the entered string. If the
entered string cannot be converted to an item object, the value property is not updated.

The program shows how to use a StringConverter in a combo box, which uses domain objects in its items list. 
The ComboBox uses Person objects. The PersonStringConverter classmis used as the StringConverter. 
You can enter a name in the format LastName, FirstName or FirstName in the ComboBox and press the Enter key. 
The entered name will be converted to a Person object and shown in the Label. 
The program ignores the error checking in name formatting. For example, if you
enter Kishori as the name, it displays null, Kishori in the Label. The program adds a ChangeListener to the
selectedItem and selectedIndex properties of the selection model to track the selection change. Notice that
when you enter a string in the ComboBox, a change in selectedIndex property is not reported. An ActionEvent
handler for the ComboBox is used to keep the values in the combo box and the text in the Label in sync.
*/
public class ComboBoxPersonsWithConverter extends Application {
    
	Label userSelectionMsgLbl = new Label("Your selection:");
	Label userSelectionDataLbl = new Label("");

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label personLbl = new Label("Select/Enter Person:");
		ComboBox<Person> persons = new ComboBox<>();
		persons.setEditable(true);
		persons.setConverter(new PersonStringConverter());
		persons.getItems().addAll(
            new Person("John", "Jacobs", null),
            new Person("Donna", "Duncan", null), 
            new Person("Layne", "Estes", null), 
            new Person("Mason", "Boyd", null)
        );
		// Add ChangeListeners to the selectedItem and selectedIndex properties of the selection model
		persons.getSelectionModel().selectedItemProperty().addListener(this::personChanged);
		persons.getSelectionModel().selectedIndexProperty().addListener(this::indexChanged);

		// Update the message Label when the value changes
		persons.setOnAction(e -> valueChanged(persons)); 

		GridPane root = new GridPane();
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
		stage.setTitle("Using StringConverter in ComboBox");
		stage.show();
	}
	
	public void valueChanged(ComboBox<Person> list) {
		Person p = list.getValue();
		String name = p.getLastName() + ", " + p.getFirstName();
		userSelectionDataLbl.setText(name); 
	}
	
	// A change listener to track the change in item selection
	public void personChanged(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {      
		System.out.println("Itemchanged: old = " + oldValue + ", new = " + newValue);
	}
	
	// A change listener to track the change in index selection
	public void indexChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		System.out.println("Indexchanged: old = " + oldValue + ", new = " + newValue);
	}
}
