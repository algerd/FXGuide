
package beans.binding.Bindings;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
selectXXX() method of the Bindings class is used to create a binding for a nested property. 
In the nested hierarchy, all classes and properties must be public.

The program prints the values of the zip property twice: once for its default value and once for its changed value. 
At the end, it tries to bind a nonexistent property p.addr.state. Binding to a nonexistent property is not a runtime error. 
When I ran the program in the latest Java Development Kit 8 release, accessing the property p.addr.state resulted in a runtime
NoSuchMethodException that seems to be a bug; earlier it returned null without throwing the exception.
*/
public class BindNestedProperty {
    
	public static class Address {       
		private StringProperty zip = new SimpleStringProperty("36106");

		public StringProperty zipProperty() {
			return zip;
		}

		public String getZip() {
			return zip.get();
		}

		public void setZip(String newZip) {
			zip.set(newZip);
		}
	}

	public static class Person {
		private ObjectProperty<Address> addr = new SimpleObjectProperty<>(new Address());

		public ObjectProperty<Address> addrProperty() {
			return addr;
		}

		public Address getAddr() {
			return addr.get();
		}

		public void setZip(Address newAddr) {
			addr.set(newAddr);
		}
	}

	public static void main(String[] args) {
		ObjectProperty<Person> p = new SimpleObjectProperty<>(new Person());
		
		// Bind p.addr.zip
		StringBinding zipBinding = Bindings.selectString(p, "addr", "zip");
		System.out.println(zipBinding.get());

		// Change the zip
		p.get().addrProperty().get().setZip("35217");
		System.out.println(zipBinding.get());

		// Bind p.addr.state, which does not exist - throw exception
		//StringBinding stateBinding = Bindings.selectString(p, "addr", "state");
		//System.out.println(stateBinding.get());
	}
}
