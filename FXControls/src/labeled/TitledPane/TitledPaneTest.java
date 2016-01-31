
package labeled.TitledPane;

import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
TitledPane is a labeled control. The TitledPane class inherits from the Labeled class. A labeled control can
have text and a graphic, so it can have a TitledPane. TitledPane displays the text as its title. The graphic is
shown in the title bar.

Besides text and a graphic, a TitledPane has content, which is a Node. Typically, a group of controls is
placed in a container and the container is added as the content for the TitledPane. TitledPane can be in
a collapsed or expanded state. In the collapsed state, it displays only the title bar and hides the content. In
the expanded state, it displays the title bar and the content. In its title bar, it displays an arrow that indicates
whether it is expanded or collapsed. Clicking anywhere in the title bar expands or collapses the content.

Use the default constructor to create a TitledPane without a title and content. You can set them later
using the setText() and setContent() methods. Alternatively, you can provide the title and content as
arguments to its constructor.
You can add a graphic to a TitledPane using the setGraphic() method, which is declared in the
Labeled class.

The TitledPane class declares four properties:
    • animated
    • collapsible
    • content
    • expanded

The animated property is a boolean property that indicates whether collapse and expand actions are
animated. By default, it is true and those actions are animated. The collapsible property is a boolean
property that indicates whether the TitledPane can collapse. By default, it is set to true and the TitledPane
can collapse. If you do not want your TitledPane to collapse, set this property to false. A noncollapsible
TitledPane does not display an arrow in its title bar. The content property is an Object property that
stores the reference of any node. The content is visible when the control is in the expanded state. The
expanded property is a boolean property. The TitledPane is in an expanded state when the property is true.
Otherwise, it is in a collapsed state. By default, a TitledPane is in an expanded state. Use the setExpanded()
method to expand and collapse the TitledPane programmatically.

Tip: Add a ChangeListener to its expanded property if you are interested in processing the expanded and
collapsed events for a TitledPane.

Typically, TitledPane controls are used in a group in an Accordion control, which displays only one
TitledPane from the group in the expanded state at a time to save space. You can also use a standalone
TitledPane if you want to show controls in groups.

Tip: Recall that the height of a TitledPane changes as it expands and collapses. Do not set its minimum,
preferred, and maximum heights in your code. Otherwise, it may result in an unspecified behavior.
*/
public class TitledPaneTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TextField firstNameFld = new TextField();
		firstNameFld.setPrefColumnCount(8);

		TextField lastNameFld = new TextField();
		lastNameFld.setPrefColumnCount(8);

		DatePicker dob = new DatePicker();
		dob.setPrefWidth(150);

		GridPane grid = new GridPane();
		grid.addRow(0, new Label("First Name:"), firstNameFld);
		grid.addRow(1, new Label("Last Name:"), lastNameFld);
		grid.addRow(2, new Label("DOB:"), dob);

		TitledPane infoPane = new TitledPane();
		infoPane.setText("Personal Info");
		infoPane.setContent(grid);

		String imageStr = "labeled/TitledPane/privacy_icon.png";		
		Image img = new Image(imageStr);
		ImageView imgView = new ImageView(img);
		infoPane.setGraphic(imgView);
		
		HBox root = new HBox(infoPane);
		root.setSpacing(10);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;"
        );
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using TitledPane Controls");
		stage.show();		
	}
}
