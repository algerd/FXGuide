
package stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javafx.stage.StageStyle.DECORATED;
import static javafx.stage.StageStyle.UNDECORATED;
import static javafx.stage.StageStyle.TRANSPARENT;
import static javafx.stage.StageStyle.UNIFIED;
import static javafx.stage.StageStyle.UTILITY;

/*
The area of a stage can be divided into two parts: content area and decorations. The content area displays
the visual content of its scene. Typically, decorations consist of a title bar and borders. The presence of a title
bar and its content varies depending on the type of decorations provided by the platform. Some decorations
provide additional features rather than just an aesthetic look. For example, a title bar may be used to drag a
stage to a different location; buttons in a title bar may be used to minimize, maximize, restore, and close a
stage; or borders may be used to resize a stage.

In JavaFX, the style attribute of a stage determines its background color and decorations. 
You can set the style of a stage using the initStyle(StageStyle style) method of the Stage class. The
style of a stage must be set before it is shown for the first time. Setting it the second time, after the stage has
been shown, throws a runtime exception. By default, a stage is decorated.
The five types of styles for a stage are defined as five constants in the StageStyle enum:
   • StageStyle.DECORATED
    • StageStyle.UNDECORATED
    • StageStyle.TRANSPARENT
    • StageStyle.UNIFIED
    • StageStyle.UTILITY
A decorated stage has a solid white background and platform decorations. 
An undecorated stage has a solid white background and no decorations. 
A transparent stage has a transparent background and no decorations. 
A unified stage has platform decorations and no border between the client area and
    decorations; the client area background is unified with the decorations. To see the effect of the unified stage
    style, the scene should be filled with Color.TRANSPARENT. Unified style is a conditional feature. 
A utility stage has a solid white background and minimal platform decorations.

Tip: T he style of a stage specifies only its decorations. The background color is controlled by its scene
background, which is solid white by default. If you set the style of a stage to TRANSPARENT, you will get a stage
with a solid white background, which is the background of the scene. To get a truly transparent stage, you will
need to set the background color of the scene to null using its setFill() method.
*/
public class StageDecorate extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// A label to display the style type
		Label styleLabel = new Label("Stage Style");
		
		// A button to close the stage
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e -> stage.close()); 

		VBox root = new VBox(); 
		root.getChildren().addAll(styleLabel, closeButton);
		Scene scene = new Scene(root, 100, 70);
		stage.setScene(scene);
	
		// The title of the stage is not visible for all styles.
		stage.setTitle("The Style of a Stage");
		
		/* Uncomment one of the following statements at a time */
		this.show(stage, styleLabel, DECORATED);
		//this.show(stage, styleLabel, UNDECORATED);
		//this.show(stage, styleLabel, TRANSPARENT);
		//this.show(stage, styleLabel, UNIFIED);
		//this.show(stage, styleLabel, UTILITY);
	}
	
	private void show(Stage stage, Label styleLabel, StageStyle style) {
		// Set the text for the label to match the style
		styleLabel.setText(style.toString());
		
		// Set the style
		stage.initStyle(style);
		
		// For a transparent style, set the scene fill to null. Otherwise, the 
		// content area will have the default white background of the scene.
		if (style == TRANSPARENT) {
			stage.getScene().setFill(null);
			stage.getScene().getRoot().setStyle("-fx-background-color: transparent");
		} 
        else if(style == UNIFIED) {
			stage.getScene().setFill(Color.TRANSPARENT);
		}
		// Show the stage
		stage.show();
	}
}
