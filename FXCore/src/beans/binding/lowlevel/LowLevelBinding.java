
package beans.binding.lowlevel;

import java.util.Formatter;
import java.util.Locale;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
Using the low-level binding API involves the following three steps:
    1. Create a class that extends one of the binding classes. For example, if you want to
        create a DoubleBinding, you need to extend the DoubleBinding class.
    2. Call the bind() method of the superclass to bind all dependencies. Note that
        all binding classes have a bind() method implementation. You need to call this
        method passing all dependencies as arguments. Its argument type is a varargs of
        Observable type.
    3. Override the computeValue() method of the superclass to write the logic for your
        binding. It calculates the current value of the binding. Its return type is the same
        as the type of the binding, for example, it is double for a DoubleBinding, String
        for a StringBinding, and so forth.

Additionally, you can override some methods of the binding classes to provide more functionality to
your binding. You can override the dispose() method to perform additional actions when a binding is
disposed. The getDependencies() method may be overridden to return the list of dependencies for the
binding. Overriding the onInvalidating() method is needed if you want to perform additional actions
when the binding becomes invalid.

The code creates an anonymous class, which extends the abstract DoubleBinding class.
It calls the bind() method, passing the reference of the radius property. An anonymous class does not have
a constructor, so you have to use an instance initializer to call the bind() method. The computeValue()
method computes and returns the area of the circle. The radius property has been declared final, because
it is being used inside the anonymous class.
The program overrides the computeValue() method for the area binding. 
For the description binding, it overrides the dispose(), getDependencies(), and onInvalidating() methods as well.
*/
public class LowLevelBinding {
    
	public static void main(String[] args) {
        
		final DoubleProperty radius = new SimpleDoubleProperty(7.0);
		final DoubleProperty area = new SimpleDoubleProperty(0);
	
		DoubleBinding areaBinding = new DoubleBinding() { 
			{
				this.bind(radius);	
			}

			@Override
			protected double computeValue() {
				double r = radius.get();
				double area = Math.PI * r *r;
				return area;
			}
		};
		
		// Bind area to areaBinding
		area.bind(areaBinding);

		// Create a StringBinding
		StringBinding desc = new StringBinding() {
			{
				this.bind(radius, area);
			}
			
			@Override
			protected String computeValue() {				
				Formatter f = new Formatter();
				f.format(Locale.US, "Radius = %.2f, Area = %.2f", 
				         radius.get(), area.get());
				String desc = f.toString();
				return desc;
			}
			
			@Override
			public ObservableList<?> getDependencies() {
				return FXCollections.unmodifiableObservableList(
							FXCollections.observableArrayList(radius, area)
						);
			}
		
			@Override
			public void dispose() {
				System.out.println("Description binding is disposed.");
			}
			
			@Override
			protected void onInvalidating() {		        
				System.out.println("Description is invalid.");
			}
		};
		
		System.out.println(desc.getValue());

		// Change the radius
		radius.set(14.0);
		System.out.println(desc.getValue());		
	}
}
