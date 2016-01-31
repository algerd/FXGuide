
package event.spec;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class EventSpecController {
    
    @FXML
	private Label msgLbl;
    
   
    public EventSpecController() {}
    
    // Слушатель для коллекции - children - тестировать
    @FXML 
    public void handleChildrenChange(ListChangeListener.Change c) {
        System.out.println("Children changed!");
    }
    
}
