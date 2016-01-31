
package property;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;


public class PersonBean { 
    public PersonBean() {}
    public PersonBean(int age, String name) {
        // this.age.set(age);   //можно напрямую
        this.setAge(age);
        //this.name.set(name);  //можно напрямую
        this.setName(name);
    }
    
    /**
    SimpleXxxProperty создаёт read/write свойство для Xxx типа.
    
    The default value for the initial value depends on the type of the property. 
    It is zero for numeric types, false for boolean types, and null for reference types.
    
    A property object can be part of a bean or it can be a standalone object. The specified bean is the
    reference to the bean object that contains the property. For a standalone property object, it can be null.
    Its default value is null.
    The name of the property is its name. If not supplied, it defaults to an empty string.
    
    Every property class has getBean() and getName() methods that return the bean reference and the
    property name, respectively.    
    */   
    //SimpleIntegerProperty():
    private IntegerProperty age = new SimpleIntegerProperty();
    
    //SimpleIntegerProperty(int initialValue):
    private IntegerProperty age1 = new SimpleIntegerProperty(16);
    
    //SimpleIntegerProperty(Object bean, String name):
    private IntegerProperty age2 = new SimpleIntegerProperty(this, "age2");
    
    //SimpleIntegerProperty(Object bean, String name, int initialValue)
    private IntegerProperty age3 = new SimpleIntegerProperty(this, "age3", 16);
       
    /*
    You declare a public getter for the property, which is named, by convention, as XXXProperty, where XXX
    is the name of the property. This getter returns the reference of the property.
    */
    public final IntegerProperty ageProperty() {
        return age;
    }
    
    /*
    According to the JavaFX design patterns, and not for any technical requirements, a JavaFX property
    has a getter and a setter that are similar to the getters and setters in JavaBeans. The return type of the
    getter and the parameter type of the setter are the same as the type of the property value. For example, for
    StringProperty and IntegerProperty, they will be String and int, respectively.
    
    Property classes provide two pairs of getter and setter methods: get()/set() and getValue()/ setValue().
    Для примитивные типов свойств get()/set() - возвращают и передают примитивные двнные, 
    getValue()/ setValue() - возвращают и передают объектные данные путём автобоксинга (анбоксинга и боксинга соответственно).
    Для объектных типов свойств get()/set() и getValue()/ setValue() работают одинаково и возвращают и передают объектные данные.

    */
    public final int getAge() {
        return age.get();
    }
     public final void setAge(int age) {
        this.age.set(age);
    }
     
     
    /************************************************************
    ReadOnlyXxxWrapper создаёт read свойство для Xxx типа.
    ReadOnlyXxxWrapper имеет такие же 4 конструктора как и SimpleXxxProperty(см. выше)
    A ReadOnlyXXXWrapper class wraps two properties of XXX type:
      - read-only: ReadOnlyXxxProperty из ReadOnlyXxxWrapper:getReadOnlyProperty() - передаётся наружу класса
      - read/write: ReadOnlyXxxWrapper - используется только внутри класса
    Both properties are synchronized. 
    */
    ReadOnlyStringWrapper name = new ReadOnlyStringWrapper(this, "name", "Unknown");
    
    /*
    There is no setter for the property value. You may declare one; however, it must be private.
    The getter for the property value works the same as for a read/write property.
    */
    public final String getName() {
        return name.get();
    }
    // сеттер только для использования внутри класса - использован при инициализации свойства namme в конструкторе
    private final void setName(String name) {
        this.name.set(name);
    }
    /*
    nameProperty() возвращает свойство типа ReadOnlyXxxProperty , которое не имеет сеттеров, т.е. только для чтения.
    */
    public final ReadOnlyStringProperty nameProperty() {
        return name.getReadOnlyProperty();
    }
    
    /************************************* Main ***********************************************/
    public static void main(String[] arg) {
        
        PersonBean pers = new PersonBean(25, "Alex");
        
        System.out.println("age=" + pers.getAge());
        System.out.println("name=" + pers.getName());
        
        pers.setAge(30);
        pers.ageProperty().set(30);
        //pers.nameProperty().set("John");  // error - nameProperty() только read only и там нет сеттера
        
        System.out.println("age=" + pers.ageProperty().get());
        System.out.println("name=" + pers.nameProperty().get());             
    }

}
