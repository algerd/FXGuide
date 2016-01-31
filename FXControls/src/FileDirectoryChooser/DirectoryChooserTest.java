
package FileDirectoryChooser;

import javafx.application.Application;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/*
DirectoryChooser does not extend Control.

Sometimes you may need to let the user browse a directory from the available file systems on the computer.
The DirectoryChooser class lets you display a platform-dependent directory dialog.
The DirectoryChooser class contains two properties:
    • title
    • initialDirectory

The title property is a string and it is the title of the directory dialog. The initialDirectory property
is a File and it is the initial directory selected in the dialog when the dialog is shown.
Use the showDialog(Window ownerWindow) method of the DirectoryChooser class to open the
directory dialog. When the dialog is opened, you can select at most one directory or close the dialog without
selecting a directory. The method returns a File, which is the selected directory or null if no directory
is selected. The method is blocked until the dialog is closed. If an owner window is specified, input to all
windows in the owner window chain is blocked when the dialog is shown. You can specify a null owner
window.
*/
public class DirectoryChooserTest extends Application {
 	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		      
        Button showDirButton = new Button("Show Directory");
        showDirButton.setOnAction(e->showDir());
        
        Pane root = new Pane(showDirButton);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Editing Resume in HTML Format");
		stage.show();
	}
    
    private void showDir() {
        DirectoryChooser dirDialog = new DirectoryChooser();
        
        // Configure the properties
        dirDialog.setTitle("Select Destination Directory");
        dirDialog.setInitialDirectory(new File("c:\\"));
        
        // Show the directory dialog
        File dir = dirDialog.showDialog(null);
        if (dir != null) {
            System.out.println("Selected directory: " + dir);
        } else {
            System.out.println("No directory was selected.");
        }	
	}
		
}
