
package collections.ObservableArray;

import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;

/*
An ObservableArray can be observed for changes. You need to add a SetChangeListener whose onChanged()
method is called for every addition or change of elements. It means if you use methods like addAll() or
set() on an ObservableArray, which affects multiple elements, multiple change notifications will be
fired—one for each element added or removed.
*/
public class ObservableArrayChange {

    public static void main(String[] args) {
        
        ObservableIntegerArray intArray = FXCollections.observableIntegerArray(1, 2, 3);
        
        // Add a change listener to the list
		intArray.addListener(ObservableArrayChange::onChanged);
        
        // Manipulate the elements of the array
        intArray.addAll(4, 5, 6);
        intArray.set(2, 433);
        intArray.clear();      
    }
    
    public static void onChanged(ObservableIntegerArray observableArray, boolean sizeChanged, int from, int to) {
		System.out.println("Array has changed");
	}
    
}
