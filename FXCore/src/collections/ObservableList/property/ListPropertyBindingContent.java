
package collections.ObservableList.property;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

/*
В отличие от bind() и bindBidirectional(), которые связывают ссылки объектов-списков у свойств,
bindContent() и bindContentBidirectional() связывают содержимое списков, сохраняя ссылки на списки
у свойств несвязанными.

The bindContent() and bindContentBidirectional() methods let you bind the content of the
ObservableList that is wrapped in a ListProperty to the content of another ObservableList in one
direction and both directions, respectively. Make sure to use the corresponding methods, unbindContent()
and unbindContentBidirectional(), to unbind contents of two observable lists.

You can also use methods of the Bindings class to create bindings for references and contents of
observable lists.

It is allowed, but not advisable, to change the content of a ListProperty whose content has been bound
to another ObservableList. In such cases, the bound ListProperty will not be synchronized with its target
list.
*/
public class ListPropertyBindingContent {

	public static void main(String[] args) {
        
		ListProperty<String> lp1 = new SimpleListProperty<>(FXCollections.observableArrayList());
		ListProperty<String> lp2 = new SimpleListProperty<>(FXCollections.observableArrayList());

		// Bind the content of lp1 to the content of lp2
		lp1.bindContent(lp2);

		/* At this point, you can change the content of lp1. However, 
		 * that will defeat the purpose of content binding, because the
		 * content of lp1 is no longer in sync with the content of lp2.
		 * Do not do this:
		 * lp1.addAll("X", "Y");
		 */
		print("Before lp2.addAll():", lp1, lp2);
		lp2.addAll("1", "2");
		print("After lp2.addAll():", lp1, lp2);

		lp1.unbindContent(lp2);
        lp2.addAll("Three");
		print("After lp1.unbindContent(lp2):", lp1, lp2);

		// Bind lp1 and lp2 contents bidirectionally
		lp1.bindContentBidirectional(lp2);

		print("Before lp1.addAll():", lp1, lp2);
		lp1.addAll("3", "4");
		print("After lp1.addAll():", lp1, lp2);

		print("Before lp2.addAll():", lp1, lp2);
		lp2.addAll("5", "6");
		print("After lp2.addAll():", lp1, lp2);
	}

	public static void print(String msg, ListProperty<String> lp1, ListProperty<String> lp2) {
		System.out.println(msg + " lp1: " + lp1.get() + ", lp2: " + lp2.get());
	}
}
