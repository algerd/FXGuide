
package TreeView;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/*
The TreeView class contains a root property. Its type is TreeItem<T> and it represents the root node.
You can create an empty TreeView and set its root node later using the setRoot() method:
    // Create an empty TreeView whose TreeItems value is String
    TreeView<String> treeView = new TreeView<>();
    // Set the root node
    treeView.setRoot(depts);

A TreeItem stores all its children in an ObservableList. The getChildren() method returns the
reference of the list.

A TreeItem supplies the data for a node. It has the following properties:
    • expanded
    • graphic
    • leaf
    • parent
    • value

The expanded property indicates whether a TreeItem is expanded. It is true if the TreeItem is in the
expanded state. Otherwise, it is false. You can expand a node using the setExpanded(true) method.
A TreeItem may optionally contain an icon represented by its graphic property. The type of the
graphic property is Node, and therefore, you can use any node for the graphic property. Typically,
a 16-by-16 image is used.

The leaf property indicates whether a TreeItem has children. It is true if the TreeItem has no children.
Otherwise, it is false. It is a read-only property.

Every TreeItem in a TreeView, except the root TreeItem, has a parent TreeItem. The parent property is
a read-only property that contains the parent of the TreeItem.

The value property stores the application-specific data for a TreeItem. Its type is the same as the
generic type of the TreeItem class.

You can get the ObservableList of children of a TreeItem using the getChildren() method. You can
traverse the tree up or down from a TreeItem using the getParent() and getChildren() methods recursively.
The TreeItem class provides several constructors to create an empty TreeItem, a TreeItem with a value,
and a TreeItem with a value and graphic

In this programm getTreeView() method shows how to create and populate a TreeView. 
It returns the reference of the TreeView. When you need a TreeView in an example, you will use this method.
*/
@SuppressWarnings("unchecked")
public class TreeViewUtil {
	public static TreeView<String> getTreeView() {
		TreeItem<String> depts = new TreeItem<>("Departments");

		// Add items to depts
		TreeItem<String> isDept = new TreeItem<String>("IS");
		TreeItem<String> claimsDept = new TreeItem<String>("Claims");
		TreeItem<String> underwritingDept = new TreeItem<String>("Underwriting");
		depts.getChildren().addAll(isDept, claimsDept, underwritingDept);

		// Add employees for each dept
		isDept.getChildren().addAll(
            new TreeItem<String>("Doug Dyer"),
            new TreeItem<String>("Jim Beeson"),
            new TreeItem<String>("Simon Ng")
        );      

		claimsDept.getChildren().addAll(
            new TreeItem<String>("Lael Boyd"),
            new TreeItem<String>("Janet Biddle")
        );

		underwritingDept.getChildren().addAll(
            new TreeItem<String>("Ken McEwen"),
            new TreeItem<String>("Ken Mann"),
            new TreeItem<String>("Lola Ng")
        );

		// Craete a TreeView with depts as its root item
		TreeView<String> treeView = new TreeView<>(depts);
        
        /*
        In a TreeView, you can hide the root node by setting the value for its showRoot property to false. By default,
        the root node is visible. Call setShowRoot(false) of the TreeView to hide the root node. Hiding root node
        makes traversing the TreeView a little easier as the user has one less level of indentation to traverse. Hiding
        the root node shows its child nodes at the first level.
        */
        // Hide the root node
        //treeView.setShowRoot(false);
        
        /*
        A TreeItem fires events as it is modified, for example, by adding or removing children or expanding or
        collapsing. An instance of the TreeModificationEvent class, which is a static inner class of the TreeItem
        class, represents all kinds of modification events. Different types of events are represented by different event
        types. It is a little strange that the TreeItem class does not contain constants for those event types. Rather, it
        contains static methods that return those event types. For example, the TreeItem.branchCollapsedEvent()
        static method returns the event type of the event that is fired when a TreeItem is collapsed.
        
        Event types are arranged in a hierarchy. The TreeNotification event type is at the top of the hierarchy.
        It is the parent of all event types for TreeItem. You can add an event handler for this event type to a TreeItem
        and it will listen for all event types for a TreeItem. The following three event types are the direct subtypes of
        the TreeNotification event type:
            • ValueChanged
            • GraphicChanged
            • TreeItemCountChange
        
        The ValueChanged and GraphicChanged event types are fired when the value and graphic properties,
        respectively, of the TreeItem change. The TreeItemCountChange event type is fired when the TreeItem is
        expanded, collapsed, or its children list is changed. It has three subtypes to handle the specific events:
            • BranchExpanded
            • BranchCollapsed
            • ChildrenModification
        
        You should add event handlers for specific type of events for better performance. When an event occurs
        on a TreeItem, all the registered listeners are called. The event bubbles up the TreeItem chain following the
        parent of the TreeItem until the root TreeItem is reached. Therefore, if you want to handle a specific event
        on all TreeItems, add an event handler only to the root TreeItem. The following snippet of code creates a
        TreeView with a root node. It adds BranchExpanded and BranchCollapsed event handlers to the root node.
        These event handlers will be called whenever any branch in the TreeView is expanded or collapsed. The
        handlers print a message on the standard output about the node being expanded or collapsed.      
        */
        // Add BranchExpended event handler
        depts.addEventHandler(
            TreeItem.<String>branchExpandedEvent(), 
            e -> System.out.println("Node expanded: " + e.getSource().getValue())
        );
        
        // Add BranchCollapsed event handler
        depts.addEventHandler(
            TreeItem.<String>branchCollapsedEvent(),
            e -> System.out.println("Node collapsed: " + e.getSource().getValue())
        );
	
		return treeView;
	}
}
