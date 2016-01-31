
package ComboBox;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
ComboBox is used to let a user select an item from a list of items. You can think of ComboBox as an advanced
version of ChoiceBox. ComboBox is highly customizable. The ComboBox class inherits from ComboBoxBase
class, which provides the common functionality for all ComboBox-like controls, such as ComboBox,
ColorPicker, and DatePicker. If you want to create a custom control that will allow users to select an item
from a pop-up list, you need to inherit your control from the ComboBoxBase class.

The items list in a ComboBox may comprise any type of objects. ComboBox is a parameterized class.
The parameter type is the type of the items in the list. If you want to store mixed types of items in a ComboBox,
you can use its raw type, as in the following code:
    ComboBox seasons = new ComboBox();
    ComboBox<String> seasons = new ComboBox<String>();

You can specify the list items while creating a ComboBox, as in the following code:
    ObservableList<String> seasonList = FXCollections.<String>observableArrayList("Spring", "Summer", "Fall", "Winter");
    ComboBox<String> seasons = new ComboBox<>(seasonList);

After you create a combo box, you can add items to its list of items using the items property, which is
of the ObjectProperty<ObservableList<T>> type, in which T is the type parameter for the combo box, as in
the following code:
    ComboBox<String> seasons = new ComboBox<>();
    seasons.getItems().addAll("Spring", "Summer", "Fall", "Winter");

Like ChoiceBox, ComboBox needs to track the selected item and its index in the list of items. It uses a
separate object, called selection model, for this purpose. The ComboBox class contains a selectionModel
property to store the item selection details. ComboBox uses an object of the SingleSelectionModel class
as its selection model. The selection model lets you select an item from the list of items and lets you add
ChangeListeners to track changes in index and item selections. Please refer to the ChoiceBoxTest.java 
for more details on using a selection model.

Unlike ChoiceBox, ComboBox can be editable. Its editable property specifies whether or not it is
editable. By default, it is not editable. When it is editable, it uses a TextField control to show the selected or
entered item. The editor property of the ComboBox class stores the reference of the TextField and it is null
if the combo box is not editable, as shown in the following code.

ComboBox has a value property that stores the currently selected or entered value. Note that when a user
enters a value in an editable combo box, the entered string is converted to the item type T of the combo box.
If the item type is not a string, a StringConverter<T> is needed to convert the String value to type T.

You can set a prompt text for a combo box that is displayed when the control is editable, it does not have
focus, and its value property is null. The prompt text is stored in the promptText property, which is of the
StringProperty type, as in the following code:
    breakfasts.setPromptText("Select/Enter an item");

The ComboBox class contains a placeholder property, which stores a Node reference. When the items
list is empty or null, the placeholder node is shown in the pop-up area. The following snippet of code sets a
Label as a placeholder:
    Label placeHolder = new Label("List is empty.\nPlease enter an item");
    breakfasts.setPlaceholder(placeHolder);

Detecting an item change in a noneditable combo box is easily performed by adding a ChangeListener to
the selectedIndex or selectedItem property of its selection model. Please refer ChoiceBox section for more details.

You can still use a ChangeListener for the selectedItem property to detect when the value in an
editable combo box changes by selecting from the items list or entering a new value. When you enter a new
value, the selectedIndex property does not change because the entered value does not exist in the items list.

Sometimes you want to perform an action when the value in a combo box changes. You can do so by
adding an ActionEvent handler, which is fired when the value changes by any means. You would do this by
setting it programmatically, selecting from items list, or entering a new value, as in the following code:
    ComboBox<String> list = new ComboBox<>();
    list.setOnAction(e -> System.out.println("Value changed"));

By default, ComboBox shows only ten items in the pop-up list. If the number of items is more than ten,
the pop-up list shows a scrollbar. If the number of items is less than ten, the height of the pop-up list is
shortened to show only the available items. The visibleRowCount property of the ComboBox controls how
many rows are visible in the pop-up list, as in the following code:
    ComboBox<String> states = new ComboBox<>();
    // Show five rows in the popup list
    states.setVisibleRowCount(5);

The program creates two ComboBox controls: seasons and breakfasts. The combo
box having the list of seasons is not editable. The combo box having the list of breakfast items is editable.
 A Label control displays the user selection. When you enter a new value in the breakfast combo box, 
you need to change the focus, press the Enter key, or open the pop-up list to refresh the message Label.

The ComboBoxBase class provides four properties that can also be used with ComboBox:
    • onShowing
    • onShown
    • onHiding
    • onHidden
These properties are of the type ObjectProperty<EventHandler<Event>>. You can set an event handler
to these properties, which will be called before the pop-up list is shown, after it is shown, before it is hidden,
and after it is hidden. For example, the onShowing event handlers are handy when you want to customize the
pop-up list just before it is shown.
*/
public class ComboBoxTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label seasonsLbl = new Label("Season:");
		ComboBox<String> seasons = new ComboBox<>();
		seasons.getItems().addAll("Spring", "Summer", "Fall", "Winter");

		Label breakfastsLbl = new Label("Breakfast:");
		ComboBox<String> breakfasts = new ComboBox<>();
		breakfasts.getItems().addAll("Apple", "Banana", "Strawberry");
		breakfasts.setEditable(true);

		// Show the user's selection in a Label
		Label selectionLbl = new Label();
		StringProperty str = new SimpleStringProperty("Your selection: ");
		selectionLbl.textProperty().bind(str.concat("Season=")
		                                    .concat(seasons.valueProperty())
		                                    .concat(", Breakfast=")
		                                    .concat(breakfasts.valueProperty()));
		
		HBox row1 = new HBox(seasonsLbl, seasons, breakfastsLbl, breakfasts);
		row1.setSpacing(10);
		VBox root = new VBox(row1, selectionLbl);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using ComboBox Controls");
		stage.show();
	}
}
