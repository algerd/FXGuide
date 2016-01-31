
package image.Writable;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.SnapshotResult;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.util.Callback;

/*
JavaFX allows you to take a snapshot of a Node and a Scene as they will appear in the next frame. You get the
snapshot in a WritableImage, which means you can perform all pixel-level operations after you take the
snapshot. The Node and Scene classes contain a snapshot() method to accomplish this.

The Node class contains an overloaded snapshot() method:
    • WritableImage snapshot(SnapshotParameters params, WritableImage image)
    • void snapshot(Callback<SnapshotResult,Void> callback, SnapshotParameters params, WritableImage image)

The first version of the snapshot() method is synchronous whereas the second one is asynchronous.
The method lets you specify an instance of the SnapshotParameters class that contains the rendering
attributes for the snapshot. If this is null, default values will be used. 

You can set the following attributes for the snapshot:
    • A fill color
    • A transform
    • A viewport
    • A camera
    • A depth buffer

By default, the fill color is white; no transform and viewport are used; a ParallelCamera is used; and,
the depth buffer is set to false. Note that these attributes are used on the node only while taking its snapshot.

You can specify a WritableImage in the snapshot() method that will hold the snapshot of the node. If
this is null, a new WritableImage is created. If the specified WritableImage is smaller than the node, the
node will be clipped to fit the image size.

The first version of the snapshot() method returns the snapshot in a WritableImage. The image is
either the one that is passed as the parameter or a new one created by the method.

The second, asynchronous version of the snapshot() method accepts a Callback object whose call()
method is called. A SnapshotResult object is passed to the call() method, which can be used to obtain the
snapshot image, the source node, and the snapshot parameters using the following methods:
    • WritableImage getImage()
    • SnapshotParameters getSnapshotParameters()
    • Object getSource()

Tip: The snapshot() method takes the snapshot of the node using the boundsInParent property of the
node. That is, the snapshot contains all effects and transformations applied to the node. If the node is being
animated, the snapshot will include the animated state of the node at the time it is taken.

The program shows how to take a snapshot of a TextField node. It displays a Label,
a TextField, and two Buttons in a GridPane. Buttons are used to take the snapshot of the TextField
synchronously and asynchronously. Click one of the Buttons to take a snapshot. A file save dialog appears
for you to enter the file name for the saved snapshot. The syncSnapshot() and asyncSnapshot() methods
contain the logic to take the snapshot. For the snapshot, the fill is set to red, and a Scale and a Rotate
transforms are applied.
*/
public class NodeSnapshot extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		GridPane root = new GridPane();

		Label nameLbl = new Label("Name:");
		TextField nameField = new TextField("Prema");

		Button syncSnapshotBtn = new Button("Synchronous Snapshot");
		syncSnapshotBtn.setOnAction(e -> syncSnapshot(nameField));

		Button asyncSnapshotBtn = new Button("Asynchronous Snapshot");
		asyncSnapshotBtn.setOnAction(e -> asyncSnapshot(nameField));

		root.setHgap(10);
		root.addRow(0, nameLbl, nameField, syncSnapshotBtn);
		root.add(asyncSnapshotBtn, 2, 1);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Taking a Snapshot of a Node");
		stage.show();
	}
	
	private void syncSnapshot(Node node) {
		SnapshotParameters params = getParams();
		WritableImage image = node.snapshot(params, null);
		ImageUtil.saveToFile(image);
	}
	
	private void asyncSnapshot(Node node) {
		// Create a Callback. Its call() method is called when 
		// the snapshot is ready. The getImage() method returns the snapshot
		Callback<SnapshotResult, Void> callback = (SnapshotResult result) -> {
			WritableImage image = result.getImage();
			ImageUtil.saveToFile(image);
			return null;
		};
		
		SnapshotParameters params = getParams();
		node.snapshot(callback, params, null);
	}
	
	private SnapshotParameters getParams() {
		// Set the fill to red and rotate the node by 30 degrees
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.RED);
		Transform tf = new Scale(0.8, 0.8);
		tf = tf.createConcatenation(new Rotate(10));
		params.setTransform(tf);
		return params;
	}
}
