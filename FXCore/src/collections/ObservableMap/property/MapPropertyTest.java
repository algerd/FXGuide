
package collections.ObservableMap.property;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/*
A MapProperty object wraps an ObservableMap. Working with a MapProperty is very similar to working
with a ListProperty. The following are the salient points to remember while working with a MapProperty:
    • The class diagram for the MapProperty class is similar to the one shown
        for the ListProperty class. You need to replace the word “List” with the word “Map”
        in all names and the generic type parameter <E> with <K, V>, where K and V stand
        for the key type and value type, respectively, of entries in the map.
    • The MapExpression and Bindings classes contain methods to support high-level
        bindings for map properties. You need to subclass the MapBinding class to create
        low-level bindings.
    • Like the ListProperty, the MapProperty exposes size and empty properties.
    • Like the ListProperty, the MapProperty supports bindings of the reference and the
        content of the ObservableMap that it wraps.
    • Like the ListProperty, the MapProperty supports three types of notifications:
        invalidation notifications, change notifications, and map change notifications.
    • The MapProperty supports binding to the value of a specific key using its valueAt() method.

Use one of the following constructors of the SimpleMapProperty class to create an instance of the
MapProperty:
    • SimpleMapProperty()
    • SimpleMapProperty(Object bean, String name)
    • SimpleMapProperty(Object bean, String name, ObservableMap<K,V> initialValue)
    • SimpleMapProperty(ObservableMap<K,V> initialValue)

The following snippet of code creates an instance of the MapProperty and adds two entries. In the end,
it gets the reference of the wrapped ObservableMap using the get() method:
    // Create a MapProperty object
    MapProperty<String, Double> mp = new SimpleMapProperty<String, Double>(FXCollections.observableHashMap());
    // Add two entries to the wrapped ObservableMap
    mp.put("Ken", 8190.20);
    mp.put("Jim", 8990.90);
    // Get the wrapped map from the mp property
    ObservableMap<String, Double> map = mp.get();
*/
public class MapPropertyTest {
    
	public static void main(String[] args) {
        
		MapProperty<String, Double> mp1 = new SimpleMapProperty<>(FXCollections.observableHashMap());
		
		// Create an object binding to bind mp1 to the value of the key "Ken"
		ObjectBinding<Double> kenSalary = mp1.valueAt("Ken");
		System.out.println("Ken Salary: " + kenSalary.get());
		
		// Bind the size and empty properties of the MapProperty to create a description of the map
		StringProperty initStr = new SimpleStringProperty("Size: " );
		StringProperty desc = new SimpleStringProperty();
		desc.bind(initStr.concat(mp1.sizeProperty())
		                 .concat(", Empty: ")
		                 .concat(mp1.emptyProperty())
		                 .concat(", Map: " )
		                 .concat(mp1.asString())
		                 .concat(", Ken Salary: ")
		                 .concat(kenSalary));
	
		System.out.println("Before mp1.put(): " + desc.get());

		// Add some entries to mp1
		mp1.put("Ken", 7890.90);
		mp1.put("Jim", 9800.80);
		mp1.put("Lee", 6000.20);	    
		System.out.println("After mp1.put(): " + desc.get());
		
		// Create a new MapProperty
		MapProperty<String, Double> mp2 = 
		new SimpleMapProperty<>(FXCollections.observableHashMap());
	
		// Bind the content of mp1 to the content of mp2
		mp1.bindContent(mp2);       
		System.out.println("Called mp1.bindContent(mp2)...");
	
		/* At this point, you can change the content of mp1. However, 
		 * that will defeat the purpose of content binding, because the
		 * content of mp1 is no longer in sync with the content of mp2.
		 * Do not do this:
		 * mp1.put("k1", 8989.90);
		 */ 
		System.out.println("Before mp2.put(): " + desc.get());
		mp2.put("Ken", 7500.90);
		mp2.put("Cindy", 7800.20);
		System.out.println("After mp2.put(): " + desc.get());
	}
}
