
package node.bounds.size;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
How do you get the actual preferred, minimum, and maximum sizes of a node? You might guess
that you can get them using the getPrefWidth(), getPrefHeight(), getMinWidth(), getMinHeight(),
getMaxWidth(), and getMaxHeight() methods. But you should not use these methods to get the actual
sizes of a node. These sizes may be set to the sentinel values and the node will compute the actual sizes
internally. These methods return the sentinel values or the override values. 

Listing creates two buttons and overrides the preferred intrinsic width for one of them to 100 pixels. 
The output below proves that these methods are not very useful to learn the actual sizes of a
node for layout purposes.
*/
public class NodeSizeSentinelValues extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button okBtn = new Button("OK");
		Button cancelBtn = new Button("Cancel");

		// Override the intrinsic width of the cancel button
		cancelBtn.setPrefWidth(100);

		VBox root = new VBox();
		root.getChildren().addAll(okBtn, cancelBtn);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Overriding Node Sizes");
		stage.show();

		System.out.println("okBtn.getPrefWidth(): " + okBtn.getPrefWidth());
		System.out.println("okBtn.getMinWidth(): " + okBtn.getMinWidth());
		System.out.println("okBtn.getMaxWidth(): " + okBtn.getMaxWidth());

		System.out.println("cancelBtn.getPrefWidth(): " + cancelBtn.getPrefWidth());
		System.out.println("cancelBtn.getMinWidth(): " + cancelBtn.getMinWidth());
		System.out.println("cancelBtn.getMaxWidth(): " + cancelBtn.getMaxWidth());
	}
}
