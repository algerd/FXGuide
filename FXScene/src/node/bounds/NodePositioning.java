
package node.bounds;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
If you do not understand the details and the reasons behind the existence of all layout-related properties,
laying out nodes in JavaFX is as confusing as it can get. The Node class has two properties, layoutX and
layoutY, to define translation of its coordinate space along the x axis and y axis, respectively. The Node class
has translateX and translateY properties that do the same thing. The final translation of the coordinate
space of a node is the sum of the two:
    finalTranslationX = layoutX + translateX
    finalTranslationY = layoutY + translateY

Why do you have two properties to define translations of the same kind? The reason is simple. They
exist to achieve the similar results in different situations. Use layoutX and layoutY to position a node for a
stable layout. Use translateX and translateY to position a node for a dynamic layout, for example, during
animation.

It is important to keep in mind that the layoutX and layoutY properties do not specify the final position
of a node. They are translations applied to the coordinate space of the node. You need to factor the minX and
minY values of the layoutBounds when you compute the value of layoutX and layoutY to position a node at
a particular position. To position the upper left corner of the bounding box of a node at finalX and finalY,
use the following formula:
    layoutX = finalX - node.getLayoutBounds().getMinX()
    layoutY = finalY - node.getLayoutBounds().getMinY()

Tip: The Node class has a convenience method, relocate(double finalX, double finalY), to position
the node at the (finalX, finalY) location. The method computes and sets the layoutX and layoutY values
correctly, taking into account the minX and minY values of the layoutBounds. To avoid errors and misplacement
of nodes, I prefer using the relocate() method over the setLayoutX() and setLayoutY() methods.

Sometimes setting the layoutX and layoutY properties of a node may not position them at the desired
location inside its parent. If you are caught in this situation, check the parent type. Most parents, which
are the subclasses of the Region class, use their own positioning policy, ignoring the layoutX and layoutY
settings of their children. For example, HBox and VBox use their own positioning policy and they will ignore
the layoutX and layoutY values for their children.

If you want to have full control on positioning a node within its parent, use a Pane or a Group. A Pane is
a Region, which does not position its children. You will need to position the children using the layoutX and
layoutY properties.
*/
public class NodePositioning extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
        // 
        Button b1 = new Button("OK");
        b1.setLayoutX(20);
        b1.setLayoutY(20);
        
        Button b2 = new Button("Cancel");
        b2.setLayoutX(50);
        b2.setLayoutY(50);
        
        VBox vb = new VBox();
        vb.getChildren().addAll(b1, b2); 
               
        Button b3 = new Button("OK");
        b3.setLayoutX(20);
        b3.setLayoutY(120);
        
        Button b4 = new Button("Cancel");
        b4.setLayoutX(50);
        b4.setLayoutY(150);
        
        Group group = new Group(); //Or. Pane parent = new Pane();
        group.getChildren().addAll(b3, b4);
            
		VBox root = new VBox(20);
		root.getChildren().addAll(vb, group);
 
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Testing Node Positioning");
		stage.show();
			
	}
}
