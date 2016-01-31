
package beans.property.lazy;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Lazily Instantiating Property Objects: property will use its default value in most of the cases.
 * Объект свойства будет создан только когда:
 *  - будет задано через сеттер новое значение свойства, отличное от дефолтного в поле-дублёре свойства
 *  - будет принудительно вызвано свойство xxxProperty()
 * Если объект свойсвта не создан, геттер свойства будет возвращать дефолтное значение поля-дублёра свойства.
 */
public class Monitor {
    
	public static final String DEFAULT_SCREEN_TYPE = "flat";
	private StringProperty screenType;

	public String getScreenType() {
		return (screenType == null) ? DEFAULT_SCREEN_TYPE : screenType.get();
	}

	public void setScreenType(String newScreenType) {
		if (screenType != null || !DEFAULT_SCREEN_TYPE.equals(newScreenType)) {
			screenTypeProperty().set(newScreenType);
		}
	}

	public StringProperty screenTypeProperty() {
		if (screenType == null) {
			screenType = new SimpleStringProperty(this, "screenType",
					DEFAULT_SCREEN_TYPE);
		}
		return screenType;
	}
    
    //debug method
    public boolean isScreenTypeInit() {
        return screenType != null;
    }
       
    /***************************** Main ****************************************/
    public static void main(String[] arg) {
        
        Monitor monitor = new Monitor();
        
        System.out.println("screenType=" + monitor.getScreenType());
        System.out.println("StringProperty screenType is instantiated: " + monitor.isScreenTypeInit());
        
        // инициализация свойства через сеттер
        monitor.setScreenType("plasma");
        System.out.println("screenType=" + monitor.getScreenType());
        System.out.println("StringProperty screenType is instantiated: " + monitor.isScreenTypeInit());
  
        // принудительная инициализация свойства через вызов screenTypeProperty()
        monitor.screenTypeProperty().setValue("lcd");
        System.out.println("screenType=" + monitor.getScreenType());
        System.out.println("StringProperty screenType is instantiated: " + monitor.isScreenTypeInit());        
    }
    
}
