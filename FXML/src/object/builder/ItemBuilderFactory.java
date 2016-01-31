
package object.builder;

import javafx.util.Builder;
import javafx.util.BuilderFactory;
import javafx.fxml.JavaFXBuilderFactory;

/**
 * ItemBuilderFactory реализует метод getBuilder, который возвращает билдер
 * для создания объектов в fxml-документе.
 * Надо предусмотреть возвращение дефолтного билдера из JavaFXBuilderFactory в случае, 
 * если встраиваемый билдер не поддерживает создание запрашиваемого объекта.
 */
public class ItemBuilderFactory implements BuilderFactory {
    
	private final JavaFXBuilderFactory fxFactory = new JavaFXBuilderFactory();

	@Override
	public Builder<?> getBuilder(Class<?> type) {
		// ItemBuilder поддерживает только Item класс
		if (type == Item.class) {
			return new ItemBuilder();
		}
		// Для остальных классов надо предоставить дефолтный билдер из JavaFXBuilderFactory
		return fxFactory.getBuilder(type);
	}
}
