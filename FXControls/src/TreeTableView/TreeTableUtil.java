
package TreeTableView;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.YEARS;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

/*
TreeTableView displays hierarchical data in tabular form.
It requires you to construct a hierarchical model using TreeItems. You need to pass the root TreeItem to the
TreeTableView. Like a TreeView, a TreeTableView contains a root property, which is the root TreeItem for
the TreeView. The root property acts as a model for the TreeTableView to supply it data.

The getModel() method constructs the family tree and returns the root
node of the tree. All other methods create a column, set the cell value factory for the column, and return the
column reference.
*/
public class TreeTableUtil {
	/* Returns a root TreeItem for a family members */
	@SuppressWarnings("unchecked")
	public static TreeItem<Person> getModel() {
		/* Create all persons */
		// First level
		Person ram = new Person("Ram", "Singh", LocalDate.of(1930, 1, 1));

		// Second level
		Person janki = new Person("Janki", "Sharan", LocalDate.of(1956, 12, 17));
		Person sita = new Person("Sita", "Sharan", LocalDate.of(1961, 3, 1));
		Person kishori = new Person("Kishori", "Sharan", LocalDate.of(1968, 1, 12));
		Person ratna = new Person("Ratna", "Sharan", LocalDate.of(1978, 4, 14));

		// Third level
		Person navin = new Person("Navin", "Sharan", LocalDate.of(1980, 5, 10));
		Person vandana = new Person("Vandana", "Sharan", LocalDate.of(1981, 3, 20));
		Person neeraj = new Person("Neeraj", "Sharan", LocalDate.of(1982, 6, 3));

		Person gaurav = new Person("Gaurav", "Sharan", LocalDate.of(1990, 8, 27));
		Person saurav = new Person("Saurav", "Sharan", LocalDate.of(1994, 5, 15));

		// Fourth level
		Person palak = new Person("Palak", "Sharan", LocalDate.of(2010, 6, 3));
		Person ashwin = new Person("Ashwin", "Sharan", LocalDate.of(2012, 10, 11));
		Person advik = new Person("Advik", "Sharan", LocalDate.of(2012, 10, 11));

		// Build nodes
		TreeItem<Person> navinNode = new TreeItem<>(navin);
		navinNode.getChildren().addAll(new TreeItem<>(ashwin), new TreeItem<>(advik));
		TreeItem<Person> vandanaNode = new TreeItem<>(vandana);
		vandanaNode.getChildren().addAll(new TreeItem<>(palak));

		TreeItem<Person> jankiNode = new TreeItem<>(janki);
		jankiNode.getChildren().addAll(navinNode, new TreeItem<>(neeraj),vandanaNode);

		TreeItem<Person> sitaNode = new TreeItem<>(sita);
		sitaNode.getChildren().addAll(new TreeItem<>(gaurav), new TreeItem<>(saurav));

		TreeItem<Person> kishoriNode = new TreeItem<>(kishori);
		TreeItem<Person> ratnaNode = new TreeItem<>(ratna);

		// Create the root node and add children
		TreeItem<Person> rootNode = new TreeItem<>(ram);
		rootNode.getChildren().addAll(jankiNode, sitaNode, kishoriNode, ratnaNode);
		return rootNode;
	}

    /*
    Setting the cell value factory for a TreeTableColumn is very similar to of the way you would for TableColumn.
    A TreeItemPropertyValueFactory reads the specified property of the object stored in the value
    property of a TreeItem to populate the cells of the column.
    */
	/* Returns Person Id TreeTableColumn */
	public static TreeTableColumn<Person, Integer> getIdColumn() {
		TreeTableColumn<Person, Integer> idCol = new TreeTableColumn<>("Id");
		idCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("personId"));
		return idCol;
	}

	/* Returns First Name TreeTableColumn */
	public static TreeTableColumn<Person, String> getFirstNameColumn() {
		TreeTableColumn<Person, String> fNameCol = new TreeTableColumn<>("First Name");
		fNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("firstName"));
		return fNameCol;
	}

	/* Returns Last Name TreeTableColumn */
	public static TreeTableColumn<Person, String> getLastNameColumn() {
		TreeTableColumn<Person, String> lNameCol = new TreeTableColumn<>("Last Name");
		lNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("lastName"));
		return lNameCol;
	}

	/* Returns Birth Date TreeTableColumn */
	public static TreeTableColumn<Person, LocalDate> getBirthDateColumn() {
		TreeTableColumn<Person, LocalDate> bDateCol = new TreeTableColumn<>("Birth Date");
		bDateCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("birthDate"));
		return bDateCol;
	}

	/* Returns Age Category TreeTableColumn */
	public static TreeTableColumn<Person, Person.AgeCategory> getAgeCategoryColumn() {
		TreeTableColumn<Person, Person.AgeCategory> bDateCol = new TreeTableColumn<>("Age Category");
		bDateCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("ageCategory"));
		return bDateCol;
	}
    
    /*
    The cellValueFactory property of the TreeTableColumn is responsible for populating cells in the column.
    It is a Callback object. The call() method receives an object of the CellDataFeatures class, which is an inner
    static class of the TreeTableColumn class, and returns an ObservableValue. The getValue() method of the
    CellDataFeatures class returns the reference of the TreeItem for the row. The following snippet of code creates
    an Age column and sets its cell value factory to compute the age in years of a Person using the birth date
    */
    public static TreeTableColumn<Person, String> getAgeColumn() {
        TreeTableColumn<Person, String> ageCol = new TreeTableColumn<>("Age");
        ageCol.setCellValueFactory(cellData -> {
            Person p = cellData.getValue().getValue();
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
        return ageCol;
    }
}
