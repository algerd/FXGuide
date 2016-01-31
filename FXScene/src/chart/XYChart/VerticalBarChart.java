
package chart.XYChart;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
A bar chart renders the data items as horizontal or vertical rectangular bars. The lengths of the bars are
proportional to the value of the data items.
An instance of the BarChart class represents a bar chart. In a bar chart, one axis must be a
CategoryAxis and the other a ValueAxis/NumberAxis. The bars are drawn vertically or horizontally,
depending on whether the CategoryAxis is the x-axis or the y-axis.
The BarChart contain two properties to control the distance between two bars in a category and the
distance between two categories:
    • barGap
    • categoryGap

The default value is 4 pixels for the barGap and 10 pixels for the categoryGap.
The BarChart class contains three constructors to create bar charts by specifying axes, data, and gap
between two categories.
    • BarChart(Axis<X> xAxis, Axis<Y> yAxis)
    • BarChart(Axis<X> xAxis, Axis<Y> yAxis, ObservableList<XYChart.Series<X,Y>> data)
    • BarChart(Axis<X> xAxis, Axis<Y> yAxis, ObservableList<XYChart.Series<X,Y>> data, double categoryGap)

Notice that you must specify at least the axes when you create a bar chart. The following snippet of code
creates two axes and a bar chart with those axes.
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("Country");
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Population (in millions)");
    // Create a bar chart
    BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);

The bars in the chart will appear vertically as the category axis is added as the x-axis. You can populate
the chart with data using its setData() method.
    // Set the data for the chart
    chart.setData(XYChartDataUtil.getYearSeries());
*/
public class VerticalBarChart extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Country");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Population (in millions)");

		BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);		
		chart.setTitle("Population by Country and Year");

		// Set the data for the chart
		ObservableList<XYChart.Series<String,Number>> chartData = XYChartDataUtil.getYearSeries();
		chart.setData(chartData);

		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Vertical Bar Chart");
		stage.show();
	}	
}
