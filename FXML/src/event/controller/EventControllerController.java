
package event.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/*
Контроолер десериализует fxml-документ, поэтому внутри него становятся доступными
все объекты fxml-документа с заданными идентификаторами(fx:id), которые соответствуют
полям контроллера
Аннотация @FXML делает доступными приватные методы и поля в fxml-загрузчике
Рекомендуется делать @FXML-аннотацию всем полям и методам, используемыми в fxml-документе
location и resources автоматически внедряются в контроллер
*/
public class EventControllerController {
    
    // <Label fx:id="msgLbl" text="FXML is cool!" prefWidth="150"/>
	@FXML
	private Label msgLbl;
    
    // <Button fx:id="sayHelloBtn" text="Say Hello" onAction="#sayHello"/>
    @FXML
    private Button sayHelloBtn;

	// URL fxml-документа
	@FXML 
	private URL location;

	@FXML 
	private ResourceBundle resources;
         
    // Необходимо явно задать публичный конструктор контроллера без аргументов!
    public EventControllerController() {}
    
    /*
        initialize() вызывается после полной загрузки fxml-документа.
        Метод должен быть без аргументов и возвращать void.
    */
    @FXML
    private void initialize() {
        System.out.println("Initializing SayHelloController...");
        System.out.println("Location = " + location);
        System.out.println("Resources = " + resources);
    }
    
    /*
        Методы-обработчики события. Вызывается при onAction на объекте <Button fx:id="sayHelloBtn">
        Метод может иметь 1 аргумент с типом совместимого события.
        Могут быть 2 метода - с аргументом и без, тогда будет вызываться метод с аргументом.
    */
    @FXML 
    private void sayHello() {
		msgLbl.setText("Hello from Controller!");
	}
    // Будет вызван этот метод. Вместо Event можно указать более конкретный тип ActionEvent для класса Button
    @FXML 
    private void sayHello(Event e) {
		msgLbl.setText("Hello from Controller!!!");
	}
           
}
