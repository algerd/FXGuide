
package lookup;

import java.io.IOException;
import java.net.URL;
import java.util.Set;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LookupMain extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
             
        // Загрузка fxml-документа
        URL fxmlUrl = this.getClass().getResource("lookup.fxml");
        HBox root = FXMLLoader.load(fxmlUrl);       
		Scene scene = new Scene(root);
        // Загрузка css-стилей
        scene.getStylesheets().add("lookup/style.css");
        
        /*
        С помощью методов поиска элемента по селектору можно найти один или коллекцию
        узлов и затем с ними работать в java-коде, напр. изменить им какие-нибудь свойства.
        Это полезно, если сцена создаётся с помощью fxml-документа и необходим доступ к узлам
        не из контроллера fxml-документа.
        
            lookup(String selector) возвращает первый найденный узел и прекращает поиск.
                Если ничего не найдено возвращает null.
            lookupAll(String selector) возвращает коллекцию всех найденных узлов.
        */
        // вернуть узел с селектором "#okButton" из всей сцены и из её детей-контейнеров
        Node okButton = scene.lookup("#okButton");
        // вернуть узел с селектором "#okButton" из контейнера root (HBox) и из его детей-контейнеров
        Node node = root.lookup("#box > .button");
        // вернуть все узлы с селектором ".button" из контейнера root (HBox) и из его детей-контейнеров
        Set<Node> set = root.lookupAll(".button");
        
        // Изменить свойство узла okButton (Button c id="okButton")
        okButton.setStyle("-fx-text-fill: blue;");
      
		stage.setScene(scene);
		stage.setTitle("Look Up");
		stage.show();
	}
}
