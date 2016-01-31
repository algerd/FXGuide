
package shape.Shape3D;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

/*
A material is used for rendering the surface of shapes. You can specify the material for the surface of 3D
objects using the material property, which is defined in the Shape3D class. The material property is an
instance of the abstract class Material. JavaFX provides the PhongMaterial class as the only concrete
implementation of Material. Both classes are in the javafx.scene.paint package. An instance of the
PhongMaterial class represents Phong shaded material. Phong shaded material is based on Phong shading
and the Phong reflection model (also known as Phong illumination and Phong lighting), which were
developed at the University of Utah by Bui Tuong Phong as part of his Ph.D. dissertation in 1973. A complete
discussion of the Phong model is beyond the scope of this book. The model provides an empirical formula
to compute the color of a pixel on the geometric surface in terms of the following properties defined in the
PhongMaterial class:
    • diffuseColor
    • diffuseMap
    • specularColor
    • specularMap
    • selfIlluminationMap
    • specularPower
    • bumpMap

The PhongMaterial class contains three constructors:
    • PhongMaterial()
    • PhongMaterial(Color diffuseColor)
    • PhongMaterial(Color diffuseColor, Image diffuseMap, Image specularMap, Image bumpMap, Image selfIlluminationMap)

The no-args constructor creates a PhongMaterial with the diffuse color as Color.WHITE. The other
two constructors are used to create a PhongMaterial with the specified properties.
When you do not provide a material for a 3D shape, a default material with a diffuse color of
Color.LIGHTGRAY is used for rendering the shape.

The program shows how to create and set material for shapes. It creates two boxes. It sets
the diffuse color for one box and the diffuse map for other. The image used for the diffuse map provides the
texture for the surface of the second box.
*/
public class MaterialTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a Box
		Box box = new Box(100, 100, 100);

		// Set the material for the box
		PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.TAN);
		box.setMaterial(material);

		// Place the box in the space
		box.setTranslateX(250);
		box.setTranslateY(0);
		box.setTranslateZ(400);

		// Create a Box with texture
		Box boxWithTexture = new Box(100, 100, 100);
		PhongMaterial textureMaterial = new PhongMaterial();
		Image randomness = new Image("shape/Shape3D/randomness.jpg");
		textureMaterial.setDiffuseMap(randomness);
		boxWithTexture.setMaterial(textureMaterial);

		// Place the box in the space
		boxWithTexture.setTranslateX(450);
		boxWithTexture.setTranslateY(-5);
		boxWithTexture.setTranslateZ(400);

		PointLight light = new PointLight();
		light.setTranslateX(250);
		light.setTranslateY(100);
		light.setTranslateZ(300);

		Group root = new Group(box, boxWithTexture);

		// Craete a Scene with depth buffer enabled
		Scene scene = new Scene(root, 300, 100, true);

		// Set a camera to view the 3D shapes
		PerspectiveCamera camera = new PerspectiveCamera(false);
		camera.setTranslateX(200);
		camera.setTranslateY(-50);
		camera.setTranslateZ(325);
		scene.setCamera(camera);

		stage.setScene(scene);
		stage.setTitle("Using Material Color and Texture for 3D Surface");
		stage.show();
	}
}
