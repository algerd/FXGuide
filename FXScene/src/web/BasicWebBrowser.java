
package web;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/*
With the NavigationBar and WebOptionsMenu classes, you can develop a basic web browser writing a
few lines of code. The program assembles the web browser components to build a basic web
browser. It displays a window with a navigation bar, options, and a WebView. You
would use the navigation bar to open any local or remote web page. Later you will enhance this program to
show the browsing history and add Back and Forward buttons.
*/
public class BasicWebBrowser extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		WebView webView = new WebView();

		// Update the stage title when a new web page title is available
		webView.getEngine().titleProperty().addListener(
			(ObservableValue<? extends String> p, String oldTitle, String newTitle) -> stage.setTitle(newTitle));

		// Load the Google web page
		String homePageUrl = "http://www.google.com";

		MenuButton options = new WebOptionsMenu(webView);
		NavigationBar navBar = new NavigationBar(webView, homePageUrl, true);
       
        BrowserHistory historyComponent = new BrowserHistory(webView);
        navBar.getChildren().addAll(options, historyComponent);
	
		VBox root = new VBox(navBar, webView);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
