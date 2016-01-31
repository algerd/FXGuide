
package effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
See DisplacementmapTest.

The DisplacementMap class contains a wrap property, which is set to false by default. A pixel in the
output is a pixel in the input that is moved to a new location. The location of the pixel in the input that needs
to move to a new location is computed by the equation. It is possible that for some locations in the output,
you do not have available pixels in the input. Suppose you have a 100px wide by 50px tall rectangle and you
apply a DisplacementMap effect to move all pixels to the left by 50px. The points at x = 75 in the output will
get the pixel at x = 125 in the input. The input is only 100px wide. Therefore, for all points x > 50 in the output,
you will not have available pixels in the input. If the wrap property is set to true, when the locations of the
pixels in the input to be moved are outside the input bounds, the locations are computed by taking their
modulus with the corresponding dimension (width along the x axis and height for along the y axis) of the
input. In the example, x = 125 will be reduced to 125 % 100, which is 25 and the pixels at x = 25 in the input
will be moved to x = 75 in the output. If the wrap property is false, the pixels in the output are left transparent.

Programm shows two Text nodes with DisplacementMap effects. Pixels in both nodes are moved
100px to the left. The Text node at the top has the wrap property set to false, whereas the Text node at the
bottom has the wrap property set to true. Notice that output for the bottom node is filled by wrapping the
input.
*/
public class DisplacementMapWrap extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a FloatMap
		int width = 200;
		int height = 25;
		
		FloatMap map = new FloatMap(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// Move all pixels 100 pixels to the left
				double u = 100.0/width; 
				map.setSamples(i, j, (float)u, 0.0f);
			}
		}

		Text t1 = new Text("Displaced Text");
		t1.setFont(Font.font(24));
		DisplacementMap effect1 = new DisplacementMap();
		effect1.setMapData(map);
		t1.setEffect(effect1);

		Text t2 = new Text("Displaced Text");
		t2.setFont(Font.font(24));
		DisplacementMap effect2 = new DisplacementMap();
		effect2.setWrap(true);
		effect2.setMapData(map);
		t2.setEffect(effect2);

		VBox root = new VBox(t1, t2);
		root.setSpacing(5);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" +
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using the warps proeprty in DisplacementMap");
		stage.show();
	}
}
