
package stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Modality;
import static javafx.stage.Modality.NONE;
import static javafx.stage.Modality.WINDOW_MODAL;
import static javafx.stage.Modality.APPLICATION_MODAL;
import javafx.stage.Window;

/*
In a GUI application, you can have two types of windows: modal and modeless. When a modal window is
displayed, the user cannot work with other windows in the application until the modal window is dismissed.
If an application has multiple modeless windows showing, the user can switch between them at any time.

Modality of a stage is defined by one of the following three constants in the Modality enum in the
javafx.stage package:
    • NONE
    • WINDOW_MODAL
    • APPLICATION_MODEL
You can set the modality of a stage using the initModality(Modality m) method of the Stage class: 
    Stage stage = new Stage();
    stage.initModality(Modality.WINDOW_MODAL);
The modality of a stage must be set before it is shown.

A Stage can have an owner. An owner of a Stage is another Window. You can set an owner of a Stage
using the initOwner(Window owner) method of the Stage class. The owner of a Stage must be set before the
stage is shown. The owner of a Stage may be null, and in this case, it is said that the Stage does not have an
owner. Setting an owner of a Stage creates an owner-owned relationship. For example, a Stage is minimized
or hidden if its owner is minimized or hidden, respectively.

The default modality of a Stage is NONE. When a Stage with the modality NONE is displayed, it does not
block any other windows in the application. It behaves as a modeless window.

A Stage with the WINDOW_MODAL modality blocks all windows in its owner hierarchy. Suppose there are
four stages: s1, s2, s3, and s4. Stages s1 and s4 have modalities set to NONE and do not have an owner; s1 is
the owner of s2; s2 is the owner of s3. All four stages are displayed. If s3 has its modality set to WINDOW_MODAL,
you can work with s3 or s4, but not with s2 and s1. The owner-owned relationship is defined as s1 to s2 to
s3. When s3 is displayed, it blocks s2 and s1, which are in its owner hierarchy. Because s4 is not in the owner
hierarchy of s3, you can still work with s4.

Tip: T he modality of WINDOW_MODAL for a stage that has no owner has the same effect as if the modality is
set to NONE.

If a Stage with its modality set to APPLICATION_MODAL is displayed, you must work with the Stage and
dismiss it before you can work with any other windows in the application. Continuing with the same example
from the previous paragraph of displaying four stages, if you set the modality of s4 to APPLICATION_MODAL,
the focus will be set to s4 and you must dismiss it before you can work with other stages. Notice that an
APPLICATION_MODAL stage blocks all other windows in the same application, irrespective of the owner-owned
relationships.

Listing shows how to use different modalities for a stage. It displays the primary stage with six
buttons. Each button opens a secondary stage with a specified modality and owner. The text of the buttons
tells you what kind of secondary stage they will open. When the secondary stage is shown, try clicking on the
primary stage. When the modality of the secondary stage blocks the primary stage, you will not be able to
work with the primary stage; clicking the primary stage will set the focus back to the secondary stage.

*/
public class StageModality extends Application {
    
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		/* Buttons to display each kind of modal stage */
		Button ownedNoneButton = new Button("Owned None");
		ownedNoneButton.setOnAction(e -> showDialog(stage, NONE)); 

		Button nonOwnedNoneButton = new Button("Non-owned None");
		nonOwnedNoneButton.setOnAction(e -> showDialog(null, NONE)); 

		Button ownedWinButton = new Button("Owned Window Modal");
		ownedWinButton.setOnAction(e -> showDialog(stage, WINDOW_MODAL)); 

		Button nonOwnedWinButton = new Button("Non-owned Window Modal");
		nonOwnedWinButton.setOnAction(e -> showDialog(null, WINDOW_MODAL)); 

		Button ownedAppButton = new Button("Owned Application Modal");
		ownedAppButton.setOnAction(e -> showDialog(stage, APPLICATION_MODAL));

		Button nonOwnedAppButton = new Button("Non-owned Application Modal");
		nonOwnedAppButton.setOnAction(e -> showDialog(null, APPLICATION_MODAL)); 

		VBox root = new VBox(); 
		root.getChildren().addAll(ownedNoneButton, nonOwnedNoneButton,
		                          ownedWinButton, nonOwnedWinButton, 
		                          ownedAppButton, nonOwnedAppButton );
		Scene scene = new Scene(root, 300, 200);
		stage.setScene(scene);
		stage.setTitle("The Primary Stage");
		stage.show();
	}

	private void showDialog(Window owner, Modality modality) {
		// Create a Stage with specified owner and modality
		Stage stage = new Stage();
		stage.initOwner(owner);
		stage.initModality(modality);
		
		Label modalityLabel = new Label(modality.toString());
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e -> stage.close()); 
		
		VBox root = new VBox();
		root.getChildren().addAll(modalityLabel, closeButton);
		Scene scene = new Scene(root, 200, 100);
		stage.setScene(scene); 
		stage.setTitle("A Dialog Box");
		stage.show();
	}
}
