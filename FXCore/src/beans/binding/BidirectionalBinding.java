
package beans.binding;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*
Интерфейс Property<T> предоставляет методы принудительного связывания и проверки связывания.

A bidirectional binding works in both directions. It has some restrictions. It can only be created
between properties of the same type. That is, a bidirectional binding can only be of the type x = y and
y = x, where x and y are of the same type.
A property can have multiple bidirectional bindings at the same time. 
A bidirectional bound property can also be changed independently; 
the change is reflected in all properties that are bound to this property.

Will both of the above bidirectional bindings end up having the same values in x, y, and z? 
The answer is no.Suppose x is 1, y is 2, and z is 3, and you have the following bidirectional bindings:
    x = y
    x = z
The first binding, x = y, will set the value of x equal to the value of y. At this point, x and y will be 2.
The second binding, x = z, will set the value of x to be equal to the value of z. That is, x and z will be 3.
However, x already has a bidirectional binding to y, which will propagate the new value 3 of x to y as well.
Therefore, all three properties will have the same value as that of z (3).
*/
public class BidirectionalBinding {
    
	public static void main(String[] args) {
        
		IntegerProperty x = new SimpleIntegerProperty(1);
		IntegerProperty y = new SimpleIntegerProperty(2);
		IntegerProperty z = new SimpleIntegerProperty(3);

		System.out.println("Before binding:");
		System.out.println("x=" + x.get() + ", y=" + y.get() + ", z=" + z.get());

		x.bindBidirectional(y);
		System.out.println("After binding-1:");
		System.out.println("x=" + x.get() + ", y=" + y.get() + ", z=" + z.get());

		x.bindBidirectional(z);
		System.out.println("After binding-2:");
		System.out.println("x=" + x.get() + ", y=" + y.get() + ", z=" + z.get());

		System.out.println("After changing z:");
		z.set(19);
		System.out.println("x=" + x.get() + ", y=" + y.get() + ", z=" + z.get());

        /*
        Unlike a unidirectional binding, when you create a bidirectional binding, the previous bindings are not
        removed because a property can have multiple bidirectional bindings. You must remove all bidirectional
        bindings using the unbindBidirectional() method, calling it once for each bidirectional binding for a
        property.
        */
		// Remove bindings
		x.unbindBidirectional(y);
		x.unbindBidirectional(z);
		System.out.println("After unbinding and changing them separately:");
		x.set(100);
		y.set(200);
		z.set(300);
		System.out.println("x=" + x.get() + ", y=" + y.get() + ", z=" + z.get());
	}
}
