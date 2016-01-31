
package event.expression;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

public class ActionHandler {

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
    
}
