
package shape.Text;

import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
You can load custom fonts from external sources: for example, from a file from the local file system or from
a URL. The loadFont() static method in the Font class loads a custom font.
    • loadFont(InputStream in, double size)
    • loadFont(String urlStr, double size)

Upon successfully loading of the custom font, the loadFont() method registers font with JavaFX
graphics engine, so a font can be created using the constructors and factory methods of the Font class. The
method also creates a Font object of the specified size and returns it. Therefore, the size parameter exists
for loading the font and creating its object in the same method call. If the method cannot load the font, it
returns null.

The program shows how to load a custom font from a local file system. The font file name
is 4starfac.ttf. The file was downloaded free from http://www.fontfile.com.  After the font is loaded successfully, 
it is set for the first Text node. 
A new Font object is created for its family name and set for the second Text node. If the font file does
not exist or the font cannot be loaded, an appropriate error message is displayed in the window.
*/
public class TextCustomFont extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Text t1 = new Text();
		t1.setLineSpacing(10);

		Text t2 = new Text("Another Text node");

		// Load the custom font
		String fontFile = "Shape/Text/4starfac.ttf";
		URL url = this.getClass().getClassLoader().getResource(fontFile);	
		if (url != null) {
			String urlStr = url.toExternalForm();
			Font customFont = Font.loadFont(urlStr, 16);
			if (customFont != null ) {
				// Set the custom font  for the first Text node
				t1.setFont(customFont);

				// Set the text and line spacing
				t1.setText("Hello from the custom font!!! \nFont Family: " + customFont.getFamily());
				
				// Create an object of the custom font and use it
				Font font2 = Font.font(customFont.getFamily(), FontWeight.BOLD, FontPosture.ITALIC, 24);

				// Set the custom font for the second Text node
				t2.setFont(font2);
			} 
            else {
				t1.setText("Could not load the custom font from " + urlStr);
			}
		} 
        else {
			t1.setText("Could not find the custom font file " + fontFile + " in CLASSPATH. Used the default font.");
		}

		HBox root = new HBox(t1, t2);
		root.setSpacing(20);
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
		stage.setTitle("Loading and Using Custom Font");
		stage.show();
	}
}
