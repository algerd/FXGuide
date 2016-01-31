
package canvas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
You can draw text using the fillText() and strokeText() methods of the GraphicsContext using the
following snippets of code:
    • void strokeText(String text, double x, double y)
    • void strokeText(String text, double x, double y, double maxWidth)
    • void fillText(String text, double x, double y)
    • void fillText(String text, double x, double y, double maxWidth)

Both methods are overloaded. One version lets you specify the text and its position. The other version
lets you specify the maximum width of the text as well. If the actual text width exceeds the specified
maximum width, the text is resized to fit the specified the maximum width.
*/
public class TextTest extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Canvas canvas = new Canvas(400, 100);
		GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.setLineWidth(1.0);
        gc.setStroke(Color.BLACK);
        gc.strokeText("Drawing Text", 10, 10);
        gc.strokeText("Drawing Text", 100, 10, 40);
        
		Pane root = new Pane(canvas);	
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Drawing on a Canvas");
		stage.show();
        
	}
		
	
}
