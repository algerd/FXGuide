
package stage;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*
The Screen class in the javafx.stage package is used to get the details, for example, dots-per-inch (DPI)
setting and dimensions of user screens (or monitors). If multiple screens are hooked up to a computer, one
of the screens is known as the primary screen and others as nonprimary screens. You can get the reference of
the Screen object for the primary monitor using the static getPrimary() method of the Screen class with the
following code:
    // Get the reference to the primary screen
    Screen primaryScreen = Screen.getPrimary();

The static getScreens() method returns an ObservableList of Screen objects:
    ObservableList<Screen> screenList = Screen.getScreens();

You can get the resolution of a screen in DPI using the getDpi() method of the Screen class as follows:
    Screen primaryScreen = Screen.getPrimary();
    double dpi = primaryScreen.getDpi();

You can use the getBounds() and getVisualBounds() methods to get the bounds and visual bounds,
respectively. Both methods return a Rectangle2D object, which encapsulates the (x, y) coordinates of
the upper-left and the lower-right corners, the width, and the height of a rectangle. The getMinX() and
getMinY() methods return the x and y coordinates of the upper-left corner of the rectangle, respectively.
The getMaxX() and getMaxY() methods return the x and y coordinates of the lower-right corner of the
rectangle, respectively. The getWidth() and getHeight() methods return the width and height of the
rectangle, respectively.

The bounds of a screen cover the area that is available on the screen. The visual bounds represent the
area on the screen that is available for use, after taking into account the area used by the native windowing
system such as task bars and menus. Typically, but not necessarily, the visual bounds of a screen represents
a smaller area than its bounds.

If a desktop spans multiple screens, the bounds of the nonprimary screens are relative to the primary
screen. For example, if a desktop spans two screens with the (x, y) coordinates of the upper-left corner of
the primary screen at (0, 0) and its width 1600, the coordinates of the upper-left corner of the second screen
would be (1600, 0).

The program prints the screens details when it was run on a Windows desktop with two
screens. You may get a different output. Notice the difference in height for bounds and visual bounds for one
screen and not for the other. The primary screen displays a task bar at the bottom that takes away some part
of the height from the visual bounds. The nonprimary screen does not display a task bar, and therefore, its
bounds and visual bounds are the same.

Tip: Although it is not mentioned in the API documentation for the Screen class, you cannot use this class
until the JavaFX launcher has started. That is, you cannot get screen descriptions in a non-JavaFX application.
This is the reason that you would write the code in the start() method of a JavaFX application class. There is
no requirement that the Screen class needs to be used on the JavaFX Application Thread. You could also write
the same code in the init() method of your class.

*/
public class ScreenDetails extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage stage) {
		ObservableList<Screen> screenList = Screen.getScreens();
		System.out.println("Screens Count: " + screenList.size());

		// Print the details of all screens
		for (Screen screen : screenList) {
			print(screen);
		}
		Platform.exit();
	}

	public void print(Screen s) {
		System.out.println("DPI: " + s.getDpi());

		System.out.print("Screen Bounds: ");
		Rectangle2D bounds = s.getBounds();
		print(bounds);

		System.out.print("Screen Visual Bounds: ");
		Rectangle2D visualBounds = s.getVisualBounds();
		print(visualBounds);
		System.out.println("-----------------------");
	}

	public void print(Rectangle2D r) {
		System.out.format("minX=%.2f, minY=%.2f, width=%.2f, height=%.2f%n",
				r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight());
	}
}
