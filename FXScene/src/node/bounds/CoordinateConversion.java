
package node.bounds;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/*
The following transformations of a Bounds or a point are supported:
    • Local to parent:
        The localToParent() method transforms a Bounds or a point in the local coordinate space of a node to
        the coordinate space of its parent.
    • Local to scene:
        The localToScene() method transforms a Bounds or a point in the local
        coordinate space of a node to the coordinate space of its scene.
    • Parent to local:
        The parentToLocal() method transforms a
        Bounds or a point in the coordinate space of the parent of a node to the local coordinate space of the node.
    • Scene to local:
        The sceneToLocal() method transforms a Bounds or a point in the coordinate space of the scene of a node
        to the local coordinate space of the node. 

All methods have three overloaded versions; one version takes a Bounds as an argument and returns the transformed Bounds; 
another version takes a Point2D as an argument and returns the transformed Point2D; 
another version takes the x and y coordinates of a point and returns the transformed Point2D.

These methods are sufficient to transform the coordinate of a point in one coordinate space to
another within a scene graph. Sometimes you may need to transform the coordinates of a point in the local
coordinate space of a node to the coordinate space of the stage or screen. You can achieve this using the x
and y properties of the Scene and Stage classes. The (x, y) properties of a scene define the coordinates of
the top left corner of the scene in the coordinate space of its stage. The (x, y) properties of a stage define the
coordinates of the top left corner of the stage in the coordinate space of the screen. For example, if (x1, y1) is
a point in the coordinate space of the scene, (x1 + x2, y1 + y2) defines the same point in the coordinate space
of the state, where x2 and y2 are the x and y properties of the stage, respectively. Apply the same logic to get
the coordinate of a point in the coordinate space of the screen.

The program has a scene consisting of three Labels and TextFields. A pair of a Label and a TextField
is placed in an HBox. All HBoxes are placed in a VBox. An unmanaged Circle is placed in the VBox. The
program adds a change listener to the focusOwner property of the scene to track the focus change. When the
focus changes, the circle is placed at the top left corner of the node that has the focus.
The placeMarker() contains the main logic.
*/
public class CoordinateConversion extends Application {

	// An instance variable to store the reference of the circle
	private Circle marker;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TextField fName = new TextField();
		TextField lName = new TextField();
		TextField salary = new TextField();

		// The Circle node is unmanaged
		marker = new Circle(5);
		marker.setManaged(false);
		marker.setFill(Color.RED);
		marker.setMouseTransparent(true);

		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		HBox hb3 = new HBox();
		hb1.getChildren().addAll(new Label("First Name:"), fName);
		hb2.getChildren().addAll(new Label("Last Name:"), lName);
		hb3.getChildren().addAll(new Label("Salary:"), salary);

		VBox root = new VBox();
		root.getChildren().addAll(hb1, hb2, hb3, marker);

		Scene scene = new Scene(root);

		// Add a focus change listener to the scene
		scene.focusOwnerProperty().addListener((prop, oldNode, newNode) -> placeMarker(newNode));

		stage.setScene(scene);
		stage.setTitle("Coordinate Space Transformation");
		stage.show();
	}

	public void placeMarker(Node newNode) {
        // It gets the (x, y) coordinates of the top left corner of the
        // bounding box of the node in focus in the local coordinate space:
		double nodeMinX = newNode.getLayoutBounds().getMinX();
		double nodeMinY = newNode.getLayoutBounds().getMinY();
        
        // It transforms the coordinates of the top left corner of the node from the local coordinate space to the
        //coordinate space of the scene:
		Point2D nodeInScene = newNode.localToScene(nodeMinX, nodeMinY);
        
        // Now the coordinates of the top left corner of the node are transformed from the coordinate space of the
        // scene to the coordinate space of the circle, which is named marker in the program:
		Point2D nodeInMarkerLocal = marker.sceneToLocal(nodeInScene);
        
        // Finally, the coordinate of the top left corner of the node is transformed to the coordinate space of the
        // parent of the circle:
		Point2D nodeInMarkerParent = marker.localToParent(nodeInMarkerLocal);
		
		// Position the circle approperiately
		marker.relocate(nodeInMarkerParent.getX()
				+ marker.getLayoutBounds().getMinX(),
				nodeInMarkerParent.getY()
				+ marker.getLayoutBounds().getMinY());
	}
}
