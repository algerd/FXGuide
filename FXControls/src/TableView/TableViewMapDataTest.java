
package TableView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
Sometimes data in a row for a TableView may not map to a domain object, for example, you may want to
display the result set of a dynamic query in a TableView. The items list consists of an observable list of Map.
A Map in the list contains values for all columns in the row. You can define a custom cell value factory to
extract the data from the Map. The MapValueFactory class is especially designed for this purpose. It is an
implementation of the cell value factory, which reads data from a Map for a specified key.

The following snippet of code creates a TableView of Map. It creates an Id column and sets an instance
of the MapValueFactory class as its cell value factory specifying the idColumnKey as the key that contains the
value for the Id column. It creates a Map and populates the Id column using the idColumnKey. You need to
repeat these steps for all columns and rows.

    TableView<Map> table = new TableView<>();

    // Define the column, its cell value factory and add it to the TableView
    String idColumnKey = "id";
    TableColumn<Map, Integer> idCol = new TableColumn<>("Id");
    idCol.setCellValueFactory(new MapValueFactory<>(idColumnKey));
    table.getColumns().add(idCol);

    // Create and populate a Map an item
    Map row1 = new HashMap();
    row1.put(idColumnKey, 1);

    // Add the Map to the TableView items list
    table.getItems().add(row1);

The program shows how to use the MapValueFactory as the cell value factory for
columns in a TableView. It displays the person’s data returned by the getPersonList() method in the
PersonTableUtil class.
*/
public class TableViewMapDataTest  extends Application {
    
	private final String idColumnKey = "id";
	private final String firstNameColumnKey = "firstName";
	private final String lastNameColumnKey = "lastName";
	private final String birthDateColumnKey = "birthDate";
		
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		TableView<Map> table = new TableView<>();
		ObservableList<Map<String, Object>> items = this.getMapData();
		table.getItems().addAll(items);
		this.addColumns(table);

		HBox root = new HBox(table);
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
		stage.setTitle("Using a Map as items in a TableView");
		stage.show();	    
	}
	
	public ObservableList<Map<String, Object>> getMapData() {
		ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();

		// Extract the person data, add the data to a Map, and add the Map to the items list
		ObservableList<Person> persons = PersonTableUtil.getPersonList();
		for(Person p : persons) {
			Map<String, Object> map = new HashMap<>();
			map.put(idColumnKey, p.getPersonId());
			map.put(firstNameColumnKey, p.getFirstName());
			map.put(lastNameColumnKey, p.getLastName());
			map.put(birthDateColumnKey, p.getBirthDate());
			items.add(map);
		}		
		return items;
	}
	
	@SuppressWarnings("unchecked")
	public void addColumns(TableView table) { 
		TableColumn<Map, Integer> idCol = new TableColumn<>("Id");
		idCol.setCellValueFactory(new MapValueFactory<>(idColumnKey));

		TableColumn<Map, String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(new MapValueFactory<>(firstNameColumnKey));
		
		TableColumn<Map, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new MapValueFactory<>(lastNameColumnKey));

		TableColumn<Map, LocalDate> birthDateCol = 	new TableColumn<>("Birth Date");
		birthDateCol.setCellValueFactory(new MapValueFactory<>(birthDateColumnKey));

		table.getColumns().addAll(idCol, firstNameCol, lastNameCol, birthDateCol);
	}
}
