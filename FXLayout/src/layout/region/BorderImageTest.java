
package layout.region;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.BorderRepeat;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

/*
Using an image for a border is not as straightforward as using a stroke. An image defines a rectangular
area; so does a Region. A border is drawn around a Region in an area called the border image area. The
border area of a Region may be the entire area of the Region; it may be partly or fully inside or outside of the
Region. The insets on four edges of the Region define the border image area. To make an image a border
around a Region, both the border image area and the image are divided into nine regions: four corners,
four sides, and a middle. The border area is divided into nine parts by specifying widths on all four sides,
top, right, bottom, and left. The width is the width of the border along those sides. The image is also sliced
(divided) into nine regions by specifying the slice width for each side.

After you have divided the border image area and the image into nine regions, you need to specify
properties that control the positioning and resizing behavior of the image slices. Each of nine slices of the
image has to be positioned and fit inside its corresponding part in the border image area. For example, the
image slice in the upper left corner of the image has to fit in the upper-left corner part of the border image
area. The two components, an image slice and its corresponding border image slice, may not be of the same
size. You will need to specify how to fill the region in the border image area (scale, repeat, etc.) with the
corresponding image slice. Typically, the middle slice of the image is discarded. However, if you want to fill
the middle region of the border image area, you can do so with the middle slice of the image.

The following CSS properties define border images for a Region.
    • -fx-border-image-source
    • -fx-border-image-repeat
    • -fx-border-image-slice
    • -fx-border-image-width
    • -fx-border-image-insets

The -fx-border-image-source property is a CSS URL for the image. For multiple images, use a
comma-separated list of CSS URLs of images.

The -fx-border-image-repeat property specifies how a slice of the image will cover the corresponding
part of the Region. You can specify the property separately for the x-axis and y-axis. Valid values:
    • no-repeat
    • repeat
    • round
    • space
The no-repeat value specifies that the image slice should be scaled to fill the area without repeating it.
The repeat value specifies that the image should be repeated (tiled) to fill the area. The round value specifies
that the image should be repeated (tiled) to fill the area using a whole number of tiles, and if necessary, scale
the image to use the whole number of tiles. The space value specifies that the image should be repeated
(tiled) to fill the area using a whole number of tiles without scaling the image and by distributing the extra
space uniformly around the tiles.

The -fx-border-image-slice property specifies inward offsets from the top, right, bottom, and
left edges of the image to divide it into nine slices. The property can be specified as a number literal or a
percentage of the side of the image. If the word fill is present in the value, the middle slice of the image
is preserved and is used to fill the middle region of the border image area; otherwise, the middle slice is
discarded.

The -fx-border-image-width property specifies the inward offsets from four sides of the border image
area to divide the border image area into nine regions. Note that we divide the border image area into nine
regions, not the Region. The property can be specified as a number literal or a percentage of the side of the
border image area.

The -fx-border-image-insets property specifies the distance between the edges of the Region and
the edges of the border image area on four sides. A positive inset is measured from the edge of the Region
toward its center. A negative inset is measured outward from the edge of the Region. In Figure 10-13, the
border image area for the Region in the middle has positive insets, whereas the border image area for the
Region (third from the left) has negative insets.
*/
public class BorderImageTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) {
	 	// Get the URL of the image
		String imagePath = "layout/region/border_with_traingles.jpg";
	
		Pane p1 = this.getCSSStyledPane(imagePath);
		Pane p2 = this.getObjectStyledPane(imagePath);

		// Place p1 and p2
		p1.setLayoutX(20);
		p1.setLayoutY(20);
		p2.layoutYProperty().bind(p1.layoutYProperty());
		p2.layoutXProperty().bind(p1.layoutXProperty().add(p1.widthProperty()).add(20));

		Pane root = new Pane(p1, p2); 
		root.setPrefSize(260, 100);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Strokes and Images as a Border");
		stage.show();
	}

		
	public Pane getCSSStyledPane(String imageURL) {
		Pane p = new Pane();
		p.setPrefSize(100, 70);		
		p.setStyle("-fx-border-image-source: url('" + imageURL + "') ;" + 
		           "-fx-border-image-repeat: no-repeat;" + 
		           "-fx-border-image-slice: 9;" +  
		           "-fx-border-image-width: 9;" +  
		           "-fx-border-image-insets: 10;" + 
		           "-fx-border-color: black;" + 
		           "-fx-border-width: 1;" + 
		           "-fx-border-style: dashed inside;");
		return p;
	}

	public Pane getObjectStyledPane(String imageURL) {
		Pane p = new Pane();
		p.setPrefSize(100, 70);
		p.setBackground(Background.EMPTY);
		
		// Create a BorderImage object
		BorderWidths regionWidths = new BorderWidths(9);
		BorderWidths sliceWidth = new BorderWidths(9);
		boolean filled = false;
		BorderRepeat repeatX = BorderRepeat.STRETCH;
		BorderRepeat repeatY = BorderRepeat.STRETCH;
        
		BorderImage borderImage = new BorderImage(
            new Image(imageURL),
            regionWidths, 
            new Insets(10),
            sliceWidth,
            filled,
            repeatX,
            repeatY);

		// Set the Pane's boundary with a dashed stroke
		List<Double> dashArray = new ArrayList<>();
		dashArray.add(2.0);
		dashArray.add(1.4);
        
		BorderStrokeStyle blackStrokeStyle = new BorderStrokeStyle(
            StrokeType.INSIDE, 
            StrokeLineJoin.MITER, 
            StrokeLineCap.BUTT, 
            10, 
            0,
            dashArray);
        
		BorderStroke borderStroke = new BorderStroke(
            Color.BLACK, 
            blackStrokeStyle, 
            CornerRadii.EMPTY, 
            new BorderWidths(1), 
            new Insets(0));

		// Create a Border object with a stroke and an image
		BorderStroke[] strokes = new BorderStroke[] { borderStroke };
		BorderImage[] images = new BorderImage[] { borderImage };
		Border b = new Border(strokes, images);
		
		p.setBorder(b);
		return p;
	}
}
