
package copy;

public class Item {
    
	private Long id;
	private String name;
    
    public Item() {}

    /**
     * The copy constructor.
     * Позволяет копировать объект этого класса с помощбю fx:copy элемента.
     */
    
    public Item(Item source) {
        this.id = source.id + 100;
        this.name = source.name + " (Copied)";
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name;
	}
}
