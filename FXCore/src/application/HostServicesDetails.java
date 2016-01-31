
package application;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import netscape.javascript.JSObject;

/*
The HostServices class in the javafx.application package provides services related to the launching
environment (desktop, web browser, or WebStart) hosting the JavaFX application. You cannot create an
instance of the HostServices class directly. The getHostServices() method of the Application class
returns an instance of the HostServices class.

The HostServices class contains the following methods:
    • String getCodeBase()
    • String getDocumentBase()
    • JSObject getWebContext()
    • String resolveURI(String base, String relativeURI)
    • void showDocument(String uri)

The getCodeBase() method returns the code base uniform resource identifier (URI) of the application.
In a stand-alone mode, it returns the URI of the directory that contains the JAR file used to launch the
application. If the application is launched using a class file, it returns an empty string. If the application is
launched using a JNLP file, it returns the value for the specified code base parameter in the JNLP file.

The getDocumentBase() method returns the URI of the document base. In a web environment, it
returns the URI of the web page that contains the application. If the application is launched using WebStart,
it returns the code base parameter specified in the JNLP file. It returns the URI of the current directory for
application launched in stand-alone mode.

The getWebContext() method returns a JSObject that allows a JavaFX application to interact with the
JavaScript objects in a web browser. If the application is not running in a web page, it returns null. You can
use the eval() method of the JSObject to evaluate a JavaScript expression from inside your JavaFX code.
The following snippet of code displays an alert box using the window.alert() function.

The resolveURI() method resolves the specified relative URI with respect to the specified base URI and
returns the resolved URI.

The showDocument() method opens the specified URI in a new browser window. Depending on the
browser preference, it may open the URI in a new tab instead. This method can be used in a stand-alone
mode as well as in a web environment. The following snippet of code opens the Yahoo! home page:
getHostServices().showDocument("http://www.yahoo.com");

The program uses all of the methods of the HostServices class. It shows a stage with two
buttons and host details. One button opens the Yahoo! home page and another shows an alert box. The
output shown on the stage will vary depending on how the application is launched.
*/
public class HostServicesDetails extends Application {
	public static void main(String[] args) {            
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		String yahooURL = "http://www.yahoo.com";
		Button openURLButton = new Button("Go to Yahoo!");
		openURLButton.setOnAction(e -> getHostServices().showDocument(yahooURL));
	
		Button showAlert = new Button("Show Alert");
		showAlert.setOnAction(e -> showAlert());
	
		VBox root = new VBox();
		
		// Add buttons and all host related details to the VBox
		root.getChildren().addAll(openURLButton, showAlert);
		
		Map<String, String> hostdetails = getHostDetails();
		for(Map.Entry<String, String> entry : hostdetails.entrySet()) {
			String desc = entry.getKey() + ": " + entry.getValue();
			root.getChildren().add(new Label(desc));
		}
		
		Scene scene = new Scene(root); 
		stage.setScene(scene);
		stage.setTitle("Knowing the Host");
		stage.show();
	}
	
	protected Map<String, String> getHostDetails() {
		Map<String, String> map = new HashMap<>();
		HostServices host = this.getHostServices();
		
		String codeBase = host.getCodeBase();
		map.put("CodeBase", codeBase);
		
		String documentBase = host.getDocumentBase();
		map.put("DocumentBase", documentBase);
		
		JSObject js = host.getWebContext();
		map.put("Environment", js == null?"Non-Web":"Web");
		
		String splashImageURI = host.resolveURI(documentBase, "splash.jpg");
		map.put("Splash Image URI", splashImageURI);
		
		return map;
	}
	
	protected void showAlert() {
		HostServices host = getHostServices();
		JSObject js = host.getWebContext();
		if (js == null) {
			Stage s = new Stage(StageStyle.UTILITY);
			s.initModality(Modality.WINDOW_MODAL);
			
			Label msgLabel = new Label("This is an FX alert!");
			Group root = new Group(msgLabel);
			Scene scene = new Scene(root);
			s.setScene(scene);
			
			s.setTitle("FX Alert");
			s.show();
		}
		else {
			js.eval("window.alert('This is a JavaScript alert!')");
		}
	}
}
