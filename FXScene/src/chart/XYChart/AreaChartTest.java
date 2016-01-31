
package chart.XYChart;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
The area chart is a variation of the line chart. It draws lines connecting all data items in a series and,
additionally, fills the area between where the line and the x-axis is painted. Different colors are used to paint
areas for different series.

An instance of the AreaChart represents an area chart. Like the LineChart, class, the class contains a
createSymbols property to control whether symbols are drawn at the data points. By default, it is set to true.

The class contains two constructors:
    • AreaChart(Axis<X> xAxis, Axis<Y> yAxis)
    • AreaChart(Axis<X> xAxis, Axis<Y> yAxis, ObservableList<XYChart.Series<X,Y>> data)

The program shows how to create an area chart. There is
nothing new in the program, except that you have used the AreaChart class to create the chart. Notice that
the area for a series overlays the area for the preceding series.
*/
public class AreaChartTest extends Application {
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

		AreaChart<Number,Number> chart = new AreaChart<>(xAxis, yAxis);
		chart.setTitle("Population by Year and Country");

		// Set the data for the chart
		ObservableList<XYChart.Series<Number,Number>> chartData = XYChartDataUtil.getCountrySeries();
		chart.setData(chartData);
		
		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("An Area Chart");
		stage.show();
	}
}
