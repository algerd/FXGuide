
package collections.ObservableMap;

import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/*
You need to use one of the following factory methods of the FXCollections class to create an
ObservableMap:
    • <K,V> ObservableMap<K, V> observableHashMap()
    • <K,V> ObservableMap<K, V> observableMap(Map<K, V> map)
    • <K,V> ObservableMap<K,V> emptyObservableMap()

The first method creates an empty observable map that is backed by a HashMap. The second method
creates an ObservableMap that is backed by the specified map. Mutations performed on the ObservableMap
are reported to the listeners. Mutations performed directly on the backing map are not reported to the
listeners. The third method creates an empty unmodifiable observable map.
*/
public class ObservableMapTest {
    
	public static void main(String[] args) {
        
		ObservableMap<String, Integer> map1 = FXCollections.observableHashMap();

		map1.put("one", 1);
		map1.put("two", 2);		
		System.out.println("Map 1: " + map1);
		
		Map<String, Integer> backingMap = new HashMap<>();
		backingMap.put("ten", 10); 
		backingMap.put("twenty", 20); 
		
		ObservableMap<String, Integer> map2 = FXCollections.observableMap(backingMap);
		System.out.println("Map 2: " + map2);
	}
}
