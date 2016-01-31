
package image;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
Loading an Image:
-------------------
An instance of the Image class is an in-memory representation of an image. The class supports BMP, PNG,
JPEG, and GIF image formats. It loads an image from a source, which can be specified as a string URL or an
InputStream. It can also scale the original image while loading.
The Image class contains several constructors that let you specify the properties for the loaded image:
    • Image(InputStream is)
    • Image(InputStream is, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth)
    • Image(String url)
    • Image(String url, boolean backgroundLoading)
    • Image(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth)
    • Image(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth, boolean backgroundLoading)

There is no ambiguity of the source of the image if an InputStream is specified as the source. If a string
URL is specified as the source, it could be a valid URL or a valid path in the CLASSPATH. If the specified URL
is not a valid URL, it is used as a path and the image source will be searched on the path in the CLASSPATH.
    // Load an image from local machine using an InputStream
    String sourcePath = "C:\\mypicture.png";
    Image img = new Image(new FileInputStream(sourcePath));
    // Load an image from a URL
    Image img = new Image("http://jdojo.com/wp-content/uploads/2013/03/randomness.jpg");
    // Load an image from the CLASSPATH. The image is located in the resources.picture package
    Image img = new Image("resources/picture/randomness.jpg");

In the above statement, the specified URL resources/picture/randomness.jpg is not a valid URL. The
Image class will treat it as a path expecting it to exist in the CLASSPATH. It treats the resource.picture as a
package and the randomness.jpg as a resource in that package.

Specifying the Image-Loading Properties:
----------------------------------------
Some constructors let you specify some image-loading properties to controls the quality of the image and
the loading process:
    • requestedWidth
    • requestedHeight
    • preserveRatio
    • smooth
    • backgroundLoading
The requestedWidth and requestedHeight properties specify the scaled width and height of the image.
By default, an image is loaded in its original size.

The preserveRatio property specifies whether to preserve the aspect ratio of the image while scaling.
By default, it is false.

The smooth property specifies the quality of the filtering algorithm to be used in scaling. By default, it is false.
If it is set to true, a better quality filtering algorithm is used, which slows down the image-loading process a bit.

The backgroundLoading property specifies whether to load the image asynchronously. By default, the
property is set to false and the image is loaded synchronously. The loading process starts when the Image
object is created. If this property is set to true, the image is loaded asynchronously in a background thread.

Reading the Loaded-Image Properties:
------------------------------------
The Image class contains the following read-only properties:
    • width
    • height
    • progress
    • error
    • exception
The width and height properties are the width and height of the loaded image, respectively. They are
zero if the image failed to load.

The progress property indicates the progress in loading the image data. It is useful to know the progress
when the backgroundLoading property is set to true. Its value is between 0.0 and 1.0 where 0.0 indicates
zero percent loading and 1.0 indicates hundred percent loading. When the backgroundLoading property is
set to false (the default), its value is 1.0. You can add a ChangeListener to the progress property to know
the progress in image loading. You may display a text as a placeholder for an image while it is loading and
update the text with the current progress in the ChangeListener.
    // Load an image in the background
    String imagePath = "resources/picture/randomness.jpg";
    Boolean backgroundLoading = true;
    Image image = new Image(imagePath, backgroundLoading);
    // Print the loading progress on the standard output
    image.progressProperty().addListener((prop, oldValue, newValue) -> {
        System.out.println("Loading:" + Math.round(newValue.doubleValue() * 100.0) + "%");
    });

The error property indicates whether an error occurred while loading the image. If it is true, the
exception property specifies the Exception that caused the error. At the time of this writing, TIFF image
format is not supported on Windows. The following snippet of code attempts to load a TIFF image on
Windows XP and it produces an error. The code contains an error handling logic that adds a ChangeListener
to the error property if backgroundLoading is true. Otherwise, it checks for the value of the error property.
    String imagePath = "resources/picture/test.tif";
    Boolean backgroundLoading = false;
    Image image = new Image(imagePath, backgroundLoading);
    // Add a ChangeListener to the error property for background loading and check its value for non-backgroudn loading
    if (image.isBackgroundLoading()) {
        image.errorProperty().addListener((prop, oldValue, newValue) -> {
            if (newValue) {
                System.out.println("An error occurred while loading the image.\n" +
                    "Error message: " + image.getException().getMessage());
            }
        });
    }
    else if (image.isError()) {
        System.out.println("An error occurred while loading the image.\n" +
        "Error message: " + image.getException().getMessage());
    }


Viewing an Image:
-------------------
An instance of the ImageView class is used to display an image loaded in an Image object. The ImageView
class inherits from the Node class, which makes an ImageView suitable to be added to a scene graph. The
class contains several constructors:
    • ImageView()
    • ImageView(Image image)
    • ImageView(String url)
The no-args constructor creates an ImageView without an image. Use the image property to set an
image. The second constructor accepts the reference of an Image. The third constructor lets you specify the
URL of the image source. Internally, it creates an Image using the specified URL.
    // Create an empty ImageView and set an Image for it later
    ImageView imageView = new ImageView();
    imageView.setImage(new Image("resources/picture/randomness.jpg"));
    // Create an ImageView with an Image
    ImageView imageView = new ImageView(new Image("resources/picture/randomness.jpg"));
    // Create an ImageView with the URL of the image source
    ImageView imageView = new ImageView("resources/picture/randomness.jpg");

The program shows how to display an image in a scene. It loads an image in an Image
object. The image is scaled without preserving the aspect ratio. The Image object is added to an ImageView,
which is added to an HBox.

*/
public class ImageTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		String imagePath = "image/randomness.jpg";
		
		// Scale the iamge to 200 X 100
		double requestedWidth = 200;
		double requestedHeight = 100;
		boolean preserveRatio = false;
		boolean smooth = true;
		Image image = new Image(
            imagePath, 
            requestedWidth, 
            requestedHeight, 
            preserveRatio, 
            smooth
        );
		ImageView imageView = new ImageView(image);
		
		HBox root = new HBox(imageView);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Displaying an Image");
		stage.show();
	}
}
