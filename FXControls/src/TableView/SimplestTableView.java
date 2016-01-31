
package TableView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
An instance of the TableColumn class represents a column in a TableView. A TableColumn is responsible
for displaying and editing the data in its cells. A TableColumn has a header that can display header text,
a graphic, or both. You can have a context menu for a TableColumn, which is displayed when the user
right-clicks inside the column header. Use the contextMenu property to set a context menu.

The TableColumn<S, T> class is a generic class. The S parameter is the items type, which is of the same
type as the parameter of the TableView. The T parameter is the type of data in all cells of the column.
The following snippet of code creates a TableColumn with First Name as its header text (See PersonTableView.java):
    TableColumn<Person, String> fNameCol = new TableColumn<>("First Name");

A TableColumn needs to know how to get the value (or data) for its cells from the model. To populate the
cells, you need to set the cellValueFactory property of the TableColumn. If the model for a TableView contains
objects of a class that is based on JavaFX properties, you can use an object of the PropertyValueFactory class as
the cell value factory, which takes the property name. It reads the property value from the model and populates
all of the cells in the column, as in the following code (See PersonTableView.java):
    // Use the firstName property of Person object to populate the column cells
    PropertyValueFactory<Person, String> fNameCellValueFactory = new PropertyValueFactory<>("firstName");
    fNameCol.setCellValueFactory(fNameCellValueFactory);

You need to create a TableColumn object for each column in the TableView and set its cell value factory
property.

The last step in setting up a TableView is to add TableColumns to its list of columns. A TableView stores
references of its columns in an ObservableList<TableColumn> whose reference can be obtained using the
getColumns() method of the TableView:
    // Add the First Name column to the TableView
    table.getColumns().add(fNameCol);

TableView supports nesting of columns. For example, you can have two columns, First and Last,
nested inside a Name column. A TableColumn stores the list of nested columns in an observable list whose
reference can be obtained using the getColumns() method of the TableColumn class. The innermost
nested columns are known as leaf columns. You need to add the cell value factories for the leaf columns.
Nested columns only provide visual effects. The following snippet of code creates a TableView and
adds an Id column and two leaf columns, First and Last, that are nested in the Name column. 
Note that you add the topmost columns to the TableView, not the nested columns. 
TableView takes care of adding all nested columns for the topmost columns. There is no limit on
the level of column nesting.

The following methods in the TableView class provide information about visible leaf columns:
    TableColumn<S,?> getVisibleLeafColumn(int columnIndex)
    ObservableList<TableColumn<S,?>> getVisibleLeafColumns()
    int getVisibleLeafIndex(TableColumn<S,?> column)

The getVisibleLeafColumn() method returns the reference of the column for the specified
column index. The column index is counted only for visible leaf column and the index starts at zero.
The getVisibleLeafColumns() method returns an observable list of all visible leaf columns. The
getVisibleLeafIndex() method returns the column reference for the specified column index of a visible
leaf column.

By default, all columns in a TableView are visible. The TableColumn class has a visible property to set the
visibility of a column. If you turn off the visibility of a parent column, a column with nested columns, all of its
nested columns will also be invisible.
Sometimes you may want to let the user control the visibility of columns. The TableView class has a
tableMenuButtonVisible property. If it is set to true, a menu button is displayed in the header area.
Clicking the menu button displays a list of all leaf columns. Columns are displayed as radio menu
items that can be used to toggle their visibility.
*/
@SuppressWarnings("unchecked")
public class SimplestTableView extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a TableView with a list of persons
		TableView<Person> table = new TableView<>(PersonTableUtil.getPersonList());
        
        // Create Name column and nest First and Last columns in it
        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        nameCol.getColumns().addAll(
            PersonTableUtil.getFirstNameColumn(), 
            PersonTableUtil.getLastNameColumn()
        );
		
		// Add columns to the TableView
		table.getColumns().addAll(
            PersonTableUtil.getIdColumn(), 
            nameCol,
            PersonTableUtil.getBirthDateColumn()
        );
        
        // Make the Id column invisible
        //nameCol.setVisible(false);
        
        // Make the table menu button visible
        table.setTableMenuButtonVisible(true);
              
		VBox root = new VBox(table);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;"
        );		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Simplest TableView");
		stage.show();
	}
}
