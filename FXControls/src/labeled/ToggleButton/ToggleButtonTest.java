
package labeled.ToggleButton;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
Для сравнения читать применение RadioButton в RadioButton.java

ToggleButton is a two-state button control. The two states are selected and unselected. Its selected property
indicates whether it is selected. The selected property is true when it is in the selected state. Otherwise, it is
false. When it is in the selected state, it stays depressed. You can toggle between the selected and unselected
states by pressing it, and hence it got the name ToggleButton. For ToggleButtons, mnemonic parsing is
enabled by default.

A ToggleButton is used to select a choice, not to execute a command. Typically, you do not add
ActionEvent handlers to a ToggleButton. Sometimes you can use a ToggleButton to start or stop an action.
For that, you will need to add a ChangeListener for its selected property.

Tip: The ActionEvent handler for a ToggleButton is invoked every time you click it. Notice that the first
click selects a ToggleButton and the second click deselects it. If you select and deselect a ToggleButton,
the ActionEvent handler will be called twice.

Toggle buttons may be used in a group from which zero or one ToggleButton can be selected. To
add toggle buttons to a group, you need to add them to a ToggleGroup. The ToggleButton class contains
a toggleGroup property. To add a ToggleButton to a ToggleGroup, set the toggleGroup property of the
ToggleButton to the group. Setting the toggleGroup property to null removes a ToggleButton from the
group.The following snippet of code creates four toggle buttons and adds them to a ToggleGroup:
    ToggleButton springBtn = new ToggleButton("Spring");
    ToggleButton summerBtn = new ToggleButton("Summer");
    // Create a ToggleGroup
    ToggleGroup group = new ToggleGroup();
    // Add all ToggleButtons to the ToggleGroup
    springBtn.setToggleGroup(group);
    summerBtn.setToggleGroup(group);
    
Each ToggleGroup maintains an ObservableList<Toggle>. Note that Toggle is an interface that is
implemented by the ToggleButton class. The getToggles() method of the ToggleGroup class returns the
list of Toggles in the group. You can add a ToggleButton to a group by adding it to the list returned by the
getToggles() method. The above snippet of code may be rewritten as follows:
    // Add all ToggleButtons to the ToggleGroup
    group.getToggles().addAll(springBtn, summerBtn,);

The ToggleGroup class contains a selectedToggle property that keeps track of the selected Toggle
in the group. The getSelectedToggle() method returns the reference of the Toggle that is selected. If no
Toggle is selected in the group, it returns null. Add a ChangeListener to this property if you are interested in
tracking the change in selection inside a ToggleGroup.

Tip: You can select zero or one ToggleButton in a ToggleGroup. Selecting a ToggleButton in a group
deselects the already selected ToggleButton. Clicking an already selected ToggleButton in a group deselects
it, leaving no ToggleButton in the group selected.

Tip: Radio buttons are used when the user must make a selection from a list of choices. Toggle buttons are
used when the user has an option to make one selection or no selection from a list of choices.

The program adds four toggle buttons to a ToggleGroup. You can select none or at the most
one ToggleButton from the group. The program adds a ChangeListener to the group
to track the change in selection and displays the label of the selected ToggleButton in a Label control.
*/
public class ToggleButtonTest extends Application {
	Label userSelectionMsg = new Label("Your selection: None");
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create four ToggleButtons
		ToggleButton springBtn = new ToggleButton("Spring");
		ToggleButton summerBtn = new ToggleButton("Summer");
		ToggleButton fallBtn = new ToggleButton("Fall");
		ToggleButton winterBtn = new ToggleButton("Winter");

		// Add all ToggleButtons to a ToggleGroup
		ToggleGroup group = new ToggleGroup();		
		group.getToggles().addAll(springBtn, summerBtn, fallBtn, winterBtn);
        
        // Another mode of adding ToggleButtons to a ToggleGroup
        /*springBtn.setToggleGroup(group);
        summerBtn.setToggleGroup(group);
        fallBtn.setToggleGroup(group);
        winterBtn.setToggleGroup(group);*/

		// Track the selection changes and display the currently selected season
		group.selectedToggleProperty().addListener(this::changed);

		Label msg = new Label("Select the season you like:");

		// Add ToggleButtons to an HBox
		HBox buttonBox = new HBox(springBtn, summerBtn, fallBtn, winterBtn);
		buttonBox.setSpacing(10);

		VBox root = new VBox(userSelectionMsg, msg, buttonBox);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using ToggleButtons in a Group");
		stage.show();
	}
	
	// A change listener to track the selection in the group
	public void changed(ObservableValue<? extends Toggle> observable, Toggle oldBtn, Toggle newBtn) {
		String selectedLabel = "None";
		if (newBtn != null ) {
			selectedLabel = ((Labeled)newBtn).getText();
		}
		userSelectionMsg.setText("Your selection: " + selectedLabel);
	}
}
