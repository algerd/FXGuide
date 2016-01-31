
package image;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
A viewport is a rectangular region to view part of a graphics. It is common to use scrollbars in conjunction
with a viewport. As the scrollbars are scrolled, the viewport shows different part of the graphics.

An ImageView lets you define a viewport for an image. In JavaFX, a viewport is an instance of the
javafx.geometry.Rectangle2D object. A Rectangle2D is immutable. It is defined in terms of four properties:
minX, minY, width, and height. The (minX, minY) value defines the location of the upper-left corner of the
rectangle. The width and height properties specify its size. 
You must specify all properties in the constructor.
    // Create a viewport located at (0, 0) and of isze 200 X 100
    Rectangle2D viewport = new Rectangle2D(0, 0, 200,100);

The ImageView class contains a viewport property, which provides a viewport into the image displayed
in the ImageView. The viewport defines a rectangular region in the image. The ImageView shows only the
region of the image that falls inside the viewport. The location of the viewport is defined relative to the
image, not the ImageView. By default, the viewport of an ImageView is null and the ImageView shows the
whole image.

The following snippet of code loads an image in its original size in an Image. The Image is set as the
source for an ImageView. A viewport 200 X 100 in size is set for the ImageView. The viewport is located at
(0, 0). This shows in the ImageView the top-left 200 X 100 region of the image
   
    String imagePath = "resources/picture/school_bus.jpg";
    Image image = new Image(imagePath);
    imageView = new ImageView(image);
    Rectangle2D viewport = new Rectangle2D(0, 0, 200, 100);
    imageView.setViewport(viewport);

The following snippet of code will change the view port to show the 200 X 100 lower-right region of the image.
    
    double minX = image.getWidth() - 200;
    double minY = image.getHeight() - 100;
    Rectangle2D viewport2 = new Rectangle2D(minX, minY, 200, 100);
    imageView.setViewport(viewport2);

Tip: The Rectangle2D class is immutable. Therefore, you need to create a new viewport every time you
want to move the viewport into the image.

The program loads an image into an ImageView. It sets a viewport for the ImageView.
You can drag the mouse, while pressing the left, right, or both buttons, to scroll to the different parts of the
image into the view.

The program declares a few class and instance variables. The VIEWPORT_WIDTH and VIEWPORT_HEIGHT
are constants holding the width and height of the viewport. The startX and startY instance variables will
hold the x and y coordinates of the mouse when the mouse is pressed or dragged. The ImageView instance
variable holds the reference of the ImageView. We need this reference in the mouse dragged event handler.

You compute the new location of the upper-left corner of the viewport. You always have a viewport in
the ImageView. The new viewport will be located at the dragged distance from the old location.
    // Get the minX and minY of the current viewport
    double curMinX = imageView.getViewport().getMinX();
    double curMinY = imageView.getViewport().getMinY();
    // Move the new viewport by the dragged distance
    double newMinX = curMinX + draggedDistanceX;
    double newMinY = curMinY + draggedDistanceY;

It is fine to place the viewport outside the region of the image. The viewport simply displays an empty
area when it falls outside the image area. To restrict the viewport inside the image area, we clamp the
location of the viewport.
    // Make sure the viewport does not fall outside the image area
    newMinX = clamp(newMinX, 0, imageView.getImage().getWidth() - VIEWPORT_WIDTH);
    newMinY = clamp(newMinY, 0, imageView.getImage().getHeight() - VIEWPORT_HEIGHT);
*/
public class ImageViewPort extends Application {
    
	private static final double VIEWPORT_WIDTH = 300;
	private static final double VIEWPORT_HEIGHT = 200;
	private double startX;
	private double startY;
	private ImageView imageView;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Load an image in its original size
		String imagePath = "image/school_bus.jpg";
		Image image = new Image(imagePath);
		imageView = new ImageView(image);

		// Set a viewport for the ImageView
		Rectangle2D viewport = new Rectangle2D(0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		imageView.setViewport(viewport);

		// Set the mouse pressed and mouse dragged event hanlders
		imageView.setOnMousePressed(this::handleMousePressed);
		imageView.setOnMouseDragged(this::handleMouseDragged);

		HBox root = new HBox(imageView);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Viewing an Image in a Viewport");
		stage.show();
	}

	private void handleMousePressed(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();
	}

	private void handleMouseDragged(MouseEvent e) {
		// How far the mouse was dragged
		double draggedDistanceX = e.getX() - startX;
		double draggedDistanceY = e.getY() - startY;

		// Reset the starting point for the next drag
		// if the user keeps the mouse pressed and drags again
		startX = e.getX();
		startY = e.getY();

		// Get the minX and minY of the current viewport
		double curMinX = imageView.getViewport().getMinX();
		double curMinY = imageView.getViewport().getMinY();

		// Move the new viewport by the dragged distance
		double newMinX = curMinX + draggedDistanceX;
		double newMinY = curMinY + draggedDistanceY;

		// Make sure the viewport does not fall outside the image area
		newMinX = clamp(newMinX, 0, imageView.getImage().getWidth() - VIEWPORT_WIDTH);
		newMinY = clamp(newMinY, 0, imageView.getImage().getHeight() - VIEWPORT_HEIGHT);

		// Set a new viewport
		imageView.setViewport(new Rectangle2D(newMinX, newMinY, VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
	}

	private double clamp(double value, double min, double max) {
		if (value < min) {
			return min;
		} else if (value > max) {
			return max;
		}
		return value;
	}
}
