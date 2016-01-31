
package stage;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*
The bounds of a stage consist of four properties: x, y, width, and height. The x and y properties determine
the location (or position) of the upper-left corner of the stage. The width and height properties determine
its size. In this section, you will learn how to position and size a stage on the screen. You can use the getters
and setters for these properties to get and set their values.

Rules of stage bounds:
1) Stage has no scene, its bounds are determined by the platform.
2) Stage has a scene with no visual nodes, its bounds are determined by the platform.
    In this case, the size of the scene is not specified.
3) Stage has a scene with some visual nodes, its bounds are determined by the visual
    nodes in the scene. In this case, the size of the scene is not specified and the stage is
    centered in the screen.
4) Stage has a scene and the size of the scene is specified, its bounds are determined by the
    specified size of the scene. The stage is centered on the screen.

The default centering of a stage centers it horizontally on the screen. The y coordinate of the upper-left
corner of the stage is one-third of the height of the screen minus the height of the stage. This is the logic used
in the centerOnScreen() method in the Window class.

If you specify the size of the stage but not its position, the stage is sized according the set size and
centered on the screen, irrespective of the presence of a scene and the size of the scene. If you specify the
position of the stage (x, y coordinates), it is positioned accordingly.

Tip: I f you want to set the width and height of a stage to fit the content of its scene, use the sizeToScene()
method of the Window class. The method is useful if you want to synchronize the size of a stage with the size
of its scene after modifying the scene at runtime. Use the centerOnScreen() method of the Window class to
center the stage on the screen.
*/
public class StageBounds extends Application {
    
	public static void main(String[] args) {
		Application.launch(args); 
	}

	@Override
	public void start(Stage stage) {
        
		stage.setTitle("A Sized Stage with a Sized Scene");
        Group root = new Group(new Button("Hello"));
        // Размеры всего окна(stage) = Размеры внутреннего окна(scene) + размеры заголовка и границ всего окна
        Scene scene = new Scene(root, 300, 100);
        stage.setScene(scene);
        // Задать явно размеры всего окна(stage). Они не будут увеличены, чтобы поместить содержимое Scene.     
        stage.setWidth(50);
        stage.setHeight(50);
        stage.show();	
        
        // If you want to center a stage on the screen horizontally as well as vertically, use the following logic:
        // Wrong!!!! Use the logic shown below after the stage.show() call
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = bounds.getMinX() + (bounds.getWidth() - stage.getWidth())/2.0;
        double y = bounds.getMinY() + (bounds.getHeight() - stage.getHeight())/2.0;
        stage.setX(x);
        stage.setY(y);
        
	}
}
