
package effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
The DisplacementMap effect shifts each pixel in the input to produce an output. The name has two parts:
“Displacement” and “Map.” The first part implies that the effect displaces the pixels in the input. The second
part implies that the displacement is based on a map that provides a displacement factor for each pixel in
the output.

An instance of the DisplacementMap class represents a DisplacementMap. The class contains several
properties to configure the effect:
    • mapData
    • scaleX
    • scaleY
    • offsetX
    • offsetY
    • wrap
    • input

The mapData property is an instance of the FloatMap class. A FloatMap is a data structure that stores
up to four values for each point in a rectangular area represented by its width and height properties. For
example, you can use a FloatMap to store four components of the color (red, green, blue, and alpha) for
each pixel in a two-dimensional rectangle. Each of the four values associated with a pair of numbers in the
FloatMap are said to be in a band numbered 0, 1, 2, and 3. The actual meaning of the values in each band is
context dependent. The following code provides an example of setting the FloatMap width and height:
    // Create a FloatMap (width = 100, height = 50)
    FloatMap map = new FloatMap(100, 50);

Now you need to populate the FloatMap with band values for each pair of numbers. You can use one of
the following methods of the FloatMap class to populate it with the data:
    • setSample(int x, int y, int band, float value)
    • setSamples(int x, int y, float s0)
    • setSamples(int x, int y, float s0, float s1)
    • setSamples(int x, int y, float s0, float s1, float s2)
    • setSamples(int x, int y, float s0, float s1, float s2, float s3)

The setSample() method sets the specified value in the specified band for the specified (x, y) location.
The setSamples() methods sets the specified values in the bands determined by the positions of the values
in the method call. That is, the first value is set for band 0, the second value for band 1, and so forth:
    // Set 0,50f for band 0 and band 1 for each point in the map
    for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 50; j++) {
            map.setSamples(i, j, 0.50f, 0.50f);
        }
    }

The DisplacementMap class requires that you set the mapData property to a FloatMap that contains
values for band 0 and band 1 for each pixel in the output.

The scaleX, scaleY, offsetX, and offsetY are double properties. They are used in the equation
(described shortly) to compute the displacement of the pixels. The scaleX and scaleY properties have 1.0 as
their default values. The offsetX and offsetY properties have 0.0 as their default values.
The following equation is used to compute the pixel at (x, y) coordinates in the output. The
abbreviations dst and src in the equation represent the destination and source, respectively:
    dst[x,y] = src[x + (offsetX + scaleX * mapData[x,y][0]) * srcWidth,
    y + (offsetY + scaleY * mapData[x,y][1]) * srcHeight]

If the above equation looks very complex, don’t be intimidated. In fact, the equation is very simple once
you read the explanation that follows. The mapData[x,y][0] and mapData[x,y][1] parts in the equation
refer to the values at band 0 and band 1, respectively, in the FloatMap for the location at (x, y).
Suppose you want to get the pixel for the (x, y) coordinates in the output, that is, you want to know
which pixel from the input will be moved to (x, y) in the output. First, make sure you get the starting point
right. To repeat, the equation starts with a point (x, y) in the output and finds the pixel at (x1, y1) in the input
that will move to (x, y) in the output.

Tip Many will get the equation wrong by thinking that you start with a pixel in the input and then find its
location in the output. This is not true. The equation works the other way around. It picks a point (x, y) in the
output and then finds which pixel in the input will move to this point.
Below are the steps to fully explain the equation:
    • You want to find the pixel in the input that will be moved to the point (x, y) in the output.
    • Get the values (band 0 and band 1) from the mapData for (x, y).
    • Multiply the mapData values by the scale (scaleX for x coordinate and scaleY for y coordinate).
    • Add the corresponding offset values to the values computed in the previous step.
    • Multiply the previous step values with the corresponding dimensions of the input.
    This gives you the offset values along the x and y coordinate axes from the output
    (x, y) from where the pixels in the input will be moving to the (x, y) in the output.
    • Add the values in the previous step to the x and y coordinates of the point in the
    output. Suppose these values are (x1, y1). The pixel at (x1, y1) in the input moves to
    the point (x, y) in the output.

If you still have problem understanding the pixel-shifting logic, you can break the above equation into
two parts:
    x1 = x + (offsetX + scaleX * mapData[x,y][0]) * srcWidth
    y1 = y + (offsetY + scaleY * mapData[x,y][1]) * srcHeight

You can read these equations as “The pixel at (x, y) in the output is obtained by moving the pixel at
(x1, y1) in the input to (x, y).”

If you leave the scale and offset values to their default:
    • Use a positive value in band 0 to move the input pixels to the left.
    • Use a negative value in band 0 to move the input pixels to the right.
    • Use a positive value in band 1 to move the input pixels up.
    • Use a negative value in band 1 to move the input pixels down.

The program creates a Text node and adds a DisplacementMap effect to the node. In the
mapData, it sets values, so all pixels in the top half of the input are moved to the right by 1 pixel, and all pixels
in the bottom half of the input are moved to the left by 1 pixel.
*/
public class DisplacementmapTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a FloatMap
		int width = 250;
		int height = 50;
		FloatMap map = new FloatMap(width, height);
		
		double xDisplacement = 1.0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				double u = xDisplacement;
				if (j < height / 2) {
					// Move the top-half pixels to the right (a nagative value)
					u = -1.0 * (u * xDisplacement / width);
				}
				else {
					// Move the bottom-half pixels to the left.(a positive value)
					u = u * xDisplacement / width;
				}

				// Set values for band 0 and 1 (x and y axes displaments factors).
				// Always use 0.0f for y-axis displacement factor.
				map.setSamples(i, j, (float)u, 0.0f);
			}
		}

		Text t1 = new Text("Displaced Text");
		t1.setFont(Font.font(36));

		DisplacementMap effect1 = new DisplacementMap();
		effect1.setMapData(map);
		t1.setEffect(effect1);

		HBox root = new HBox(t1);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" +
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying the DisplacementMap Effect");
		stage.show();
	}
}
