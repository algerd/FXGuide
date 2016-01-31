
package TableView;

import DatePicker.LocalDateStringConverter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import java.time.LocalDate;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/*
Пояснения к использованию класса DatePickerTableCell смотреть в DatePickerTableCell.java

The program uses DatePickerTableCell to edit data in the cells of a Birth Date column.
Run the application and then double-click a cell in the Birth Date column. The cell will display a DatePicker
control. You cannot edit the date in the DatePicker, as it is noneditable. You will need to select a date from
the pop-up calendar.
*/
public class CustomTableCellTest extends Application {		
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		TableView<Person> table = new TableView<>(PersonTableUtil.getPersonList());
		
		// Make sure teh TableView is editable
		table.setEditable(true);
		
		// Set up teh Birth Date column to use DatePickerTableCell
		TableColumn<Person, LocalDate> birthDateCol = PersonTableUtil.getBirthDateColumn();
		StringConverter converter = new LocalDateStringConverter("MMMM dd, yyyy");
		birthDateCol.setCellFactory(DatePickerTableCell.<Person>forTableColumn(converter, false));

		table.getColumns().addAll(
            PersonTableUtil.getIdColumn(), 
            PersonTableUtil.getFirstNameColumn(), 
            PersonTableUtil.getLastNameColumn(), 
            birthDateCol
        );		
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
		stage.setTitle("Using a Custom TableCell");
		stage.show();
	}
}
