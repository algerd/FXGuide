
package object.bean;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader; 

public class BeanMain {	
    
	public static void main(String[] args) throws IOException {	   
	    URL fxmlUrl = BeanMain.class.getClassLoader().getResource("object/bean/bean.fxml");	   	
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(fxmlUrl);
       
        // прочитать корневой элемент <ArrayList> fxml-документа.
	    ArrayList items = loader.<ArrayList>load(); 
        for( Object person : items) {
            System.out.println(person);
        }
	}
	
}
