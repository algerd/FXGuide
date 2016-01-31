
package object.builder;

import javafx.util.Builder;

/**
 * ItemBuilder реализует метод build(), который возвращает объект Item;
 * ItemBuilder не реализует Map интерфейс, поэтому он должен реализовать
 * сеттер методы (и  геттеры?) всех свойств класса Item, которые будут доступны
 * в fxml-документе.
 */
public class ItemBuilder implements Builder<Item> {
    
	private Long id;
	private String name;
    
    @Override
	public Item build() {
		return new Item(id, name);
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
