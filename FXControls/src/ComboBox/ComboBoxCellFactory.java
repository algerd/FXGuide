
package ComboBox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
A combo box has two areas:
    • Button area to display the selected item
    • Pop-up area to display the items list
Both areas use ListCells to display items. A ListCell is a Cell. A Cell is a Labeled control to display
some form of content that may have text, a graphic, or both. The pop-up area is a ListView that contains an
instance of ListCell for each item in the list. I will discuss ListView in the next section.

Elements in the items list of a combo box can be of any type, including Node type. It is not recommended
to add instances of the Node class directly to the items list. When nodes are used as items, they are added as
the graphic to the cells. Scene graphics need to follow the rule that a node cannot be displayed in two places
at the same time. That is, a node must be inside one container at a time. When a node from the items list is
selected, the node is removed from the pop-up ListView cell and added to the button area. When the pop-up
is displayed again, the selected node is not shown in the list as it is already showing in the button area. To
avoid this inconsistency in display, avoid using nodes directly as items in a combo box.

You can fix the display issue that occurs when you use nodes as items. The solution is to add nonnode
items in the list and supply a cell factory to create the desired node inside the cell factory. You need to make
sure that the nonnode items will provide enough pieces of information to create the node you wanted to
insert.

The ComboBox class contains a cellFactory property, which is declared as follows:
    public ObjectProperty<Callback<ListView<T>, ListCell<T>>> cellFactory;

Callback is an interface in the javafx.util package. It has a call() method that takes an argument of
type P and returns and object of type R, as in the following code:
        public interface Callback<P,R> {
            public R call(P param);
        }

The declaration of the cellFactory property states that it stores a Callback object whose call()
method receives a ListView<T> and returns a ListCell<T>. Inside the call() method, you create an
instance of the ListCell<T> class and override the updateItem(T item, boolean empty) method of the
Cell class to populate the cell.

Let’s use a cell factory to display nodes in the button area and the pop-up area of a combo box.
StringShapeCell.java be our starting point.It declares a StringShapeCell class, which inherits from the
ListCell<String> class. You need to update its content in its updateItem() method, which is automatically
called. The method receives the item, which in this case is String, and a boolean argument indicating
whether the cell is empty. Inside the method, you call the method in the superclass first. You derive a
shape from the string argument and set the text and graphic in the cell. The shape is set as the graphic.
The getShape() method returns a Shape from a String.

The next step is to create a Callback class, as shown in ShapeCellFactory.java. The program in this listing is
very simple. Its call() method returns an object of the StringShapeCell class. The class will act as a cell
factory for ComboBox.

The program shows how to use a custom cell factory and button cell in a combo box. 
The program is very simple. It creates a combo box with three String items. It sets an object of the
ShapeCellFactory as the cell factory, as in the following code:
    shapes.setCellFactory(new ShapeCellFactory());

Setting the cell factory is not enough in this case. It will only resolve the issue of displaying the shapes in
the pop-up area. When you select a shape, it will display the String item, not the shape, in the button area.
To make sure, you see the same item in the list for selection and after you select one, you need to set the
buttonCell property, as in the following code:
    shapes.setButtonCell(new StringShapeCell());

Notice the use of the StringShapeCell class in the buttonCell property and ShapeCellFactory class.
Run the program. You should be able to select a shape from the list and the shape
should be displayed in the combo box correctly.

Using a custom cell factory and button cell in a combo box gives you immense power to customize the
look of the pop-up list and the selected item. If using a cell factory looks hard or confusing to you, keep in
mind that a cell is a Labeled control and you are setting the text and graphic in that Labeled control inside
the updateItem() method. The Callback interface comes into play because the ComboBox control needs to
give you a chance to create a cell when it needs it. Otherwise, you would have to know how many cells to
create and when to create them. There is nothing more to it.
*/
public class ComboBoxCellFactory extends Application {			
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label shapeLbl = new Label("Shape:");
		ComboBox<String> shapes = new ComboBox<>();		
		shapes.getItems().addAll("Line", "Rectangle", "Circle");
		
		// Set the cellFactory property
		shapes.setCellFactory(new ShapeCellFactory());

		// Set the buttonCell property
		shapes.setButtonCell(new StringShapeCell());
		
		HBox root = new HBox(shapeLbl, shapes);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);		
		stage.setTitle("Using CellFactory in ComboBox");
		stage.show();
	}
}
