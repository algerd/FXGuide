
package beans.observable.weaklistener;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/*
When you add an invalidation listener to an Observable, the Observable stores a strong reference to the listener. 
Like an Observable, an ObservableValue also keeps a strong reference to the registered change listeners.

In the addStrongListener() method, you have added a change listener to the counter property but did not
remove it. The second line in the output proves that even after the addStrongListener() method finishes
executing, the counter property is still holding the reference to the change listener you had added. After
the addStrongListener() method is finished, you do not have a reference to the change listener variable,
because it was declared as a local variable. Therefore, you do not even have a way to remove the listener.
This use case shows the intrinsic nature of memory leaks while using invalidation and change listeners with properties.
*/

public class StrongListener {
    
	public static IntegerProperty counter = new SimpleIntegerProperty(100);
	
	public static void main(String[] args) {
        
		// Add a change listener to the property
		addStrongListener();
		
		// Change counter value. It will fire a change event.
		counter.set(300);
	}
	
	public static void addStrongListener() {
		ChangeListener<Number> listener = StrongListener::changed;
		counter.addListener(listener);
		
		// Change the counter value
		counter.set(200);
	}
	
	public static void changed(ObservableValue<? extends Number> prop, Number oldValue, Number newValue) {
		System.out.print("Counter changed: ");
		System.out.println("old = " + oldValue + ", new = " + newValue);
	}
}
