
package image.Writable;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import static javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;

/*
Saving an Image to the file system is easy:
    • Convert the Image to a BufferedImage using the fromFXImage() method of the
    SwingFXUtils class.
    • Pass the BufferedImage to the write() method of the ImageIO class.
    Notice that we have to use two classes – BufferedImage and ImageIO – that are part of the standard Java
    library, not the JavaFX library.

    Image image = create an image...
    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    // Save the image to the file
    File fileToSave = ...
    String imageFormat = "png";
    try {
        ImageIO.write(bImage, imageFormat, fileToSave);
    }
    catch (IOException e) {
        throw new RuntimeException(e);
    }

The program has code for a utility class ImageUtil. Its static saveToFile(Image image)
method can be used to save an Image to a local file system. The method asks for a file name. The user can
select a PNG or a JPEG format for the image.
*/
public class ImageUtil {
	public static void saveToFile(Image image) {
		// Ask the user for the file name
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select an image file name");
		fileChooser.setInitialFileName("untitled");
		ExtensionFilter pngExt = new ExtensionFilter("PNG Files", "*.png");
		ExtensionFilter jpgExt = new ExtensionFilter("JPEG Files", "*.jpg", "*.jpeg");
		fileChooser.getExtensionFilters().addAll(pngExt, jpgExt);

		File outputFile = fileChooser.showSaveDialog(null);
		if (outputFile == null) {
			return;
		}

		ExtensionFilter selectedExt = fileChooser.getSelectedExtensionFilter();
		String imageFormat = "png";
		if (selectedExt == jpgExt) {
			imageFormat = "jpg";
		}

		// Check for the file extension. Add oen, iff not specified
		String fileName = outputFile.getName().toLowerCase();
		switch (imageFormat) {
			case "jpg":
				if (!fileName.endsWith(".jpeg") && !fileName.endsWith(".jpg")) {
					outputFile = new File(outputFile.getParentFile(), outputFile.getName() + ".jpg");
				}
				break;
			case "png":
				if (!fileName.endsWith(".png")) {
					outputFile = new File(outputFile.getParentFile(), outputFile.getName() + ".png");
				}
		}

		// Convert the image to a buffered image
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);

		// Save the image to the file
		try {
			ImageIO.write(bImage, imageFormat, outputFile);
		} 
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
