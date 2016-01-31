
package beans.observable;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

/*
The output below it shows that when the property value changes, both events, invalidation and change, are always fired. 
This is because a change event makes a property valid immediately!!! after the change, 
and the next change in the value fires an invalidation event, and of course, a change event too.

A rule of thumb is that if you read the value of the property inside the invalidation event handler, 
you should use a change listener instead. When you read the value of a property inside an invalidation listener, 
it triggers the recomputation of the value, which is automatically done before firing a change event. 
If you do not need to read the value of a property, use invalidation listeners.
*/
public class ChangeAndInvalidation {
    
	public static void main(String[] args) {
        
		IntegerProperty counter = new SimpleIntegerProperty(100);
		
		// Add an invalidation listener to the counter property
		counter.addListener(ChangeAndInvalidation::invalidated);
		
		// Add a change listener to the counter property
		counter.addListener(ChangeAndInvalidation::changed);
		
		System.out.println("Before changing the counter value-1");
		counter.set(101);
		System.out.println("After changing the counter value-1");
		
		System.out.println("\nBefore changing the counter value-2");
		counter.set(102);
		System.out.println("After changing the counter value-2");

		// Try to set the same value
		System.out.println("\nBefore changing the counter value-3");
		counter.set(102);
		System.out.println("After changing the counter value-3");
		
		// Try to set a different value
		System.out.println("\nBefore changing the counter value-4");
		counter.set(103);
		System.out.println("After changing the counter value-4");
	}
	
	public static void invalidated(Observable prop) {
		System.out.println("Counter is invalid.");
	}
	
	public static void changed(ObservableValue<? extends Number> prop, Number oldValue, Number newValue) {
		System.out.print("Counter changed: ");
		System.out.println("old = " + oldValue + ", new = " + newValue);        
	}	
}
