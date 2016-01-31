
package collections.ObservableList;

import java.util.List;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.Callback;

/*
ObservableList отслеживает изменения самих ссылок, из которых состоит список, а не их значений. 
Чтобы отслеживать изменение значений в ссылках, надо использовать Callback<E, Observable[]> extractor.
В нём надо обернуть ссылку списка типом Observable[] и вернуть Observable[]-ссылку. 
И теперь любое изменение значения в ссылке списка будет вызывать событие, которое будет
уведомлять слушателей изменений ObservableList-класса.

It is two methods of the FXCollections class that create an ObservableList:
    • <E> ObservableList<E> observableArrayList(Callback<E, Observable[]> extractor)
    • <E> ObservableList<E> observableList(List<E> list, Callback<E, Observable[]> extractor)

If you want to be notified when elements of a list are updated, you need to create the list using one of
these methods. Both methods have one thing in common: They take a Callback<E,Observable[]> object as
an argument. The Callback<P,R> interface is in the javafx.util package. It is defined as follows:
    public interface Callback<P,R> {
        R call(P param)
    }

The Callback<P,R> interface is used in situations where further action is required by APIs at a later
suitable time. The first generic type parameter specifies the type of the parameter passed to the call()
method and the second one specifies the returns type of the call() method.

If you notice the declaration of the type parameters in Callback<E,Observable[]>, the first type
parameter is E, which is the type of the elements of the list. The second parameter is an array of Observable.
When you add an element to the list, the call() method of the Callback object is called. The added element
is passed to the call() method as an argument. You are supposed to return an array of Observable from the
call() method. If any of the elements in the returned Observable array changes, listeners will be notified of an
“update” change for the element of the list for which the call() method had returned the Observable array.

Let’s examine why you need a Callback object and an Observable array to detect updates to elements
of a list. A list stores references of its elements. Its elements can be updated using their references from
anywhere in the program. A list does not know that its elements are being updated from somewhere else. It
needs to know the list of Observable objects, where a change to any of them may be considered an update
to its elements. The call() method of the Callback object fulfills this requirement. The list passes every
element to the call() method. The call() method returns an array of Observable. The list watches for any
changes to the elements of the Observable array. When it detects a change, it notifies its change listeners
that its element associated with the Observable array has been updated. The reason this parameter is
named extractor is that it extracts an array of Observable for an element of a list.

The main() method of the ObservableListUpdateCallback class creates an extractor that is an object of the
Callback<IntegerProperty, Observable[]> interface. The call() method takes an IntegerProperty
argument and returns the same by wrapping it in an Observable array. It also prints the object that is
passed to it.
The extractor is used to create an ObservableList. Two IntegerProperty objects are added to the list.
When the objects are being added, the call() method of the extractor is called with the object being added
as its argument. This is evident from the output. The call() method returns the object being added. This
means that the list will watch for any changes to the object (the IntegerProperty) and notify its change
listeners of the same.
A change listener is added to the list. It handles only updates to the list. At the end, you change the value
for the first element of the list from 10 to 100 to trigger an update change notification.
*/

public class ObservableListUpdateCallback {
    
	public static void main(String[] args) {
		// Create an extractor for IntegerProperty.		
		Callback<IntegerProperty, Observable[]> extractor = (IntegerProperty p) -> {
            // Print a message to know when it is called
            System.out.println("The extractor is called for " + p);

            // Wrap the parameter in an Observable[] and return it
            return new Observable[]{p};
        };

		// Create an empty observable list with a callback to extract the
		// observable values for each element of the list
		ObservableList<IntegerProperty> list =
			FXCollections.observableArrayList(extractor);

		// Add two elements to the list
		System.out.println("Before adding two elements...");
		IntegerProperty p1 = new SimpleIntegerProperty(10);
		IntegerProperty p2 = new SimpleIntegerProperty(20);
		list.addAll(p1, p2); // Will call the call() method of the 
		// extractor - once for p1 and once for p2.
		System.out.println("After adding two elements...");

		// Add a change listener to the list
		list.addListener(ObservableListUpdateCallback::onChanged);

		// Update p1 from 10 to 100, which will trigger  an update change for the list
		p1.set(100);
	}

	public static void onChanged(ListChangeListener.Change<? extends IntegerProperty> change) {
		System.out.println("List is " + change.getList());

		// Work on only updates to the list
		while (change.next()) {
			if (change.wasUpdated()) {
				// Print the details of the update
				System.out.println("An update is detected.");

				int start = change.getFrom();
				int end = change.getTo();
				System.out.println("Updated range: [" + start + ", " + end + "]");

				List<? extends IntegerProperty> updatedElementsList;
				updatedElementsList = change.getList().subList(start, end);

				System.out.println("Updated elements: " + updatedElementsList);
			}
		}
	}
}
