
package ContextMenu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
ContextMenu extends PopupControl -> PopupWindow -> Window.
ContextMenu does not extend Control and because it is not control.

ContextMenu is a pop-up control that displays a list of menu items on request. It is also known as a context
or pop-up menu. By default, it is hidden. The user has to make a request, usually by right-clicking the mouse
button, to show it. It is hidden once a selection is made. The user can dismiss a context menu by pressing the
Esc key or clicking outside its bounds.
A context menu has a usability problem. It is difficult for users to know about its existence.

An object of the ContextMenu class represents a context menu. It stores the reference of its menu items
in an ObservableList<MenuItem>. The getItems() method returns the reference of the observable list.
Menu items in a context menu could be an object of the MenuItem class or its subclasses (See package Menu).

Typically, context menus are provided for controls for accessing their commonly used features, for
example, Cut, Copy, and Paste features of text input controls. Some controls have default context menus.
The control class makes it easy to display a context menu. It has a contextMenu property. You need to set this
property to your context menu reference for the control. The following snippet of code sets the context menu
for a TextField control:
    ContextMenu ctxMenu = ...
    TextField nameFld = new TextField();
    nameFld.setContextMenu(ctxMenu);
When you right-click the TextField, your context menu will be displayed instead the default one.

Tip: A ctivating an empty context menu does not show anything. If you want to disable the default context
menu for a control, set its contextMenu property to an empty ContextMenu.

Nodes that are not controls do not have a contextMenu property. You need to use the show() method of
the ContextMenu class to display the context menu for these nodes. The show() method gives you full control
of the position where the context menu is displayed. You can use it for controls as well if you want to finetune
the positioning of the context menu. The show() method is overloaded:
    void show(Node anchor, double screenX, double screenY)
    void show(Node anchor, Side side, double dx, double dy)

The first version takes the node for which the context menu is to be displayed with the x and y
coordinates relative to the screen. Typically, you display a context menu in the mouse-clicked event where
the MouseEvent object provides you the coordinates of the mouse pointer relative to the screen through the
getScreenX() and getScreenY() methods.

The second version lets you finetune the position of the context menu relative to the specified anchor
node. The side parameter specifies on which side of the anchor node the context menu is displayed. The
possible values are one of the constants—TOP, RIGHT, BOTTOM, and LEFT—of the Side enum. The dx and dy
parameters specify the x and y coordinates, respectively, relative to the anchor node coordinate system. This
version of the show() method requires a little more explanation. (see page 583 K.Sharan)

The hide() method of the ContextMenu class hides the context menu, if it was showing. Typically, the
context menu is hidden when you select a menu item. You need to use the hide() method when the context
menu uses a custom menu item with hideOnClick property set to true.

Typically, an ActionEvent handler is added to the menu items of a context menu. The ContextMenu
class contains an onAction property, which is an ActionEvent handler. The ActionEvent handler, if set,
for a ContextMenu is called every time a menu item is activated. You can use this ActionEvent to execute a
follow-up action when a menu item is activated.

The program shows how to use a context menu. It displays a Label and a Canvas.
When you right-click the canvas, a context menu with three menu items—Rectangle, Circle, and Ellipse—is
displayed. Selecting one of the shapes from the menu items draws the shape on the canvas. The context
menu is displayed when the mouse pointer is clicked.
*/
public class ContextMenuTest extends Application {
	// A canvas to draw shapes
	Canvas canvas = new Canvas(200, 200);

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Add mouse click event handler to the canvas to show the context menu
		canvas.setOnMouseClicked(e -> showContextMenu(e));

		BorderPane root = new BorderPane();  
		root.setTop(new Label("Right click below to display a context menu."));
		root.setCenter(canvas);        
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Context Menus");
		stage.show();
	}

	public void showContextMenu(MouseEvent me) {
		// Show menu only on right click
		if (me.getButton() == MouseButton.SECONDARY) { 
            
			MenuItem rectItem = new MenuItem("Rectangle");
			MenuItem circleItem = new MenuItem("Circle");
			MenuItem ellipseItem = new MenuItem("Ellipse");
            
			rectItem.setOnAction(e -> draw("Rectangle"));
			circleItem.setOnAction(e -> draw("Circle"));
			ellipseItem.setOnAction(e -> draw("Ellipse")); 
            
			ContextMenu ctxMenu = new ContextMenu(rectItem, circleItem, ellipseItem);
			ctxMenu.show(canvas, me.getScreenX(), me.getScreenY());
		}
	}

	public void draw(String shapeType) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, 200, 200); // clear the canvas first
		gc.setFill(Color.TAN);

		if (shapeType.equals("Rectangle")) {
			gc.fillRect(0, 0, 200, 200);
		} else if (shapeType.equals("Circle")) { 
			gc.fillOval(0, 0, 200, 200); 
		} else if (shapeType.equals("Ellipse")) {
			gc.fillOval(10, 40, 180, 120);
		}
	}
}
