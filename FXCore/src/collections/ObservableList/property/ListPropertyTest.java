
package collections.ObservableList.property;

import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/*
The ListProperty class implements the ObservableValue and ObservableList interfaces. 
It is an observable value in the sense that it wraps the reference of an ObservableList. 
Implementing the ObservableList interface makes all of its methods available to a ListProperty object. 
Calling methods of the ObservableList on a ListProperty has the same effect 
as if they were called on the wrapped ObservableList.

You can use one of the following constructors of the SimpleListProperty class to create an instance of
the ListProperty:
    • SimpleListProperty()
    • SimpleListProperty(ObservableList<E> initialValue)
    • SimpleListProperty(Object bean, String name)
    • SimpleListProperty(Object bean, String name, ObservableList<E> initialValue)
One of the common mistakes in using the ListProperty class is not passing an ObservableList to its
constructor before using it. A ListProperty must have a reference to an ObservableList before you can
perform a meaningful operation on it. If you do not use an ObservableList to create a ListProperty object,
you can use its set() method to set the reference of an ObservableList.

The following snippet of code shows how to create and initialize a ListProperty before using it:
    ObservableList<String> list1 = FXCollections.observableArrayList();
    ListProperty<String> lp1 = new SimpleListProperty<String>(list1);
    lp1.add("Hello");

    ListProperty<String> lp2 = new SimpleListProperty<String>();
    lp2.set(FXCollections.observableArrayList());
    lp2.add("Hello");

Operations performed on a ListProperty that wraps a null reference are treated as if the operations
were performed on an immutable empty ObservableList.

ListProperty implements Observable, ObservableValue и ObservableList interfaces and
you can attach three types of listeners to a ListProperty:
    • An InvalidationListener - Observable::addListner(InvalidationListener listener)
    • A ChangeListener   - ObservableValue::(ChangeListener listner) 
    • A ListChangeListener - ObservableList::addListner(ListChangeListener listener)
All three listeners are notified when the reference of the ObservableList, which is wrapped in the
ListProperty, changes or the content of the ObservableList changes. When the content of the list changes,
the changed() method of ChangeListeners receives the reference to the same list as the old and new
value. If the wrapped reference of the ObservableList is replaced with a new one, this method receives
references of the old list and the new list. To handle the list change events, please refer to the 
examples from observable.collections.ObservableList. 

*/
public class ListPropertyTest {
    
	public static void main(String[] args) {
        
		// Create an observable list property	
		ListProperty<String> lp =
		new SimpleListProperty<>(FXCollections.observableArrayList());

		// Add invalidation, change, and list change listeners
		lp.addListener(ListPropertyTest::invalidated);
		lp.addListener(ListPropertyTest::changed);
		lp.addListener(ListPropertyTest::onChanged);

		System.out.println("Before addAll()");
		lp.addAll("one", "two", "three");
		System.out.println("After addAll()");

		System.out.println("\nBefore set()");

		// Replace the wrapped list with a new one
		lp.set(FXCollections.observableArrayList("two", "three"));
		System.out.println("After set()");

		System.out.println("\nBefore remove()");
		lp.remove("two");
		System.out.println("After remove()");
	}

	// An invalidation listener
	public static void invalidated(Observable list) {
		System.out.println("List property is invalid.");
	}

	// A change listener
	public static void changed(ObservableValue<? extends ObservableList<String>> observable,
			                   ObservableList<String> oldList,
			                   ObservableList<String> newList) {
		System.out.print("List Property has changed.");
		System.out.print(" Old List: " + oldList);
		System.out.println(", New List: " + newList);
	}

	// A list change listener
	public static void onChanged(ListChangeListener.Change<? extends String> change) {
		while (change.next()) {
			String action = change.wasPermutated() ? "Permutated" : change.wasUpdated()
					? "Updated" : change.wasRemoved() && change.wasAdded() ? "Replaced"
							: change.wasRemoved() ? "Removed" : "Added";

			System.out.print("Action taken on the list: " + action);
			System.out.print(". Removed: " + change.getRemoved());
			System.out.println(", Added: " + change.getAddedSubList());
		}
	}
}
