
package collections.ObservableList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
You need to use one of the following factory methods of the FXCollections class to create an
ObservableList:
    • <E> ObservableList<E> emptyObservableList()
    • <E> ObservableList<E> observableArrayList()
    • <E> ObservableList<E> observableArrayList(Collection<? extends E> col)
    • <E> ObservableList<E> observableArrayList(E... items)
    • <E> ObservableList<E> observableList(List<E> list)
    • <E> ObservableList<E> observableArrayList(Callback<E, Observable[]> extractor)
    • <E> ObservableList<E> observableList(List<E> list, Callback<E, Observable[]> extractor)

The emptyObservableList() method creates an empty, unmodifiable ObservableList. Often, this
method is used when you need an ObservableList to pass to a method as an argument and you do not have
any elements to pass to that list. You can create an empty ObservableList of String as follows:
    ObservableList<String> emptyList = FXCollections.emptyObservableList();

The observableArrayList() method creates an ObservableList backed by an ArrayList. Other
variants of this method create an ObservableList whose initial elements can be specified in a Collection as
a list of items or as a List.

The last two methods in the above list create an ObservableList whose elements can be observed
for updates. They take an extractor, which is an instance of the Callback<E, Observable[]> interface. An
extractor is used to get the list of Observable values to observe for updates.
*/
public class ObservableListTest {
    
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
        
		// Create a list with some elements
		ObservableList<String> list = FXCollections.observableArrayList("one", "two");
		System.out.println("After creating list: " + list);

		// Add some more elements to the list
		list.addAll("three", "four");
		System.out.println("After adding elements: " + list);

		// We have four elements. Remove the middle two from index 1 (inclusive) to index 3 (exclusive)
		list.remove(1, 3);
		System.out.println("After removing elements: " + list);

		// Retain only the element "one"
		list.retainAll("one");
		System.out.println("After retaining \"one\": " + list);

		// Create another ObservableList
		ObservableList<String> list2 = FXCollections.<String>observableArrayList("1", "2", "3");

		// Set list2 to list
		list.setAll(list2);
		System.out.println("After setting list2 to list: " + list);

		// Create another list
		ObservableList<String> list3 = FXCollections.<String>observableArrayList("ten", "twenty", "thirty");

		// Concatenate elements of list2 and list3		
		ObservableList<String> list4 = FXCollections.concat(list2, list3);
		System.out.println("list2 is " + list2);
		System.out.println("list3 is " + list3);
		System.out.println("After concatenating list2 and list3:" + list4);
	}
}
