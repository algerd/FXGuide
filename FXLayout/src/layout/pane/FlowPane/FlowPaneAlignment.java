
package layout.pane.FlowPane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
The alignment property of a FlowPane controls the alignment of its content. A Pos value contains a vertical
alignment (vpos) and horizontal alignment (hpos). For example, Pos.TOP_LEFT has the vertical alignment
as top and horizontal alignment as left. In a horizontal FlowPane, each row is aligned using the hpos value
of the alignment and rows (the entire content) is aligned using the vpos value. In a vertical FlowPane, each
column is aligned using the vpos value of the alignment and the columns (the entire content) are aligned
using the hpos value.
*/
public class FlowPaneAlignment extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		FlowPane fp1 = createFlowPane(Pos.BOTTOM_RIGHT);
		FlowPane fp2 = createFlowPane(Pos.BOTTOM_LEFT);
		FlowPane fp3 = createFlowPane(Pos.CENTER);

		HBox root = new HBox(fp1, fp2, fp3);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("FlowPane Alignment");
		stage.show();
	}

	private FlowPane createFlowPane(Pos alignment) {
		FlowPane fp = new FlowPane(5, 5);
		fp.setPrefSize(200, 100);
		fp.setAlignment(alignment);
		fp.getChildren().addAll(
            new Text(alignment.toString()), 
            new Button("Button 1"), 
            new Button("Button 2"),
            new Button("Button 3")
        );
        
		fp.setStyle(
            "-fx-padding: 10;" +
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;"
        );

		return fp;
	}
}
