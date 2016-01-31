
package collections.ObservableMap;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

/*
An ObservableMap can be observed for changes by adding a MapChangeListener. The onChanged() method
of map change listeners is called for every addition and removal of a (key, value) pair and for a change in the
value of an existing key.
An object of the MapChangeListener.Change class is passed to the onChanged() method of the
MapChangeListener interface. MapChangeListener.Change is a static inner class of the MapChangeListener
interface with the following methods:
    • boolean wasAdded()
    • boolean wasRemoved()
    • K getKey()
    • V getValueAdded()
    • V getValueRemoved()
    • ObservableMap<K,V> getMap()

The wasAdded() method returns true if a (key, value) pair is added. 
The wasRemoved() method returns true if a (key, value) pair is removed. If the value for an existing key is replaced, 
    both methods return true for the same change event. Replacing the value of a key is treated as a removal 
    of the (key, oldValue) pair followed by an addition of a new (key, newValue) pair.
The getKey method returns the key associated with the change. If it is a removal, the key returned by
    this method does not exist in the map when the change is reported. 
The getValueAdded() method returns the new key value for an addition. For a removal, it returns null. 
The getValueRemoved() method returns the old value of the removed key. This is null if and only 
    if the value was added to the key that was not previously in the map. 
The getMap() method returns the source ObservableMap on which the changes are performed.
*/
public class ObservableMapChange {
    
	public static void main(String[] args) {
        
		ObservableMap<String, Integer> map = FXCollections.observableHashMap();

		// Add an MapChangeListener to the map
		map.addListener(ObservableMapChange::onChanged);
		
		System.out.println("Before adding (\"one\", 1)");
		map.put("one", 1);	    
		System.out.println("After adding (\"one\", 1)");
		
		System.out.println("\nBefore adding (\"two\", 2)");
		map.put("two", 2);
		System.out.println("After adding (\"two\", 2)");
		
		System.out.println("\nBefore adding (\"one\", 3)");
		
		// Will remove ("one", 1) and add("one", 3)
		map.put("one", 3);
		System.out.println("After adding (\"one\", 3)");
		
		System.out.println("\nBefore calling clear()");
		map.clear();
		System.out.println("After calling clear()");
	}
	
	public static void onChanged(
			MapChangeListener.Change<? extends String, ? extends Integer> change) {
		if (change.wasRemoved()) {
			System.out.println("Removed (" + change.getKey() + ", " + 
			                    change.getValueRemoved() + ")"); 
		}
		
		if (change.wasAdded()) {		    
			System.out.println("Added (" + change.getKey() + ", " + 
			                   change.getValueAdded() + ")");	
		}
	}
}
