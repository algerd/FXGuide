
package labeled.MenuButton;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/*
A MenuButton control looks like a button and behaves like a menu. When it is activated (by clicking or other
means), it shows a list of options in the form of a pop-up menu. The list of options in the menu is maintained
in an ObservableList<MenuItem> whose reference is returned by the getItems() method. To execute a
command when a menu option is selected, you need to add the ActionEvent handler to the MenuItems.

The MenuButton class declares two properties:
    • popupSide
    • showing

The popupSide property is of the ObjectProperty<Side> type and the showing property is of the ReadOnlyBooleanProperty type.
The popupSide property determines which side of the menu should be displayed. Its value is one of the
constants in the Side enum: TOP, LEFT, BOTTOM, and RIGHT. The default value is Side.BOTTOM. An arrow in the
MenuItem shows the direction set by the popupSide property. The arrow in Figure 12-5 is pointing downward,
indicating that the popupSide property is set to Side.BOTTOM. The menu is opened in the direction set in the
popupSide property only if space is available to display the menu in that side. If space is not available, the
JavaFX runtime will make a smart decision as to which side the menu should be displayed. The value of the
showing property is true when the pop-up menu is showing. Otherwise, it is false.
*/
public class MenuButtonTest extends Application {
	private WebView webview;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Must create a WebView object from the JavaFX Application Thread
		webview = new WebView(); 

		MenuItem jdojo = new MenuItem("JDojo");
		jdojo.setOnAction(e -> loadPage("http://www.jdojo.com"));
	
		MenuItem yahoo = new MenuItem("Yahoo");
		yahoo.setOnAction(e -> loadPage("http://www.yahoo.com"));

		MenuItem google = new MenuItem("Google");
		google.setOnAction(e -> loadPage("http://www.google.com"));
	
		// Add menu items to the MenuButton
		MenuButton links = new MenuButton("Visit");
		links.getItems().addAll(jdojo, yahoo, google); 

		BorderPane root = new BorderPane();
		root.setTop(links);
		BorderPane.setAlignment(links, Pos.TOP_RIGHT);
		root.setCenter(webview);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using MenuButton Controls");
		stage.show();
	}
	
	public void loadPage(String url) {
		webview.getEngine().load(url);
	}
}
