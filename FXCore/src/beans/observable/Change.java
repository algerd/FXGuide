
package beans.observable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public class Change {
    
	public static void main(String[] args) {
        /*
        All JavaFX properties implement ObservableValue<T> interface.
        Properties can be observed for change of its content.
        The ObservableValue<T> interface has two methods to support this: 
            - addListener(ChangeListener<? super T> listener)
            - removeListener(ChangeListener<? super T> listener)
        The T changed(ObservableValue<? extends T> observable, T oldValue, T newValue)  method of the ChangeListener 
        is called when the content of the ObservableValue<T> is changed. 
        An ChangeListener can be removed using its removeListener() method.
        
        A property change event is fired immediately every time the value of a property changes. 
        The changed() method of a ChangeListener three values: the reference of the property object, the old value, and the new value.
        Unlike generating invalidation events, a property uses an eager evaluation for its value to generate
        change events, because it has to pass the new value to the property change listeners.
        
        You are not limited to adding only one change listener to a property. You can add as many
        change listeners as you need. Once you are done with an change listener, make sure to remove it
        by calling the removeListener() method of the ObservableValue<T> interface; otherwise, it may lead to memory leaks.
        */
        
		IntegerProperty counter = new SimpleIntegerProperty(100);

		// Add a change listener to the counter property
		counter.addListener(Change::changed);

		System.out.println("\nBefore changing the counter value-1");
		counter.set(101);
		System.out.println("After changing the counter value-1");

		System.out.println("\nBefore changing the counter value-2");
		counter.set(102);
		System.out.println("After changing the counter value-2");

		// Try to set the same value
		System.out.println("\nBefore changing the counter value-3");
		counter.set(102); // No change event is fired.
		System.out.println("After changing the counter value-3");

		// Try to set a different value
		System.out.println("\nBefore changing the counter value-4");
		counter.set(103);
		System.out.println("After changing the counter value-4");
	}

	public static void changed(ObservableValue<? extends Number> prop, Number oldValue, Number newValue) {
		System.out.print("Counter changed: ");
		System.out.println("Old = " + oldValue + ", new = " + newValue);
	}
}
