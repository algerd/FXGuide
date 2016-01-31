
package event.controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventControllerMain extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
       
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/event/controller/eventController.fxml"));
        /*
        Cледует вызывать только объектные(нестатические) методы load() 
        или load(InputStream inputStream) загрузчика, чтобы загрузить 
        документ в объект загрузчика и потом получить доступ к контроллеру. 
        */
        VBox root = loader.load();     
        // Получаем ссылку fxml-контроллера 
        EventControllerController controller = loader.getController();
        
        Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Controller Method Event Handlers");
		stage.show();    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
