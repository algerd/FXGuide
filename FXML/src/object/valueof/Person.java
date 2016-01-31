
package object.valueof;

import javafx.fxml.FXML;

public class Person {
    
    private String name;
    
    private Person(String str) {
        name = str;
    }
    
    @FXML
    public static Person valueOf(String str) {     
        return new Person(str);
    }

}

