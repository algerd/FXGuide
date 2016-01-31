package include.resource;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class ResourceMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Locale.setDefault(new Locale("en", "US"));
        /*
        Чтобы загружать локализации в fxml-документе с помощью элемента fx:include и его
        аттрибута resource, необходимо вылечить баг:
            - создать локализацию главного fxml-документа и подключить её с помощью
            кастомного класса-обёртки (ResourceBundleWrapper)
        */
        String resourceBundlePath = "include/resource/i18n/MainView";
        ResourceBundle resourceBundle = new ResourceBundleWrapper(ResourceBundle.getBundle(resourceBundlePath));
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainView.fxml"));
        loader.setResources(resourceBundle);

        BorderPane pane = loader.load();
        stage.setScene(new Scene(pane));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /** 
     * This class effectively does nothing, but it will be loaded by the
     * application class loader instead of the system class loader.
     */
    private static class ResourceBundleWrapper extends ResourceBundle {
        private final ResourceBundle bundle;

        ResourceBundleWrapper(ResourceBundle bundle) {
            this.bundle = bundle;
        }
        @Override
        protected Object handleGetObject(String key) {
            return bundle.getObject(key);
        }
        @Override
        public Enumeration<String> getKeys() {
            return bundle.getKeys();
        }
        @Override
        public boolean containsKey(String key) {
            return bundle.containsKey(key);
        }
        @Override
        public Locale getLocale() {
            return bundle.getLocale();
        }
        @Override
        public Set<String> keySet() {
            return bundle.keySet();
        }
    }
}
