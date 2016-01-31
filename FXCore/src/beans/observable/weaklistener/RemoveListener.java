
package beans.observable.weaklistener;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/*
Listing 2-9 shows a simple use case, where a change listener is added, used, and removed. 
It creates an IntegerProperty named counter as a static variable. 
In the main() method, it calls the addListener() method that adds a change listener to the counter property, 
changes the value of counter to fire a change event, as shown in the output, and finally, it removes the change listener. 
The main() method changes the value of counter again, which does not fire any change events, 
because the change listener has already been removed. This is a use case where everything worked as expected.
*/
public class RemoveListener {
    
	public static IntegerProperty counter = new SimpleIntegerProperty(100);

	public static void main(String[] args) {
        
		// Add a change listener to the property
		ChangeListener<Number> listener = RemoveListener::changed;
		counter.addListener(listener);

		// Change the counter value
		counter.set(200);

		// Remove the listener
		counter.removeListener(listener);

		// Will not fire change event as change listener has already been removed.
		counter.set(300);
	}

	public static void changed(ObservableValue<? extends Number> prop, Number oldValue, Number newValue) {
		System.out.print("Counter changed: ");
		System.out.println("old = " + oldValue + ", new = " + newValue);
	}
}
