
package ListView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/*
An instance of the TextFieldListCell is a ListCell that displays an item in a Label when the item is not
being edited and in a TextField when the item is being edited. If you want to edit a domain object to a
ListView, you will need to use a StringConverter to facilitate the two-way conversion. The forListView()
static method of the TextFieldListCell class returns a cell factory configured to be used with String items.
The following snippet of code shows how to set a TextField as the cell editor for a ListView:
    ListView<String> breakfasts = new ListView<>();
    ...
    breakfasts.setEditable(true);
    // Set a TextField as the editor
    Callback<ListView<String>, ListCell<String>> cellFactory = TextFieldListCell.forListView();
    breakfasts.setCellFactory(cellFactory);

The following snippet of code shows how to set a TextField as the cell editor with a converter for a
ListView that contains Person objects. The converter object will be used to convert a Person object 
to a String for displaying and a String to a Person object after editing.
Подробнее про конвертер смотреть в ChoiceBoxPersonsWithConverter.java и Для более глубокого понимания смотреть ComboBoxPersonsWithConverter.java.

    ListView<Person> persons = new ListView<>();
    ...
    persons.setEditable(true);
    // Set a TextField as the editor.
    // Need to use a StringConverter for Person objects.
    StringConverter<Person> converter = new PersonStringConverter();
    Callback<ListView<Person>, ListCell<Person>> cellFactory = TextFieldListCell.forListView(converter);
    persons.setCellFactory(cellFactory);

The program in Listing 12-19 shows how to edit a ListView item in a TextField. It uses a ListView of
domain objects (Person) and a ListView of String objects. After running the program, double-click on any
items in the two ListViews to start editing. When you are done editing, press the Enter key to commit the
changes.

*/
public class ListViewEditing extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ListView<String> breakfasts = getBreakfastListView();
		ListView<Person> persons = getPersonListView();
		
		GridPane root = new GridPane();
		root.setHgap(20);
		root.setVgap(10);
		root.add(new Label("Double click an item to edit."), 0, 0, 2, 1);
		root.addRow(1, new Label("Persons:"), new Label("Breakfasts:"));
		root.addRow(2, persons, breakfasts);
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
		stage.setTitle("Using ListView Cell Factory");
		stage.show();
	}
	
	public ListView<Person> getPersonListView() {
		ListView<Person> persons = new ListView<>();
		persons.setPrefSize(200, 120);
		persons.setEditable(true);
		persons.getItems().addAll(
            new Person("John", "Jacobs", null),
            new Person("Donna", "Duncan", null), 
            new Person("Layne", "Estes", null), 
            new Person("Mason", "Boyd", null)
        );		
		// Set a TextField cell factory to edit the Person items. Also use a
		// StringConverter to convert a String to a Person and vice-versa
		StringConverter<Person> converter = new PersonStringConverter();
		Callback<ListView<Person>, ListCell<Person>> cellFactory = TextFieldListCell.forListView(converter);
		persons.setCellFactory(cellFactory);

		return persons;
	}
	
	public ListView<String> getBreakfastListView() {
		ListView<String> breakfasts = new ListView<>();
		breakfasts.setPrefSize(200, 120);
		breakfasts.setEditable(true);
		breakfasts.getItems().addAll("Apple", "Banana", "Donut", "Hash Brown");	

		// Set a TextField cell factory to edit the String items
		Callback<ListView<String>, ListCell<String>> cellFactory = TextFieldListCell.forListView();
		breakfasts.setCellFactory(cellFactory);
		
		return breakfasts;
	}
}
