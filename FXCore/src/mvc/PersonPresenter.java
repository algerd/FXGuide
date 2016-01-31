
package mvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
The PersonPresenter class represents the presenter in this application. It is mainly
responsible for intercepting the new input in the view and processing it. It communicates directly with the
model and the view.

The constructor of the PersonPresenter class takes the model and the view as arguments. The
attachEvents() method attaches event handlers to the UI components of the view. In this example you are
not interested in intercepting all inputs in the view. But you are interested in the birth date changes and the
clicking of the Save and Close buttons. You do not want to detect all edit changes in the birth date field.
If you are interested in all changes in the birth date field, you would need to add a change listener for its text
property. You want to detect changes only when the user is done entering the birth date. For this reason:
    - You attach a focus listener to the scene and detect if the birth date has lost the focus.
    - You attach an action listener to the birth date field, so you intercept the Enter key
        press while the field has focus.

This validates and refreshes the birth date and age category whenever the birth date field loses focus or
the Enter key is pressed while focus is still in the field.

The handleBirthDateChange() method handles a change in the birth date field. It validates the birth
date format before updating the model. It displays an error message to the user if the birth date is not valid.
Finally, it tells the view to update the birth date and age category.

The saveData() method is called when the user clicks the Save button, and it commands the model
to save the data. The showError() method does not belong to the presenter. Here you added it instead of
creating a new view class. It is used to display an error message.
*/
public class PersonPresenter {
	private final Person model;
	private final PersonView view;
	
	public PersonPresenter(Person model, PersonView view) {
		this.model = model;
		this.view = view;
		attachEvents();
	}

	private void attachEvents() {
		// We need to detect the birth date change when the bDate field loses
		// focus or the user presses the Enter key while it still has focus
		view.bDateFld.setOnAction(e -> handleBirthDateChange());
		view.bDateFld.getScene().focusOwnerProperty().addListener(this::focusChanged);	

		// Save the data
		view.saveBtn.setOnAction(e -> saveData()); 

		// Close the window when the Close button is pressed
		view.closeBtn.setOnAction(e -> view.getScene().getWindow().hide());
	}

	public void focusChanged(ObservableValue<? extends Node> value, Node oldNode, Node newNode) {	
		// The birth date field has lost focus
		if (oldNode == view.bDateFld) {
			handleBirthDateChange();
		}
	}
	
	private void handleBirthDateChange() {
		String bdateStr = view.bDateFld.getText();
		if (bdateStr == null || bdateStr.trim().equals("")) {
			model.setBirthDate(null);
			view.syncBirthDate();
		} else {		
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(view.dateFormat);
				LocalDate bdate = LocalDate.parse(bdateStr, formatter);

				List<String> errorList = new ArrayList<>();
				if (model.isValidBirthDate(bdate, errorList)) {
					model.setBirthDate(bdate);
					view.syncAgeCategory();
				} else {
					this.showError(errorList);
					view.syncBirthDate();
				}
			}
			catch (DateTimeParseException e) {
				// Birth date is not in the specified date format
				List<String> errorList = new ArrayList<>();
				errorList.add("Birth date must be in the " + 
				              view.dateFormat.toLowerCase() + " format.");
				this.showError(errorList);
				
				// Refresh the view
				view.syncBirthDate();
			}
		}
	}
	
	private void saveData() {
		List<String> errorList = new ArrayList<>();
		boolean isSaved = model.save(errorList);
		if (!isSaved) {
			this.showError(errorList);
		}		
	}
	
	public void showError(List<String> errorList) {
		String msg = "";
		if (errorList.isEmpty()) {
			msg = "No message to display.";
		} else {
			for (String s : errorList) {
				msg = msg + s + "\n";
			}
		}

		Label msgLbl = new Label(msg);
		Button okBtn = new Button("OK");
		VBox root = new VBox(new StackPane(msgLbl), new StackPane(okBtn));
		root.setSpacing(10);

		Scene scene = new Scene(root);
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setScene(scene);		
		stage.initOwner(view.getScene().getWindow());

		// Set the Action listener for the OK button
	 	okBtn.setOnAction(e -> stage.close());
 
		stage.setTitle("Error");
		stage.sizeToScene();
		stage.showAndWait();
	}
}
