
package Scroll;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
A ScrollPane provides a scrollable view of a node. A ScrollPane consists of a horizontal ScrollBar, a
vertical ScrollBar, and a content node. The node for which the ScrollPane provides scrolling is the content
node. If you want to provide a scrollable view of multiple nodes, add them to a layout pane, for example, a
GridPane, and then, add the layout pane to the ScrollPane as the content node. ScrollPane uses a scroll
policy to specify when to show a specific scrollbar. The area through which the content is visible is known as
viewport.

Tip: Some of the commonly used controls that need scrolling capability, for example, a TextArea, provide
a built-in ScrollPane, which is part of such controls.

You can use the constructors of the ScrollPane class to create an empty ScrollPane or a ScrollPane
with a content node.

Tip: The ScrollPane provides the scrolling for its content based on the layout bounds of the content. If the
content uses effects or transformation, for example, scaling, you need to wrap the content in a Group and add
the Group to the ScrollPane get proper scrolling.

The ScrollPane class contains several properties, most of which are commonly not used by developers:
    • content
    • pannable
    • fitToHeight
    • fitToWidth
    • hbarPolicy
    • vbarPolicy
    • hmin
    • hmax
    • hvalue
    • vmin
    • vmax
    • vvalue
    • prefViewportHeight
    • prefViewportWidth
    • viewportBounds

The content property is an object property of the Node type and it specifies the content node. You can
scroll the content using the scrollbars or by panning. If you use panning, you need to drag the mouse while
left, right, or both buttons are pressed to scroll the content. By default, a ScrollPane is not pannable and you
need to use the scrollbars to scroll through the content. The pannable property is a boolean property that
specifies whether the ScrollPane is pannable. Use the setPannable(true) method to make a ScrollPane
pannable.

The fitToHeight and fitToWidth properties specify whether the content node is resized to match the
height and width of the viewport, respectively. By default, they are false. These properties are ignored if the
content node is not resizable. 

The hbarPolicy and vbarPolicy properties are object properties of the ScrollPane.ScrollBarPolicy
enum type. They specify when to show the horizontal and vertical scrollbars. The possible values are ALWAYS,
AS_NEEDED, and NEVER. When the policy is set to ALWAYS, the scrollbar is shown all the time. When the policy
is set to AS_NEEDED, the scrollbar is shown when required based on the size of the content. When the policy is
set to NEVER, the scrollbar is never shown.

The hmin, hmax, and hvalue properties specify the min, max, and value properties of the horizontal
scrollbar, respectively. The vmin, vmax, and vvalue properties specify the min, max, and value properties
of the vertical scrollbar, respectively. Typically, you do not set these properties. They change based on the
content and as the user scrolls through the content.

The prefViewportHeight and prefViewportWidth are the preferred height and width, respectively, of
the viewport that is available to the content node.

The viewportBounds is an object property of the Bounds type. It is the actual bounds of the viewport.
The program shows how to use a ScrollPane. It sets a Label with four lines of text as its
content. It also makes the ScrollPane pannable. That is, you can drag the mouse clicking its button to scroll
through the text.

The default CSS style-class name for a ScrollPane control is scroll-pane. Please refer to the
modena.css file for sample styles and the online JavaFX CSS Reference Guide for the complete list of CSS
properties and pseudo-classes supported by the ScrollPane.
*/
public class ScrollPaneTest extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		Label poemLbl = new Label("I told her this; her laughter light\n" + 
		                         "Is ringing in my ears;\n" + 
		                         "And when I think upon that night\n" + 
		                         "My eyes are dim with tears.");

		// Create a scroll pane with poemLbl as its content
		ScrollPane sPane = new ScrollPane(poemLbl);
		sPane.setPannable(true);

		HBox root = new HBox(sPane);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using ScrollPane Controls");
		stage.show();
	}
}
