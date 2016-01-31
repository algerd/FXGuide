
package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BaseMain extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
    /*
     * Методы load() загрузчика FXMLLoader выбрасывают IOException,
     * поэтому их надо словить через try-catch или декларацию метода через tyrows
     */
	public void start(Stage stage) throws IOException {
        /*
        * Относительные пути URL fxml-документа:
        */
        //относительно директории данного класса
		//URL fxmlUrl = this.getClass().getResource("base.fxml");
        
        //относительно CLASSPATH, через указание "/"
		//URL fxmlUrl = this.getClass().getResource("/base/base.fxml");

        //относительно CLASSPATH, через загрузчик классов
        URL fxmlUrl = this.getClass().getClassLoader().getResource("base/base.fxml");
        
        /* 
         * Загрузка fxml-документа с помощью статических методов FXMLLoader.load()
         */
		//VBox root = FXMLLoader.<VBox>load(fxmlUrl);	
        /* можно без указания возвращаемого типа */
        //VBox root = FXMLLoader.load(fxmlUrl); 
        
        /*
         * Загрузка fxml-документа с помощью объекта FXMLLoader и его объектных методов load()
         * Предоставляет больше гибкости и информационнасти. Объект загрузчика через геттеры, 
         * через которые предоставляет доступ к контроллеру, ресурсам, кодировке и т.д.
         * Поэтому такая загрузка предпочтительнее для специфичных задач.
         */
        /* Загрузка fxml-документа через InputStream, используя объектный метод load() */
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fxmlUrl);
        VBox root = loader.load();
                  
        /* Загрузка fxml-документа через InputStream, используя объектный метод load(InputStream inputStream) */        
        //VBox root = loader.load(new FileInputStream("src/base/base.fxml"));
              
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Base of FXML");
		stage.show();
	}
    
}
