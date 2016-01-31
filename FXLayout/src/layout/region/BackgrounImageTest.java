
package layout.region;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
The following CSS properties define the background image for a Region.
    • -fx-background-image
    • -fx-background-repeat
    • -fx-background-position
    • -fx-background-size

The -fx-background-image property is a CSS URL for the image. The -fx-background-repeat property
indicates how the image will be repeated (or not repeated) to cover the drawing area of the Region. The
-fx-background-position determines how the image is positioned with the Region. The -fx-background-size
property determines the size of the image relative to the Region.

*/
public class BackgrounImageTest extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		Pane p1 = this.getCSSStyledPane();
		Pane p2 = this.getObjectStyledPane();
        
		p1.setLayoutX(10);
		p1.setLayoutY(10);

		// Place p2 20px right to p1
		p2.layoutYProperty().bind(p1.layoutYProperty());
		p2.layoutXProperty().bind(p1.layoutXProperty().add(p1.widthProperty()).add(20));

		Pane root = new Pane(p1, p2);
		root.setPrefSize(240, 70);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Setting Background Images for a Region");
		stage.show();
		stage.sizeToScene();
	}

	public Pane getCSSStyledPane() {
		Pane p = new Pane();
		p.setPrefSize(100, 50);
		p.setStyle("-fx-background-image: url('/layout/region/randomness.jpg');"
				+ "-fx-background-repeat: space;"
				+ "-fx-background-position: center;"
                + "-fx-background-size: cover;"
        );
		return p;
	}

	public Pane getObjectStyledPane() {
		Pane p = new Pane();
		p.setPrefSize(100, 50);

        String urlString = getClass().getClassLoader().getResource("layout/region/randomness.jpg").toExternalForm();
        Image image = new Image(urlString);
        BackgroundSize bgSize = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage bgImage = new BackgroundImage(
            image,
            BackgroundRepeat.SPACE,
            BackgroundRepeat.SPACE,
            BackgroundPosition.DEFAULT,
            bgSize);
        // Create a Background object with an BackgroundImage object
        Background bg = new Background(bgImage);
        
		p.setBackground(bg);
		return p;
	}
}
