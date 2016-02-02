package TextFormatter;

import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/*
As with TextFormatterDemo2, Listing describes an application that presents a text field for entering a phone number. 
As well as preserving TextFormatterDemo2's change formatter, Listing adds a value formatter. 
The value formatter ensures that the seven-digit phone number remains hyphenated when Enter is pressed.

With 555-5555 highlighted, press Delete and then Enter. You should observe 333-3333 in the text field. 
Press the backspace key once. You should now observe 333-333. Press Enter and the erroneous number is still displayed. 
You will have to switch to another application and return to this application to see the value replaced by 333-3333.

Erase the contents of the text field and enter 1234567. Press Enter and you should observe 123-4567 in the text field.
*/
public class TextFormatterDemo3 extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}
    
    @Override
    public void start(Stage primaryStage)
    {
        Label lblPhone = new Label("Phone");
        TextField txtPhone = new TextField("555-5555");
        
        StringConverter<String> formatter = new StringConverter<String>() {
            @Override
            public String fromString(String string) {
                System.out.println("fromString(): string = " + string);
                if (string.length() == 8) {
                    return string;
                }  
                else if (string.length() == 7 && string.indexOf('-') == -1) {                   
                    return string.substring(0, 3) + "-" + string.substring(3);
                }   
                else {
                    return "333-3333";
                }   
            }

            @Override
            public String toString(String object) {
                System.out.println("toString(): object = " + object);
                // only null when called from TextFormatter constructor without default
                if (object == null) {   
                   return "777-7777"; 
                }                      
                System.out.println("object.tostring = " + object.toString());
                return object;
            }
        };
        
        UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                System.out.println(change);
                String text = change.getText();
                for (int i = 0; i < text.length(); i++) {
                    if (!Character.isDigit(text.charAt(i))) {
                        return null;
                    }    
                }  
                return change;
            }
        };
        txtPhone.setTextFormatter(new TextFormatter<String>(formatter, "555-5555", filter));
        
        HBox hboxForm = new HBox(10);
        hboxForm.setPadding(new Insets(10, 10, 10, 10));
        hboxForm.getChildren().addAll(lblPhone, txtPhone);
        Scene scene = new Scene(hboxForm);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("TextFormatterDemo");
        primaryStage.show();
   }
}