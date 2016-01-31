
package beans.binding.fluent;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*
The BooleanExpression class contains methods such as and(), or(), and not() that let you use boolean
logical operators in an expression. Its isEqualTo() and isNotEqualTo() methods let you compare a
BooleanExpression with another ObservableBooleanValue. The result of a BooleanExpression is
true or false.
*/
public class BooleanExpressionTest {
    
	public static void main(String[] args) {
        
		IntegerProperty x = new SimpleIntegerProperty(1);
		IntegerProperty y = new SimpleIntegerProperty(2);
		IntegerProperty z = new SimpleIntegerProperty(3);

		// Create a boolean expression for x > y && y <> z
		BooleanExpression condition = x.greaterThan(y).and(y.isNotEqualTo(z));

		System.out.println(condition.get());

		// Make the condition true by setting x to 3
		x.set(3);
		System.out.println(condition.get());
	}
}
