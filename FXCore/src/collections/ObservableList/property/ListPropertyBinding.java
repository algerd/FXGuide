
package collections.ObservableList.property;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/*
A ListProperty exposes two properties, size and empty, which are of type ReadOnlyIntegerProperty
and ReadOnlyBooleanProperty, respectively. You can access them using the sizeProperty() and
emptyProperty() methods. The size and empty properties are useful for binding in GUI applications.
For example, the model in a GUI application may be backed by a ListProperty, and you can bind these
properties to the text property of a label on the screen. When the data changes in the model, the label
will be updated automatically through binding. The size and empty properties are declared in the
ListExpression class.
*/
public class ListPropertyBinding {
    
	public static void main(String[] args) {
        
		ListProperty<String> lp = new SimpleListProperty<>(FXCollections.observableArrayList());
		
		// Bind the size and empty properties of the ListProperty to create a description of the list
		StringProperty initStr = new SimpleStringProperty("Size: " );
		StringProperty desc = new SimpleStringProperty();
		desc.bind(initStr.concat(lp.sizeProperty())
		                 .concat(", Empty: ")
		                 .concat(lp.emptyProperty())
		                 .concat(", List: ")
		                 .concat(lp.asString()));
		
		System.out.println("Before addAll(): " + desc.get());
		lp.addAll("John", "Jacobs");
		System.out.println("After addAll(): " + desc.get());
	}
}
