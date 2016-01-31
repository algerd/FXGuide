
package labeled.Hyperlink;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/*
An instance of the Hyperlink class represents a hyperlink control, which looks like a hyperlink in a
web page. In a web page, a hyperlink is used to navigate to another web page. However, in JavaFX, an
ActionEvent is triggered when a Hyperlink control is activated, for example, by clicking it, and you are free
to perform any action in the ActionEvent handler.

A Hyperlink control is simply a button styled to look like a hyperlink. By default, mnemonic parsing is off.
A Hyperlink control can have focus, and by default, it draws a dashed rectangular border when it has focus.
When the mouse cursor hovers over a Hyperlink control, the cursor changes to a hand and its text is underlined.

The Hyperlink class contains a visited property of BooleanProperty type. When a Hyperlink control
is activated for the first time, it is considered “visited” and the visited property is set to true automatically.
All visited hyperlinks are shown in a different color than the not visited ones. You can also set the visited
property manually using the setVisited() method of the Hyperlink class.
*/
public class HyperlinkTest extends Application {
	private WebView webview;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Must create a WebView object from the JavaFX Application Thread
		webview = new WebView(); 

		// Create some hyperlinks
		Hyperlink jdojoLink = new Hyperlink("JDojo");
		jdojoLink.setOnAction(e -> loadPage("http://www.jdojo.com"));
		
		Hyperlink yahooLink = new Hyperlink("Yahoo!");
		yahooLink.setOnAction(e -> loadPage("http://www.yahoo.com"));

		Hyperlink googleLink = new Hyperlink("Google");
		googleLink.setOnAction(e -> loadPage("http://www.google.com"));

		HBox linkBox = new HBox(jdojoLink, yahooLink, googleLink);
		linkBox.setSpacing(10);
		linkBox.setAlignment(Pos.TOP_RIGHT);

		BorderPane root = new BorderPane();
		root.setTop(linkBox);
		root.setCenter(webview); 

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Hyperlink Controls");
		stage.show();
	}
	
	public void loadPage(String url) {
		webview.getEngine().load(url);
	}
}
