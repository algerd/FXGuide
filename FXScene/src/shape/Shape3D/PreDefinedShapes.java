
package shape.Shape3D;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

/*
JavaFX 8 provides the following three built-in 3D geometric shapes:
    • Box
    • Sphere
    • Cylinder

All shapes are nodes. Therefore, you can apply transformations to them. 
You can position them at any point in the 3D space using the translateX, translateY, and translateZ transformations
Tip: The center of a 3D shape is located at the origin of the local coordinate system of the shape.

A Box is defined by the following three properties:
    • width
    • height
    • depth
The Box class contains two constructors:
    • Box()
    • Box(double width, double height, double depth)

A Sphere is defined by only one property named radius. The Sphere class contains three constructors:
    • Sphere()
    • Sphere(double radius)
    • Sphere(double radius, int divisions)
The no-args constructor creates a sphere of radius 1.0.
The second constructor lets you specify the radius of the sphere.
The third constructor lets you specify the radius and divisions. A 3D sphere is made up of many
divisions, which are constructed from connected triangles. The value of the number of divisions defines the
resolution of the sphere. The higher the number of divisions, the smoother the sphere looks. By default, a
value of 64 is used for the divisions. The value of divisions cannot be less than 1.

A Cylinder is defined by two properties:
    • radius
    • height
The radius of the cylinder is measured on the XZ plane. The axis of the cylinder is measured along the
y-axis. The height of the cylinder is measured along its axis. The Cylinder class contains three constructors:
    • Cylinder()
    • Cylinder(double radius, double height)
    • Cylinder(double radius, double height, int divisions)
The no-args constructor creates a Cylinder with a 1.0 radius and a 2.0 height.
The second constructor lets you specify the radius and height properties.
The third constructor lets you specify the number of divisions, which defines the resolution of the
cylinder. The higher the number of divisions, the smoother the cylinder looks. Its default value is 15 along the
x-axis and z-axis each. Its value cannot be less than 3. If a value less than 3 is specified, a value of 3 is used. Note
that the number of divisions does not apply along the y-axis. Suppose the number of divisions is 10. It means
that the vertical surface of the cylinder is created using 10 triangles. The height of the triangle will extend the
entire height of the cylinder. The base of the cylinder will be created using 10 triangles.

The program creates the three shapes and positions them in the space. It creates a light, which is an
instance of the PointLight, and positions it in the space. Note that a light is also a Node. The light is used to
light the 3D shapes. All shapes and the light are added to a group, which is added to the scene.

To view the shapes, you need to add a camera to the scene. The program adds a PerspectiveCamera to
the scene. Note that you need to position the camera as its position and orientation in the space determine
what you see. The origin of the local coordinate system of the camera is located at the center of the scene.
Try resizing the window after you run the program. You will notice that the view of the shapes changes as
you resize the window. It happens because the center of the scene is changing when you resize the window,
which in turn repositions the camera, resulting in the change in the view.
*/
public class PreDefinedShapes extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a Box
		Box box = new Box(100, 100, 100);
		box.setTranslateX(150);
		box.setTranslateY(0);
		box.setTranslateZ(400);
		
		// Create a Sphere
		Sphere sphere = new Sphere(50);
		sphere.setTranslateX(300);
		sphere.setTranslateY(-5);
		sphere.setTranslateZ(400);

		// Create a cylinder
		Cylinder cylinder = new Cylinder(40, 120);
		cylinder.setTranslateX(500);
		cylinder.setTranslateY(-25);
		cylinder.setTranslateZ(600);

		// Create a light
		PointLight light = new PointLight();
		light.setTranslateX(350);
		light.setTranslateY(100);
		light.setTranslateZ(300);

		// Add shapes and a light to the group
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
		stage.setTitle("Using 3D Shapes: Box, Sphere and Cylinder");
		stage.show();
	}
}
