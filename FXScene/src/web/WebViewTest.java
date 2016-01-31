
package web;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
/*
The constructor of the WebView class creates a WebEngine automatically and you cannot change it.
The getEngine() method of the WebView class returns the reference of the WebEngine:
    WebEngine webEngine = webView.getEngine();

A WebEngine can load content from a URL or a string in memory. You would use the load() method of
the WebEngine class to load content from a URL. The URL is specified as a String. It can be a local or remote
URL. You would use the reload() method of the WebEngine to reload the current page, as shown in the
following code:
    // Load the Google web page
    webEngine.load("http://www.google.com");

You would use one of the loadContent() methods to load the content from a String:
    • loadContent(String content)
    • loadContent(String content, String contentType)
Typically, this method is used when the content is retrieved from a database or the content is
constructed in memory. The first version assumes that the content type is “text/html”.

The WebEngine class contains a title property, which is updated at some point while a web page is
being loaded. You can achieve the same effect as above by listening to the change in the title property of
the WebEngine.
*/
public class WebViewTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		WebView webView = new WebView();
		WebEngine webEngine = webView.getEngine();
		
		// Update the stage title when a new web page title is available
		webEngine.titleProperty().addListener(
			(ObservableValue<? extends String> p,  String oldTitle, String newTitle) -> {stage.setTitle(newTitle);}
        );

		// Load the Google web page
		webEngine.load("http://www.google.com");

		VBox root = new VBox(webView);
		Scene scene = new Scene(root);
		stage.setScene(scene);		
		stage.show();
	}
}
