
package shape.Shape3D;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
A scene can use only one camera. Sometimes, you may want to view different parts of a scene using multiple
cameras. A subscene is a container for a scene graph. It can have its own width, height, fill color, depth buffer, 
antialiasing flag, and camera. An instance of the SubScene class represents a subscene. The SubScene inherits from the Node class. 
Therefore, a subscene can be used wherever a node can be used. A subscene can be used to separate 2D and 3D nodes in an application. You
can use a camera for the subscene to view 3D objects that will not affect the 2D nodes in the other part of the
main scene. The following snippet of code creates a SubScene and sets a camera to it:
    SubScene ss = new SubScene(root, 200, 200, true, SceneAntialiasing.BALANCED);
    PerspectiveCamera camera = new PerspectiveCamera(false);
    ss.setCamera(camera);

Tip: If a SubScene contains Shape3D nodes having a light node, a head light with a PointLight with
Color.WHITE light source is provided. The head light is positioned at the camera position.

The program shows how to use subscenes. The getSubScene() method creates a SubScene
with a Box, a PerspectiveCamera, and a PointLight. An animation is set up to rotate the camera along the
specified axis. The start() method creates two subscenes and adds them to an HBox. One subscene swings the
camera along the y-axis and another along the x-axis. The HBox is added to the main scene.
*/
public class SubSceneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		SubScene ySwing = getSubScene(Rotate.Y_AXIS);
		SubScene xSwing = getSubScene(Rotate.X_AXIS);
		HBox root = new HBox(20, ySwing, xSwing);
		Scene scene = new Scene(root, 500, 300, true);
		stage.setScene(scene);
		stage.setTitle("Using Sub-Scenes");
		stage.show();
	}

	private SubScene getSubScene(Point3D rotationAxis) {
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
		rt.setFromAngle(-10);
		rt.setToAngle(10);
		rt.setAutoReverse(true);
		rt.setAxis(rotationAxis);
		rt.play();

		PointLight redLight = new PointLight(Color.RED);
		redLight.setTranslateX(250);
		redLight.setTranslateY(-100);
		redLight.setTranslateZ(290);

		// If you remove the redLight from the following group, 
		// a default head light will be provided by the SubScene.
		Group root = new Group(box, redLight);
		root.setRotationAxis(Rotate.X_AXIS);
		root.setRotate(30);
		
		SubScene ss = new SubScene(root, 200, 200, true, SceneAntialiasing.BALANCED);
		ss.setCamera(camera);
		return ss;
	}
}
