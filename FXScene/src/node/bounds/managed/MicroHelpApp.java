
package node.bounds.managed;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
The Node class has a managed property, which is of type BooleanProperty. By default, all nodes are
managed. The laying out of a managed node is managed by its parent. A Parent node takes into account
the layoutBounds of all its managed children when it computes its own size. A Parent node is responsible
for resizing its managed resizable children and positioning them according to its layout policy. When the
layoutBounds of a managed child changes, the relevant part of the scene graph is relaid out.

If a node is unmanaged, the application is solely responsible for laying it out (computing its size and
position). That is, a Parent node does not lay out its unmanaged children. Changes in the layoutBounds of
an unmanaged node do not trigger the relayout above it. An unmanaged Parent node acts as a layout root. If
a child node calls the Parent.requestLayout() method, only the branch rooted by the unmanaged Parent
node is relaid out.

Tip: Contrast the visible property of the Node class with its managed property. A Parent node takes into
account the layoutBounds of all its invisible children for layout purposes and ignores the unmanaged children.

Listing demonstrates how to use unmanaged nodes. It uses an unmanaged Text node to display a micro help when a node has the focus.
The node needs to have a property named "microHelpText". When the micro help is shown, the layout for the
entire application is not disturbed as the Text node to show the micro help is an unmanaged node. You place
the node at an appropriate position in the focusChanged() method. The program registers a change listener to
the focusOwner property of the scene, so you show or hide the micro help Text node when the focus inside the
scene changes.
*/
public class MicroHelpApp extends Application {
	// An instance variable to store the Text node reference
	private Text helpText = new Text();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TextField fName = new TextField();
		TextField lName = new TextField();
		TextField salary = new TextField();

		Button closeBtn = new Button("Close");
		closeBtn.setOnAction(e -> Platform.exit());

        /*
        Every node maintains an observable map of user-defined properties (key/value pairs). You can use it to store
        any useful information. The getProperties() method of the Node class returns an ObservableMap<Object, Object> in
        which you can add or remove properties for the node.
        
        The Node class has two convenience methods, setUserData(Object value) and getUserData(), to
        store a user-defined value as a property for a node. The value specified in the setUserData() method uses
        the same ObservableMap to store the data that are returned by the getProperties() method. The Node class
        uses an internal Object as the key to store the value. You need to use the getUserData() method to get the
        value that you store using the setUserData() method, as follows:
            nameField.setUserData("Saved"); // Set the user data
            ...
            String userData = (String)nameField.getUserData(); // Get the user data
        
        Tip: You cannot access the user data of a node directly except by using the getUserData() method.
        Because it is stored in the same ObservableMap returned by the getProperties() method, you can get to it
        indirectly by iterating through the values in that map.
        */
		fName.getProperties().put("microHelpText", "Enter the first name");
		lName.getProperties().put("microHelpText", "Enter the last name");
		salary.getProperties().put("microHelpText", "Enter a salary greater than $2000.00.");

		// The help text node is unmanaged
		helpText.setManaged(false);
		helpText.setTextOrigin(VPos.TOP);
		helpText.setFill(Color.RED);
		helpText.setFont(Font.font(null, 9));
		helpText.setMouseTransparent(true);
		
		// Add all nodes to a GridPane
		GridPane root = new GridPane();

		root.add(new Label("First Name:"), 1, 1);
		root.add(fName, 2, 1);
		root.add(new Label("Last Name:"), 1, 2);
		root.add(lName, 2, 2);

		root.add(new Label("Salary:"), 1, 3);
		root.add(salary, 2, 3);
		root.add(closeBtn, 3, 3);
		root.add(helpText, 4, 3);

		Scene scene = new Scene(root, 300, 100);

		// Add a change listener to the scene, so we know when the focus owner changes and display the micro help	
		scene.focusOwnerProperty().addListener(
			(ObservableValue<? extends Node> value, Node oldNode, Node newNode) 
				-> focusChanged(value, oldNode, newNode));
		stage.setScene(scene);
		stage.setTitle("Showing Micro Help");
		stage.show();
	}

	public void focusChanged(ObservableValue<? extends Node> value, Node oldNode, Node newNode) {
		// Focus has changed to a new node
		String microHelpText = (String)newNode.getProperties().get("microHelpText");
		
		if (microHelpText != null && microHelpText.trim().length() > 0)  {
			helpText.setText(microHelpText);
			helpText.setVisible(true);

			// Position the help text node
			double x = newNode.getLayoutX() + 
			           newNode.getLayoutBounds().getMinX() - 
			           helpText.getLayoutBounds().getMinX();
			double y = newNode.getLayoutY() + 
			           newNode.getLayoutBounds().getMinY() + 
			           newNode.getLayoutBounds().getHeight() - 
			           helpText.getLayoutBounds().getMinX();

			helpText.setLayoutX(x);
			helpText.setLayoutY(y);
			helpText.setWrappingWidth(newNode.getLayoutBounds().getWidth());
		}
		else {
			helpText.setVisible(false);
		}
	}
}
