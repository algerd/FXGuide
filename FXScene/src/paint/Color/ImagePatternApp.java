
package paint.Color;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
An image pattern lets you fill a shape with an image. The image may fill the entire shape or use a tiling
pattern. Here are the steps you would use to get an image pattern:
    1. Create an Image object using an image from a file.
    2. Define a rectangle, known as the anchor rectangle, relative to the upper left
        corner of the shape to be filled.

The image is shown in the anchor rectangle and is then resized to fit the anchor rectangle. If the
bounding box for the shape to be filled is bigger than that of the anchor rectangle, the anchor rectangle with
the image is repeated within the shape in a tiling pattern.
You can create an object of the ImagePattern using one of its constructors:
    • ImagePattern(Image image)
    • ImagePattern(Image image, double x, double y, double width, double height, boolean proportional)

The first constructor fills the entire bounding box with the image without any pattern. The second
constructor lets you specify the x and y coordinates, width, and height of the anchor rectangle. If the
proportional argument is true, the anchor rectangle is specified relative to the bounding box of the shape
to be filled in terms of a unit square. If the proportional argument is false, the anchor rectangle is specified
in the local coordinate system of the shape. 
*/
public class ImagePatternApp extends Application {	
	private Image img; 
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() {
		// Create an Image object
		final String IMAGE_PATH = "paint/Color/blue_rounded_rectangle.png";				
		img = new Image(IMAGE_PATH);
	}

	@Override
	public void start(Stage stage) {		
		// An anchor rectangle at (0, 0) that is 25% wide and 25% tall
		// relative to the rectangle to be filled
		ImagePattern p1 = new ImagePattern(img, 0, 0, 0.25, 0.25, true);
		Rectangle r1 = new Rectangle(100, 50);
		r1.setFill(p1);
		
		// An anchor rectangle at (0, 0) that is 50% wide and 50% tall
		// relative to the rectangle to be filled
		ImagePattern p2 = new ImagePattern(img, 0, 0, 0.5, 0.5, true);
		Rectangle r2 = new Rectangle(100, 50);
		r2.setFill(p2);
		
		// Using absolute bounds for the anchor rectangle
		ImagePattern p3 = new ImagePattern(img, 40, 15, 20, 20, false);
		Rectangle r3 = new Rectangle(100, 50);
		r3.setFill(p3);
	
		// Fill a circle
		ImagePattern p4 = new ImagePattern(img, 0, 0, 0.1, 0.1, true);
		Circle c = new Circle(50, 50, 25);
		c.setFill(p4);
		
		HBox root = new HBox();
		root.getChildren().addAll(r1, r2, r3, c);
	
		Scene scene = new Scene(root);
		stage.setScene(scene);
	
		stage.setTitle("Using Image Patterns");
		stage.show();
	}
}
