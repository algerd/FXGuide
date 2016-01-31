
package TreeTableView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
An instance of the TreeTableColumn class represents a column in a TreeTableView. The getColumns()
method of the TreeTableView class returns an ObservableList of TreeTableColumns, which are columns
that are added to the TreeTableView. You need to add columns to this columns list.

TableCell contains tableColumn, tableRow, and tableView properties that store the references of its
TableColumn, TableRow, and TableView, respectively. These properties are useful to access the item in the
data model that represents the row for the TableCell.

TreeTableCell contains tableColumn, tableRow, and treeTableView properties that store the
references of its TreeTableColumn, TreeTableRow, and TreeTableView, respectively. These properties are
useful to access the item in the model that represents the row for the cell.

The following subclasses of TreeTableCell render cell data in different ways. For example, a
CheckBoxTreeTableCell renders cell data in a CheckBox and a ProgressBarTreeTableCell renders a
number using a ProgressBar:
    • CheckBoxTreeTableCell
    • ChoiceBoxTreeTableCell
    • ComboBoxTreeTableCell
    • ProgressBarTreeTableCell
    • TextFieldTreeTableCell

The CheckBox, ChoiceBox, ComboBox, and TextField versions of the XxxTreeTableCell are used to edit
data in cells.
*/
public class TreeTableViewTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		TreeItem<Person> rootNode = TreeTableUtil.getModel();
		rootNode.setExpanded(true);

		// Create a TreeTableView with model
		TreeTableView<Person> treeTable = new TreeTableView<>(rootNode);
		treeTable.setPrefWidth(400);
        
        /*
        The following snippet of code creates a computed column. It sets the cell factory for the column to
        display a CheckBox. If the Person falls in the baby age category, the CheckBox is selected.
        */
        // Create a "Baby?" column
        TreeTableColumn<Person, Boolean> babyCol = new TreeTableColumn<>("Baby?");
        babyCol.setCellValueFactory(cellData -> {
            Person p = cellData.getValue().getValue();
            Boolean v = (p.getAgeCategory() == Person.AgeCategory.BABY);
            return new ReadOnlyBooleanWrapper(v);
        });
        // Set a cell factory that will use a CheckBox to render the value
        babyCol.setCellFactory(CheckBoxTreeTableCell.<Person>forTreeTableColumn(babyCol));
               
        TreeTableColumn<Person, String> lNameCol = TreeTableUtil.getLastNameColumn();
        TreeTableColumn<Person, LocalDate> birthDateCol = TreeTableUtil.getBirthDateColumn();       
		// Add columns
		treeTable.getColumns().addAll(
            TreeTableUtil.getFirstNameColumn(),
            lNameCol, 
            birthDateCol,
            TreeTableUtil.getAgeCategoryColumn(),
            TreeTableUtil.getAgeColumn(),
            babyCol
        );
        /*
        A cell in a TreeTableColumn is an instance of the TreeTableCell class, which displays the data in the cell.
        A TreeTableCell is a Labeled control, which is capable of displaying text, a graphic, or both.
        
        You can specify a cell factory for a TreeTableColumn. The job of a cell factory is to render the data in the
        cell. The TreeTableColumn class contains a cellFactory property, which is a Callback object. Its call()
        method is passed in the reference of the TreeTableColumn to which the cell belongs. The method returns an
        instance of TreeTableCell. The updateItem() method of the TreeTableCell is overridden to provide the
        custom rendering of the cell data.
        
        TreeTableColumn uses a default cell factory if its cellFactory property is not specified. The default
        cell factory displays the cell data depending on the type of the data. If the cell data is a Node, the data are
        displayed in the graphic property of the cell. Otherwise, the toString() method of the cell data is called and
        the retuned string is displayed in the text property of the cell.
        
        You can display formatted birth dates in the Birth Date column in the previous example. The Birth
        Date column is formatted as yyyy-mm-dd, which is the default ISO date format returned by the toString()
        method of the LocalDate class. You may want to format birth dates in the mm/dd/yyyy format. You can
        achieve this by setting a custom cell factory for the column, as shown in the following code.
        
        You can use the above technique to display images in cells. In the updateItem() method, create an
        ImageView object for the image and display it using the setGraphic() method of the TreeTableCell.
        */
        birthDateCol.setCellFactory (col -> {
            TreeTableCell<Person, LocalDate> cell = new TreeTableCell<Person, LocalDate>() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    // Cleanup the cell before populating it
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        // Format the birth date in mm/dd/yyyy format
                        String formattedDob =
                        DateTimeFormatter.ofPattern("MM/dd/yyyy").format(item);
                        this.setText(formattedDob);
                    }
                }
            };
            return cell;
        });
        
        /*
        By default, a TreeTableView shows the disclosure node in the first column. You can show it in any other
        column using the treeColumn property.
        */
		// Show the disclosure node in the Last Name column
        //treeTable.setTreeColumn(lNameCol);
        
        /*
        Showing and hiding columns in a TreeTableView work the same way they do for TableView. By default,
        all columns in a TreeTableView are visible. The TreeTableColumn class has a visible property to set the
        visibility of a column. If you turn off the visibility of a parent column, a column with nested columns, all its
        nested columns will become invisible.
        
        Sometimes you may want to let the user control the visibility of columns. The TreeTableView class has a
        tableMenuButtonVisible property. If it is set to true, a menu button is displayed in the header area. Clicking
        the Menu button displays a list of all leaf columns. Columns are displayed as radio menu items that can be
        used to toggle their visibility.
        */
        // Make the Id column invisible
        //lNameCol.setVisible(false);
        

		HBox root = new HBox(treeTable);
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
		stage.setTitle("Using a TreeTableView");
		stage.show();	
	}
}
