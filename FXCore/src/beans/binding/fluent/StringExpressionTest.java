
package beans.binding.fluent;

import java.util.Locale;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
The methods in the StringExpression class let you create binding using a fluent style. 
Methods are provided to concatenate an object to the StringExpression, compare two strings, check for null, among others.
It has two methods to get its value: getValue() and getValueSafe(). Both return the current value.
However, the latter returns an empty String when the current value is null.

The program shows how to use StringBinding and StringExpression classes.
The concat() method in the StringExpression class takes an Object type as an argument. If the argument
is an ObservableValue, the StringExpression is updated automatically when the argument changes.
Note the use of the asString() method on the radius and area properties. The asString() method on a
NumberExpression returns a StringBinding.
*/
public class StringExpressionTest {
    
	public static void main(String[] args) {
        
		DoubleProperty radius = new SimpleDoubleProperty(7.0);
		DoubleProperty area = new SimpleDoubleProperty(0);
		StringProperty initStr = new SimpleStringProperty("Radius = ");
		
		// Bind area to an expression that computes the area of the circle
		area.bind(radius.multiply(radius).multiply(Math.PI));

		// Create a string expression to describe the circle
		StringExpression desc = initStr.concat(radius.asString())
		                               .concat(", Area = ")
		                               .concat(area.asString(Locale.US, "%.2f"));

		System.out.println(desc.getValue());

		// Change the radius
		radius.set(14.0);
		System.out.println(desc.getValue());
	}
}
