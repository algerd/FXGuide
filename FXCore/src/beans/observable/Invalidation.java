
package beans.observable;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Invalidation {
    
	public static void main(String[] args) {
        
        /*
        All JavaFX properties implement Observable interface.
        Properties can be observed for invalidations of its content.
        The Observable interface has two methods to support this: 
            - addListener(InvalidationListener listener) 
            - removeListener(InvalidationListener listener)
        The invalidated(Observable observable) method of the InvalidationListener 
        is called when the content of the Observable becomes invalid. 
        An InvalidationListener can be removed using its removeListener() method.
            
        A property generates an invalidation event when the status of its value changes from valid to invalid for the
        first time. Properties in JavaFX use lazy evaluation. When an invalid property becomes invalid again, an
        invalidation event is not generated. An invalid property becomes valid when it is recomputed, for example,
        by calling its get() or getValue() method. 
        
        You are not limited to adding only one invalidation listener to a property. You can add as many
        invalidation listeners as you need. Once you are done with an invalidation listener, make sure to remove it
        by calling the removeListener() method of the Observable interface; otherwise, it may lead to memory leaks.
        */       
		IntegerProperty counter = new SimpleIntegerProperty(100);

		// Add an invalidation listener to the counter property
		counter.addListener(Invalidation::invalidated);

		System.out.println("Before changing the counter value-1");
		counter.set(101);
		System.out.println("After changing the counter value-1");

		/* At this point counter property is invalid and further changes
		   to its value will not generate invalidation events.
		 */
		System.out.println("\nBefore changing the counter value-2");
		counter.set(102);
		System.out.println("After changing the counter value-2");

		// Make the counter property valid by calling its get() method
		int value = counter.get();
		System.out.println("Counter value = " + value);

		/* At this point counter property is valid and further changes
		   to its value will generate invalidation events.
		 */
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
}
