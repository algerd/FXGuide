
package collections.ObservableSet.property;

import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/*
A SetProperty object wraps an ObservableSet. Working with a SetProperty is very similar to working
with a ListProperty. The following are the salient points to remember while working with a SetProperty:
    • The class diagram for the SetProperty class is similar to the one shown
        for the ListProperty class. You need to replace the word “List” with the word “Set”
        in all names.
    • The SetExpression and Bindings classes contain methods to support high-level
        bindings for set properties. You need to subclass the SetBinding class to create lowlevel
        bindings.
    • Like the ListProperty, the SetProperty exposes the size and empty properties.
    • Like the ListProperty, the SetProperty supports bindings of the reference and the
        content of the ObservableSet that it wraps.
    • Like the ListProperty, the SetProperty supports three types of notifications:
        invalidation notifications, change notifications, and set change notifications.
    • Unlike a list, a set is an unordered collection of items. Its elements do not
        have indexes. It does not support binding to its specific elements. Therefore,
        the SetExpression class does not contain a method like valueAt() as the
        ListExpression class does.

You can use one of the following constructors of the SimpleSetProperty class to create an instance of
the SetProperty:
    • SimpleSetProperty()
    • SimpleSetProperty(ObservableSet<E> initialValue)
    • SimpleSetProperty(Object bean, String name)
    • SimpleSetProperty(Object bean, String name, ObservableSet<E> initialValue)

The following snippet of code creates an instance of the SetProperty and adds two elements to the
ObservableSet that the property wraps. In the end, it gets the reference of the ObservableSet from the
property object using the get() method:
    // Create a SetProperty object
    SetProperty<String> sp = new SimpleSetProperty<String>(FXCollections.observableSet());
    // Add two elements to the wrapped ObservableSet
    sp.add("one");
    sp.add("two");
    // Get the wrapped set from the sp property
    ObservableSet<String> set = sp.get();  
*/
public class SetPropertyTest {
    
	public static void main(String[] args) {
        
		SetProperty<String> sp1 = new SimpleSetProperty<>(FXCollections.observableSet());
		
		// Bind the size and empty properties of the SetPropertyto create a description of the set
		StringProperty initStr = new SimpleStringProperty("Size: " );
		StringProperty desc = new SimpleStringProperty();
		desc.bind(initStr.concat(sp1.sizeProperty())
		                  .concat(", Empty: ")
		                  .concat(sp1.emptyProperty())
		                  .concat(", Set: " )
		                  .concat(sp1.asString()));
		
		System.out.println("Before sp1.add(): " + desc.get());
		sp1.add("John");
		sp1.add("Jacobs");
		System.out.println("After sp1.add(): " + desc.get());
		
		SetProperty<String> sp2 = new SimpleSetProperty<>(FXCollections.observableSet());
		
		// Bind the content of sp1 to the content of sp2
		sp1.bindContent(sp2);	    
		System.out.println("Called sp1.bindContent(sp2)...");
		
		/* At this point, you can change the content of sp1. However, 
		 * that will defeat the purpose of content binding, because the
		 * content of sp1 is no longer in sync with the content of sp2.
		 * Do not do this:
		 * sp1.add("X"); 
		 */	
		print("Before sp2.add():", sp1, sp2);
		sp2.add("1");
		print("After sp2.add():", sp1, sp2);
		
		sp1.unbindContent(sp2);         
		print("After sp1.unbindContent(sp2):", sp1, sp2);

		// Bind sp1 and sp2 contents bidirectionally
		sp1.bindContentBidirectional(sp2);
		
		print("Before sp2.add():", sp1, sp2);
		sp2.add("2");		
		print("After sp2.add():", sp1, sp2);
	}
	
	public static void print(String msg, SetProperty<String> sp1, SetProperty<String> sp2) {
		System.out.println(msg + " sp1: " + sp1.get() + ", sp2: " + sp2.get());                           
	}
}
