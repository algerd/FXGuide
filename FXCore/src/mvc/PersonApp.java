
package mvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
The program creates the model, view, and presenter, glues them together, and displays the view in a window. 
Notice that the view must be attached to a scene before the presenter is created. It is required
because the presenter attaches a focus change listener to the scene. Creating the presenter before adding the
view to the scene will result in a NullPointerException.
*/
public class PersonApp extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Person model = new Person();		
		String dateFormat = "MM/dd/yyyy";
		PersonView view = new PersonView(model, dateFormat);

		// Must set the scene before creating the presenter that uses 
		// the scene to listen for the focus change
		Scene scene = new Scene(view);

		PersonPresenter presenter = new PersonPresenter(model, view);
		view.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		
		stage.setScene(scene);
		stage.setTitle("Person Management");
		stage.show();
	}
}
