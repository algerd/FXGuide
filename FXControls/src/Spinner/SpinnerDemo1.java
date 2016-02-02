package Spinner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
Listing start() method first creates an integer spinner via the constructor. 
It then creates a double precision floating-point spinner via the Spinner(double min, double max, double initialValue, double amountToStepBy) constructor. 
This constructor receives the units in which to increment or decrement the spinner via the value passed to amountToStepBy, which happens to be 0.5.

Continuing, start() creates and configures a grid pane container and populates the 2-row-by-2-column grid with these spinners and associated labels. 
It then creates the scene based on the grid and configures/shows the stage.
*/
public class SpinnerDemo1 extends Application {
    public static void main(String[] args) {
		Application.launch(args);
	}
    
    @Override
    public void start(Stage primaryStage) {
        
        Spinner<Integer> ispinner = new Spinner<>(1, 10, 2);
        Spinner<Double> dspinner = new Spinner<>(1.5, 3.5, 1.5, 0.5);

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