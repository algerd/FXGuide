
package Menu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
A menu bar is a horizontal bar that acts as a container for menus. An instance of the MenuBar class represents
a menu bar. MenuBar is a control. Typically, it is added to the top part of a window. If you use a BorderPane as the
root for a scene in a window, the top region is the usual place for a MenuBar:
    BorderPane root = new BorderPane();
    root.setBottom(menuBar);

The MenuBar class contains a useSystemMenuBar property, which is of boolean type. By default, it is
set to false. When set to true, it will use the system menu bar if the platform supports it. For example, Mac
supports a system menu bar. If you set this property to true on Mac, the MenuBar will use the system menu
bar to display its items:
    menuBar.setUseSystemMenuBar(true);

A MenuBar itself does not take any space unless you add menus to it. Its size is computed based on
the details of the menus it contains. A MenuBar stores all of its menus in an ObservableList of Menu whose
reference is returned by its getMenus() method.

A menu contains a list of actionable items, which are displayed on demand, for example, by clicking it.
The list of menu items is hidden when the user selects an item or moves the mouse pointer outside the list.
A menu is typically added to a menu bar or another menu as a submenu.

An instance of the Menu class represents a menu. A menu displays text and a graphic.
The Menu class is inherited from the MenuItem class, which is inherited from the Object class. Menu is not
a node, and therefore, it cannot be added to a scene graph directly. You need to add it to a MenuBar. Use the
getMenus() method to get the ObservableList<Menu> for the MenuBar and add instances of the Menu class to
the list.

When a menu is clicked, typically its list of menu items are displayed, but no action is taken. The Menu
class contains the following properties that can be set to handle when its list of options are showing, shown,
hiding, and hidden, respectively:
    • onShowing
    • onShown
    • onHiding
    • onHidden
    • showing

The onShowing event handler is called just before the menu items for the menu is shown. The onShown
event handler is called after the menu items are displayed. The onHiding and onHidden event handlers are
the counterparts of the onShowing and onShown event handlers, respectively.

Typically, you add an onShowing event handler that enables or disables its menu items based on
some criteria. For example, suppose you have an Edit menu with Cut, Copy, and Paste menu items. In the
onShowing event handler, you would enable or disable these menu items depending on whether the focus is
in a text input control, if the control is enabled, or if the control has selection:
    editMenu.setOnAction(e -> { });

The showing property is a read-only boolean property. It is set to true when the items in the menu are
showing. It is set to false when they are hidden.

The program creates four menus, a menu bar, adds menus to the menu bar, and adds the menu bar to the top region of a BorderPane.  But you have not seen anything exciting about menus yet! 
You will need to add menu items to the menus to experience some excitement - see MenuItemTest.java
*/
public class MenuTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create some menus
		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");
		Menu optionsMenu = new Menu("Options");
		Menu helpMenu = new Menu("Help");

		// Add menus to a menu bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, editMenu, optionsMenu, helpMenu);

		BorderPane root = new BorderPane();
		root.setTop(menuBar);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Menus");
		stage.show();
	}
}
