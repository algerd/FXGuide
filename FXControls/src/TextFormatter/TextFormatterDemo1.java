package TextFormatter;

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
Listing describes an application that presents a text field for entering an age. 
It registers a value formatter with this text field to take appropriate action when the user enters non-digits 
(although the minus sign can be entered once -- I'll leave it as an exercise to ensure that the minus sign isn't entered).

Additionally, the following messages are output to the standard output stream:
    toString(): object = null
    toString(): object = null

These messages indicate that the value formatter's toString() method is called to convert the initial default value, 
which is null when you call TextFormatter(StringConverter<V> valueConverter) to install the value formatter 
but can be a non-null reference when you call TextFormatter(StringConverter<V> valueConverter, V defaultValue), to a string.

Next, replace the highlighted 0 with 10 and press Enter. You should observe the following messages on the standard output stream:
    fromString(): string = 10
    toString(): object = 10
    object.tostring = 10

The fromString() method is called to convert the entered string to an integer. 
If an illegal value had been entered, fromString()'s Integer.parseInt(string) method would have thrown NumberFormatException 
and the text formatter would have replaced what was entered with the previous legal value. 
This would have necessitated converting that value to a string. Either way, toString() is called to perform the conversion.

If you were to type 10a instead of 10 and press Enter, you would observe the text field reverting to 0 and the following messages:
    fromString(): string = 10a
    toString(): object = null

fromString()'s parseInt() call results in NumberFormatException being thrown. 
This causes toString() to be called with the previously valid value, which happens to be null because there is no default. 
When null is seen, toString() returns "0" as the default, which is why you initially see 0 in the text field and also after entering 10a.


*/
public class TextFormatterDemo1 extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}    
    
    @Override
    public void start(Stage primaryStage){
        
        Label lblAge = new Label("Age");
        TextField txtAge = new TextField("");  
        
        StringConverter<Integer> formatter = new StringConverter<Integer>() {
            @Override
            public Integer fromString(String string) {
               System.out.println("fromString(): string = " + string);
               return Integer.parseInt(string);
            }

            @Override
            public String toString(Integer object) {
                System.out.println("toString(): object = " + object);
                if (object == null) {
                   return "0";
                }  
                System.out.println("object.tostring = " + object.toString());
                return object.toString();
            }
        };
        txtAge.setTextFormatter(new TextFormatter<Integer>(formatter));
        // This code installs a value formatter with a default value of 1.
        //txtAge.setTextFormatter(new TextFormatter<Integer>(formatter, 1));
        
        HBox hboxForm = new HBox(10);
        hboxForm.setPadding(new Insets(10, 10, 10, 10));
        hboxForm.getChildren().addAll(lblAge, txtAge);
        Scene scene = new Scene(hboxForm);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("TextFormatterDemo");
        primaryStage.show();
   }
}