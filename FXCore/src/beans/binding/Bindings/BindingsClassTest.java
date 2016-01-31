
package beans.binding.Bindings;

import java.util.Locale;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/*
The Bindings class is a helper class to create simple bindings. It consists of more than 150 static methods.
Most of them are overloaded with several variants.

Most of our examples using the Fluent API can also be written using the Bindings class. 
The program uses the Bindings class instead of the Fluent API. 
It uses the multiply() method to compute the area and the format() method to format the results. There may be
several ways of doing the same thing. For formatting the result, you can also use the Bindings.concat()
method, as shown here:
    StringExpression desc = Bindings.concat(
        "Radius = ", radius.asString(Locale.US, "%.2f"),
        ", Area = ", area.asString(Locale.US, "%.2f")
    );
*/
public class BindingsClassTest {
    
	public static void main(String[] args) {
        
		DoubleProperty radius = new SimpleDoubleProperty(7.0);
		DoubleProperty area = new SimpleDoubleProperty(0.0);
	
		// Bind area to an expression that computes the area of the circle
		area.bind(Bindings.multiply(Bindings.multiply(radius, radius), Math.PI));

		// Create a string expression to describe the circle	    
		StringExpression desc = Bindings.format(Locale.US, "Radius = %.2f, Area = %.2f", radius, area);

		System.out.println(desc.get());

		// Change the radius
		radius.set(14.0);
		System.out.println(desc.getValue());
	}
}
