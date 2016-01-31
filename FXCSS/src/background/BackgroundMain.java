
package background;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BackgroundMain extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
             
        // Загрузка fxml-документа
        URL fxmlUrl = this.getClass().getResource("background.fxml");
        VBox root = FXMLLoader.load(fxmlUrl);       
		Scene scene = new Scene(root);
        // Загрузка css-стилей
        scene.getStylesheets().add("background/style.css");
              
		stage.setScene(scene);
		stage.setTitle("CSS Types");
		stage.show();
	}
}
