
package labeled.SplitMenuButton;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/*
With our definitions of different button types, a SplitMenuButton falls under the hybrid category. It combines
the features of a pop-up menu and a command button. It lets you select an action like a MenuButton control
and execute a command like a Button control. The SplitMenuButton class inherits from the MenuButton class.

A SplitMenuButton is divided into two areas: the action area and the menu-open area. When you
click in the action area, ActionEvent is fired. The registered ActionEvent handlers execute the command.
When the menu-open area is clicked, a menu is shown from which the user will select an action to execute.
Mnemonic parsing for SplitMenuButton is enabled by default.

The program ishows how to use a SplitMenuButton. It adds a SplitMenuButton with the
text Home and three menu items in the top right region of a BorderPane. A WebView is added in the center
region. When you click Home, the www.jdojo.com web page is opened. When you select a web site using the
menu by clicking the down arrow, the corresponding web site is opened. The program is very similar to the
ones you developed earlier using MenuButton and Hyperlink controls.
*/
public class SplitMenuButtonTest extends Application {
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

		// Create a SplitMenuButton
		SplitMenuButton splitBtn = new SplitMenuButton();
		splitBtn.setText("Home");

		// Add menu items to the SplitMenuButton
		splitBtn.getItems().addAll(jdojo, yahoo, google); 

		// Add ActionEvent handler when "Home" is clicked
		splitBtn.setOnAction(e -> loadPage("http://www.jdojo.com"));

		BorderPane root = new BorderPane();
		root.setTop(splitBtn);
		BorderPane.setAlignment(splitBtn, Pos.TOP_RIGHT);
		root.setCenter(webview); 

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using SplitMenuButton Controls");
		stage.show();
	}
	
	public void loadPage(String url) {
		webview.getEngine().load(url);
	}
}
