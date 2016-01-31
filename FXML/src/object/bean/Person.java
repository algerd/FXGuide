
package object.bean;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Person соответствует JavaBean-конструкции:
 *  - имеет публичный безаргументный конструктор
 *  - правильно именнованные в соответствии с соглашением геттеры и сеттеры свойств,
 *    которые будут доступны в fxml-документе
 *  Свойствами могут быть как простые поля, так и JavaBean-property     
 */
public class Person {
    
    private Integer age;
    private String name;
    private IntegerProperty index = new SimpleIntegerProperty(this, "index", 0);
    private StringProperty city = new SimpleStringProperty(this, "city", "Unknown");
      
    public Person() {     
    }

    public Integer getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
       
    // index - методы
    public final IntegerProperty indexProperty() {
        return index;
    }
    public final Integer getIndex() {
        return index.get();
    }
    public final void setIndex(Integer index) {
        this.index.set(index);
    }
     
    // city - методы
    public final StringProperty cityProperty() {
        return city;
    }
    public final String getCity() {
        return city.get();
    }
    public final void setCity(String city) {
        this.city.set(city);
    }
    
    
    @Override
    public String toString() {
        return "name:" + getName() + " age:" + getAge() + " city:" + getCity() + " index:" + getIndex();
    }
}
