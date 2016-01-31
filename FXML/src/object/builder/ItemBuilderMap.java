
package object.builder;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import javafx.util.Builder;

/**
 * ItemBuilderMap реализует метод build(), который возвращает объект Item;
 * ItemBuilderMap реализует AbstractMap, поэтому доступ к свойствам объекта Item
 * на запись предоставляется через put().
 */
public class ItemBuilderMap extends AbstractMap<String, Object> implements Builder<Item> {
	
    private String name;
	private Long id;
	
	@Override
	public Object put(String key, Object value) {
		if ("name".equals(key)) {
			this.name = (String)value;
		} else if ("id".equals(key)) {
			this.id = Long.valueOf((String)value);
		} else {
			throw new IllegalArgumentException("Unknown Item property: " + key);
		}
		return null;
	}
	
	@Override
	public Set<Map.Entry<String, Object>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Item build() {
		return new Item(id, name);
	}
}
