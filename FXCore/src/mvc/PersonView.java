
package mvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/*
The PersonView class represents the view in this application. It is mainly responsible
for displaying the data in the model.

The PersonView class inherits from the GridPane class. It contains an instance variable for each
UI component. Its constructor takes the model (an instance of the Person class) and a date format as
arguments. The date format is the format used to display the birth date. Note that the format for the birth
date is view specific and it should be part of the view as such. The model knows nothing about the format in
which the birth date is displayed by views.

The initFieldData() method initializes the view with the data. I used JavaFX bindings to bind the data
in UI nodes to the model data except for the birth date and age category fields. This method synchronizes
the birth date and the age category fields with the model. The layoutForm() method lays out the UI nodes in
the grid pane. The bindFieldsToModel() method binds the person ID, first name, and last name TextFields
to the corresponding data fields in the model, so they stay in sync. The syncBirthDate() method reads
the birth date from the model, formats it, and displays it in the view. The syncAgeCategory() method
synchronizes the age category field, which is computed by the model based on the birth date.

Notice that the view, the PersonView class, does not know about the presenter, the PersonPresenter
class. So how will the view and the presenter communicate? The role of a presenter is mainly to get the user’s
inputs from the view and act upon them. The presenter will have a reference to the view. It will add event
listeners to the view, so it is notified when the data in the view change. In the event handlers, the presenter
takes control and processes the inputs. If the application requires a reference to the presenter in the view,
you can have that as an argument to the constructor of the view class. Alternatively, you can provide a setter
method in the view class to set the presenter.
*/
public class PersonView extends GridPane {
	private final Person model;

	// Labels
	Label personIdLbl = new Label("Person Id:");
	Label fNameLbl = new Label("First Name:");
	Label lNameLbl = new Label("Last Name:");
	Label bDateLbl = new Label("Birth Date:");
	Label ageCategoryLbl = new Label("Age Category:");

	// Fields
	TextField personIdFld = new TextField();
	TextField fNameFld = new TextField();
	TextField lNameFld = new TextField();
	TextField bDateFld = new TextField();
	TextField ageCategoryFld = new TextField();
	
	// Buttons
	Button saveBtn = new Button("Save");
	Button closeBtn = new Button("Close");

	// Date format
	String dateFormat;

	public PersonView(Person model, String dateFormat) {
		this.model = model;
		this.dateFormat = dateFormat;
		layoutForm();
		initFieldData();
		bindFieldsToModel();
	}

	private void initFieldData() {
		// Id and names are populated using bindings. 
		// Populate birth date and age category
		syncBirthDate();
	}

	private void layoutForm() {
		this.setHgap(5);
		this.setVgap(5);

		this.add(personIdLbl, 1, 1);
		this.add(fNameLbl, 1, 2);
		this.add(lNameLbl, 1, 3);
		this.add(bDateLbl, 1, 4);
		this.add(ageCategoryLbl, 1, 5);

		this.add(personIdFld, 2, 1);
		this.add(fNameFld, 2, 2);
		this.add(lNameFld, 2, 3);
		this.add(bDateFld, 2, 4);
		this.add(ageCategoryFld, 2, 5);

		// Add buttons and make them the same width
		VBox buttonBox = new VBox(saveBtn, closeBtn);
		saveBtn.setMaxWidth(Double.MAX_VALUE);
		closeBtn.setMaxWidth(Double.MAX_VALUE);

		this.add(buttonBox, 3, 1, 1, 5);

		// Disable the personId field
		personIdFld.setDisable(true);
		ageCategoryFld.setDisable(true);

		// Set the prompt text for the birth date field
		bDateFld.setPromptText(dateFormat.toLowerCase());
	}

	public void bindFieldsToModel() {
		personIdFld.textProperty().bind(model.personIdProperty().asString());
		fNameFld.textProperty().bindBidirectional(model.firstNameProperty());
		lNameFld.textProperty().bindBidirectional(model.lastNameProperty());
	}

	public void syncBirthDate() {
		LocalDate bdate = model.getBirthDate();
		if (bdate != null) {
			bDateFld.setText(bdate.format(DateTimeFormatter.ofPattern(dateFormat)));
		}
		syncAgeCategory();
	}

	public void syncAgeCategory() {
		ageCategoryFld.setText(model.getAgeCategory().toString());
	}
}
