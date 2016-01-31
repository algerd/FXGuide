
package beans.binding.fluent;

import javafx.beans.binding.When;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.binding.StringBinding;

/*
The Java programming language offers a ternary operator, (condition?value1:value2), to perform a
ternary operation of the form when-then-otherwise. The JavaFX binding API has a When class for this purpose.
The general syntax of using the When class is shown here:
    new When(condition).then(value1).otherwise(value2)

The condition must be an ObservableBooleanValue. When the condition evaluates to true, it returns value1. 
Otherwise, it returns value2. The types of value1 and value2 must be the same. Values may be
constants or instances of ObservableValue.

Let’s use a ternary operation that returns a String even or odd depending on whether the value of an
IntegerProperty is even or odd, respectively. The Fluent API does not have a method to compute modulus.
You will have to do this yourself. Perform an integer division by 2 on an integer and multiply the result by 2.
If you get the same number back, the number is even. Otherwise, the number is odd. For example, using an
integer division, (7/2)*2, results in 6, and not 7.
*/
public class TernaryTest {	
    
	public static void main(String[] args) {
        
		IntegerProperty num = new SimpleIntegerProperty(10);				
		StringBinding desc = new When(num.divide(2).multiply(2).isEqualTo(num))
		                        .then("even")
		                        .otherwise("odd");
		
		System.out.println(num.get() + " is " + desc.get());
		
		num.set(19);
		System.out.println(num.get() + " is " + desc.get());	
	}
}
