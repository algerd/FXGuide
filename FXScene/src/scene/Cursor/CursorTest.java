
package scene.Cursor;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CursorTest extends Application {
    
    @Override
    public void start(Stage stage) {
        
        stage.setTitle("Cursor");
        Group root = new Group(new Button("Hello"));
        Scene scene = new Scene(root, 300, 100);
        
        // Create a Cursor from a bitmap
        String urlString = getClass().getClassLoader().getResource("scene/Cursor/wow_cursor.gif").toExternalForm();       
        
        /*
        Стаический метод cursor(String identifier) принимает или строку ссылки на изображение курсора,
        или строку стандартного курсора: 
            DEFAULT, CROSSHAIR, TEXT, WAIT, OPEN_HAND, CLOSED_HAND,
            SW_RESIZE, SE_RESIZE, NW_RESIZE, NE_RESIZE, N_RESIZE, S_RESIZE, W_RESIZE, E_RESIZE, 
        Если передаётся ссылка на изображение, то внутренне метод использует:
            new ImageCursor(new Image(identifier))
        Такой способ курсора требует более тонкой работы с изображением, потому что нельзя указать
        его активную область. В данном примере кнопка становится активной тогда, когда изображение
        касается её, но сам мечик, который далеко от края изображения, не касается кнопки.
        */
        Cursor cursor = Cursor.cursor(urlString);
        //scene.setCursor(cursor);
              
        // Get the WAIT standard cursor using its name
        Cursor waitCur = Cursor.cursor("WAIT");
        //scene.setCursor(waitCur);
        
        /*
        Если требуется более тонкая работа с курсором, то надо использовать класс ImageCursor.
        В отличие от Cursor ImageCursor позволяет задать позиционирование активной точки курсора
            ImageCursor(final Image image, double hotspotX, double hotspotY)
        */
        Image image = new Image(urlString);
        ImageCursor imageCursor = new ImageCursor(image, image.getWidth() / 2 - 10, image.getHeight() / 2 - 10);
        scene.setCursor(imageCursor);
         
        stage.setScene(scene);
        stage.show();     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
