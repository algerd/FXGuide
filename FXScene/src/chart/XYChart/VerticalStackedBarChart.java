
package chart.XYChart;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
A stacked bar chart is a variation of the bar chart. In a stacked bar chart, the bars in a category are stacked.
Except for the placement of the bars, it works the same way as the bar chart.

An instance of the StackedBarChart class represents a stacked bar chart. The bars can be placed
horizontally or vertically. If the x-axis is a CategoryAxis, the bars are placed vertically. Otherwise, they
are placed horizontally. Like the BarChart, one of the axes must be a CategoryAxis and the other a
ValueAxis/NumberAxis.

The StackedBarChart class contains a categoryGap property that defines the gap between bars in
adjacent categories. The default gap is 10px. Unlike the BarChart class, the StackedBarChart class does not
contain a barGap property, as the bars in one category are always stacked.

The constructors of the StackedBarChart class are similar to the ones for the BarChart class. They let
you specify the axes, chart data, and category gap.

There is one notable difference in a creating the CategoryAxis for the BarChart and the
StackedBarChart. The BarChart reads the categories values from the data whereas you must explicitly add
all category values to the CategoryAxis for a StackedBarChart.

    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setLabel("Country");
    // Must set the categories in a StackedBarChart explicitly. Otherwise, the chart will not show bars.
    xAxis.getCategories().addAll("China," "India," "Brazil," "UK," "USA");
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Population (in millions)");
    StackedBarChart<String, Number> chart = new StackedBarChart<>(xAxis, yAxis);

The program shows how to create a vertical stacked bar chart.
To create a horizontal stacked bar chart, use a CategoryAxis as the y-axis.
*/
public class VerticalStackedBarChart extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Country");

		// Must set the categories in a StackedBarChart explicitly. Otherwise, 
		// the chart will not show any bars.
		xAxis.getCategories().addAll("China", "India", "Brazil", "UK", "USA");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Population (in millions)");

		StackedBarChart<String, Number> chart =
			new StackedBarChart<>(xAxis, yAxis);
		chart.setTitle("Population by Country and Year");

		// Set the data for the chart
		ObservableList<XYChart.Series<String, Number>> chartData = XYChartDataUtil.getYearSeries();
		chart.setData(chartData);

		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Vertical Stacked Bar Chart");
		stage.show();
	}
}
