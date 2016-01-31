
package ListView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/*
Each item in a ListView is displayed in an instance of ListCell, which a Labeled control. Recall that a
Labeled control contains text and a graphic. The ListView class contains a cellFactory property that lets you
use custom cells for its items. The property type is ObjectProperty<Callback<ListView<T>,ListCell<T>>>.
The reference of the ListView is passed to the call() method of the Callback object and it returns an instance
of the ListCell class. In a large ListView, say 1,000 items, the ListCell returned from the cell factory may
be reused. The control needs to create only the number of cells that are visible. Upon scrolling, it may reuse
the cells that went out of the view to display newly visible items. The updateItem() method of the ListCell
receives the reference of the new item.

By default, a ListView calls the toString() method of its items and it displays the string in its cell. In
the updateItem() method of your custom ListCell, you can populate the text and graphic for the cell to
display anything you want in the cell based on the item in that cell.

Tip: You used a custom cell factory for the pop-up list of the combo box in ComboBoxCellFactory.java. The pop-up
list in a combo box uses a ListView. Therefore, using a custom cell factory in a ListView would be the same
as discussed in the earlier combo box section.

The ListView control offers many customizations, and one of them is its ability to let users edit the items.
You need to set two properties for a ListView before it can be edited:
    • Set the editable property of the ListView to true.
    • Set the cellFactory property of the ListView to a cell factory that produces an
    editable ListCell.
Select a cell and click to start editing. Alternatively, press the spacebar when a cell has focus to start
editing. If a ListView is editable and has an editable cell, you can also use the edit(int index) method of
the ListView to edit the item in the cell at the specified index.

Tip: The ListView class contains a read-only editingIndex property. Its value is the index of the item
being edited. Its value is -1 if no item is being edited.

JavaFX provides cell factories that let you edit a ListCell using TextField, ChoiceBox, ComboBox,
and CheckBox. You can create a custom cell factory to edit cells in some other way. Instances of the
TextFieldListCell, ChoiceBoxListCell, ComboBoxListCell, and CheckBoxListCell classes, as list cells in a
ListView, provide editing support. These classes are included in the javafx.scene.control.cell package.

The program in Listingshows how to use a custom cell factory to display the formatted names of
Person items. The snippet of code in the program creates and sets a custom cell factory. 
The updateItem() method of the ListCell formats the name of the Person object and adds a serial number that is the index of the cell plus one.
*/
public class ListViewCellFactory extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		ListView<Person> persons = new ListView<>();
		persons.setPrefSize(150, 120);
		persons.getItems().addAll(
            new Person("John", "Jacobs", null),
            new Person("Donna", "Duncan", null), 
            new Person("Layne", "Estes", null), 
            new Person("Mason", "Boyd", null)
        );

		// Add a custom cell factory to display formatted names of persons
		persons.setCellFactory( 
			new Callback<ListView<Person>,ListCell<Person>>() {
			@Override
			public ListCell<Person> call(ListView<Person> listView) {
				return new ListCell<Person>() {
					@Override
					public void updateItem(Person item, boolean empty) {
						// Must call super
						super.updateItem(item, empty);

						int index = this.getIndex();
						String name = null;

						// Format name 
						if (item == null || empty) {
							// No action to perform
						} else {
							name = (index + 1) + ". " + 
							       item.getLastName() + ", " + 
							       item.getFirstName();
						}
						
						this.setText(name);
						setGraphic(null);
					}
				}; 
		}});

		HBox root = new HBox(new Label("Persons:"), persons);
		root.setSpacing(20);
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
}
