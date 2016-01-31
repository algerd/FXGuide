
package collections.ObservableMap;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/*
You can add invalidation listeners to an ObservableMap. It fires an invalidation event when a new (key,
value) pair is added, the value for an existing key is changed, or a (key, value) pair is removed. Invalidation
events are fired once for every affected (key, value) pair. For example, if you call the clear() method on an
observable map that has two entries, two invalidation events are fired.
*/
public class ObservableMapInvalidation {
    
	public static void main(String[] args) {
        
		ObservableMap<String, Integer> map = FXCollections.observableHashMap();

		// Add an InvalidationListener to the map
		map.addListener(ObservableMapInvalidation::invalidated);

		System.out.println("Before adding (\"one\", 1)");
		map.put("one", 1);
		System.out.println("After adding (\"one\", 1)");

		System.out.println("\nBefore adding (\"two\", 2)");
		map.put("two", 2);
		System.out.println("After adding (\"two\", 2)");

		System.out.println("\nBefore adding (\"one\", 1)");
		
		// Adding the same (key, value) does not trigger an invalidation event
		map.put("one", 1);
		System.out.println("After adding (\"one\", 1)");

		System.out.println("\nBefore adding (\"one\", 100)");
		
		// Adding the same key with different value triggers invalidation event
		map.put("one", 100);
		System.out.println("After adding (\"one\", 100)");

		System.out.println("\nBefore calling clear()");
		map.clear();
		System.out.println("After calling clear()");
	}

	public static void invalidated(Observable map) {
		System.out.println("Map is invalid.");
	}
}
