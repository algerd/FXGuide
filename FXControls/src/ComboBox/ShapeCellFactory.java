
package ComboBox;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

// Для ComboBoxCellFactory.java
/*
The next step is to create a Callback class, as shown in ShapeCellFactory.java. The program in this listing is
very simple. Its call() method returns an object of the StringShapeCell class. The class will act as a cell
factory for ComboBox.
*/
public class ShapeCellFactory implements Callback<ListView<String>, ListCell<String>> {
	@Override
	public ListCell<String> call(ListView<String> listview) {
		return new StringShapeCell();
	}
}
