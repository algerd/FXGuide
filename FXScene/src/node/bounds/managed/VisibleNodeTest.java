
package node.bounds.managed;

import javafx.application.Application;
import javafx.beans.binding.When;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
Sometimes you may want to use the space that is used by a node if the node becomes invisible. Suppose
you have an HBox with several buttons. When one of the buttons becomes invisible, you want to slide all
buttons from right to left. You can achieve a slide-up effect in VBox. Achieving sliding effects in HBox and VBox
(or any other containers with relative positioning) is easy by binding the managed property of the node to the
visible property. Listing shows how to achieve the slide-left feature in an HBox. It displays four buttons.
The first button is used to make the third button, b2, visible and invisible. The managed property of the b2
button is bound to its visible property:
    b2.managedProperty().bind(b2.visibleProperty());

When the b2 button is made invisible, it becomes unmanaged, and the HBox does not use its
layoutBounds in computing its own layoutBounds. This makes the b3 button slide to the left.
*/
public class VisibleNodeTest extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button b1 = new Button("B1");
		Button b2 = new Button("B2");
		Button b3 = new Button("B3");
		Button visibleBtn = new Button("Make Invisible");

		// Add an action listener to the button to make b2 visible if it is invisible and invisible if it is visible
		// visible = false делает узел невидимым, но родитель(в нашем случае HBox) продолжает учитывать размеры узла 
        visibleBtn.setOnAction(e -> b2.setVisible(!b2.isVisible()));

		// Bind the text property of the button to the visible property of the b2 button
		visibleBtn.textProperty().bind(
                new When(b2.visibleProperty())
				.then("Make Invisible")
				.otherwise("Make Visible"));

		// Bind the managed property of b2 to its visible property
        // при managedProperty = false родитель(в нашем случае HBox) не учитывает узел в своей разметке    
		b2.managedProperty().bind(b2.visibleProperty());

		HBox root = new HBox();
		root.getChildren().addAll(visibleBtn, b1, b2, b3);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Sliding to the Left");
		stage.show();
	}
}
