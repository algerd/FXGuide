// StyleClassTest.java
package selectors.classes;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Пример задания style-class имени классу из Java-кода.
 */
public class Main extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
        
		Label nameLbl = new Label("Name:");
		TextField nameTf = new TextField("");
		Button closeBtn  = new Button("Close");
		closeBtn.setOnAction(e -> Platform.exit());
			
		HBox root = new HBox();		
		root.getChildren().addAll(nameLbl, nameTf, closeBtn);
		
        /*
        Некоторые JavaFX-классы не имеют дефолтного style-class имени. Например, классы Region, Pane, HBox, VBox.
        Чтобы определить имеет ли класс дефолтное style-class имя, надо посмотреть его константу DEFAULT_STYLE_CLASS
        или конструктор класса, в котором может быть добавлено имя класса:
            getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        или с указанием конкретного класса
            getStyleClass().add("text-area");
        Для ускорения определения наличия дефолтного имени стилевого класса запустить программку Снеск,
        предварительно прописав там искомый класс.
        
        Чтобы использовать селектор класса для классов без style-class имён, надо явно задать
        им имя, используя метод getStyleClass() родителя Node. Это можно сделать в Java-коде,
        как в данном примере, или в fxml-документе (см. пакет selectors).
        
        Любому классу, расширяющему Node, можно дать любое количество style-class имён.
        getStyleClass() - метод Node, возвращает ObservableList<String> styleClass.
        Set the styleClass for the HBox to "hbox":
        */       
		root.getStyleClass().add("hbox");
        // root.getStyleClass().addAll("hbox", "hbox2", "hbox3");

		Scene scene = new Scene(root);
		scene.getStylesheets().add("selectors/classes/style.css");

		stage.setScene(scene);
		stage.setTitle("Using Style Class Selectors");
		stage.show();
	}
}
