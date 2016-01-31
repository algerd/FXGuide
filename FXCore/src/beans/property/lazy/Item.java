
package beans.property.lazy;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Lazily Instantiating Property Objects: the property will not use its observable and binding features in most cases.
 * В отличие от ленивой инициализации в классе Monitor, здесь инициализация ещё более ленивая:
 * сеттер не инициализирует свойство.
 * Объект свойства будет создан только когда будет принудительно вызвано свойство xxxProperty().
 * Если объект свойства не создан, геттер свойства будет возвращать дефолтное значение поля-дублёра свойства.
 * Сеттер свойства меняет значение или поля-дублёра, или свойства, если оно было ранее создано через xxxProperty().
 */
public class Item {
    
	private DoubleProperty weight;
	private double _weight = 150;

	public double getWeight() {
		return (weight == null) ? _weight : weight.get();
	}

	public void setWeight(double newWeight) {
		if (weight == null) {
			_weight = newWeight;
		}
		else {
			weight.set(newWeight);
		}
	}

	public DoubleProperty weightProperty() {
		if (weight == null) {
			weight = new SimpleDoubleProperty(this, "weight", _weight);
		}
		return weight;
	}
    
    //debug method
    public boolean isWeightInit() {
        return weight != null;
    }
    
    /***************************** Main ****************************************/
    public static void main(String[] arg) {
        
        Item item = new Item();
   
        System.out.println("weight=" + item.getWeight());
        System.out.println("DoubleProperty weight is instantiated: " + item.isWeightInit());
        
        item.setWeight(300);
        System.out.println("weight=" + item.getWeight());
        System.out.println("DoubleProperty weight is instantiated: " + item.isWeightInit());
        
        // принудительно инициалируем свойство через вызов item.weightProperty()
        System.out.println("weight=" + item.weightProperty().get());
        System.out.println("DoubleProperty weight is instantiated: " + item.isWeightInit());

        item.setWeight(400);
        System.out.println("weight=" + item.getWeight());
        System.out.println("DoubleProperty weight is instantiated: " + item.isWeightInit());        
    }
         
}
