
package check.style_class_name;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Проверка дефолтного style-class имени класса.
 * @author Alex
 */
public class Check extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage){
    
        // Проверяемый класс TextArea
        Node obj = new TextArea();

        ObservableList<String> list = obj.getStyleClass();

        if (list.isEmpty()) {
            System.out.println("No default style class name");
        } 
        else {
            for(String styleClassName : list) {
                System.out.println(styleClassName);
            }
        }
    }    
}
