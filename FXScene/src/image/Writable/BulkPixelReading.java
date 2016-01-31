
package image.Writable;

import java.nio.ByteBuffer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
See ReadPixelInfo.

void getPixels(
    int x, int y,
    int width, int height,
    WritablePixelFormat<ByteBuffer> pixelformat,
    byte[] buffer,
    int offset,
    int scanlineStride)

The method reads the pixels from rows in order. The pixels in the first row are read, then the pixels from
the second row, and so on. It is important that you understand the meaning of all parameters to the method:
The method reads the pixels of a rectangular region in the source.

The x and y coordinates of the upper-left corner of the rectangular region are specified in the x and y
arguments.

The width and height arguments specify the width and height of the rectangular region.

The pixelformat specifies the format of the pixel that should be used to store the read pixels in the
specified buffer.

The buffer is a byte array in which the PixelReader will store the read pixels. The length of the array
must be big enough to store all read pixels.

The offset specifies the starting index in the buffer array to store the first pixel data. Its value of zero
indicates that the data for the first pixel will start at index 0 in the buffer.

The scanlineStride specify the distance between the start of one row of data In the buffer to the start
of the next row of data. Suppose you have two pixels in a row and you want to read in the BYTE_BGRA format
taking four bytes for a pixel. One row of data can be stored in eight bytes. If you specify 8 as the argument
value, the data for the next row will start in the buffer just after the data for the previous row data ends. If you
specify the argument value 10, last two bytes will be empty for each row of data. The first row pixels will be
stored from index 0 to 7. The indexes 8 and 9 will be empty (or not written). Indexes 10 to 17 will store pixel
data for the second row leaving indexes 19 and 19 empty. You may want to specify a bigger value for the
agrument than needed to store one row of pixel data if you want to fill the empty slots with yor won values
later. Specifying avlaue less than needed will overwrite part of the data in the previous row.

The x and y coordinates of the upper-left corner of the rectangular region to be read are set to zero. The
width and height of the region are set to the width and height of the image. This sets up the arguments to
read the entire image.

You want to read the pixel data into the buffer starting at index 0, so you set the offset argument to 0.
You want to read the pixel data in BYTE_BGRA format type, which takes 4 bytes to store data for one pixel.
We have set the scanlineStride argument value, which is the length of a row data, to width * 4, so a row
data starts at the next index from where the previous row data ended.

You get an instance of the WritablePixelFormat to read the data in the BYTE_BGRA format type. Finally,
we call the getPixels() method of the PixelReader to read the pixel data. The buffer will be filled with the
pixel data when the getPixels() method returns.

Tip: Setting the value for the scanlineStride argument and the length of the buffer array depends on
the pixelFormat argument. Other versions of the getPixels() method allows reading pixel data in different
formats.

The program has the complete source code to read pixels in bulk. After reading all pixels,
it decodes the color components in the byte array for the pixel at (0, 0). It reads the pixel at (0, 0) using the
getColor() method. The pixel data at (0, 0) obtained through both methods are printed on the standard
output.
*/
public class BulkPixelReading extends Application {
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
		stage.setTitle("Reading Pixels in Bulk");
		stage.show();
		
		// Read pixels in bulk from the image
		this.readPixelsInfo(image);
	}
	
	private void readPixelsInfo(Image image) {
		// Obtain the pixel reader from the image
		PixelReader pixelReader = image.getPixelReader();
		if (pixelReader == null) {
			System.out.println("Connot read pixels from the image");
			return;
		}

		// Read all pixels in a byte array in one go
		int x = 0;
		int y = 0;
		int width = (int)image.getWidth();
		int height = (int)image.getHeight();
		int offset = 0;
		int scanlineStride = width * 4;
		byte[] buffer = new byte[width * height * 4];

		// Get a WritablePixelFormat
		WritablePixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteBgraInstance();

		// Read all pixels at once
		pixelReader.getPixels(x, y, 
		                      width, height, 
		                      pixelFormat, 
		                      buffer, 
		                      offset, 
		                      scanlineStride);

		// Read the color of the pixel at (0, 0)
		int blue = (buffer[0] & 0xff);
		int green = (buffer[1] & 0xff);
		int red = (buffer[2] & 0xff);
		int alpha = (buffer[3] & 0xff);
		System.out.println("red=" + red +  ", green=" + green + 
		                   ", blue=" + blue +  ", alpha=" + alpha);

		// Get the color of the pixel at (0, 0)
		Color c = pixelReader.getColor(0, 0); 
		System.out.println("red=" + (int)(c.getRed() * 255) + 
		                   ", green=" + (int)(c.getGreen() * 255) + 
		                   ", blue=" + (int)(c.getBlue() * 255) + 
		                   ", alpha=" + (int)(c.getOpacity() * 255));
	}
}
