
package collections.ObservableList.property;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

/*
It is possible to bind to a specific element of the ObservableList wrapped in a ListProperty using one
of the following methods of the ListExpression class:
    • ObjectBinding<E> valueAt(int index)
    • ObjectBinding<E> valueAt(ObservableIntegerValue index)
The first version of the method creates an ObjectBinding to an element in the list at a specific index.
The second version of the method takes an index as an argument, which is an ObservableIntegerValue
that can change over time. When the bound index in the valueAt() method is outside the list range, the
ObjectBinding contains null.

Let’s use the second version of the method to create a binding that will bind to the last element of a
list. Here you can make use of the size property of the ListProperty in creating the binding expression.
The program shows how to use the valueAt() method. 
*/
public class ListPropertyBindingElements {
    
	public static void main(String[] args) {
        
		ListProperty<String> lp = new SimpleListProperty<>(FXCollections.observableArrayList());
		
		// Create a binding to the last element of the list
		ObjectBinding<String> last = lp.valueAt(lp.sizeProperty().subtract(1));
		System.out.println("List:" + lp.get() + ", Last Value: " + last.get());
		
		lp.add("John");
		System.out.println("List:" + lp.get() + ", Last Value: " + last.get());
		
		lp.addAll("Donna", "Geshan");
		System.out.println("List:" + lp.get() + ", Last Value: " + last.get());

		lp.remove("Geshan");
		System.out.println("List:" + lp.get() + ", Last Value: " + last.get());
		
		lp.clear();
		System.out.println("List:" + lp.get() + ", Last Value: " + last.get());
	}
}
