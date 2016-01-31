
package collections.ObservableSet;

import java.util.HashSet;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;

/*
An ObservableSet can be observed for changes. You need to add a SetChangeListener whose onChanged()
method is called for every addition or removal of elements. It means if you use methods like addAll() or
removeAll() on an ObservableSet, which affects multiple elements, multiple change notifications will be
fired—one for each element added or removed.

An object of the SetChangeListener.Change class is passed to the onChanged() method of the
SetChangeListener interface. The SetChangeListener.Change class is a static inner class of the
SetChangeListener interface with the following methods:
    • boolean wasAdded()
    • boolean wasRemoved()
    • E getElementAdded()
    • E getElementRemoved()
    • ObservableSet<E> getSet()

The wasAdded() and wasRemoved() methods return true if an element was added and removed,
    respectively. Otherwise, they return false. 
The getElementAdded() and getElementRemoved() methods
    return the element that was added and removed, respectively. 
The getElementAdded() method returns
    null if removal of an element triggers a change notification. 
The getElementRemoved() method returns
    null if addition of an element triggers a change notification. 
The getSet() method returns the source ObservableSet on which the changes are performed.
*/
public class ObservableSetChange {
	public static void main(String[] args) {
		// Create an observable set with some elements
		ObservableSet<String> set = FXCollections.observableSet("one", "two");

		// Add a change lisetener to the set
		set.addListener(ObservableSetChange::onChanged);
	
		set.add("three"); // Fires an add change event

		// Will not fire a change event as "one" already exists in the set
		set.add("one");

		// Create a Set
		Set<String> s = new HashSet<>();
		s.add("four");
		s.add("five");

		// Add all elements of s to set in one go
		set.addAll(s); // Fires two add change events

		set.remove("one"); // Fires a removal change event
		set.clear();       // Fires four removal change events
	}

	public static void onChanged(SetChangeListener.Change<? extends String> change) {
		if (change.wasAdded()) {
			System.out.print("Added: " + change.getElementAdded());
		} else if (change.wasRemoved()) {
			System.out.print("Removed: " + change.getElementRemoved());
		}		
		System.out.println(", Set after the change: " + change.getSet());
	}
}
