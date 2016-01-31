
package beans.binding.fluent;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


/*
The methods in the NumberExpression interface allow for mixing types (int, long, float, and
double) while defining a binding, using an arithmetic expression. When the return type of a method in
this interface is NumberBinding, the actual returned type would be of IntegerBinding, LongBinding,
FloatBinding, or DoubleBinding. The binding type of an arithmetic expression is determined by the
same rules as the Java programming language. The results of an expression depend on the types of the
operands. The rules are as follows:
    • If one of the operands is a double, the result is a double.
    • If none of the operands is a double and one of them is a float, the result is a float.
    • If none of the operands is a double or a float and one of them is a long, the result is a long.
    • Otherwise, the result is an int.

Consider the following snippet of code:
    IntegerProperty x = new SimpleIntegerProperty(1);
    IntegerProperty y = new SimpleIntegerProperty(2);
    NumberBinding sum = x.add(y);
    int value = sum.intValue();

The number expression x.add(y) involves only int operands (x and y are of int type). Therefore,
according to the above rules, its result is an int value and it returns an IntegerBinding object. Because the
add() method in the NumberExpression specifies the return type as NumberBinding, a NumberBinding type is
used to store the result. You have to use the intValue() method from the ObservableNumberValue interface.
You can rewrite the above snippet of code as follows:
    IntegerProperty x = new SimpleIntegerProperty(1);
    IntegerProperty y = new SimpleIntegerProperty(2);

Casting to IntegerBinding is safe
    IntegerBinding sum = (IntegerBinding)x.add(y);
    int value = sum.get();
*/
public class NumberExpressionTest {
    
	public static void main(String[] args) {
        
		DoubleProperty radius = new SimpleDoubleProperty(7.0);

		// Create a binding for computing arae of the circle
		DoubleBinding area = radius.multiply(radius).multiply(Math.PI);

		System.out.println("Radius = " + radius.get() + ", Area = " + area.get());

		// Change the radius
		radius.set(14.0);
		System.out.println("Radius = " + radius.get() + ", Area = " + area.get());

		// Create a DoubleProperty and bind it to an expression that computes the area of the circle
		DoubleProperty area2 = new SimpleDoubleProperty();
		area2.bind(radius.multiply(radius).multiply(Math.PI));
		System.out.println("Radius = " + radius.get() + ", Area2 = " + area2.get());
	}
}
