
package custom.root;

import java.io.IOException;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Класс-загрузчик кастомного контрола.
 * Должен расширять fxml-элемент, который указан в аттрибуте type корневого
 * элемента fx:root кастомного контрола.
 */
public class CustomControl extends VBox {
    
    @FXML private TextField textField;

    /**
     * Конструктор должен загрузить fxml-документ кастомного контрола,
     * предварительно передав в загрузчик сам fxml-документ кастомного контрола
     * и самого себя как контроллер.
     */
    public CustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("custom_control.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    /**
     * Вызывается сразу после создания объекта CustomControl
     */
    @FXML
	private void initialize() {
	}

    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }

    public StringProperty textProperty() {
        return textField.textProperty();
    }

    @FXML
    protected void doSomething() {
        System.out.println("The button was clicked!");
    }
}