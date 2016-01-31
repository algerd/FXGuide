
package Tab;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
TabPane supports single selection model, which allows selecting only one tab at a time. If a tab is selected
by the user or programmatically, the previously selected tab is unselected. The Tab class provides the API
to allow working with the selection state of an individual tab. The TabPane class provides API that allows
working with the selection of all of its tabs.

The Tab class contains a read-only selected property of the boolean type. It is true when the tab is
selected. Otherwise, it is false. Note that it is a property of the Tab, not the TabPane.
Tab lets you add event handlers that are notified when the tab is selected or unselected.

    Tab generalTab = ...
    generalTab.setOnSelectionChanged(e -> {
        if (generalTab.isSelected()) {
            System.out.println("General tab has been selected.");
        } else {
            System.out.println("General tab has been unselected.");
        }
    });

TabPane tracks the selected tab and its index in the list of tabs. It uses a separate object, called selection
model, for this purpose. The TabPane class contains a selectionModel property to store the tab selection
details. The property is an object of the SingleSelectionModel class. You can use your own selection model,
which is almost never needed. The selection model provides the selection-related functionalities:
    • It lets you select a tab using the index of the tab. The first tab has an index of 0.
    • It lets you select the first, next, previous, or last tab in the list.
    • It lets you clear the selection. Note that this feature is available, but is not commonly
    used. A TabPane should always typically have a selected tab.
    • The selectedIndex and selectedItem properties track the index and reference
    of the selected tab. You can add a ChangeListener to these properties to handle a
    change in tab selection in a TabPane.

By default, a TabPane selects its first tab. The following snippet of code selects the last Tab in a TabPane:
    tabPane.getSelectionModel().selectLast();

Use the selectNext() method of the selection model to select the next tab from the list. Calling this
method when the last tab is already selected has no effect.

Use the selectPrevious() and selectLast() methods to select the previous and the last tabs in the list.
The select(int index) and select(T item) methods select a tab using the index and reference of the tab.

The program adds two tabs to a TabPane. It adds a selection-changed event handler
to both tabs. A ChangeListener is added to the selectedItem property of the selectionModel property of
the TabPane. When a selection is made, a detailed message is printed on the standard output. Notice that a
message is printed when you run the application because the TabPane selection model selects the first tab
by default.
*/
public class TabSelection extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		GeneralTab generalTab = new GeneralTab("General", null);
		AddressTab addressTab = new AddressTab("Address", null);

		// Add selection a change listener to Tabs
		generalTab.setOnSelectionChanged(e -> tabSelectedChanged(e));
		addressTab.setOnSelectionChanged(e -> tabSelectedChanged(e));

		TabPane tabPane = new TabPane();

		// Add a ChangeListsner to the selection model
		tabPane.getSelectionModel().selectedItemProperty().addListener(this::selectionChanged);

		tabPane.getTabs().addAll(generalTab, addressTab);

		HBox root = new HBox(tabPane);	
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("TabPane Selection Model");
		stage.show();
	}

	public void selectionChanged(ObservableValue<? extends Tab> prop, Tab oldTab, Tab newTab) {
		String oldTabText = oldTab == null? "None": oldTab.getText();
		String newTabText = newTab == null? "None": newTab.getText();	
		System.out.println("Selection changed in TabPane: old = " + oldTabText + ", new = " + newTabText);
	}

	public void tabSelectedChanged(Event e) {
		Tab tab = (Tab)e.getSource();
		System.out.println("Selection changed event for " + tab.getText() + " tab, selected = " + tab.isSelected());
	}
}
