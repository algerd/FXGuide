
package beans.binding.fluent;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/*
ObjectExpression and ObjectBinding classes create bindings of any type of objects.
Their class diagram is very similar to that of the StringExpression and StringBinding classes. The
ObjectExpression class has methods to compare objects for equality and to check for null values.
*/
public class ObjectBindingTest {
    
	public static void main(String[] args) {
        
		Book b1 = new Book("J1", 90, "1234567890");
		Book b2 = new Book("J2", 80, "0123456789");
        
		ObjectProperty<Book> book1 = new SimpleObjectProperty<>(b1);
		ObjectProperty<Book> book2 = new SimpleObjectProperty<>(b2);

		// Create a binding that computes if book1 and book2 are equal
		BooleanBinding isEqual = book1.isEqualTo(book2);
		System.out.println(isEqual.get());

		book2.set(b1);
		System.out.println(isEqual.get());
	}
}
