
package collections.ObservableSet;

import java.util.HashSet;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/*
You need to use one of the following factory methods of the FXCollections class to create an
ObservableSet:
    • <E> ObservableSet<E> observableSet(E... elements)
    • <E> ObservableSet<E> observableSet(Set<E> set)
    • <E> ObservableSet<E> emptyObservableSet()

The first method lets you specify initial elements for the set. The second method lets you create an
ObservableSet that is backed by the specified set. Mutations performed on the ObservableSet are reported
to the listeners. Mutations performed directly on the backing set are not reported to the listeners. The third
method creates an empty unmodifiable observable set.
*/
public class ObservableSetTest {
    
	public static void main(String[] args) {
        
		// Create an ObservableSet with three initial elements
		ObservableSet<String> s1 = FXCollections.observableSet("one", "two", "three");
		System.out.println("s1: " + s1);

		// Create a Set, and not an ObservableSet
		Set<String> s2 = new HashSet<String>();
		s2.add("one");
		s2.add("two");
		System.out.println("s2: " + s2);

		// Create an ObservableSet backed by the Set s2
		ObservableSet<String> s3 = FXCollections.observableSet(s2);
		s3.add("three");
		System.out.println("s3: " + s3);
	}
}
