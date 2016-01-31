
package image.Writable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;;
import javafx.scene.SnapshotResult;
import javafx.util.Callback;
import javafx.stage.Stage;

/*
The Scene class contains an overloaded snapshot() method:
    • WritableImage snapshot(WritableImage image)
    • void snapshot(Callback<SnapshotResult,Void> callback, vWritableImage image)

Compare the snapshot() methods of the Scene class with that of the Node class(NodeSnapshot.java). The only difference is
that the snapshot() method in the Scene class does not contain the SnapshotParameters argument. This
means that you cannot customize the scene snapshot. Except this, the method works the same way as it
works for the Node class, as discussed in the previous section.

The first version of the snapshot() method is synchronous whereas the second one is asynchronous.
You can specify a WritableImage to the method that will hold the snapshot of the node. If this is null, a new
WritableImage is created. If the specified WritableImage is smaller than the scene, the scene will be clipped
to fit the image size.

The program ishows how to take a snapshot of a scene. The main logic in the program
is essentially the same as that of the program in NodeSnapshot.java, except that, this time, it takes a snapshot of a
scene.
*/
public class SceneSnapshot extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		GridPane root = new GridPane();
		Scene scene = new Scene(root);

		Label nameLbl = new Label("Name:");
		TextField nameField = new TextField("Prema");

		Button syncSnapshotBtn = new Button("Synchronous Snapshot");
		syncSnapshotBtn.setOnAction(e -> syncSnapshot(scene));

		Button asyncSnapshotBtn = new Button("Asynchronous Snapshot");
		asyncSnapshotBtn.setOnAction(e -> asyncSnapshot(scene));

		root.setHgap(10);
		root.addRow(0, nameLbl, nameField, syncSnapshotBtn);
		root.add(asyncSnapshotBtn, 2, 1);
		
		stage.setScene(scene);
		stage.setTitle("Taking a Snapshot of a Scene");
		stage.show();
	}
	
	private void syncSnapshot(Scene scene) {		
		WritableImage image = scene.snapshot(null);
		ImageUtil.saveToFile(image);
	}
	
	private void asyncSnapshot(Scene scene) {
		// Create a Callback. Its call() method is called when 
		// the snapshot is ready. The getImage() method returns the snapshot
		Callback<SnapshotResult, Void> callback = (SnapshotResult result) -> {
			WritableImage image = result.getImage();
			ImageUtil.saveToFile(image);
			return null;
		};

		scene.snapshot(callback, null);
	}
}
