
package layout.pane.hbox;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

Using the alignment property is simple. It specifies how children are aligned within the content area of the HBox.
By default, an HBox allocates just enough space for its content to lay out all children at their preferred size.
The effect of the alignment property is noticeable when the HBox grows bigger than its preferred size.

The program uses an HBox with two Buttons. It sets the alignment of the HBox to
Pos.BOTTOM_RIGHT. It sets the preferred size of the HBox a little bigger than needed to accommodate all its
children, so you can see the effect of the alignment. The resulting window is shown in Figure 10-19. When
you resize the window, the children stay aligned in the bottom-right area.
*/
public class HBoxAlignment extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");

		HBox hbox = new HBox(10);
		hbox.setPrefSize(200, 100);
		hbox.getChildren().addAll(okBtn, cancelBtn);

		// Set the alignment to bottom right
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		
		hbox.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(hbox);
		stage.setScene(scene);
		stage.setTitle("Using HBox Alignment Property");
		stage.show();		
	}
}
