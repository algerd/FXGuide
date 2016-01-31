
package FileDirectoryChooser;

import javafx.application.Application;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static javafx.stage.FileChooser.ExtensionFilter;

/*
FileChooser does not extend Control.

avaFX provides the FileChooser and DirectoryChooser classes in the javafx.stage package that are used
to show file and directory dialogs. The dialogs have a platform dependent look and feel and cannot be styled
using JavaFX. They are not controls.

A FileChooser is a standard file dialog. It is used to let the user select files to open or save. Some of its parts,
for example, the title, the initial directory, and the list of file extensions, can be specified before opening the
dialogs. There are three steps in using a file dialog:
    1. Create an object of the FileChooser class.
    2. Set the initial properties for the file dialog.
    3. Use one of the showXXXDialog() methods to show a specific type of file dialog.

You can set the following initial properties of the file dialog:
    • Title
    • initialDirectory
    • initialFileName
    • Extension filters
The title property of the FileChooser class is a string, which represents the title of the file dialog:
    fileDialog.setTitle("Open Resume");

The initialDirectory property of the FileChooser class is a File, which represents the initial
directory when the file dialog is shown:
    fileDialog.setInitialDirectory(new File("C:\\"));

The initialFileName property of the FileChooser class is a string that is the initial file name for the file
dialog. Typically, it is used for a file save dialog. Its effect depends on the platform if it is used for a file open
dialog. For example, it is ignored on Windows:
    fileDialog.setInitialFileName("untitled.htm");

You can set a list of extension filters for a file dialog. Filters are displayed as a drop-down box. One
filter is active at a time. The file dialog displays only those files that match the active extension filter. An
extension filter is represented by an instance of the ExtensionFilter class, which is an inner static class
of the FileChooser class. The getExtensionFilters() method of the FileChooser class returns an
ObservableList<FileChooser.ExtensionFilter>. You add the extension filters to the list. An extension
filter has two properties: a description and a list of file extension in the form *.<extension>:
    fileDialog.getExtensionFilters().addAll(
        new ExtensionFilter("HTML Files", "*.htm", "*.html"),
        new ExtensionFilter("Text Files", "*.txt"),
        new ExtensionFilter("All Files", "*.*"));

By default, the first extension filter in the list is active when the file dialog is displayed. Use the
selectedExtensionFilter property to specify the initial active filter when the file dialog is opened:
    // Continuing with the above snippet of code, select *.txt filter by default
    fileDialog.setSelectedExtensionFilter(fileDialog.getExtensionFilters().get(1));

The same selectedExtensionFilter property contains the extension filter that is selected by the user
when the file dialog is closed.

An instance of the FileChooser class can open three types of file dialogs:
    • A file open dialog to select only one file
    • A file open dialog to select multiple files
    • A file save dialog

The following three methods of the FileChooser class are used to open three types of file dialogs:
    • showOpenDialog(Window ownerWindow)
    • showOpenMultipleDialog(Window ownerWindow)
    • showSaveDialog(Window ownerWindow)

The methods do not return until the file dialog is closed. You can specify null as the owner window. If
you specify an owner window, the input to the owner window is blocked when the file dialog is displayed.
The showOpenDialog() and showSaveDialog() methods return a File object, which is the selected file,
or null if no file is selected. The showOpenMultipleDialog() method returns a List<File>, which contains
all selected files, or null if no files are selected:
    // Show a file open dialog to select multiple files
    List<File> files = fileDialog.showOpenMultipleDialog(primaryStage);
    if (files != null) {
        for(File f : files) {
            System.out.println("Selected file :" + f);
        }
    } else {
        System.out.println("No files were selected.");
    }

Use the selectedExtensionFilter property of the FileChooser class to get the selected extension filter
at the time the file dialog was closed:
    // Print the selected extension filter description
    ExtensionFilter filter = fileDialog.getSelectedExtensionFilter();
    if (filter != null) {
        System.out.println("Selected Filter: " + filter.getDescription());
    } else {
        System.out.println("No extension filter selected.");
    }

The program shows how to use open and save file dialogs. It displays a window with an
HTMLEditor and three buttons. Use the Open button to open an HTML file in the editor. Edit the content
in the editor. Use the Save button to save the content in the editor to a file. If you chose an existing file in
the Save Resume dialog, the content of the file will be overwritten
*/
public class FileChooserTest extends Application {
    
	private Stage primaryStage;
	private HTMLEditor resumeEditor;	
	private final FileChooser fileDialog = new FileChooser();
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		primaryStage = stage; // Used in file dialogs later
		resumeEditor = new HTMLEditor();
		resumeEditor.setPrefSize(600, 300);

		// Filter only HTML files
		fileDialog.getExtensionFilters().add(new ExtensionFilter("HTML Files", "*.htm", "*.html"));
	
		Button openBtn = new Button("Open");
		Button saveBtn = new Button("Save");
		Button closeBtn = new Button("Close");
		openBtn.setOnAction(e -> openFile());
		saveBtn.setOnAction(e -> saveFile());
		closeBtn.setOnAction(e -> stage.close());

		HBox buttons = new HBox(20, openBtn, saveBtn, closeBtn);
		buttons.setAlignment(Pos.CENTER_RIGHT);
		VBox root = new VBox(resumeEditor, buttons);
		root.setSpacing(20);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Editing Resume in HTML Format");
		stage.show();
	}
	
	private void openFile() {
		fileDialog.setTitle("Open Resume");
		File file = fileDialog.showOpenDialog(primaryStage);
		if (file == null) {
			return;
		}		
		try {
			// Read the file and populate the HTMLEditor		
			byte[] resume = Files.readAllBytes(file.toPath());
			resumeEditor.setHtmlText(new String(resume));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveFile() {
		fileDialog.setTitle("Save Resume");
		fileDialog.setInitialFileName("untitled.htm");
		File file = fileDialog.showSaveDialog(primaryStage);		
		if (file == null) {
			return;
		}	
		try {
			// Write the HTML contents to the file. Overwrite the existing file.
			String html = resumeEditor.getHtmlText();
			Files.write(file.toPath(), html.getBytes()); 
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
