package Spinner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/*
When I make the previous spinners editable, type illegal characters (such as letters) into the text field, and press the Enter key, 
an exception is thrown. The exception is java.lang.NumberFormatException for the integer-based spinner and java.lang.RuntimeException 
(wrapping java.text.ParseException) for the double precision floating-point spinner. 
How can I prevent this exception from being thrown?

You can prevent this exception from being thrown by installing an instance of a subclass of the abstract javafx.util.StringConverter<T> class 
(where T is the type being converted to or from String) as the SpinnerValueFactory converter. 
This object would catch the exception being thrown and take action.

Spinner uses a javafx.scene.control.TextField object as an editor for obtaining user input.
Because a text field can store any character, inappropriate characters such as letters in a numeric context can be entered. 
After the user presses Enter, the input is passed to the SpinnerValueFactory converter's T fromString(String string) method. 
For the integer or double precision floating-point factories, T is either Integer or Double. 
Converting from a string with illegal characters to a number results in the exception being thrown from fromString(). 
Obtain a reference to the current converter and install a new converter whose fromString() method invokes 
the other converter's fromString() method in a try statement with an appropriate catch block. 
*/
public class SpinnerDemo3 extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}
    
    @Override
    public void start(Stage primaryStage)
    {
        Spinner<Integer> ispinner = new Spinner<>(1, 10, 2);
        ispinner.setEditable(true);
        StringConverter<Integer> sci = ispinner.getValueFactory().getConverter();
        StringConverter<Integer> sci2 = new StringConverter<Integer>() {
            @Override
            public Integer fromString(String value) {
                try {
                   return sci.fromString(value);
                }
                catch (NumberFormatException nfe) {
                   System.out.println("Bad integer: " + value);
                   return 0;
                }
            }

            @Override
            public String toString(Integer value) {
               return sci.toString(value);
            }
        };
        
        ispinner.getValueFactory().setConverter(sci2);

        Spinner<Double> dspinner = new Spinner<>(1.5, 3.5, 1.5, 0.5);
        dspinner.setEditable(true);
        StringConverter<Double> scd = dspinner.getValueFactory().getConverter();
        StringConverter<Double> scd2 = new StringConverter<Double>() {
               @Override
               public Double fromString(String value) {
                    try {
                       return scd.fromString(value);
                    }
                    catch (RuntimeException re) {
                       System.out.println("Bad double: " + value);
                       System.out.println("Cause: " + re.getCause());
                       return 0.0;
                    }
               }

               @Override
               public String toString(Double value){
                  return scd.toString(value);
               }
        };
        dspinner.getValueFactory().setConverter(scd2);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        grid.add(new Label("Integer Spinner"), 0, 0);
        grid.add(ispinner, 1, 0);
        grid.add(new Label("Double Spinner"), 0, 1);
        grid.add(dspinner, 1, 1);

        Scene scene = new Scene(grid, 350, 100);
        primaryStage.setTitle("SpinnerDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}