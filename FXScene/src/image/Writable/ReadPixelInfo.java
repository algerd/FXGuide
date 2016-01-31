
package image.Writable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
JavaFX supports reading pixels from an image, writing pixels to an image, and creating a snapshot of the
scene. It supports creating an image from scratch. If an image is writable, you can also modify the mage in
memory and save it to the file system. The image API provides access to each pixel in the image. It supports
reading and writing one pixel or a chunk of pixel at a time.

Pixel Formats:
---------------
The image API in JavaFX gives you access to each pixel in an image. A pixel stores information about its color
(red, green, blue) and opacity (alpha). The pixel information can be stored in several formats.

An instance the PixelFormat<T extends Buffer> represents the layout of data for a pixel. You
need to know the pixel format when you read the pixels from an image. You need to specify the pixel
format when you write pixels to an image. The WritablePixelFormat class inherits from the PixelFormat
class and its instance represents a pixel format that can store full color information. An instance of the
WritablePixelFormat class is used when writing pixels to an image.

Both class PixelFormat and its subclass WritablePixelFormat are abstract. The PixelFormat class
provides several static methods to obtain instances to PixelFormat and WritablePixelFormat abstract
classes. Before we discuss how to get an instance of the PixelFormat, let us discuss types of storage formats
available for storing the pixel data.

A PixelFormat has a type that specifies the storage format for a single pixel. The constants of the
PixelFormat.Type enum represent different type of storage formats:

    • BYTE_RGB
    • BYTE_BGRA
    • BYTE_BGRA_PRE
    • BYTE_INDEXED
    • INT_ARGB
    • INT_ARGB_PRE
In the BYTE_RGB format, the pixels are assumed opaque. The pixels are stored in adjacent byes as red,
green, and blue, in order.

In the BYTE_BGRA format, pixels are stored in adjacent byes as blue, green, red, and alpha in order. The
color values (red, green, and blue) are not pre-multiplied with the alpha value.

The BYTE_BGRA_PRE type format is similar to BYTE_BGRA, except that in BYTE_BGRA_PRE the stored color
component values are pre-multiplied by the alpha value.

In the BYTE_INDEXED format, a pixel is as a single byte. A separate lookup list of colors is provided. The
single byte value for the pixel is used as an index in the lookup list to get the color value for the pixel.

In the INT_ARGB format, each pixel is stored in a 32-bit integer. Bytes from the most significant byte
(MSB) to the least significant byte (LSB) store alpha, red, green, and blue values. The color values (red,
green, and blue) are not pre-multiplied with the alpha value.

The INT_ARGB_PRE format is similar to the INT_ARGB format, except that INT_ARGB_PRE stores the color
values (red, green, and blue) pre-multiplied with the alpha value.
Typically, you need to create a WritablePixelFormat when you write pixels to create a new image.
When you read pixels from an image, the pixel reader will pride you a PixelFormat instance that will tell you
how the color information in the pixels are stored.
    // BYTE_BGRA Format type
    WritablePixelFormat<ByteBuffer> format1 = PixelFormat.getByteBgraInstance();
    // BYTE_BGRA_PRE Format type
    WritablePixelFormat<ByteBuffer> format2 = PixelFormat.getByteBgraPreInstance();

Reading Pixels from an Image:
------------------------------
An instance of the PixelReader interface is used to read pixels from an image. Use the getPixelReader()
method of the Image class to obtains a PixelReader. The PixelReader interface contains the following
methods:
    • int getArgb(int x, int y)
    • Color getColor(int x, int y)
    • Void getPixels(int x, int y, int w, int h, WritablePixelFormat<ByteBuffer> pixelformat, byte[] buffer, int offset, int scanlineStride)
    • void getPixels(int x, int y, int w, int h, WritablePixelFormat<IntBuffer> pixelformat, int[] buffer, int offset, int scanlineStride)
    • <T extends Buffer> void getPixels(int x, int y, int w, int h, WritablePixelFormat<T> pixelformat, T buffer, int scanlineStride)
    • PixelFormat getPixelFormat()

The PixelReader interface contains methods to read one pixel or multiple pixels at a time. Use the
getArgb() and getColor() methods to read the pixel at the specified (x, y) coordinate. Use the getPixels()
method to read pixels in bulk. Use the getPixelFormat() method to get the PixelFormat that best describes
the storage format for the pixels in the source.

The getPixelReader() method of the Image class returns a PixelReader only if the image is readable.
Otherwise, it returns null. Am image may not be readable if it is not fully loaded yet, it had an error during
loading, or its format does not support reading pixels.
    Image image = new Image("resources/picture/ksharan.jpg");
    // Get the pixel reader
    PixelReader pixelReader = image.getPixelReader();
    if (pixelReader == null) {
        System.out.println("Connot read pixels from the image");
    } else {
        // Read image pixels
    }

Once you have a PixelReader, you can read pixels invoking one of its methods. The program in
Listing how to read pixels from an image. The code is self-explanatory.
    • The start() method creates an Image. The Image is loaded synchronously.
    • The logic to read the pixels is in the readPixelsInfo() method. The method receives
    a fully loaded Image. It uses the getColor() method of the PixelReader to get the
    pixel at a specified location. It prints the colors for all pixels. At the end, it prints the
    pixel format, which is BYTE_RBG.
*/
public class ReadPixelInfo extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		String imagePath = "image/Writable/ksharan.jpg";
		Image image = new Image(imagePath);
		ImageView imageView = new ImageView(image);
		HBox root = new HBox(imageView);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Reading Pixels from an Image");
		stage.show();
		
		// Read pixels from the image
		this.readPixelsInfo(image);
	}
	
	private void readPixelsInfo(Image image) {
		// Obtain the pixel reader from the image
		PixelReader pixelReader = image.getPixelReader();		
		if (pixelReader == null) {
			System.out.println("Connot read pixels from the image");
			return;
		}
		
		// Get image width and height
		int width = (int)image.getWidth();
		int height = (int)image.getHeight();

		// Read all pixels
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				Color color = pixelReader.getColor(x, y);
				System.out.println("Color at (" + x + ", " + y + ") = " + color);
			}
		}

		PixelFormat format = pixelReader.getPixelFormat();
		PixelFormat.Type formatType = format.getType();
		System.out.println("Pixel format type: " + formatType);
	}
}
