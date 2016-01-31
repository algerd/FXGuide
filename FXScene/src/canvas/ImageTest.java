
package canvas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
You can draw an image on the canvas using the drawImage() method. The method has three versions:
    • void drawImage(Image img, double x, double y)
    • void drawImage(Image img, double x, double y, double w, double h)
    • void drawImage(Image img, double sx, double sy, double sw, double sh, double dx, double dy, double dw, double dh)

You can draw the whole or part of the image. The drawn image can be stretched or shortened on the
canvas. The following snippet of code draws the whole image in its original size on the canvas at (10, 10):
    Image image = new Image("your_image_URL");
    Canvas canvas = new Canvas(400, 400);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.drawImage(image, 10, 10);

The following statement will draw the whole image on the canvas by resizing it to fit in a 100px wide by
150px high area. Whether the image is stretched or shortened depends on its original size.
    // Draw the whole image in 100X150 area at (10, 10)
    gc.drawImage(image, 10, 10, 100, 150);

The following statement will draw part of an image on the canvas. Here it is assumed that the source
image is bigger than 100px by 150px. The image part being drawn is 100px wide and 150px high and its
upper left corner is at (0, 0) in the source image. The part of the image is drawn on the canvas at (10, 10) and
it is stretched to fit 200px wide and 200px high area on the canvas.
    // Draw part of the image in 200X200 area at (10, 10)
    gc.drawImage(image, 0, 0, 100, 150, 10, 10, 200, 200);

You can also directly modify pixels on the canvas(see Image.Writable package). 
The getPixelWriter() method of the GraphicsContext object returns a PixelWriter that can be used 
to write pixels to the associated canvas:
    Canvas canvas = new Canvas(200, 100);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    PixelWriter pw = gc.getPixelWriter();
    Once you get a PixelWriter, you can write pixels to the canvas.

*/
public class ImageTest extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Canvas canvas = new Canvas(400, 100);
		GraphicsContext gc = canvas.getGraphicsContext2D();
        
        String imagePath = "canvas/ksharan.jpg";
		Image image = new Image(imagePath); 
		gc.drawImage(image, 130, 10, 60, 80);
        
		Pane root = new Pane(canvas);	
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Drawing on a Canvas");
		stage.show();
        
	}
		
	
}
