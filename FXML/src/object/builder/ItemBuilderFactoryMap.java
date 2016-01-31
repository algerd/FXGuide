
package object.builder;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import javafx.util.BuilderFactory;

/**
 * ItemBuilderFactoryMap реализует метод getBuilder, который возвращает билдер,
 * реализующий Map интерфейс, для создания объектов в fxml-документе.
 * Надо предусмотреть возвращение дефолтного билдера из JavaFXBuilderFactory в случае, 
 * если встраиваемый билдер не поддерживает создание запрашиваемого объекта.
 */
public class ItemBuilderFactoryMap implements BuilderFactory {
    
	private final JavaFXBuilderFactory fxFactory = new JavaFXBuilderFactory();

	@Override
	public Builder<?> getBuilder(Class<?> type) {
        // ItemBuilder поддерживает только Item класс
		if (type == Item.class) {
			return new ItemBuilderMap();
		}
        // Для остальных классов надо предоставить дефолтный билдер из JavaFXBuilderFactory
		return fxFactory.getBuilder(type);
	}
}
