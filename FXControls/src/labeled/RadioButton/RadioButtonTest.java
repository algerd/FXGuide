
package labeled.RadioButton;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Toggle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
An instance of the RadioButton class represents a radio button. It inherits from the ToggleButton class.
Therefore, it has all of the features of a toggle button. A radio button is rendered differently compared to a
toggle button. Like a toggle button, a radio button can be in one of the two states: selected and unselected.
Its selected property indicates its current state. Like a toggle button, its mnemonic parsing is enabled by
default. Like a toggle button, it also sends an ActionEvent when it is selected and unselected.

There is a significant difference in the use of radio buttons compared to the use of toggle buttons.
Recall that when toggle buttons are used in a group, there may not be any selected toggle button in the
group. When radio buttons are used in a group, there must be one selected radio button in the group. Unlike
a toggle button, clicking a selected radio button in a group does not unselect it. To enforce the rule that
one radio button must be selected in a group of radio buttons, one radio button from the group is selected
programmatically by default.

Правила добавления радиокнопок в группу смотреть в ToggleButton.java по аналогии.

Tip: Radio buttons are used when the user must make a selection from a list of choices. Toggle buttons are
used when the user has an option to make one selection or no selection from a list of choices.
*/
public class RadioButtonTest extends Application {
	Label userSelectionMsg = new Label("Your selection: None");
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create four RadioButtons
		RadioButton springBtn = new RadioButton("Spring");
		RadioButton summerBtn = new RadioButton("Summer");		
		RadioButton fallBtn = new RadioButton("Fall");
		RadioButton winterBtn = new RadioButton("Winter");
			
		// Add all RadioButtons to a ToggleGroup
		ToggleGroup group = new ToggleGroup();		
		group.getToggles().addAll(springBtn, summerBtn, fallBtn, winterBtn);
        
        // Another mode of adding RadioButtons to a ToggleGroup
        /*springBtn.setToggleGroup(group);
        summerBtn.setToggleGroup(group);
        fallBtn.setToggleGroup(group);
        winterBtn.setToggleGroup(group);*/
        	
		// Track the selection changes and display the currently selected season
		group.selectedToggleProperty().addListener(this::changed);
		
		// Select the default season as Summer				 
		summerBtn.setSelected(true);
		
		Label msg = new Label("Select the season you like the most:");
		
		// Add RadioButtons to an HBox
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
		stage.setTitle("Using RadioButtons in a Group");
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
