
package Accordion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
Accordion is a simple control. It displays a group of TitledPane controls where only one of them is in
the expanded state at a time.

Accordion stores the list of its TilePane controls in an ObservableList<TitledPane>. The getPanes()
method returns the list of the TitledPane. Use the list to add or remove any TitledPane to the Accordion,
as shown in the following code:
    TitledPane generalPane = new TitledPane();
    TitledPane addressPane = new TitledPane();
    TitledPane phonePane = new TitledPane();
    ...
    Accordion root = new Accordion();
    root.getPanes().addAll(generalPane, addressPane, phonePane);

The Accordion class contains an expandedPane property, which stores the reference of the currently
expanded TitledPane. By default, an Accordion displays all of its TitledPanes in a collapsed state, and this
property is set to null. Click the title bar of a TitledPane or use the setExpandedPane() method to expand a
TitledPane. Add a ChangeListener to this property if you are interested in when the expanded TitledPane
changes.
*/
public class AccordionTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TitledPane generalPane = this.getGeneralPane();
		TitledPane addressPane = this.getAddressPane();
		TitledPane phonePane = this.getPhonePane();

		Accordion root = new Accordion();
		root.getPanes().addAll(generalPane, addressPane, phonePane);
		root.setExpandedPane(generalPane);
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
		stage.setTitle("Using Accordion Controls");
		stage.show();		
	}

	public TitledPane getGeneralPane() {
		GridPane grid = new GridPane();
		grid.addRow(0, new Label("First Name:"), new TextField());
		grid.addRow(1, new Label("Last Name:"), new TextField());
		grid.addRow(2, new Label("DOB:"), new DatePicker());

		TitledPane generalPane = new TitledPane("General", grid);
		return generalPane;
	}

	public TitledPane getAddressPane() {
		GridPane grid = new GridPane();
		grid.addRow(0, new Label("Street:"), new TextField());
		grid.addRow(1, new Label("City:"), new TextField());
		grid.addRow(2, new Label("State:"), new TextField());
		grid.addRow(3, new Label("ZIP:"), new TextField());

		TitledPane addressPane = new TitledPane("Address", grid);
		return addressPane;
	}
	
	public TitledPane getPhonePane() {
		GridPane grid = new GridPane();
		grid.addRow(0, new Label("Home:"), new TextField());
		grid.addRow(1, new Label("Work:"), new TextField());
		grid.addRow(2, new Label("Cell:"), new TextField());

		TitledPane phonePane = new TitledPane("Phone", grid);
		return phonePane;
	}
}
