
package object.map;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javafx.fxml.FXMLLoader; 

public class MapMain {	
	public static void main(String[] args) throws IOException {	   
	    URL fxmlUrl = MapMain.class.getClassLoader().getResource("object/map/map.fxml");	   	
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(fxmlUrl);
       
        // прочитать корневой элемент <HashMap> fxml-документа.
	    HashMap items = loader.<HashMap>load();
	    System.out.println("Map:" + items); 
             
	}
	
}
