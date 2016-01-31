
package event.expression;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class EventExpressionController {
    
    @FXML
	private Label msgLbl;
    
    @FXML
	private Button sayHello;

    @FXML
	private Button sayHelloLambda;
    
    // Обработчики для Expression Event Handlers - дают ошибку
    @FXML
    public EventHandler<Event> onActionHandler = new EventHandler<Event>() { 
        @Override
        public void handle(Event e) {
            System.out.println("Hello from Expression!!!");
        }
    };
    @FXML
    public EventHandler<Event> onActionHandlerLambda = e -> System.out.println("Hello from Expression!!!");

    
    public EventExpressionController() {}
    
}
