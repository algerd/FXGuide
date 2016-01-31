
package ComboBox;

import javafx.scene.control.ListCell;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

// Для ComboBoxCellFactory.java
/*
Let’s use a cell factory to display nodes in the button area and the pop-up area of a combo box.
StringShapeCell.java be our starting point.It declares a StringShapeCell class, which inherits from the
ListCell<String> class. You need to update its content in its updateItem() method, which is automatically
called. The method receives the item, which in this case is String, and a boolean argument indicating
whether the cell is empty. Inside the method, you call the method in the superclass first. You derive a
shape from the string argument and set the text and graphic in the cell. The shape is set as the graphic.
The getShape() method returns a Shape from a String.
*/
public class StringShapeCell extends ListCell<String> {
	@Override
	public void updateItem(String item, boolean empty) {
		// Need to call the super first
		super.updateItem(item, empty);

		// Set the text and graphic for the cell
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			setText(item);
			Shape shape = this.getShape(item);
			setGraphic(shape);
		}
	}

	public Shape getShape(String shapeType) {
		Shape shape = null;
		switch (shapeType.toLowerCase()) {
			case "line":
				shape = new Line(0, 10, 20, 10);
				break;
			case "rectangle":
				shape = new Rectangle(0, 0, 20, 20);
				break;
			case "circle":
				shape = new Circle(20, 20, 10);
				break;
			default:
				shape = null;
		}
		return shape;
	}
}
