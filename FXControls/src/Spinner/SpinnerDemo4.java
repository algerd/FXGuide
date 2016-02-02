package Spinner;

import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
You can obtain a string-based spinner via the Spinner(ObservableList<T> items) constructor.
*/
public class SpinnerDemo4 extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}
    
    @Override
    public void start(Stage primaryStage) {
        
        List<String> weekDays = Arrays.asList("Monday", "Tuesday", "Wednesday",
                                              "Thursday", "Friday", "Saturday",
                                              "Sunday");
        ObservableList<String> obsWeekDays = FXCollections.observableList(weekDays);
        Spinner<String> sspinner = new Spinner<>(obsWeekDays);
        sspinner.setEditable(true);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        grid.add(new Label("String Spinner"), 0, 0);
        grid.add(sspinner, 1, 0);

        Scene scene = new Scene(grid, 350, 100);
        primaryStage.setTitle("SpinnerDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}