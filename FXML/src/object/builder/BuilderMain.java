
package object.builder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader; 
import javafx.util.BuilderFactory;

public class BuilderMain {	
	public static void main(String[] args) throws IOException {	   
	    // Use the Builder with property getter and setter
	    loadItems(new ItemBuilderFactory());
		
	    // Use the Builder with Map
		loadItems(new ItemBuilderFactoryMap());	    
	}
	
	public static void loadItems(BuilderFactory builderFactory) throws IOException {
	           
        URL fxmlUrl = BuilderMain.class.getClassLoader().getResource("object/builder/items.fxml");	   	
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(fxmlUrl);

        /**
         * Задаём кастомную BuilderFactory. 
         */ 
	    loader.setBuilderFactory(builderFactory);
        
        // прочитать корневой элемент <ArrayList> fxml-документа.
	    ArrayList items = loader.<ArrayList>load();
	    System.out.println("List:" + items);  
	}
}
