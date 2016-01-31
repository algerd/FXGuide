
package TableView;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import static java.time.temporal.ChronoUnit.YEARS;

/*
A TableColumn object defines:
    • Header text and graphic for the column
    • A cell value factory to populate the cells in the column

The TableColumn class gives you full control over how cells in a column are populated. The
cellValueFactory property of the TableColumn class is responsible for populating cells of the column. A cell
value factory is an object of the Callback class, which receives a TableColumn.CellDataFeatures object and
returns an ObservableValue.

The CellDataFeatures class is a static inner class of the TableColumn class, which wraps the reference
of the TableView, TableColumn, and the item for the row for which the cells of the column are being
populated. Use the getTableView(), getTableColumn(), and getValue() methods of the CellDataFeatures
class to get the reference of the TableView, TableColumn, and the item for the row, respectively.

When the TableView needs the value for a cell, it calls the call() method of the cell value factory
object of the column to which the cell belongs. The call() method is supposed to return the reference of
an ObservableValue object, which is monitored for any changes. The return ObservableValue object may
contain any type of object. If it contains a node, the node is displayed as a graphic in the cell. Otherwise, the
toString() method of the object is called and the retuned string is displayed in the cell.

The following snippet of code creates a cell value factory using an anonymous class. The factory returns
the reference of the firstName property of the Person class. Note that a JavaFX property is an ObservableValue.

    // Create a String column with the header "First Name" for Person object
    TableColumn<Person, String> fNameCol = new TableColumn<>("First Name");
    // Create a cell value factory object
    Callback<CellDataFeatures<Person, String>, ObservableValue<String>> fNameCellFactory =
        new Callback<CellDataFeatures<Person, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(CellDataFeatures<Person, String> cellData) {
            Person p = cellData.getValue();
            return p.firstNameProperty();
    }};
    // Set the cell value factory
    fNameCol.setCellValueFactory(fNameCellFactory);

Using a lambda expression to create and set a cell value factory comes in handy. The above snippet of
code can be written as follows:

    TableColumn<Person, String> fNameCol = new TableColumn<>("First Name");
    fNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

When a JavaFX property supplies values for cells in a column, creating the cell value factory is easier if
you use an object of the PropertyValueFactory class. You need to pass the name of the JavaFX property
to its constructor. The following snippet of code does the same as the code shown above. You would take
this approach to create TableColumn objects inside the utility methods in the PersonTableUtil class.
        
    TableColumn<Person, String> fNameCol = new TableColumn<>("First Name");
    fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

Tip: Using JavaFX properties as the value supplied for cells has a big advantage. The TableView keeps the
value in the property and the cell in sync. Changing the property value in the model automatically updates
the value in the cell.

TableColumn also supports POJO (Plain Old Java Object) as items in the TableView. The disadvantage
is that when the model is updated, the cell values are not automatically updated. You use the same
PropertyValueFactory class to create the cell value factory. The class will look for the public getter and setter
methods with the property name you pass. If only the getter method is found, the cell will be read-only. For an
xxx property, it tries looking for getXxx() and setXxx() methods using the JavaBeans naming conventions.
If the type of xxx is boolean, it also looks for the isXxx() method. If a getter or a setter method is not found,
a runtime exception is thrown. The following snippet of code creates a column with the header text AgeCategory:
    
    TableColumn<Person, Person.AgeCategory> ageCategoryCol = new TableColumn<>("Age Category");
    ageCategoryCol.setCellValueFactory(new PropertyValueFactory<>("ageCategory"));

It indicates that the items type is Person and the column type is Person.AgeCategory. It passes
ageCategory as the property name into the constructor of the PropertyValueFactory class. First, the class
will look for an ageCategory property in the Person class. The Person class does not have this property.
Therefore, it will try using Person class as a POJO for this property. Then it will look for getAgeCategory()
and setAgeCategory() methods in the Person class. It finds only the getter method, getAgeCategory(), and
hence, it will make the column read-only.

The values in the cells of a column do not necessarily have to come from JavaFX or POJO properties.
They can be computed using some logic. In such cases, you need to create a custom cell value factory and
return a ReadOnlyXxxWrapper object that wraps the computed value as is done in TableViewDataTest.
    ageCol.setCellValueFactory(cellData -> {...});

The program creates cell value factories for JavaFX properties, a POJO property, and a
computed value.
*/
public class TableViewDataTest extends Application {		
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		// Create a TableView with data		
		TableView<Person> table = new TableView<>(PersonTableUtil.getPersonList());

		// Create an "Age" computed column
		TableColumn<Person, String> ageCol = new TableColumn<>("Age");
		ageCol.setCellValueFactory(cellData -> {
            Person p = cellData.getValue();
            LocalDate dob = p.getBirthDate();
            String ageInYear = "Unknown";

            if (dob != null) {
                long years = YEARS.between(dob, LocalDate.now());
                if (years == 0) {
                    ageInYear = "< 1 year";
                } else if (years == 1) {
                    ageInYear = years + " year";
                } else {
                    ageInYear = years + " years";
                }
            }
			return new ReadOnlyStringWrapper(ageInYear);
		});
	
		// Create an "Age Cotegory" column
		TableColumn<Person, Person.AgeCategory> ageCategoryCol = new TableColumn<>("Age Category");
		ageCategoryCol.setCellValueFactory(new PropertyValueFactory<>("ageCategory"));

		// Add columns to the TableView		
		table.getColumns().addAll(
            PersonTableUtil.getIdColumn(), 
            PersonTableUtil.getFirstNameColumn(),
            PersonTableUtil.getLastNameColumn(), 
            PersonTableUtil.getBirthDateColumn(),
            ageCol,
            ageCategoryCol);
		
		HBox root = new HBox(table);
		root.setStyle(
            "-fx-padding: 10;" + 
            "-fx-border-style: solid inside;" + 
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" + 
            "-fx-border-radius: 5;" + 
            "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Populating TableViews");
		stage.show();	    
	}
}
