
package effect;

import java.net.URL;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
The ImageInput effect works like the ColorInput effect. It passes the given image as an input to another
effect. The given image is not modified by this effect. Typically, it is used as an input to another effect, not as
an effect directly applied to a node.
An instance of the ImageInput class represents the ImageInput effect. The class contains three
properties that define the location and the source of the image:
    • x
    • y
    • source

The x and y properties specify the location of the upper left corner of the image in the local coordinate
system of the content node on which the effect is finally applied. Their default values are 0.0. The source
property specifies the Image object to be used.

You can use the following constructors to create an object of the ColorInput class:
    • ImageInput()
    • ImageInput(Image source)
    • ImageInput(Image source, double x, double y)

The program shows how to use the ImageInput effect. It passes an ImageInput as an
input to a DropShadow effect, which is applied on a rectangle
*/
public class ImageInputTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		String path = "\\Effect\\randomness.jpg";
		URL url = getClass().getClassLoader().getResource(path);

		Node node = null;
		if (url == null) {
			node = new Text("Missing image file " + path + " in classpath.");
		}
		else {
			ImageInput imageInputEffect = new ImageInput();
			double requestedWidth = 100;
			double requestedHeight = 50;
			boolean preserveRation = false;
			boolean smooth = true;
			Image image = new Image(
                url.toExternalForm(), 
                requestedWidth, 
                requestedHeight, 
                preserveRation, 
                smooth
            );
			imageInputEffect.setSource(image);

			node = new Rectangle(100, 50);
			GaussianBlur dsEffect = new GaussianBlur();
			dsEffect.setInput(imageInputEffect);
			node.setEffect(dsEffect);
		}

		HBox root = new HBox(node);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;"
        );
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the ImageInput Effect");
		stage.show();
	}
}
