package TextFormatter;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
Listing describes an application that presents a text field for entering a phone number. 
It registers a change formatter with this text field to take appropriate action when the user enters non-digits. 
Because UnaryOperator is a functional interface, I could have used a lambda instead of instantiating an anonymous class 
that implements an interface.

Whenever you press a key while the text field has the focus, the apply() method is called. 
It outputs the change argument to the standard output stream, obtains the text that has changed, 
and scans it for a non-digit. If a non-digit is seen, null is returned to abort the change. Otherwise, change is returned to accept the change.
If you type a non-digit apart from the backspace or Delete, nothing will happen.
*/
public class TextFormatterDemo2 extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}
    
    @Override
    public void start(Stage primaryStage) {
        
        TextField txtPhone = new TextField("555-5555");     
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
        
        /*
        // TextFormatter (JavaFX) to restrict user input only for decimal number via lambda
        // http://stackoverflow.com/questions/31039449/java-8-u40-textformatter-javafx-to-restrict-user-input-only-for-decimal-number
        TextField txtPhone = new TextField();
        DecimalFormat format = new DecimalFormat( "#.0" );
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (c.getControlNewText().isEmpty()) {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition( 0 );
            Object object = format.parse( c.getControlNewText(), parsePosition );

            if (object == null || parsePosition.getIndex() < c.getControlNewText().length()){
                return null;
            }
            else {
                return c;
            }
        };
        */
           
        txtPhone.setTextFormatter(new TextFormatter<String>(filter));
        Label lblPhone = new Label("Phone");
        
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