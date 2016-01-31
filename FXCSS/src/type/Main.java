
package type;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
             
        // Загрузка fxml-документа
        URL fxmlUrl = this.getClass().getResource("type.fxml");
        VBox root = FXMLLoader.load(fxmlUrl);       
		Scene scene = new Scene(root);
        // Загрузка css-стилей
        scene.getStylesheets().add("type/main.css");
        scene.getStylesheets().add("type/number.css");
        scene.getStylesheets().add("type/font.css");
        scene.getStylesheets().add("type/color.css");
        scene.getStylesheets().add("type/effect.css");
        scene.getStylesheets().add("type/paint.css");
         
		stage.setScene(scene);
		stage.setTitle("CSS Types");
		stage.show();
	}
}
