
package node.bounds;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
The layoutBounds property is computed based on the geometric properties of the node in the untransformed
local coordinate space of the node. Effects, clip, and transformations are not included. Different rules,
depending on the resizable behavior of the node, are used to compute the coordinates of the upper left corner
of the bounding box described by the layoutBounds:

    • For a resizable node (a Region, a Control, and a WebView), the coordinates for the
        upper left corner of the bounding box are always set to (0, 0). For example,
        the (minX, minY) values in the layoutBounds property are always (0, 0) for a button.
    • For a nonresizable node (a Shape, a Text, and a Group), the coordinates of the upper
        left corner of the bounding box are computed based on the geometric properties. For
        a shape (a rectangle, a circle, etc.) or a Text, you can specify the (x, y) coordinates
        of a specific point in the node relative to the untransformed coordinate space of
        the node. For example, for a rectangle, you can specify the (x, y) coordinates of the
        upper left corner, which become the (x, y) coordinates of the upper left corner of the
        bounding box described by its layoutBounds property. For a circle, you can specify
        the centerX, centerY, and radius properties, where centerX and centerY are the x
        and y coordinates of the center of the circle, respectively. The (x, y) coordinates of
        the upper left corner of the bounding box described by the layoutBounds for a circle
        are computed as (centerX - radius, centerY - radius).

The width and height in layoutBounds are the width and height of the node. Some nodes let you set
their width and height; but some compute them automatically for you and let you override them.

Where do you use the layoutBounds property of a node? Containers allocate spaces to lay out child
nodes based on their layoutBounds. Let’s look at an example as shown in Listing. It displays four
buttons in a VBox. The first button has a drop shadow effect. The third button has a drop shadow effect and a
30-degree rotation transformation. The second and the fourth buttons have no effect or transformation.
The output shows that irrespective of the effect and transformation,
all buttons have the same layoutBounds values. The size (width and height) in the layoutBounds objects for
all buttons is determined by the text of the button and the font, which is the same for all buttons. The output
may differ on your platform.

Tip: The layoutBounds of a node is computed based on the geometric properties of a node. Therefore,
you should not bind such properties of a node to an expression that includes the layoutBounds of the node.
*/
public class LayoutBoundsTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button b1 = new Button("Close");
		b1.setEffect(new DropShadow());

		Button b2 = new Button("Close");

		Button b3 = new Button("Close");
		b3.setEffect(new DropShadow());
		b3.setRotate(30);

		Button b4 = new Button("Close");

		VBox root = new VBox();
		root.getChildren().addAll(b1, b2, b3, b4);
        /*
        Sometimes you may want to include the space needed to show the effects and transformations of a
        node in its layoutBounds. The solution for this is easy. You need to wrap the node in a Group and the Group
        in a container. Now the container will query the Group for its layoutBounds. The layoutBounds of a Group is
        the union of the boundsInParent for all its children. Recall that the boundsInParent of a node
        includes the space needed for showing effects and transformation of the node. If you change the statement
        */
        //root.getChildren().addAll(new Group(b1), b2, new Group(b3), b4);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Testing LayoutBounds");
		stage.show();

		System.out.println("b1=" + b1.getLayoutBounds());
		System.out.println("b2=" + b2.getLayoutBounds());
		System.out.println("b3=" + b3.getLayoutBounds());
		System.out.println("b4=" + b4.getLayoutBounds());
        
        Bounds b1Bound = b1.getLayoutBounds();
        System.out.println("getHeight()=" + b1Bound.getHeight());
        System.out.println("getWidth()=" + b1Bound.getWidth());
        System.out.println("contains(b2.getLayoutBounds())=" + b1Bound.contains(b2.getLayoutBounds()));
        System.out.println("intersects(b2.getLayoutBounds())=" + b1Bound.intersects(b2.getLayoutBounds()));
	}
}
