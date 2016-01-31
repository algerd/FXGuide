
package Tab;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import static javafx.scene.control.TabPane.TabClosingPolicy;

/*
Sometimes the user needs to add tabs to a TabPane on demand and they should be able to close tabs as
well. For example, all modern web browsers use tabs for browsing and let you open and close tabs. Adding
tabs on demand requires some coding in JavaFX. However, closing tabs by the user is built in the Tab and
TabPane classes.

Users can close Tabs in a TabPane using the close button that appears in the title bar of Tabs. The tab-closing
feature is controlled by the following properties:
    • The closable property of the Tab class
    • The tabClosingPolicy property of the TabPane class

The closable property of a Tab class specifies whether the tab can be closed. If it is set to false, the tab
cannot be closed, irrespective of the value for the tabClosingPolicy. The default value for the property is
true. The tabClosingPolicy property specifies how the tab-closing buttons are available. Its value is one of
the following constants of the TabPane.TabClosingPolicy enum:
    • ALL_TABS
    • SELECTED_TAB
    • UNAVAILABLE

ALL_TABS means the close button is available for all tabs. That is, any tab can be closed at any time
provided the closable property of the tab is true. SELECTED_TAB means the close button appears only for the
selected tab. That is, only the selected tab can be closed at any time. This is the default tab-closing policy of a
TabPane. UNAVAILABLE means the close button is not available for any tabs. That is, no tabs can be closed by
the user, irrespective of their closable properties.

A distinction has to be made between:
    • Closing tabs by the user using the close button
    • Removing them programmatically by removing them from the observable list of Tabs
    of the TabPane

Both have the same effect, that Tabs are removed from the TabPane. The discussion in this section
applies to closing tabs by the user.

The user action to closing tabs can be vetoed. You can add event handlers for the TAB_CLOSE_REQUEST_
EVENT event for a tab. The event handler is called when the user attempts to close the tab. If the event
handler consumes the event, the closing operation is canceled. You can use the onCloseRequest property of
the Tab class to set such an event:
    Tab myTab = new Tab("My Tab");
        myTab.setOnCloseRequest(e -> { if (SOME_CONDITION_IS_TRUE) {
            // Cancel the close request
            e.consume();
        }
    });

A tab also generates a closed event when it is closed by the user. Use the onClosed property of the Tab
class to set a closed event handler for a tab. The event handler is typically used to release resources held by
the tab:
    myTab.setOnClosed(e -> { });

The program shows how to use the tab-closing–related properties and events. It displays
two tabs in a TabPane. A check box lets you veto the closing of tabs. Unless the check box is selected, an
attempt to close tabs is vetoed on the close request event. If you close tabs, you can restore them using the
Restore Tabs button. Use the tab-closing policy ChoiceBox to use a different tab-closing policy. For example,
if you select UNAVAILABLE as the tab-closing policy, the close buttons will disappear from all tabs. When a tab
is closed, a message is printed on the standard output.
*/
public class TabClosingTest extends Application {
    
	GeneralTab generalTab = new GeneralTab("General", null);
	AddressTab addressTab = new AddressTab("Address", null);
	TabPane tabPane = new TabPane();
	CheckBox allowClosingTabsFlag = new CheckBox("Are Tabs closable?");
	Button restoreTabsBtn = new Button("Restore Tabs");
	ChoiceBox<TabPane.TabClosingPolicy> tabClosingPolicyChoices = new ChoiceBox<>();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Add Tabs to the TabPane
		tabPane.getTabs().addAll(generalTab, addressTab);

		// Set a tab close request event handler for tabs
		generalTab.setOnCloseRequest(this::tabClosingRequested);
		addressTab.setOnCloseRequest(this::tabClosingRequested);

		// Set a closed event handler for the tabs
		generalTab.setOnClosed(e -> tabClosed(e));
		addressTab.setOnClosed(e -> tabClosed(e));

		// Set an action event handler for the restore button
		restoreTabsBtn.setOnAction(e -> restoreTabs());
		
		// Add choices to the choice box
		tabClosingPolicyChoices.getItems().addAll(
            TabClosingPolicy.ALL_TABS, 
            TabClosingPolicy.SELECTED_TAB,
            TabClosingPolicy.UNAVAILABLE
        );

		// Set the default value for the tab closing policy
		tabClosingPolicyChoices.setValue(tabPane.getTabClosingPolicy());

		// Bind the tabClosingPolicy of the tabPane to the value property of the of the ChoiceBoxx
		tabPane.tabClosingPolicyProperty().bind(tabClosingPolicyChoices.valueProperty());

		BorderPane root = new BorderPane();
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setStyle("-fx-padding: 10;");
		grid.addRow(0, allowClosingTabsFlag, restoreTabsBtn);
		grid.addRow(1, new Label("Tab Closing Policy:"), tabClosingPolicyChoices);
		root.setTop(grid);
		root.setCenter(tabPane);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Closing Tabs");
		stage.show();
	}

	public void tabClosingRequested(Event e) {
		if (!allowClosingTabsFlag.isSelected()) {
			e.consume(); // Closing tabs is not allowed
		}
	}
	
	public void tabClosed(Event e) {
		Tab tab = (Tab)e.getSource();
		String text = tab.getText();
		System.out.println(text + " tab has been closed.");
	}
	
	public void restoreTabs() {
		ObservableList<Tab> list = tabPane.getTabs();
		if (!list.contains(generalTab)) {
			list.add(0, generalTab);
		}		
		if (!list.contains(addressTab)) {
			list.add(1, addressTab);
		}
	}
	
	public void closingPolicyChanged(
				ObservableValue<? extends TabPane.TabClosingPolicy> prop, 
				TabPane.TabClosingPolicy oldPolicy,
				TabPane.TabClosingPolicy newPolicy) 
    {
		tabPane.setTabClosingPolicy(newPolicy);
	}
}
