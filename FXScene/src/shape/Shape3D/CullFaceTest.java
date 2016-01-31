
package shape.Shape3D;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;

/*
Face culling is a technique of rendering 3D geometry based on the principle that the nonvisible parts of
an object should not be rendered. For example, if you are facing a building from the front, there is no need to
render the sides, top, and bottom of the building, as you cannot see them.
Face culling is used in 3D rendering to enhance performance.

The Shape3D class contains a cullFace property that specifies the type of culling applied in rendering
the shape. Its value is one of the constants of the CullFace enum:
    • BACK
    • FRONT
    • NONE

The CullFace.BACK specifies that all triangles that cannot be seen through the camera in its current
position should be culled (i.e., not rendered). That is, all triangles whose exterior faces are not facing the
camera should be culled. If you are facing the front of a building, this setting will render only the front part of
the building. This is the default.

The CullFace.FRONT specifies that the all triangles whose exterior faces are facing the camera should
be culled. If you are facing the front of a building, this setting will render all parts of the building, except the
front part.

The CullFace.NONE specifies that no face culling should be applied. That is, all triangles making up the
shape should be rendered.

*/
public class CullFaceTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
	
		Box boxFront = new Box(100, 100, 100);
        boxFront.setDrawMode(DrawMode.LINE);
		boxFront.setCullFace(CullFace.FRONT);
		boxFront.setTranslateX(150);
		boxFront.setTranslateY(0);
		boxFront.setTranslateZ(400);

		Box boxBack = new Box(100, 100, 100);
        boxBack.setDrawMode(DrawMode.LINE);
        boxBack.setCullFace(CullFace.BACK);
		boxBack.setTranslateX(300);
		boxBack.setTranslateY(-5);
		boxBack.setTranslateZ(400);

		Box boxNone = new Box(100, 100, 100);
        boxNone.setDrawMode(DrawMode.LINE);
		boxNone.setCullFace(CullFace.NONE);
		boxNone.setTranslateX(500);
		boxNone.setTranslateY(-25);
		boxNone.setTranslateZ(600);

		PointLight light = new PointLight();
		light.setTranslateX(350);
		light.setTranslateY(100);
		light.setTranslateZ(300);

		Group root = new Group(boxFront, boxBack, boxNone, light);
		
		// Craete a Scene with depth buffer enabled
		Scene scene = new Scene(root, 300, 100, true);

		// Set a camera to view the 3D shapes
		PerspectiveCamera camera = new PerspectiveCamera(false);
		camera.setTranslateX(100);
		camera.setTranslateY(-50);
		camera.setTranslateZ(300);
		scene.setCamera(camera);

		stage.setScene(scene);
		stage.setTitle("Specifying the Face Culling for Shapes");
		stage.show();
	}
}
