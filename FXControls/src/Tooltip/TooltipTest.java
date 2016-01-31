
package Tooltip;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
A tool tip is a pop-up control used to show additional information about a node. It is displayed when a
mouse pointer hovers over the node. There is a small delay between when the mouse pointer hovers over a
node and when the tool tip for the node is shown. The tool tip is hidden after a small period. It is also hidden
when the mouse pointer leaves the control. You should not design a GUI application where the user depends
on seeing tool tips for controls, as they may not be shown at all if the mouse pointer never hovers over the
controls.

A tool tip is represented by an instance of the Tooltip class, which inherits from the PopupControl
class( extends PopupWindow extends Window) . A tool tip can have text and a graphic. You can create a tool tip using its default constructor, which
has no text and no graphic. You can also create a tool tip with text using the other constructor, as in the
following code:
    Tooltip tooltip1 = new Tooltip();
    Tooltip tooltip2 = new Tooltip("Closes the window");

A tool tip needs to be installed for a node using the install() static method of the Tooltip class.
Use the uninstall() static method to uninstalled a tool tip for a node:
    Button saveBtn = new Button("Save");
    Tooltip tooltip = new Tooltip("Saves the data");
    // Install a tooltip
    Tooltip.install(saveBtn, tooltip);
    // Uninstall the tooltip
    Tooltip.uninstall(saveBtn, tooltip);

Tool tips are frequently used for UI controls. Therefore, installing tool tips for controls has been made
easier. The Control class contains a tooltip property, which is an object property of the Tooltip type.
You can use the setTooltip() method of the Control class to set a Tooltip for controls. If a node is not a
control, for example, a Circle node, you will need to use the install() method to set a tool tip.

Tip: A tool tip can be shared among multiple nodes. A tool tip uses a Label control to display its text and
graphic. Internally, all content-related properties set on a tool tip are delegated to the Label control.

The Tooltip class contains several properties:
    • text
    • graphic
    • contentDisplay
    • textAlignment
    • textOverrun
    • wrapText
    • graphicTextGap
    • font
    • activated
The text property is a String property, which is the text to be displayed in the tool tip. The graphic
property is an object property of the Node type. It is an icon for the tool tip. The contentDisplay property
is an object property of the ContentDisplay enum type. It specifies the position of the graphic relative to
the text. The possible value is one of the constants in the ContentDisplay enum: TOP, RIGHT, BOTTOM, LEFT,
CENTER, TEXT_ONLY, and GRAPHIC_ONLY. The default value is LEFT, which places the graphic left to the text.

The textAlignment property is an object property of the TextAlignment enum type. If specifies
the text alignment when the text spans multiple lines. The possible value is one of the constants in the
TextAlignment enum: LEFT, RIGHT, CENTER, and JUSTIFY.

The textOverrun property is an object property of the OverrunStyle enum type. It specifies the
behavior to use when there is not enough space in the tool tip to display the entire text. The default behavior
is to use an ellipsis.

The wrapText is a boolean property. It specifies whether text should be wrapped onto another line if its
run exceeds the width of the tool tip. The default value is false.

The graphicTextGap property is a double property that specifies the space between the text and graphic
in pixel. The default value is 4. The font property is an object property of the Font type. It specifies the
default font to use for the text. The activated property is a read-only boolean property. It is true when the
tool tip is activated. Otherwise, it is false. A tool tip is activated when the mouse moves over a control, and it
is shown after it is activated.

The program shows how to create, configure, and set tool tips for controls. After you run
the application, place the mouse pointer over the name field, Save button, and Close button. After a short
time, their tool tips will be displayed. The tool tip for the Close button looks different from that of the Save
button. It uses an icon and different background and text colors.
*/
public class TooltipTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Label nameLbl = new Label("Name:");
		TextField nameFld = new TextField();
		Button saveBtn = new Button("Save");
		Button closeBtn = new Button("Close");

		// Set an ActionEvent handler
		closeBtn.setOnAction(e -> stage.close());

		// Add tooltips for Name field and Save button
		nameFld.setTooltip(new Tooltip("Enter your name\n(Max. 10 chars)"));
		saveBtn.setTooltip(new Tooltip("Saves the data"));
	
		// Create and configure the Tooltip for Close button
		Tooltip closeBtnTip = new Tooltip("Closes the window");
		closeBtnTip.setStyle("-fx-background-color: yellow; " + 
		                     " -fx-text-fill: black;");

		// Display the icon above the text
		closeBtnTip.setContentDisplay(ContentDisplay.TOP);

		Label closeTipIcon = new Label("X");
		closeTipIcon.setStyle("-fx-text-fill: red;");
		closeBtnTip.setGraphic(closeTipIcon);

		// Set its Tooltip for Close button
		closeBtn.setTooltip(closeBtnTip);

		HBox root = new HBox(nameLbl, nameFld, saveBtn, closeBtn);
		root.setSpacing(10);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using Tooltip Controls");
		stage.show();
	}
}
