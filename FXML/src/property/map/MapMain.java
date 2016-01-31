
package property.map;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MapMain extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
       
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/property/map/map.fxml"));
        VBox root = loader.load();     
              
        Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Read-Only Map Properties");
		stage.show();    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
