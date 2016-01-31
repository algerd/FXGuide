
package collections.ObservableList;

import java.util.List;
import javafx.collections.ListChangeListener;

/*
Sometimes you may want to analyze changes to a list in more detail rather than just knowing that the list
has changed. The ListChangeListener.Change object that is passed to the onChanged() method contains a
report to a change performed on the list. You need to use a combination of its methods to know the details of
a change:
    ObservableList<E> getList() General
    boolean next()
    void reset()                Cursor movement
    boolean wasAdded()
    boolean wasRemoved()
    boolean wasReplaced()
    boolean wasPermutated()
    boolean wasUpdated()        Change type
    int getFrom()
    int getTo()                 Affected range
    int getAddedSize()
    List<E> getAddedSubList()   Addition
    List<E> getRemoved()
    int getRemovedSize()        Removal
    int getPermutation(int oldIndex) Permutation

The getList() method returns the source list after changes have been made. A ListChangeListener.
Change object may report a change in multiple chunks. This may not be obvious at first. Consider the
following snippet of code:
    ObservableList<String> list = FXCollections.observableArrayList();
    // Add a change listener here...
    list.addAll("one", "two", "three");
    list.removeAll("one", "three");

In this code, the change listener will be notified twice: once for the addAll() method call and once for
the removeAll() method call. The ListChangeListener.Change object reports the affected range of indexes.
In the second change, you remove two elements that fall into two different ranges of indexes. Note that there
is an element "two" between the two removed elements. In the second case, the Change object will contain
a report of two changes. The first change will contain the information that, at index 0, the element "one" has
been removed. Now, the list contains only two elements with the index 0 for the element "two" and index
1 for the element "three". The second change will contain the information that, at index 1, the element
"three" has been removed.

A Change object contains a cursor that points to a specific change in the report. The next() and reset()
methods are used to control the cursor. When the onChanged() method is called, the cursor points before the
first change in the report. Calling the next() method the first time moves the cursor to the first change in the
report. Before attempting to read the details for a change, you must point the cursor to the change by calling
the next() method. The next() method returns true if it moves the cursor to a valid change. Otherwise, it
returns false. The reset() method moves the cursor before the first change. Typically, the next() method
is called in a while-loop, as shown in the following snippet of code:
    ObservableList<String> list = FXCollections.observableArrayList();
    ...
    // Add a change listener to the list
    list.addListener(new ListChangeListener<String>() {
        @Override
        public void onChanged(ListChangeListener.Change<? extends String> change) {
            while(change.next()) {
                // Process the current change here...
            }
        }
    });
In the change type category, methods report whether a specific type of change has occurred. The
wasAdded() method returns true if elements were added. The wasRemoved() method returns true if
elements were removed. The wasReplaced() method returns true if elements were replaced. You can think
of a replacement as a removal followed by an addition at the same index. If wasReplaced() returns true,
both wasRemoved() and wasAdded() return true as well. The wasPermutated() method returns true if
elements of a list were permutated (i.e., reordered) but not removed, added, or updated. The wasUpdated()
method returns true if elements of a list were updated.

In the affected range type category, the getFrom() and getTo() methods report the range of indexes
affected by a change. The getFrom() method returns the beginning index and the getTo() method returns
the ending index plus one. If the wasPermutated() method returns true, the range includes the elements
that were permutated. If the wasUpdated() method returns true, the range includes the elements that were
updated. If the wasAdded() method returns true, the range includes the elements that were added. If the
wasRemoved() method returns true and the wasAdded() method returns false, the getFrom() and getTo()
methods return the same number—the index where the removed elements were placed in the list.

The getAddedSize() method returns the number of elements added. The getAddedSubList() method
returns a list that contains the elements added. The getRemovedSize() method returns the number of
elements removed. The getRemoved() method returns an immutable list of removed or replaced elements. The
getPermutation(int oldIndex) method returns the new index of an element after permutation. For example,
if an element at index 2 moves to index 5 during a permutation, the getPermutation(2) will return 5.

The PersonListChangeListener class is a change listener class. It implements the onChanged() method 
of the ListChangeListener interface to handle all types of change notifications for
an ObservableList of Person objects.
*/
public class PersonListChangeListener implements ListChangeListener<Person> {
    
	@Override
	public void onChanged(ListChangeListener.Change<? extends Person> change) {
		while (change.next()) {
			if (change.wasPermutated()) {
				handlePermutated(change);
			}
			else if (change.wasUpdated()) {
			    handleUpdated(change);
			}
			else if (change.wasReplaced()) {
			    handleReplaced(change);
			}
			else {
				if (change.wasRemoved()) {
				    handleRemoved(change);
				}
				else if (change.wasAdded()) {
				    handleAdded(change);
				}
			}
		}
	}

	public void handlePermutated(ListChangeListener.Change<? extends Person> change) {
		System.out.println("Change Type: Permutated");		
		System.out.println("Permutated Range: " + getRangeText(change));		
		int start = change.getFrom();
		int end = change.getTo();
		for(int oldIndex = start; oldIndex < end; oldIndex++) {
			int newIndex = change.getPermutation(oldIndex);
			System.out.println("index[" + oldIndex + "] moved to " + 
			                   "index[" + newIndex + "]");
		}
	}

	public void handleUpdated(ListChangeListener.Change<? extends Person> change) {
		System.out.println("Change Type: Updated");	    
		System.out.println("Updated Range : " + getRangeText(change));		
		System.out.println("Updated elements are: " + 
			change.getList().subList(change.getFrom(), change.getTo()));
	}

	public void handleReplaced(ListChangeListener.Change<? extends Person> change) {
		System.out.println("Change Type: Replaced");

		// A "replace" is the same as a “remove” followed with an "add"
		handleRemoved(change);
		handleAdded(change);
	}	
	
	public void handleRemoved(ListChangeListener.Change<? extends Person> change) {
		System.out.println("Change Type: Removed");

		int removedSize = change.getRemovedSize();
		List<? extends Person> subList = change.getRemoved();

		System.out.println("Removed Size: " + removedSize);
		System.out.println("Removed Range: " + getRangeText(change));
		System.out.println("Removed List: " + subList);    
	}	
	
	public void handleAdded(ListChangeListener.Change<? extends Person> change) {
		System.out.println("Change Type: Added");
		
		int addedSize = change.getAddedSize();
		List<? extends Person> subList = change.getAddedSubList();
		
		System.out.println("Added Size: " + addedSize);
		System.out.println("Added Range: " + getRangeText(change));
		System.out.println("Added List: " + subList);	
	}
	
	public String getRangeText(ListChangeListener.Change<? extends Person> change) {
		return "[" + change.getFrom() + ", " + change.getTo() + "]";
	}
}
