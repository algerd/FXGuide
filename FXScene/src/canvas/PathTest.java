
package canvas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
Use can use path commands and SVG path strings to create a shape of your choice. A path consists of
multiple subpaths. The following methods are used to draw paths:
    • beginPath()
    • lineTo(double x1, double y1)
    • moveTo(double x0, double y0)
    • quadraticCurveTo(double xc, double yc, double x1, double y1)
    • appendSVGPath(String svgpath)
    • arc(double centerX, double centerY, double radiusX, double radiusY, double startAngle, double length)
    • arcTo(double x1, double y1, double x2, double y2, double radius)
    • bezierCurveTo(double xc1, double yc1, double xc2, double yc2, double x1, double y1)
    • closePath()
    • stroke()
    • fill()
The beginPath() and closePath() methods start and close a path, respectively. Methods such as
arcTo() and lineTo() are the path commands to draw a specific type of subpath. Do not forget to call the
stroke() or fill() method at the end, which will draw an outline or fill the path.
*/
public class PathTest extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Canvas canvas = new Canvas(400, 100);
		GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.setLineWidth(2.0);
        gc.setStroke(Color.BLACK);
        
        gc.beginPath();
        gc.moveTo(25, 0);
        gc.appendSVGPath("L50, 25L0, 25");
        gc.closePath();
        gc.stroke();
        
		Pane root = new Pane(canvas);	
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Drawing on a Canvas");
		stage.show();
        
	}
		
	
}
