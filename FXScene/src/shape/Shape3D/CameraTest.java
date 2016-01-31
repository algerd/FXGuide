
package shape.Shape3D;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
Cameras are used to render the scene. Two types of cameras are available.
    • Perspective camera
    • Parallel camera
The names of the cameras suggest the projection type they use to render the scene. Cameras in JavaFX
are nodes. They can be added to the scene graph and positioned like other nodes.
The abstract base class Camera represents a camera. Two concrete subclasses of the Camera class exist:
PerspectiveCamera and ParallelCamera. The three classes are in the javafx.scene package.

The content that the camera will project onto the projection plane is defined by two properties in the
Camera class.
    • nearClip
    • farClip

The nearClip is the distance between the camera and the near clipping plane. Objects closer to the
camera than the nearClip are not rendered. The default value is 0.1.
The farClip is the distance between the camera and the far clipping plane. Objects farther from the
camera than the farClip are not rendered. The default value is 100.
The PerspectiveCamera class contains two constructors.
    • PerspectiveCamera()
    • PerspectiveCamera(boolean fixedEyeAtCameraZero)

The no-args constructor creates a PerspectiveCamera with the fixedEyeAtCameraZero flag set to false,
which makes it behave more or less like a parallel camera where the objects in the scene at Z=0 stay the
same size when the scene is resized. The second constructor lets you specify this flag. If you want to view 3D
objects with real 3D effects, you need to set this flag to true. Setting this flag to true will adjust the size of the
projected images of the 3D objects as the scene is resized. Making the scene smaller will make the objects
look smaller as well.

The PerspectiveCamera class declares two additional properties.
    • fieldOfView
    • verticalFieldOfView
The fieldOfView is measured in degrees and it is the view angle of the camera. Its default value is
30 degrees.

The verticalFieldOfView property specifies whether the fieldOfView property applies to the vertical
dimension of the projection plane. By default, its value is true.

An instance of the ParallelCamera specifies the viewing volume for a parallel projection, which is a
rectangular box. The ParallelCamera class does not declare any additional properties. It contains a no-args
constructor.

You can move and rotate the camera as you move and rotate nodes. To move it to a different position,
use the translateX, translateY, and translateZ properties. To rotate, use the Rotate transformation.

-------------------------------------
Using Light Sources:

Similar to the real world, you need a light source to view the 3D objects in a scene. An instance of the
abstract base class LightBase represents a light source. Its two concrete subclasses, AmbientLight and
PointLight, represent an ambient light and a point light. Light source classes are in the javafx.scene
package. The LightBase class inherits from the Node class. Therefore, a light source is a node and it can be
added to the scene graph as any other nodes.

A light source has three properties: light color, on/off switch, and a list of affected nodes. The LightBase
class contains the following two properties:
    • color
    • lightOn

The color specifies the color of the light. The lightOn specifies whether the light is on. The getScope()
method of the LightBase class returns an ObservableList<Node>, which is the hierarchical list of nodes
affected by this light source. If the list is empty, the scope of the light source is universe, which means that it
affects all nodes in the scene.

An instance of the AmbientLight class represents an ambient light source. An ambient light is a
nondirectional light that seems to come from all directions. Its intensity is constant on the surface of the
affected shapes.
    AmbientLight redLight = new AmbientLight(Color.RED);

An instance of the PointLight class represents a point light source. A point light source is a fixed point
in space and radiates lights equally in all directions. The intensity of a point light decreases as the distance of
the of the lighted point increases from the light source.
--------------------------------------------------------------

The program uses a PerspectiveCamera to view a Box. You have used two lights: one
to light the front and the top faces and one to light the bottom face of the box. The camera is animated by
rotating it indefinitely along the x-axis. As the camera rotates, it brings different parts of the box into the view.
You can see the effect of the two lights when the bottom of the box comes into the view. The bottom is shown
in green whereas the top and front are in red.
*/
public class CameraTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Box box = new Box(100, 100, 100);
		box.setCullFace(CullFace.NONE);
		box.setTranslateX(250);
		box.setTranslateY(100);
		box.setTranslateZ(400);

		PerspectiveCamera camera = new PerspectiveCamera(false);
		camera.setTranslateX(100);
		camera.setTranslateY(-50);
		camera.setTranslateZ(300);

		// Add a Rotation animation to the camera
		RotateTransition rt = new RotateTransition(Duration.seconds(2), camera);
		rt.setCycleCount(Animation.INDEFINITE);
		rt.setFromAngle(0);
		rt.setToAngle(90);
		rt.setAutoReverse(true);
		rt.setAxis(Rotate.X_AXIS);
		rt.play();

		PointLight redLight = new PointLight();
		redLight.setColor(Color.RED);
		redLight.setTranslateX(250);
		redLight.setTranslateY(-100);
		redLight.setTranslateZ(250);

		PointLight greenLight = new PointLight();
		greenLight.setColor(Color.GREEN);
		greenLight.setTranslateX(250);
		greenLight.setTranslateY(300);
		greenLight.setTranslateZ(300);

		Group root = new Group(box, redLight, greenLight);
		root.setRotationAxis(Rotate.X_AXIS);
		root.setRotate(30);

		Scene scene = new Scene(root, 500, 300, true);
		scene.setCamera(camera);
		stage.setScene(scene);
		stage.setTitle("Using camaras");
		stage.show();
	}
}
