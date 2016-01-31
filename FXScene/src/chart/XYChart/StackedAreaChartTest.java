
package chart.XYChart;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
The stacked area chart is a variation of the area chart. It plots data items by painting an area for each series.
Unlike the area chart, areas for series do not overlap; they are stacked.
An instance of the StackedAreaChart represents a stacked area chart. Like the AreaChart class, the class
contains a createSymbols property. 

The class contains two constructors:
    • StackedAreaChart(Axis<X> xAxis, Axis<Y> yAxis)
    • StackedAreaChart(Axis<X> xAxis, Axis<Y> yAxis, ObservableList<XYChart.Series<X,Y>> data)

The program is the same as the one that created an AreaChartTest, except that you have used the StackedAreaChart
class to create the chart.
*/
public class StackedAreaChartTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Year");

		// Customize the x-axis, so points are scattred uniformly
		xAxis.setAutoRanging(false);
		xAxis.setLowerBound(1900);
		xAxis.setUpperBound(2300);
		xAxis.setTickUnit(50);

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Population (in millions)");

		StackedAreaChart<Number,Number> chart = new StackedAreaChart<>(xAxis, yAxis);
		chart.setTitle("Population by Year and Country");

		// Set the data for the chart
		ObservableList<XYChart.Series<Number,Number>> chartData = 
			XYChartDataUtil.getCountrySeries();
		chart.setData(chartData);

		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Stacked Area Chart");
		stage.show();
	}
}
