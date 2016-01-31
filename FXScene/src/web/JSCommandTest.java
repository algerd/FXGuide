
package web;

import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
The program shows how to open the jshandlers.html page in a WebView. If this page is not displayed, use the Open
button in the navigation bar to open the file, which is located in resources\html directory in the source
directory. Click each button on the page to test the JavaScript command handlers in JavaFX.
*/
public class JSCommandTest extends Application {
	private final String DEFAULT_HOME_PAGE = "web\\jshandlers.html"; 
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		String homePageUrl = getDefaultHomePageUrl(); 
		BrowserPane root = new BrowserPane(homePageUrl, stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);		
		stage.show();
	}
	
	public String getDefaultHomePageUrl() {
		String pageUrl = "http://www.google.com";           
		URL url = this.getClass().getClassLoader()
		              .getResource(DEFAULT_HOME_PAGE);
		if (url == null) {
			System.out.println(
				"Could not find " + DEFAULT_HOME_PAGE + " in CLASSPATH. " +
				"Using " + pageUrl + " as the default home page." );
		}
		else {
			pageUrl = url.toExternalForm();
		}       
		return pageUrl;
	}
}
