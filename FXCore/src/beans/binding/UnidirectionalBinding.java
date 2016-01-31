
package beans.binding;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*
Интерфейс Property<T> предоставляет методы принудительного связывания и проверки связывания.
Метод bind() осуществляет Unidirectional binding. Unidirectional binding has a restriction. 
Once a property has a unidirectional binding, you cannot change the value of the property directly; 
its value must be computed automatically based on the binding. You must unbind it before changing its value directly.
A property can have only one unidirectional binding at a time!
*/
public class UnidirectionalBinding {
    
	public static void main(String[] args) {
        
		IntegerProperty x = new SimpleIntegerProperty(10);
		IntegerProperty y = new SimpleIntegerProperty(20);
		IntegerProperty z = new SimpleIntegerProperty(60);
        
        /*
        In JavaFX, you can also bind a property to a binding.
        XxxBinding имплементирует ObservableValue интерфейс, поэтому его можно связать со свойством,
        используя метод bind(ObservableValue<? extends T> observable) интерфейса Propеrty.
            NumberBinding sum = x.add(y);
            z.bind(sum);
        */
		z.bind(x.add(y));       
		System.out.println("After binding z: Bound = " + z.isBound() + ", z = " + z.get());

		// Change x and y
		x.set(15);
		y.set(19);
		System.out.println("After changing x and y: Bound = " + z.isBound() + ", z = " + z.get());
		      
        /*
        Чтобы изменить связанное свойство z, надо предварительно его отвязать!
        Чтобы отвязать свойство надо использовать метод unbind() интерфейса Propеrty.
        Метод isBound() интерфейса Propеrty проверяет связывание свойства.
        */
        // z.set(7878); // Will throw a RuntimeException
		z.unbind();

		// Will not affect the value of z as it is not bound to x and y anymore
		x.set(100);
		y.set(200);
		System.out.println("After unbinding z: Bound = " + z.isBound() + ", z = " + z.get());
        z.set(7878);
        
        // A property can have only one unidirectional binding at a time!
        z.bind(x.add(y));
        System.out.println("z = " + z.get()); 
        z.bind(x.multiply(y)); // Will unbind the previous binding
        System.out.println("z = " + z.get());
         
    }
}
