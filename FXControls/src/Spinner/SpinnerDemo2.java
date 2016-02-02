package Spinner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
I want to make the previous spinners editable. How do I accomplish this task?

Call Spinner's void setEditable(boolean value) method, passing true to value
*/
public class SpinnerDemo2 extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}
    
    @Override
    public void start(Stage primaryStage) {
       
        Spinner<Integer> ispinner = new Spinner<>(1, 10, 2);
        ispinner.setEditable(true);
        Spinner<Double> dspinner = new Spinner<>(1.5, 3.5, 1.5, 0.5);
        dspinner.setEditable(true);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        grid.add(new Label("Integer Spinner"), 0, 0);
        grid.add(ispinner, 1, 0);
        grid.add(new Label("Double Spinner"), 0, 1);
        grid.add(dspinner, 1, 1);

        Scene scene = new Scene(grid, 350, 100);
        primaryStage.setTitle("SpinnerDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}