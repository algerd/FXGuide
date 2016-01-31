
package layout.pane.vbox;

import javafx.application.Application;
import javafx.geometry.Pos;
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

Using the alignment property is simple. It specifies how children are aligned within the content area
of the VBox. By default, a VBox allocates just enough space for its content to lay out all children at their
preferred size. The effect of the alignment property is noticeable when the VBox grows bigger than its
preferred size.

The program uses a VBox with two Buttons. It sets the alignment of the VBox to
Pos.BOTTOM_RIGHT. It sets the preferred size of the VBox a little bigger than needed to accommodate all its
children, so you can see the effect of the alignment.  
When you resize the window, the children stay aligned in the bottom-right area.
*/
public class VBoxAlignment extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");

		VBox vbox = new VBox(10);
		vbox.setPrefSize(200, 100);
		vbox.getChildren().addAll(okBtn, cancelBtn);

		// Set the alignment to bottom right
		vbox.setAlignment(Pos.BOTTOM_RIGHT);
		
		vbox.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.setTitle("Using VBox Alignment Property");
		stage.show();		
	}
}
