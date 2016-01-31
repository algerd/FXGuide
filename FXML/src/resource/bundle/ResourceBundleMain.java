
package resource.bundle;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResourceBundleMain extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
     
        /**
         * Resourse Bundles представляют собой текстовые файлы локализаций,
         * организованные в properties:
         *   - bundlename.properties - дефолтная папка для дефолтного языка
         *   - bundlename_lang.properties - папка для lang-языка
         *   - bundlename_country.properties - папка для country-языка
         *  Чтобы использовать нужную папку локализации, надо установить локаль 
         *  Locale.setDefault(Locale locale);
         *  Locale имеет конструкторы :
         *       Locale(String language)
         *       Locale(String language, String country)
         *  и в зависимости какой конструктор мы используем и какой язык передаём,
         *  будет к resourcePath добавляться суффикс (resource/bundle/greetings_hi) и
         *  затем лоадер будет брать текстовый файл из этого пути для сопоставления 
         * "%text_key" из fxml с ключом text_key. И заменять text_key на текст из
         *  локализации
         */
        
		URL fxmlUrl = this.getClass().getClassLoader().getResource("resource/bundle/greetings.fxml");

		// Create a ResourceBundle to use in FXMLLoader
		String resourcePath = "resource/bundle/greetings";
		ResourceBundle resourceBundle = ResourceBundle.getBundle(resourcePath);

		// Load teh Label for default Locale
		Label defaultGreetingLbl = FXMLLoader.<Label>load(fxmlUrl, resourceBundle);

		// Change the default Locale : Locale(String language, String country)
        // Меняет resourceBundle-путь с resource/bundle/greetings на resource/bundle/greetings_hi и resource/bundle/greetings_in
		Locale.setDefault(new Locale("hi", "in"));
		
		// We need to recreate the ResourceBundler to pick up the new default Locale
		resourceBundle = ResourceBundle.getBundle(resourcePath);
        
        // load the Label again
		Label indianGreetingLbl = FXMLLoader.<Label>load(fxmlUrl, resourceBundle);

		// Add both Labels to a Vbox 
		VBox root = new VBox(5, defaultGreetingLbl, indianGreetingLbl);		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using a ResourceBundle in FXML");
		stage.show();
            
	}
}
