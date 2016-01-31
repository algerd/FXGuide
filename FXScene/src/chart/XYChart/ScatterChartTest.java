
package chart.XYChart;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
A bar chart renders the data items as symbols. All data items in a series use the same symbol. The location of
the symbol for a data item is determined by the values on the data item along the x-axis and y-axis.
An instance of the ScatterChart class represents a scatter chart. You can use any type of Axis for the
x-axis and y-axis. The class does not define any additional properties. It contains constructors that allow you
to a create scatter chart by specifying axes and data.
    • ScatterChart(Axis<X> xAxis, Axis<Y> yAxis)
    • ScatterChart(Axis<X> xAxis, Axis<Y> yAxis, ObservableList<XYChart.Series<X,Y>> data)

Recall that the autoRanging for an Axis is set to true by default. If you are using numeric values in a
scatter chart, make sure to set the autoRanging to false. It is important to set the range of the numeric values
appropriately to get uniformly distributed points in the chart. Otherwise, the points may be located densely
in a small area and it will be hard to read the chart.

Tip: You can use the node property for data items to specify symbols in a ScatterChart.

The program shows how to create and populate a scatter chart.
Both axes are numeric axes. The x-axis is customized. The autoRanging is set to false; reasonable lower and
upper bounds are set. The tick unit is set to 50. If you do not customize these properties, the ScatterChart
will automatically determine them and the chart data will be hard to read.
*/
public class ScatterChartTest extends Application {
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

		ScatterChart<Number,Number> chart = new ScatterChart<>(xAxis, yAxis);
		chart.setTitle("Population by Year and Country");

		// Set the data for the chart
		ObservableList<XYChart.Series<Number,Number>> chartData = XYChartDataUtil.getCountrySeries();
		chart.setData(chartData);
		
		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Scatter Chart");
		stage.show();
	}	
}
