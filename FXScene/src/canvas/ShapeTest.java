
package canvas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
The GraphicsContext class provides two types of methods to draw the basic shapes. The method fillXxx()
draws a shape Xxx and fills it with the current fill paint. The method strokeXxx() draws a shape Xxx with the
current stroke. Use the following methods for drawing shapes:
    • fillArc()
    • fillOval()
    • fillPolygon()
    • fillRect()
    • fillRoundRect()
    • strokeArc()
    • strokeLine()
    • strokeOval()
    • strokePolygon()
    • strokePolyline()
    • strokeRect()
    • strokeRoundRect()

*/
public class ShapeTest extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Canvas canvas = new Canvas(400, 100);
		GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.setLineWidth(5.0);
         
        gc.setFill(Color.YELLOW);
        gc.fillRect(0, 0, 100, 50);
        
        gc.setStroke(Color.RED);
        gc.strokeRect(0, 0, 100, 50);
        
		Pane root = new Pane(canvas);	
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Drawing on a Canvas");
		stage.show();
        
	}
		
	
}
