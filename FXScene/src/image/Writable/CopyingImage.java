
package image.Writable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
You can write pixels to an image or any surface that supports writing pixels. For example, you can write
pixels to a WritableImage and a Canvas.

Tip: An Image is a read-only pixel surface. You can read pixels from an Image. However, you cannot write
pixels to an Image. If you want to write to an image or create an image from scratch, use a WritableImage.

An instance of the PixelWriter interface is used to write pixels to a surface. A PixelWriter is provided
by the writable surface. For example, you can use the getPixelWriter() method of the Canvas and
WritableImage to obtain a PixelWriter for them.

The PixelWriter interface contains methods to write pixels to a surface and obtain the pixel format
supported by the surface:
    • PixelFormat getPixelFormat()
    • void setArgb(int x, int y, int argb)
    • void setColor(int x, int y, Color c)
    • void setPixels(int x, int y, int w, int h, PixelFormat<ByteBuffer> pixelformat, byte[] buffer, int offset, int scanlineStride)
    • void setPixels(int x, int y, int w, int h, PixelFormat<IntBuffer> pixelformat, int[] buffer, int offset, int scanlineStride)
    • <T extends Buffer> void setPixels(int x, int y, int w, int h, PixelFormat<T> pixelformat, T buffer, int scanlineStride)
    • void setPixels(int dstx, int dsty, int w, int h, PixelReader reader, int srcx, int srcy)

The getPixelFormat() method returns the pixel format in which the pixels can be written to the
surface. The setArgb() and setColor() methods allow for writing one pixel at the specified (x, y) location in
the destination surface. The setArgb() method accepts the pixel data in an integer in the INT_ARGB format
whereas the setColor() method accepts a Color object. The setPixels() methods allow for bulk pixel
writing.
You can use an instance of the WritableImage to create an image from scratch. The class contains three
constructors:
    • WritableImage(int width, int height)
    • WritableImage(PixelReader reader, int width, int height)
    • WritableImage(PixelReader reader, int x, int y, int width, int height)
The first constructor creates an empty image of the specified width and height.

The second constructor creates an image of the specified width and height. The specified reader is
used to fill the image with pixels. An ArrayIndexOutOfBoundsException is thrown if the reader reads from
a surface that does not have the necessary number of rows and columns to fill the new image. Use this
constructor to copy the whole or part of an image. The following snippet of code creates a copy of an image.
    String imagePath = "resources/picture/ksharan.jpg";
    Image image = new Image(imagePath, 200, 100, true, true);
    int width = (int)image.getWidth();
    int height = (int)image.getHeight();
    // Create a copy of the image
    WritableImage newImage = new WritableImage(image.getPixelReader(), width, height);

The third constructor lets you copy a rectangular region from a surface. The (x, y) value is coordinates
of the upper-left corner of the rectangular region. The (width, height) value is the dimension of
the rectangular region to be read using the reader and the desired dimension of the new image. An
ArrayIndexOutOfBoundsException is thrown if the reader reads from a surface that does not have the
necessary number of rows and columns to fill the new image.

The WritableImage is a read-write image. Its getPixelWriter() method returns a PixelWriter to write
pixels to the image. It inherits the getPixelReader() method that returns a PixelReader to read data from
the image.

The program creates an Image. It creates three instances of the WritableImage and
copies the pixels from the original image to them. The copied pixels are modified before they written to
the destination. For one destination, pixels are darkened: for one brightened, and for one, made
semi-transparent. All four images are displayed in ImageViews
*/
public class CopyingImage extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		String imagePath = "image/Writable/ksharan.jpg";
		Image image = new Image(imagePath, 200, 100, true, true);

		int width = (int)image.getWidth();
		int height = (int)image.getHeight(); 

		// Create three WritableImage instances
		// one will be a darker, one brighter, and one semi-transparent
		WritableImage darkerImage = new WritableImage(width, height);
		WritableImage brighterImage = new WritableImage(width, height);
		WritableImage semiTransparentImage = new WritableImage(width, height);

		// Copy source pixels to the destinations 
		this.createImages(image, 
		                 darkerImage, 
		                 brighterImage, 
		                 semiTransparentImage, 
		                 width, 
		                 height);
		
		ImageView imageView = new ImageView(image);
		ImageView darkerView = new ImageView(darkerImage);
		ImageView brighterView = new ImageView(brighterImage);
		ImageView semiTransparentView = new ImageView(semiTransparentImage);

		HBox root = new HBox(10, 
		       new VBox(imageView, new Text("Original")), 
		       new VBox(darkerView, new Text("Darker")), 
		       new VBox(brighterView, new Text("Brighter")), 
		       new VBox(semiTransparentView, new Text("Semi-Transparent")));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Writing Pixels to an Image");
		stage.show();
	}
	
	private void createImages(Image image, 
	                          WritableImage darkerImage, 
	                          WritableImage brighterImage, 
	                          WritableImage semiTransparentImage,
	                          int width, int height) {
	// Obtain the pixel reader from the image
	PixelReader pixelReader = image.getPixelReader();
	PixelWriter darkerWriter = darkerImage.getPixelWriter();
	PixelWriter brighterWriter = brighterImage.getPixelWriter();
	PixelWriter semiTransparentWriter = semiTransparentImage.getPixelWriter();

		// Read one pixel at a time from the source and write it to the destinations
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				Color color = pixelReader.getColor(x, y);

				// Write a darker pixel to the new image
				darkerWriter.setColor(x, y, color.darker());

				// Write a brighter pixel to the new image
				brighterWriter.setColor(x, y, color.brighter());

				// Write a semi-transparent pixel to the new image
				semiTransparentWriter.setColor(x, y, Color.color(color.getRed(), color.getGreen(), color.getBlue(), 0.50));
			}
		}
	}
}
