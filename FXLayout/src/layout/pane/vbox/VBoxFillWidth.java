
package layout.pane.vbox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
Properties Declared in the VBox Class:
Property    Type                    Description
alignment   ObjectProperty<Pos>     It specifies the alignment of children relative to the content area
                                    of the VBox. The default value is Pos.TOP_LEFT.
fillWidth   BooleanProperty         It specifies whether the resizable children are resized to fill the
                                    full width of the VBox or they are given their preferred widths.
                                    The default value is true.
spacing     DoubleProperty          It specifies the vertical spacing between adjacent children.
                                    The default value is zero.

The fillWidth property specifies whether the VBox expands its children horizontally to fill the width of its
content area or keeps them to their preferred height. Note that this property affects only those child nodes
that allow for the horizontal expansion. For example, by default, the maximum width of a Button is set to its
preferred width, and a Button does become wider than its preferred width in a VBox, even if horizontal space
is available. If you want a Button to expand horizontally, set its maximum width to Double.MAX_VALUE. By
default, a TextField is set to expand. Therefore, a TextField inside a VBox will become wider as the width
of the VBox is increased. If you do not want the resizable children to fill the width of the content area of a
VBox, set the fillWidth property to false. 

Tip: The preferred width of the content area of a VBox is the largest of the preferred width of its children.
Resizable children fill the full width of the content area, provided their maximum width property allows them to
expand. Otherwise, they are kept at their preferred width.

It is often needed in a GUI application that you need to arrange a set of Buttons in a vertical column and
make them the same size. You need to add the buttons to a VBox and set the maximum width of all buttons
to Double.MAX_VALUE so they can grow to match the width of the widest button in the group.

When you expand the VBox horizontally, all buttons grow to fill the available extra space.
To prevent the buttons growing when the VBox expands in the horizontal direction, you can add the VBox in
an HBox and add the HBox to the scene.

Tip: You can create powerful visual effects by nesting HBox and VBox layout panes. You can also add buttons
(or any other types of nodes) in a column in a GridPane to make them the same size. 

Для дополнительного понимания смотреть пример аналогичного свойства для HBox HBoxFillHeight.java
*/
public class VBoxFillWidth extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		Button b1 = new Button("New");
		Button b2 = new Button("New Modified");
		Button b3 = new Button("Not Modified");
		Button b4 = new Button("Data Modified");
		
		// Set the max width of the buttons to Double.MAX_VALUE,
		// so they can grow horizontally
		b1.setMaxWidth(Double.MAX_VALUE);
		b2.setMaxWidth(Double.MAX_VALUE);
		b3.setMaxWidth(Double.MAX_VALUE);
	 	b4.setMaxWidth(Double.MAX_VALUE);
		
		VBox root = new VBox(10, b1, b2, b3, b4);		
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using VBox fillWidth Property");
		stage.show();
	}
}
