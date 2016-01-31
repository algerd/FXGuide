
package collections.ObservableArray;

import com.sun.javafx.collections.ObservableIntegerArrayImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableFloatArray;
import javafx.collections.ObservableIntegerArray;

/*
ObservableArray можно создавать только с типами данных int и float.

Объекты ObservableArray типа можно создавать напрямую через реализации классов:
    new ObservableFloatArrayImpl()
    new ObservableFloatArrayImpl(float... elements)
    new ObservableFloatArrayImpl(ObservableFloatArray src)
    new ObservableIntegerArrayImpl()
    new ObservableIntegerArrayImpl(int... elements)
    new ObservableIntegerArrayImpl(ObservableIntegerArray src)

Или же используя фабричные методы класса FXCollections, которые полностью повторяют создание объектов
через реализации классов ObservableFloatArrayImpl и ObservableIntegerArrayImpl:
    ObservableFloatArray observableFloatArray()
    ObservableFloatArray observableFloatArray(float... values)
    ObservableFloatArray observableFloatArray(ObservableFloatArray array)
    ObservableIntegerArray observableIntegerArray()
    ObservableIntegerArray observableIntegerArray(int... values)
    ObservableIntegerArray observableIntegerArray(ObservableIntegerArray array)

FXCollections не предоставляет никакой дополнительной функциональности для ObservableArray.
*/
public class ObservableArrayTest {
    
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        
        // Create a array with some elements
        ObservableIntegerArray intArray = new ObservableIntegerArrayImpl(1, 2, 3);
        System.out.println("After creating int array: " + intArray);
        
        // Add some more elements to the array
        intArray.addAll(4, 5, 6);
        System.out.println("After adding elements: " + intArray);
        
        // Create ObservableFloatArray
        float[] arr = {1.1f, 1.5f, 3.78f};
        ObservableFloatArray floatArray = FXCollections.observableFloatArray(arr);
        System.out.println("After creating float array: " + floatArray);
        
        // Add some more elements to the array
        floatArray.addAll(4, 5.5f, 6);
        System.out.println("After adding elements: " + floatArray);     
    }

}
