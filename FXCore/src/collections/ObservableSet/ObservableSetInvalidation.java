
package collections.ObservableSet;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/*
You can add invalidation listeners to an ObservableSet. It fires an invalidation event when elements are
added or removed. Adding an already existing element does not fire an invalidation event.
*/
public class ObservableSetInvalidation {
    
	public static void main(String[] args) {
        
		// Create a set with some elements
		ObservableSet<String> set = FXCollections.observableSet("one", "two");

		// Add an InvalidationListener to the set
		set.addListener(ObservableSetInvalidation::invalidated);

		System.out.println("Before adding three.");
		set.add("three");
		System.out.println("After adding three.");

		System.out.println("\nBefore adding four.");
		set.add("four");
		System.out.println("After adding four.");

		System.out.println("\nBefore adding one.");
		set.add("one");
		System.out.println("After adding one.");

		System.out.println("\nBefore removing one.");
		set.remove("one");
		System.out.println("After removing one.");

		System.out.println("\nBefore removing 123.");
		set.remove("123");
		System.out.println("After removing 123.");
	}

	public static void invalidated(Observable set) {
		System.out.println("Set is invalid.");
	}
}
