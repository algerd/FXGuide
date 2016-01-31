
package chart.XYChart;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
A line chart displays the data items in a series by connecting them by line segments. Optionally, the data
points themselves may be represented by symbols. You can think of a line chart as a scatter chart with
symbols in a series connected by straight line segments. Typically, a line chart is used to view the trend in
data change over time or in a category.

An instance of the LineChart class represents a line chart. The class contains a createSymbols property,
which is set to true by default. It controls whether symbols are created for the data points. Set it to false to
show only straight lines connecting the data points in a series.

The LineChart class contains two constructors to create line charts by specifying axes and data.
    • LineChart(Axis<X> xAxis, Axis<Y> yAxis)
    • LineChart(Axis<X> xAxis, Axis<Y> yAxis, ObservableList<XYChart.Series<X,Y>> data)

The program is the same as ScatterChartTest, except that it uses the LineChart class. The chart
displays circles as symbols for data items. You can remove the symbols by using the following statement,
after you create the line chart.
*/
public class LineChartTest extends Application {
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

		LineChart<Number,Number> chart = new LineChart<>(xAxis, yAxis);
		chart.setTitle("Population by Year and Country");

		// Set the data for the chart
		ObservableList<XYChart.Series<Number,Number>> chartData = XYChartDataUtil.getCountrySeries();
		chart.setData(chartData);

		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Line Chart");
		stage.show(); 
	}
}
