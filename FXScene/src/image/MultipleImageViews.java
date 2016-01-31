
package image;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
An Image loads an image in memory from its source. You can have multiple views of the same Image. An
ImageView provides one of the views.
You have an option to resize the original image while loading, displaying, or at both times. Which option
you choose to resize an image depends on the requirement at hand.
    • Resizing an image in an Image object resizes the image permanently in memory and
    all views of the image will use the resized image. Once an Image is resized, its size
    cannot be altered. You may want to reduce the size of an image in an Image object to
    save memory.
    • Resizing an image in an ImageView resizes the image only for this view. You can
    resize the view of an image in an ImageView even after the image has been displayed.

Similar to the Image class, the ImageView class contains the following four properties to control the
resizing of view of an image.
    • fitWidth
    • fitHeight
    • preserveRatio
    • smooth

The fitWidth and fitHeight properties specify the resized width and height of the image, respectively.
By default, they are zero, which means that the ImageView will use the width and height of the loaded image
in the Image.

The preserveRatio property specifies whether to preserve the aspect ratio of the image while resizing.
By default, it is false.

The smooth property specifies the quality of the filtering algorithm to be used in resizing. Its default
value is platform dependent. If it is set to true, a better quality filtering algorithm is used.

The program loads an image in an Image object in original size. It creates three
ImageView objects of the Image specifying different sizes.
*/
public class MultipleImageViews extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Load an image in its original size
		String imagePath = "image/school_bus.jpg";
		Image image = new Image(imagePath);
		
		// Create three views of different sizes of the same image
		ImageView view1 = getImageView(image, 100, 50, false);
		ImageView view2 = getImageView(image, 100, 50, true);
		ImageView view3 = getImageView(image, 100, 100, true);

		HBox root = new HBox(10, view1, view2, view3);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Multiple Views of an Image");
		stage.show();
	}
	
	private ImageView getImageView(Image image, double fitWidth, double fitHeight, boolean preserveRation) {
		ImageView view = new ImageView(image);
		view.setFitWidth(fitWidth);
		view.setFitHeight(fitHeight);
		view.setPreserveRatio(preserveRation);
		view.setSmooth(true);
		return view;
	}
}
