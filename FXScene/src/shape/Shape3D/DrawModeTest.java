
package shape.Shape3D;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

/*
A 3D shape surface consists of many connected polygons made up of triangles. For example, a Box is made
up of 12 triangles—each side of the Box using two triangles. The drawMode property in the Shape3D class
specifies how the surface of 3D shapes is rendered. Its value is one of the constants of the DrawMode enum.
    • DrawMode.FILL
    • DrawMode.LINE

The DrawMode.FILL is the default and it fills the interior of the triangles. The DrawMode.LINE draws only
the outline of the triangles. That is, it draws only lines connecting the vertices of the consecutive triangles.
    // Create a Box with outline only
    Box box = new Box(100, 100, 100);
    box.setDrawMode(DrawMode.LINE);

The program shows how to draw only the outline of 3D shapes. The program sets the drawMode property
of all shapes to DrawMode.LINE. The program specifies the divisions of creating the Sphere and Cylinder.
Change the value for divisions to a lesser value. You will notice that the number of triangles used to create
the shapes decreases, making the shape less smooth.
*/
public class DrawModeTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a Box
		Box box = new Box(100, 100, 100);
		box.setDrawMode(DrawMode.LINE);
		box.setTranslateX(150);
		box.setTranslateY(0);
		box.setTranslateZ(400);

		// Create a Sphere: radius = 50, divisions=20
		Sphere sphere = new Sphere(50, 20);
		sphere.setDrawMode(DrawMode.LINE);
		sphere.setTranslateX(300);
		sphere.setTranslateY(-5);
		sphere.setTranslateZ(400);

		// Create a cylinder: radius=40, height=120, divisions=5
		Cylinder cylinder = new Cylinder(40, 120, 5);
		cylinder.setDrawMode(DrawMode.LINE);
		cylinder.setTranslateX(500);
		cylinder.setTranslateY(-25);
		cylinder.setTranslateZ(600);

		PointLight light = new PointLight();
		light.setTranslateX(350);
		light.setTranslateY(100);
		light.setTranslateZ(300);

		Group root = new Group(box, sphere, cylinder, light);
		
		// Craete a Scene with depth buffer enabled
		Scene scene = new Scene(root, 300, 100, true);

		// Set a camera to view the 3D shapes
		PerspectiveCamera camera = new PerspectiveCamera(false);
		camera.setTranslateX(100);
		camera.setTranslateY(-50);
		camera.setTranslateZ(300);
		scene.setCamera(camera);

		stage.setScene(scene);
		stage.setTitle("Drawing Only Lines");
		stage.show();
	}
}
