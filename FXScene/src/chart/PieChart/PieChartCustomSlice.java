
package chart.PieChart;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
You can also use a background image in a pie slice. The following style defines the background image for the
first pie slice.
    .chart-pie.data0 {
        -fx-background-image: url("china_flag.jpg");
    }

Tip: It is also possible to style the shape of the line joining the pie slices and their labels, labels for the pie
slices, and the legend symbols in a pie chart.

Notice that slices and legend symbols show the flags of the countries. 
It is important to keep in mind that you have matched the index of the chart data and the
index in the CSS file to match countries and their flags.
*/
public class PieChartCustomSlice extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		PieChart chart = new PieChart();
		chart.setTitle("Population in 2000");

		// Place the legend on the left side
		chart.setLegendSide(Side.LEFT);
		
		// Set the data for the chart
		ObservableList<PieChart.Data> chartData = PieChartUtil.getChartData();
		chart.setData(chartData);

		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		
		// Set a CSS for the scene
		scene.getStylesheets().addAll("chart/resources/pie_slice.css");

		stage.setScene(scene);
		stage.setTitle("Custom Pie Slices");
		stage.show();
	}
}
