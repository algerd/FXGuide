
package custom.controls;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginMain extends Application {	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Кастомный контрол исподьзуется как обычный JavaFX-элемент типа расширяемого класса
        // Пример использования кастомного контрола в разметке fxml, а не в java, рассмотрен в пакете custom.root
		GridPane root = new LoginControl();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using FXMl Custom Control");
		stage.show();
	}
}
