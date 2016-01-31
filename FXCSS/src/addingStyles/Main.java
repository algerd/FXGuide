
package addingStyles;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
       
        // Загрузка fxml-документа
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("main.fxml"));
        VBox root = loader.load();                  
        Scene scene = new Scene(root);
 
        /*
        1) Внешние(глобальные) стили в Scene.
        Вызвать у сцены (объект Scene) свойство ObservableList<String> stylesheets и добавить в него пути CSS файлов.
        Метод addAll() добавляет список строк-путей через запятую. 
        Метод add() добавляет пути стилей по одному.
        Путь стиля - путь относительно ClassLoader(CLASSPATH) главного класса, расширяющего класс Application.
        Строка, начинающаяся со слеша или без, будет приведена к одному и тому же пути относительно ClassLoader(CLASSPATH).
        Путь стиля может быть абсолютным: "http://javafx.ru/css/ss1.css" или "file:/C:/css/ss2.css"
        */
        scene.getStylesheets().addAll("addingStyles/main1.css", "/addingStyles/main2.css");
        /*
        Указание пути с использованием ClassLoader:getResource() - путь относительно ClassLoader(CLASSPATH) указанного класса. 
        Отсутствие/наличие слеша в начале строки критично.
        */
        String urlString = Main.class.getClassLoader().getResource("addingStyles/main3.css").toExternalForm();
        scene.getStylesheets().add(urlString);
        
        /*
        2) Внешние(глобальные) стили в Parent.
        Класс Parent как и Scene тоже имеет свойство ObservableList<String> stylesheets 
        и методы добавления css-файлов addAll() и add().
        Поэтому любому классу, расширяющему Parent (это такие ui-элементы: Pane, Сontrol, Chart)
        можно подключить внешний файл стиля(глобальный стиль). При этом чем более специфичный класс, 
        тем его стиль приортитетнее других глобальных стилей его родителей.
        */
        // VBox root
        root.getStylesheets().addAll("addingStyles/root.css");
        
        /*
        3) Инлайн-стили в java-файле и fxml-файле.
        Класс Node (а сл-но и его дети) имеет свойство StringProperty style, куда можно
        добавлять стили инлайн, каждое стилевое свойство разделяя точкой с запятой.
        Для укзазния инлайн стилей в java-файле, надо воспользоваться методом setStyle().
        Для укзазния инлайн стилей в fxml-файле, надо воспользоваться аттрибутом style=""
        или тегом-свойством <style></style> в случае длинных силей.
        При этом инлайн-стиль, загруженный в java-файле, будет приоритетнее этого же стиля 
        в fxml-файле (наверное, потому что он подключается позже, после загрузки fxml-документа).
        */
        root.setStyle("-fx-border-width: 4.0; -fx-border-color: blue;");
        
        /*
        Принцип приоритета подключаемых стилей: чем ближе стиль к элементу, тем приоритетнее.
        Приоритеты подключаемых стилей (по убыванию):
            - инлайн стиль в java-файле (методом Node:setStyle(String style))
            - инлайн стиль в fxml-файле в аттрибуте элемента (style="String style")
            - инлайн стиль в fxml-файле в свойстве-теге (<style></style>)
            - внешние стили в Parent-расширяемом элементе (методом Parent:getStylesheets().addAll() или add())
              Чем ближе элемент с внешними стилями в иерархии наследования, тем стили приоритетнее.
            - внешние стили в Scene (методом Scene::getStylesheets().addAll() или add()) 
            - свойства заданные без стилей, напрямую через сеттеры JavaFX API (напр. setFont())
            - дефолтный стиль (напр. modena в JavaFX)  
        Крайне не рекомендуется смешивать css-стили со JavaFX API свойствами.
        Также рекомендуется ограничить использование инлайн-стилей в пользу внешних стилей.
        Во внешних стилях более глобальные стили (напр. шрифты) подключают через Scene,
        более специфичные в Parent-расширяемых элементах.
        */
                           
		stage.setScene(scene);
		stage.setTitle("fx:constant");
		stage.show();    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
