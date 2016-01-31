
package beans.observable.weaklistener;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;

/*
The solution is to use weak listeners, which are garbage collected automatically. A weak listener is an
instance of the WeakListener interface. JavaFX provides two implementation classes of the WeakListener
interface that can be used as invalidation and change listeners: WeakInvalidationListener and
WeakChangeListener classes.
WeakInvalidationListener и WeakChangeListener - это классы обёртки слушателей InvalidationListener и ChangeListener
c конструкторами:
    public WeakChangeListener(@NamedArg("listener") ChangeListener<T> listener) {
        if (listener == null) {
            throw new NullPointerException("Listener must be specified.");
        }
        this.ref = new WeakReference<ChangeListener<T>>(listener);
    }
You need to keep a strong reference of the change listener around as long as you do not want it to be garbage collected.
В примере ниже надо сохранять ссылку changeListener. Чтобы удалить слушателя, надо просто удалить ссылку changeListener
(changeListener = null) и тогда сборщик мусора удалит слушателя у свойства. Это удобно, когда ссылка свойства недоступна и 
удаление через removeListener() невозможно, тогда удаление слушателя можно провести через простое его обнуление. Но это
бывает невозможно, потому что бывает необходимо сохранять ссылку слушателя после удаления его из свойства.

The addWeakListener() method creates a change listener, stores its reference to the static variable, 
wraps it in a weak change listener, and adds it to the counter property. The counter property is changed at the end.
The main() method changes the counter property several times. It also tries to invoke garbage collection, 
using System.gc(), and prints a message to check if the change listener has been garbage collected. 
As long as you keep a strong reference to the change listener in the changeListener static variable,
the change listener is not garbage collected. After you set it to null and then invoke the garbage collection
again, the change listener will be garbage collected. The last change in the counter property, inside the
main() method, did not fire a change event as the change listener had already been removed automatically,
as it was wrapped in a weak change listener.
*/

public class WeakListener {
    
	public static IntegerProperty counter = new SimpleIntegerProperty(100);
	public static WeakChangeListener<Number> weakListener ;
	public static ChangeListener<Number> changeListener;
	
	public static void main(String[] args) {
        
		// Add a weak change listener to the property
		addWeakListener();
	
		// It will fire a change event
		counter.set(300); 
		
		// Try garbage collection
		System.gc();
		
		// Check if change listener got garbage collected
		System.out.println("Garbage collected: " + weakListener.wasGarbageCollected());
		
		// It will fire a change event
		counter.set(400);
		
		// You do not need a strong reference of the change listener
		changeListener = null; 
		
		// Try garbage collection
		System.gc();
		
		// Check if the change listener got garbage collected
		System.out.println("Garbage collected: " + weakListener.wasGarbageCollected());
		
		// It will not fire a change event, if it was garbage collected
		counter.set(500);		
	}
	
	public static void addWeakListener() {
		// Keep a strong reference to the change listener
		changeListener = WeakListener::changed;
		
		// Wrap the change listener inside a weak change listener
		weakListener = new WeakChangeListener<>(changeListener);
		
		// Add weak change listener
		counter.addListener(weakListener);
		
		// Change the value
		counter.set(200);
	}
	
	public static void changed(ObservableValue<? extends Number> prop, Number oldValue, Number newValue) {
		System.out.print("Counter changed: ");
		System.out.println("old = " + oldValue + ", new = " + newValue);
	}
}
