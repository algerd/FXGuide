
package collections.ObservableArray;

import collections.ObservableList.ObservableListInvalidation;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;

/*
You can add invalidation listeners to an ObservableArray as you do to any Observable. Listing shows how
to use an invalidation listener with an ObservableArray.
In the case of the ObservableList, the invalidation listeners are notified for every change in the array,
irrespective of the type of a change.
*/
public class ObservableArrayInvalidation {
    
    public static void main(String[] args) {
        
        ObservableIntegerArray intArray = FXCollections.observableIntegerArray(1, 2, 3);
        
        // Add an InvalidationListener to the list
		intArray.addListener(ObservableListInvalidation::invalidated);
        
        System.out.println("Before adding 4 and 5."); 
		intArray.addAll(4, 5);
		System.out.println("After adding 4 and 5."); 
		
        // intArray[1] = 25
		System.out.println("Before replacing"); 
		intArray.set(1, 25);
		System.out.println("After replacing");
        
        System.out.println("Array: " + intArray);       
    }
       
    public static void invalidated(Observable list) {
		System.out.println("Array is invalid.");	
	}

}
