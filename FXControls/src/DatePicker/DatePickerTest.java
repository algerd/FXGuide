
package DatePicker;

import java.time.DayOfWeek;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

/*
DatePicker is a combo-box style control. The user can enter a date as text or select a date from a calendar.
The DatePicker class inherits from the ComboBoxBase<LocalDate> class. All properties declared in the 
ComboBoxBase class are also available to the DatePicker control.

The first row of the pop-up displays the month and year. You can scroll through months and years using
the arrows. The second row displays the short names of weeks. The first column displays the week number
of the year. By default, the week numbers column is not displayed. You can use the context menu on the
pop-up to display it or you can set the showWeekNumbers property of the control to show it.

The calendar always displays dates for 42 days. Dates not applicable to the current month are disabled
for selection. Each day cell is an instance of the DateCell class. You can provide a cell factory to use your
custom cells.

Right-clicking the first row, week names, week number column, or disabled dates displays the context
menu. The context menu also contains a Show Today menu item, which scrolls the calendar to the current date.

The value property of the control holds the current date in the control. You can use the property to set
a date. When the control has a null value, the pop-up shows the dates for the current month.

DatePicker has a converter property that uses a StringConverter to convert a LocalDate to a string
and vice versa. Its value property stores the date as LocalDate and its editor displays it as a string, which is
the formatted date. When you enter a date as text, the converter converts it to a LocalDate and stores it in
the value property. When you pick a date from the calendar pop-up, the converter creates a LocalDate to
store in the value property and it converts it to a string to display in the editor. The default converter uses
the default Locale and chronology to format the date. When you enter a date as text, the default converter
expects the text in the default Locale and chronology format.

Listing contains the code for a LocalDateStringConverter class that is a StringConverter
for LocalDate. By default, it formats dates in MM/dd/yyyy format. You can pass a different format in its
constructor.

You can configure the DatePicker control to work with a specific chronology instead of the default one.
The following statement sets the chronology to Thai Buddhist chronology:
    birthDate.setChronology(ThaiBuddhistChronology.INSTANCE);

You can change the default Locale for the current instance of the JVM and the DatePicker will use the
date format and chronology for the default Locale:
    // Change the default Locale to Canada
    Locale.setDefault(Locale.CANADA);


*/
public class DatePickerTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		DatePicker birthDate = new DatePicker();
        /*
        The DatePicker control provides a TextField to enter a date as text. Its editor property stores the
        reference of the TextField. The property is read-only. If you do not want users to enter a date, you can set
        the editable property of the DatePicker to false, as in the following code
        */
		birthDate.setEditable(false);
        // Set the current value
        //birthDate.setValue(LocalDate.of(2016, 01, 11));
		
        /*
        The DatePicker control fires an ActionEvent when its value property changes. The value property
        may change when a user enters a date, selects a date from the pop-up, or a date is set programmatically,
        as provided in the following code:
        */
		// Print the new date on standard output
		birthDate.setOnAction(e -> System.out.println("New Date:" + birthDate.getValue()));
		
		String pattern = "MM/dd/yyyy";
		birthDate.setConverter(new LocalDateStringConverter(pattern));
		birthDate.setPromptText(pattern.toLowerCase());
		
        /*
        Each day cell in the pop-up calendar is an instance of the DateCell class, which is inherited from the
        Cell<LocalDate> class. The dayCellFactory property of the DatePicker class lets you provide a custom
        day cell factory. The concept is the same as discussed earlier for providing the cell factory for the ListView
        control. The following statement creates a day cell factory. It changes the text color of weekend cells to blue
        and disables all future day cells. If you set this day cell factory to a DatePicker, the pop-up calendar will not
        let users select a future date because you will have disabled all future day cells:
        */
		// Create a day cell factory
		Callback<DatePicker, DateCell> dayCellFactory = 
		new Callback<DatePicker, DateCell>() {
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override 
					public void updateItem(LocalDate item, boolean empty) {
						// Must call super
						super.updateItem(item, empty);

						// Disable all future date cells
						if (item.isAfter(LocalDate.now())) {
							this.setDisable(true);
						}

						// Show Weekends in blue color
						DayOfWeek day = DayOfWeek.from(item);
						if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
							this.setTextFill(Color.BLUE);
						}
					}
				};
			}};
		
		// Set a day cell factory to disable all future day cells and show weekends in blue
		birthDate.setDayCellFactory(dayCellFactory);
	
		HBox root = new HBox(new Label("Birth Date:"), birthDate); 
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
		stage.setTitle("Using DatePicker Control");
		stage.show();
		stage.sizeToScene();
	}
}
