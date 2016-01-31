
package labeled.CheckBox;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
CheckBox is a three-state selection control: checked, unchecked, and undefined. The undefined state is also
known as an indeterminate state. A CheckBox supports a selection of three choices: true/false/unknown or
yes/no/unknown. Usually, a CheckBox has text as a label, but not a graphic (even though it can). Clicking a
CheckBox transitions it from one state to another cycling through three states.

A box is drawn for a CheckBox. In the unchecked state, the box is empty. A tick mark (or a check mark)
is present in the box when it is in the checked state. In the undefined state, a horizontal line is present in the
box.

By default, the CheckBox control supports only two states: checked and unchecked. The
allowIndeterminate property specifies whether the third state (the undefined state) is available for
selection. By default, it is set to false:
    // Create a CheckBox that supports checked and unchecked states only
    CheckBox hungryCbx = new CheckBox("Hungry");
    // Create a CheckBox and configure it to support three states
    CheckBox agreeCbx = new CheckBox("Hungry");
    agreeCbx.setAllowIndeterminate(true);

The CheckBox class contains selected and indeterminate properties to track its three states. If the
indeterminate property is true, it is in the undefined state. If the indeterminate property is false, it is
defined and it could be in a checked or unchecked state. If the indeterminate property is false and the
selected property is true, it is in a checked state. If the indeterminate property is false and the selected
property is false, it is in an unchecked state. Table summarizes the rules for determining the state of a
check box.

Determining the State of a Check Box Based on Its Indeterminate and Selected Properties:
---------------------------------------------
indeterminate       selected       State
---------------------------------------------
false               true            Checked
false               false           Unchecked
true                true/false      Undefined
---------------------------------------------

Sometimes you may want to detect the state transition in a check box. Because a check box maintains
the state information in two properties, you will need to add a ChangeListener to both properties. An
ActionEvent is fired when a check box is clicked. You can also use an ActionEvent to detect a state change in
a check box. The following snippet of code shows how to use two ChangeListeners to detect a state change
in a CheckBox. It is assumed that the changed() method and the rest of the code are part of the same class.

    // Create a CheckBox to support three states
    CheckBox agreeCbx = new CheckBox("I agree");
    agreeCbx.setAllowIndeterminate(true);
    // Add a ChangeListener to the selected and indeterminate properties
    agreeCbx.selectedProperty().addListener(this::changed);
    agreeCbx.indeterminateProperty().addListener(this::changed);
    ...
    // A change listener to track the selection in the group
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        String state = null;
        if (agreeCbx.isIndeterminate()) {
            state = "Undefined";
        } else if (agreeCbx.isSelected()) {
            state = "Checked";
        } else {
            state = "Unchecked";
        }
        System.out.println(state);
    }

The program shows how to use CheckBox controls. The program creates two CheckBox controls. The Hungry CheckBox supports
only two states. The I agree CheckBox is configured to support three states. When you change the state for the
I agree CheckBox by clicking it, the Label at the top displays the description of the state.

The default CSS style-class name for a CheckBox is check-box. The CheckBox class supports three CSS
pseudo-classes: selected, determinate, and indeterminate. The selected pseudo-class applies when the
selected property is true. The determinate pseudo-class applies when the indeterminate property is false.
The indeterminate pseudo-class applies when the indeterminate property is true.

The CheckBox control contains two substructures: box and mark. You can style them to change their
appearance. You can change the background color and border for the box and you can change the color and
shape of the tick mark. Both box and mark are an instance of StackPane. The tick mark is shown giving a
shape to the StackPane. You can change the shape for the mark by supplying a different shape in a CSS. By
changing the background color of the mark, you change the color of the tick mark. The following CSS will
show the box in tan and tick mark in red:
    .check-box .box {
        -fx-background-color: tan
    }
    .check-box:selected .mark {
        -fx-background-color: red;
    }
*/
public class CheckBoxTest extends Application {
	Label userSelectionMsg = new Label("Do you agree? No");
	CheckBox agreeCbx;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a CheckBox to support only two states
		CheckBox hungryCbx = new CheckBox("Hungry");
	 	
		// Create a CheckBox to support three states
		agreeCbx = new CheckBox("I agree");
		agreeCbx.setAllowIndeterminate(true);
		
		// Track the state change for the "I agree" CheckBox
		// Text for the Label userSelectionMsg will be updated
		agreeCbx.selectedProperty().addListener(this::changed);
		agreeCbx.indeterminateProperty().addListener(this::changed);

		VBox root = new VBox(userSelectionMsg, hungryCbx, agreeCbx);
		root.setSpacing(20);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root, 200, 130);
		stage.setScene(scene);
		stage.setTitle("Using CheckBoxes");
		stage.show();	   
	}
	
	// A change listener to track the state change in agreeCbx
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		String msg;
		if (agreeCbx.isIndeterminate()) {
		    msg = "Not sure";
		} else if (agreeCbx.isSelected()) {
		    msg = "Yes";
		} else {
			msg = "No";
		}
		this.userSelectionMsg.setText("Do you agree? " + msg);
	}
}
