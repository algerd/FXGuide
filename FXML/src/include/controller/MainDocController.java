
package include.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Главный контроллер главного fxml-документа.
 * Он имеет ссылку на вложенный контроллер includedCloseBtnController.
 * Имя ссылки вложенного контроллера в главном контроллере должна соответствовать правилу:
 *  fx:id элемента fx:include + Controller = includedCloseBtn + Controller
 */
public class MainDocController {
	@FXML 
	private Button includedCloseBtn;
		
	@FXML
    // вложенный контроллер
	private CloseBtnController includedCloseBtnController;
	
	@FXML
	public void initialize() {
		System.out.println("MainDocController.initialize()");	    
		// You can use the nested controller here
	}
}
