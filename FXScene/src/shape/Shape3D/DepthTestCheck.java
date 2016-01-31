
package shape.Shape3D;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.DepthTest;
import javafx.stage.Stage;

/*
You need to do two things:
    • When creating a Scene object, specify that it needs to have a depth buffer.
    • Specify in the nodes that their z coordinate values should be used during
    rendering. That is, they need to be rendered according to their depth (the distance
    from the viewer).

The depthBuffer flag for a scene cannot be changed after the scene is created. You can check whether a
scene has a depthBuffer using the isDepthBuffer() method of the Scene object.
The Node class contains a depthTest property, which is available for all nodes in JavaFX. Its value is one
of the constants of the javafx.scene.DepthTest enum:
    • ENABLE
    • DISABLE
    • INHERIT

The ENABLE value for the depthTest indicates that the z coordinate values should be taken into account
when the node is rendered. When the depth testing is enabled for a node, its z coordinate is compared with
all other nodes with depth testing enabled, before rendering.

The DISABLE value indicates that the nodes are rendered in the order they are added to the scene graph.
The INHERIT value indicates that the depthTest property for a node is inherited from its parent. If a
node has null parent, it is the same as ENABLE.

The program demonstrates the concepts of using the depth buffer for a scene and the
depth test for nodes. It adds two rectangles to a group. The rectangles are filled with red and green colors.
The z coordinates for the red and green rectangles are 400px and 300px, respectively. The green rectangle is
added to the group first. However, it is rendered first as it is closer to the viewer. You have added a camera to
the scene, which is needed to view objects having depth (the z coordinate). The CheckBox is used to enable
and disable the depth test for the rectangles. When the depth test is disabled, the rectangles are rendered in
the order they are added to the group: the green rectangle followed with the red rectangle.
*/
public class DepthTestCheck  extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create two rectangles and add then to a Group
		Rectangle red = new Rectangle(100, 100);
		red.setFill(Color.RED);
		red.setTranslateX(100);
		red.setTranslateY(100);
		red.setTranslateZ(400);

		Rectangle green = new Rectangle(100, 100);
		green.setFill(Color.GREEN);
		green.setTranslateX(150);
		green.setTranslateY(150);
		green.setTranslateZ(300);

		Group center = new Group(green, red);

		CheckBox depthTestCbx = new CheckBox("DepthTest for Rectangles");
		depthTestCbx.setSelected(true);	
		depthTestCbx.selectedProperty().addListener(
			(prop, oldValue, newValue) -> {
                if (newValue) {
                    red.setDepthTest(DepthTest.ENABLE);
                    green.setDepthTest(DepthTest.ENABLE);
                }
                else {
                    red.setDepthTest(DepthTest.DISABLE);
                    green.setDepthTest(DepthTest.DISABLE);
                }
            }
        );

		// Create a BorderPane as the root node for the scene. Need to 
		// set the background transparent, so the cemera can view the
		// rectangles behind the surface of the BorderPane
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: transparent;");	
		root.setTop(depthTestCbx);
		root.setCenter(center);

		// Craete a scene with depthBuffer enabled
		Scene scene = new Scene(root, 200, 200, true);

		// Need to set a camera to look into the 3D space of the scene
		scene.setCamera(new PerspectiveCamera());

		stage.setScene(scene);
		stage.setTitle("Depth Test");
		stage.show();
	}
}
