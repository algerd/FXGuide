
package Tab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
A window may not have enough space to display all of the pieces of information in one page view. JavaFX
provides several controls to break down large content into multiple pages, for example, Accordion and
Pagination controls. TabPane and Tab let you present information in a page much better. A Tab represents a
page and a TabPane contains the Tab.

A Tab is not a control. An instance of the Tab class represents a Tab. The Tab class inherits from the
Object class. However, the Tab supports some features as controls do, for example, they can be disabled,
styled using CSS, and can have context menus and tool tips.

A Tab consists of a title and content. The title consists of text, an optional graphic, and an optional close
button to close the tab. The content consists of controls. Typically, controls are added to a layout pane,
which is added to the Tab as its content.

Typically, the titles of the Tab in a TabPane are visible. The content area is shared by all Tabs. You need to
select a Tab, by clicking its title, to view its content. You can select only one tab at a time in a TabPane. If the
titles of all tabs are not visible, a control button is displayed automatically that assists the user in selecting
the invisible tabs.

Tabs in a TabPane may be positioned at the top, right, bottom, or left side of the TabPane. By default, they
are positioned at the top.

A TabPane is divided into two parts: header area and content area. The header area displays the titles
of tabs; the content area displays the content of the selected tab. The header area is subdivided into the
following parts:
    • Headers region
    • Tab header background
    • Control buttons tab
    • Tab area
The headers region is the entire header area.
The tab header background is the area occupied by the titles of the tabs. The control buttons tab contains
control buttons that are displayed when the width of the TabPane cannot display all of the tabs. The control
button tab lets you select the tabs that are currently not visible. The tab area contains a Label and a close
button (the X icon next to the tab label). The Label displays the text and icon for a tab. The close button is
used to close a selected tab.

The Tab class contains the following properties that let you set the title and content:
    • text
    • graphic
    • closable
    • content
The text, graphic, and closable properties specify what appears in the title bar of a tab. The text
property specifies a string as the title text. The graphic property specifies a node as the title icon. Notice
that the type of the graphic property is Node, so you can use any node as a graphic. Typically, a small icon
is set as the graphic. The text property can be set in the constructor or using the setText() method.

The closable property is a boolean property that specifies whether the tab can be closed. If it is set to
false, the tab cannot be closed. Closing of tabs is also controlled by the tab-closing policy of the TabPane.
If the closable property is set to false, the tab cannot be closed by the user, irrespective of the tab-closing
policy of the TabPane.

The content property is a node that specifies the content of the tab. The content of the tab is visible
when the tab is selected. Typically, a layout pane with controls is set as the content of a tab.

A TabPane stores the references of its tabs in an ObservableList<Tab>. The getTabs() method of the
TabPane class returns the reference of the observable list. To add a tab to the TabPane, you need to add it to
the observable list.

When a tab is not supposed to be part of a TabPane, you need to remove it from the observable list.
The TabPane will update its view automatically:
    tabPane.getTabs().remove(addressTab);

The read-only tabPane property of the Tab class stores the reference of the TabPane that contains the
tab. If a tab has not yet been added to a TabPane, its tabPane property is null. Use the getTabPane() method
of the Tab class to get the reference of the TabPane.

The program creates two tabs. They are instances of the GeneralTab and AddressTab
classes. They are added to a TabPane, which is added to center region of a BorderPane.
*/
public class TabTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
      
		ImageView privacyIcon = getImage("privacy_icon.png");
		GeneralTab generalTab = new GeneralTab("General", privacyIcon);
		       
		ImageView addressIcon = getImage("address_icon.png");
		AddressTab addressTab = new AddressTab("Address", addressIcon);
		
		TabPane tabPane = new TabPane();
		tabPane.getTabs().addAll(generalTab, addressTab);

		BorderPane root = new BorderPane(); 
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
		stage.setTitle("Using TabPane and Tab Controls");
		stage.show();
	}

	public ImageView getImage(String fileName) {
		ImageView imgView = null;
		try {
            String imagePathString = getClass().getClassLoader().getResource("Tab/" + fileName).toExternalForm();		
			Image img = new Image(imagePathString);
			imgView = new ImageView(img);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return imgView;
	}
}
