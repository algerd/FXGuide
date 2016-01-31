
package layout.pane.hbox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
Properties Declared in the HBox Class:
Property            Type                    Description
alignment       ObjectProperty<Pos>     It specifies the alignment of children relative to the content area
                                        of the HBox. The fillHeight property is ignored if the vertical
                                        alignment is set to BASELINE. The default value is Pos.TOP_LEFT.
fillHeight      BooleanProperty         It specifies whether the resizable children are resized to fill the
                                        full height of the HBox or they are given their preferred heights.
                                        This property is ignored, if the vertical alignment is set to
                                        BASELINE. The default value is true.
spacing         DoubleProperty          It specifies the horizontal spacing between adjacent children.
                                        The default value is zero.

The fillHeight property specifies whether the HBox expands its children vertically to fill the height of its
content area or keeps them to their preferred height. Note that this property affects only those child nodes
that allow for the vertical expansion. For example, by default, the maximum height of a Button is set to its
preferred height, and a Button does become taller than its preferred width in an HBox, even if vertical space
is available. If you want a Button to expand vertically, set its maximum height to Double.MAX_VALUE. By
default, a TextArea is set to expand. Therefore, a TextArea inside an HBox will become taller as the height
of the HBox is increased. If you do not want the resizable children to fill the height of the content area of an
HBox, set the fillHeight property to false.

Tip: T he preferred height of the content area of an HBox is the largest of the preferred height of its children.
Resizable children fill the full height of the content area, provided their maximum height property allows them to
expand. Otherwise, they are kept at their preferred height.

The program shows how the fillHeight property affects the height of the children of
an HBox. It displays some controls inside an HBox. A TextArea can grow vertically by default. The maximum
height of the Cancel button is set to Double.MAX_VALUE, so it can grow vertically. A CheckBox is provided
to change the value of the fillHeight property of the HBox.  Resize the window to make it taller and change
the fillHeight property using the CheckBox; the TextArea and the Cancel button expands and shrinks
vertically.

Для дополнительного понимания смотреть пример аналогичного свойства для VBox VBoxFillWidth.java
*/
public class HBoxFillHeight extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		HBox root = new HBox(10); // 10px spacing
		
		Label descLbl = new Label("Description:");
		TextArea desc = new TextArea();
		desc.setPrefColumnCount(10);
		desc.setPrefRowCount(3);
		
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");
		
		// Let the Cancel button expand vertically
		cancelBtn.setMaxHeight(Double.MAX_VALUE);

		CheckBox fillHeightCbx = new CheckBox("Fill Height");
		fillHeightCbx.setSelected(true);
		
		// Add an event handler to the CheckBox, so the user can set the 
		// fillHeight property using the CheckBox
		fillHeightCbx.setOnAction(e -> root.setFillHeight(fillHeightCbx.isSelected()));

		root.getChildren().addAll(descLbl, desc, fillHeightCbx, okBtn, cancelBtn);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using HBox fillHeight Property");
		stage.show();
	}
}
