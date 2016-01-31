
package object.factory;

import javafx.fxml.FXML;

public class Person {
      
    private Person() {}
    
    @FXML
    public static Person createPerson() {     
        return new Person();
    }

}

