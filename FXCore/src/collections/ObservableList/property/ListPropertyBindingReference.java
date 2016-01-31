
package collections.ObservableList.property;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

/*
Methods to support high-level binding for a list property are in the ListExpression and Bindings
classes. Low-level binding can be created by subclassing the ListBinding class. A ListProperty supports
two types of bindings:
    • Binding the reference of the ObservableList that it wraps
    • Binding the content of the ObservableList that it wraps

The bind() and bindBidirectional() methods are used to create the first kind of binding. 
The program shows how to use these methods. 
As shown in the output below, notice that both list properties 
have the reference of the same ObservableList after binding!!!

В отличие от bindContent() и bindContentBidirectional(), которые связывают только содержимое объектов-списков у свойств,
сохраняя ссылки на списки у свойств несвязанными, bind() и bindBidirectional() связывают ссылки объектов-списков у свойств. 
*/
public class ListPropertyBindingReference {

	public static void main(String[] args) {
        
		ListProperty<String> lp1 = new SimpleListProperty<>(FXCollections.observableArrayList());
		ListProperty<String> lp2 = new SimpleListProperty<>(FXCollections.observableArrayList());

		lp1.bind(lp2);

		print("Before addAll():", lp1, lp2);
		lp1.addAll("One", "Two");
		print("After addAll():", lp1, lp2);

		// Change the reference of the ObservableList in lp2
		lp2.set(FXCollections.observableArrayList("1", "2"));
		print("After lp2.set():", lp1, lp2);

		// Cannot do the following as lp1 is a bound property
		// lp1.set(FXCollections.observableArrayList("1", "2"));
		// Unbind lp1
		lp1.unbind();
        // lp1 и lp2 ссылаются на один объект-список и поэтому они ещё не отвязаны друг от друга 
        lp2.addAll("Three");
		print("After unbind():", lp1, lp2);
        // lp2 передана ссылка на новый объект-ссылку и теперь lp1 и lp2 ссылаются на разные объекты, т.е. полностью отвязаны
        lp2.set(FXCollections.observableArrayList("4", "5"));
        print("After unbind() with new reference lp2:", lp1, lp2);
        
		// Bind lp1 and lp2 bidirectionally
		lp1.bindBidirectional(lp2);
		print("After bindBidirectional():", lp1, lp2);

		lp1.set(FXCollections.observableArrayList("X", "Y"));
		print("After lp1.set():", lp1, lp2);
	}

	public static void print(String msg, ListProperty<String> lp1, ListProperty<String> lp2) {
		System.out.println(msg);
		System.out.println("lp1: " + lp1.get() + ", lp2: " + lp2.get() + 
		                   ", lp1.get() == lp2.get(): " + (lp1.get() == lp2.get()));
		System.out.println("---------------------------");
	}
}
