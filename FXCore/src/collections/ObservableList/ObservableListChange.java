
package collections.ObservableList;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/*
Observing an ObservableList for changes is a bit tricky. There could be several kinds of changes to a list.
Some of the changes could be exclusive, whereas some can occur along with other changes. Elements of a
list can be permutated, updated, replaced, added, and removed.
You can add a change listener to an ObservableList using its addListener() method, which takes
an instance of the ListChangeListener interface. The changed() method of the listeners is called every
time a change occurs in the list.

The program showing how to detect changes in an ObservableList. It
uses a lambda expression with a method reference, which are features of Java 8, to add a change listener.
After adding a change listener, it manipulates the list four times, and the listener is notified each time, as is
evident from the output that follows.
*/
public class ObservableListChange {
    
	public static void main(String[] args) {
		// Create an observable list
		ObservableList<String> list = FXCollections.observableArrayList();
		
		// Add a change listener to the list
		list.addListener(ObservableListChange::onChanged);
		
		// Manipulate the elements of the list
		list.add("one");
		list.addAll("two");
		FXCollections.sort(list);
		list.clear();
	}
	
	public static void onChanged(ListChangeListener.Change<? extends String> change) {
		System.out.println("List has changed");
	}
}
