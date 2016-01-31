
package chart.XYChart;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
A bubble chart is very similar to a scatter chart, except that it has the ability to represent three values for
a data point. A bubble is used to represent a data items in series. You can set the radius of the bubble to
represent the third value for the data point.

An instance of the BubbleChart class represents a bubble chart. The class does not define any new
properties. A bubble chart uses the extraValue property of the XYChart.Data class to get the radius of the
bubble. The bubble is an ellipse whose radii are scaled based on the scale used for the axes. Bubbles look
more like a circle (or less stretched on one direction) if the scales for x-axis and y-axis are almost equal.

Tip: The bubble radius is set by default, which is scaled using the scale factor of the axes. You may not see
the bubbles if the scale factor for axes are very small. To see the bubbles, set the extraValue in data items to a
high value or use a higher scale factors along the axes.

The BubbleChart class defines two constructors:
• BubbleChart(Axis<X> xAxis, Axis<Y> yAxis)
• BubbleChart(Axis<X> xAxis, Axis<Y> yAxis, ObservableList<XYChart.Series<X,Y>> data)

The program shows how to create a bubble chart. The chart
data is passed to the setBubbleRadius() method, which explicitly sets the extraValue for all data points
to 20px. If you want to use the radii of bubbles to represent another dimension of data, you can set the
extraValue accordingly.
*/
public class BubbleChartTest extends Application {
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
 
		BubbleChart<Number,Number> chart = new BubbleChart<>(xAxis, yAxis);
		chart.setTitle("Population by Year and Country");

		// Get the data for the chart
		ObservableList<XYChart.Series<Number,Number>> chartData = XYChartDataUtil.getCountrySeries();

		// Set the bubble radius
		setBubbleRadius(chartData);

		// Set the data for the chart
		chart.setData(chartData);

		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Bubble Chart");
		stage.show();
	}
	
	private void setBubbleRadius(ObservableList<XYChart.Series<Number,Number>> chartData) {
		for(XYChart.Series<Number,Number> series: chartData) {
			for(XYChart.Data<Number,Number> data : series.getData()) {
				data.setExtraValue(20); // Bubble radius
			}
		}
	}
}
