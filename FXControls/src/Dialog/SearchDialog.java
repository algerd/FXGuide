
package Dialog;

import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

/*
Listing describes an application that displays a search dialog box, 
which solicits search text and a case-sensitive search true/false value.
When the Search button is clicked, these values are output the standard output stream.

Listing uses the java.util.Pair<K, V> class to conveniently store a pair of items: 
the search text and the Boolean case-sensitive-search value. 
A result converter has been installed to convert the dialog's search text field 
and case-sensitive search check box values to a Pair<K, V> object that is returned from the call() method. 
This conversion happens only when the Search button is clicked; it doesn't happen when Cancel is clicked.
*/
public class SearchDialog extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}
    
    @Override
    public void start(Stage primaryStage) {
        
        // Create the custom dialog.
        Dialog<Pair<String, Boolean>> dialog = new Dialog<>();
        dialog.setTitle("Search");
        dialog.setHeaderText("Enter search parameters");

        // Set the search icon.
        dialog.setGraphic(new ImageView(this.getClass().getResource("search.jpg").toString()));

        // Set the button types.
        ButtonType searchButtonType = new ButtonType("Search", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(searchButtonType, ButtonType.CANCEL);

        // Create the layout for the controls.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // Create and initialize the search-text and case-sensitive-search controls.
        TextField srchText = new TextField();
        srchText.setPromptText("Search text");
        CheckBox css = new CheckBox("Case-sensitive search");

        // Populate the layout with a label along with the search text and 
        // case-sensitive search controls.
        grid.add(new Label("Search Text:"), 0, 0);
        grid.add(srchText, 1, 0);
        grid.add(css, 0, 1);

        // Disable/enable search button depending on whether search-text field is 
        // empty. Button defaults to being disabled.
        Node searchButton = dialog.getDialogPane().lookupButton(searchButtonType);
        searchButton.setDisable(true);
        srchText.textProperty().addListener(
            (observable, oldValue, newValue) -> {searchButton.setDisable(newValue.trim().isEmpty());}
        );

        // Install controls layout in the dialog panel.
        dialog.getDialogPane().setContent(grid);

        // Request focus on the search-text field. See 
        // https://community.oracle.com/thread/2321126 for information on why 
        // Platform.runLater() is used.
        Platform.runLater(() -> srchText.requestFocus());

        // Convert the result to a srchtext-css-status pair when the search button is clicked.
        dialog.setResultConverter(
            dialogButton -> {
                if (dialogButton == searchButtonType)
                   return new Pair<>(srchText.getText(), css.isSelected());
                return null;
            }
        );

        // Display dialog box and wait for user response.
        Optional<Pair<String, Boolean>> result = dialog.showAndWait();

        // If the user closed the dialog box via the search button, output the 
        // chosen search text and case-sensitive search status.
        result.ifPresent(
            stcss -> {
                System.out.println("Search text = " + stcss.getKey() + ", Case-sensitive search = " + stcss.getValue());
            }
        );
    }
    
}