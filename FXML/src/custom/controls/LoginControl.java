
package custom.controls;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Класс-загрузчик кастомного контрола.
 * Должен расширять fxml-элемент, который указан в аттрибуте type корневого
 * элемента fx:root кастомного контрола.
 */
public class LoginControl extends GridPane {
	@FXML
	private TextField userId;
	
	@FXML
	private PasswordField pwd;
	
    /**
     * Конструктор должен загрузить fxml-документ кастомного контрола,
     * предварительно передав в загрузчик сам fxml-документ кастомного контрола
     * и самого себя как контроллер.
     */
	public LoginControl() {	

		URL fxmlUrl = this.getClass().getClassLoader().getResource("custom/controls/login.fxml");     
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlUrl);
		loader.setRoot(this);
		loader.setController(this);
		
        try {
			loader.load();
		} 
		catch (IOException exception) {
		   throw new RuntimeException(exception);
		}
	}
	
    /**
     * Вызывается сразу после создания объекта CustomControl
     */
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void okClicked() {
		System.out.println("Ok cliked");
	}
	
	@FXML
	private void cancelClicked() {
	    System.out.println("Cancel cliked");
	}
	
	public String getUserId() {
		return userId.getText();
	}
			
	public String getPassword() {
		return pwd.getText();
	}
}
