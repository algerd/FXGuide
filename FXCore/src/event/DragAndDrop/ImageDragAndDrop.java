
package event.DragAndDrop;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
You can also place a URL or a file on the dragboard that refers to the image location. Let’s develop a simple
application to demonstrate an image data transfer. To transfer an image, the user can drag and drop the
following to a scene:
    • An image
    • An image file
    • A URL pointing to an image

The program opens a window with a text message, an empty ImageView, and a button.
The ImageView will display the dragged and dropped image. Use the button to clear the image.

The entire scene is a potential target for a drag-and-drop gesture. A drag-over event handler is set for
the scene. It checks whether the dragboard contains an image, a list of files, or a URL. If it finds one of these
data types in the dragboard, it reports that it will accept ANY data transfer mode. In the drag-dropped event
handler for the scene, the program attempts to read the image data, list of files, and the URL in order. If it is
a list of files, you look at the mime type of each file to see if the name starts with image/. You use the first file
with an image mime type and ignore the rest. If it is a URL, you simply try creating an Image object from it.
You can play with the application in different ways:
    • Run the program and open the HTML file drag_and_drop.html in a browser. The file
    is included in the src/resources\html directory. The HTML file contains two links:
    one pointing to a local image file and the other to a remote image file. Drag and drop
    the links onto the scene. The scene will show the images referred to by the links. Drag
    and drop the image from the web page. The scene will display the image. (Dragging
    and dropping of the image worked fine in Mozilla and Google Chrome browsers, but
    not in Windows Explorer.)
    • Open a file explorer, for example, Windows Explorer on Windows. Select an image
    file and drag and drop the file onto the scene. The scene will display the image from
    the file. You can drop multiple files, but the scene will display only an image from
    one of those files.

You can enhance the application by allowing the user to drag multiple files onto the scene and showing
them all in a TilePane. You can also add more error checks and feedbacks to the user about the drag-anddrop
gesture.
*/
public class ImageDragAndDrop extends Application {
	ImageView imageView = new ImageView();
	Button clearBtn = new Button("Clear Image");
	Scene scene; 
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Build UI
		VBox root = getUIs();
		scene = new Scene(root);
		stage.setScene(scene);

		// Add event handlers for the source and target
		this.addDnDEventHanders();

		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" +
		              "-fx-border-color: blue;");
		stage.setTitle("Performing a Drag-and-Drop Gesture");
		stage.show();
	}

	private VBox getUIs() {
		Label msgLbl = new Label("Drag and drop an image, an image file, or an image URL below.");
		
		// Set the size for the image view
		imageView.setFitWidth(300);
		imageView.setFitHeight(300);
		imageView.setSmooth(true);
		imageView.setPreserveRatio(true);

		clearBtn.setOnAction(e -> imageView.setImage(null));

		VBox box = new VBox(20, msgLbl, imageView, clearBtn);
		return box;
	}

	private void addDnDEventHanders() {
		scene.setOnDragOver(this::dragOver);
		scene.setOnDragDropped(this::dragDropped);
	}

	private void dragOver(DragEvent e) {
		// You can drag an image, a URL or a file
		Dragboard dragboard = e.getDragboard();

		if (dragboard.hasImage() || dragboard.hasFiles() || dragboard.hasUrl()) {
			e.acceptTransferModes(TransferMode.ANY);
		}
		
		e.consume();
	}

	private void dragDropped(DragEvent e) {
		boolean isCompleted = false;
		
		// Transfer the data to the target
		Dragboard dragboard = e.getDragboard();
		
		if (dragboard.hasImage()) {
			this.transferImage(dragboard.getImage());
			isCompleted = true;
		} else if (dragboard.hasFiles()) {
			isCompleted = this.transferImageFile(dragboard.getFiles());
		} else if (dragboard.hasUrl()) {
		    isCompleted = this.transferImageUrl(dragboard.getUrl());
		} else {
			System.out.println("Dragboard does not contain an image" + 
			                   " in the expected format: Image, File, URL");
		}

		// Data transfer is not successful
		e.setDropCompleted(isCompleted);
		
		e.consume();
	}
	
	private void transferImage(Image image) {
		imageView.setImage(image);
	}
	
	private boolean transferImageFile(List<File> files) {
		// Look at the mime typeof all file.
		// Use the first file having the mime type as "image/xxx"
		for(File file : files) {
			String mimeType;
			try {
				mimeType = Files.probeContentType(file.toPath());
				if (mimeType != null && mimeType.startsWith("image/")) {
					this.transferImageUrl(file.toURI().toURL().toExternalForm());
					return true;
				}
			} 
			catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return false;
	}
	
	private boolean transferImageUrl(String imageUrl) {
		try {
			imageView.setImage(new Image(imageUrl));
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
}
