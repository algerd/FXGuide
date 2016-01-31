
package inheritance;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
             
        // Загрузка fxml-документа
        URL fxmlUrl = this.getClass().getResource("inheritance.fxml");
        HBox root = FXMLLoader.load(fxmlUrl);       
		Scene scene = new Scene(root);
        // Загрузка css-стилей
        scene.getStylesheets().add("inheritance/style.css");
        
		stage.setScene(scene);
		stage.setTitle("CSS Inheritance");
		stage.show();
	}
}
