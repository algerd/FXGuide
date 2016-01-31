
package chart.PieChart;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
It is quite possible that you will have more than eight series (slices in a pie chart) in a chart and you do not
want to repeat the colors for the series. The technique is discussed for a pie chart. However, it can be used for
a 2-axis chart as well.

Suppose you want to use a pie that will display populations of ten countries. If you use the code for
this pie chart, the colors for the ninth and tenth slices will be the same as the colors for the first and second
slices, respectively. First, you need to define the colors for the ninth and tenth slices.
    .chart-pie.default-color8 {
        -fx-pie-color: gold;
    }
    .chart-pie.default-color9 {
        -fx-pie-color: khaki;
    }

The pie slices and the legend symbols will be assigned style-class names such as default-color0,
default-color2… default-color7. You need to identify the nodes for the slices and legend symbols
associated with data items with index greater than 7 and replace their default-color <j> style-class name
with the new ones. For example, for the ninth and tenth slices, the style-class names are default-color0
and default-color1 as the color series number is assigned as (dataIndex % 8). You will replace them with
default-color9 and default-color10.

The program shows how to change the colors for the slices and legend symbols. It adds
ten slices to a pie chart. The setSeriesColorStyles() method replaces the style-class names for the slice
nodes for the ninth and tenth slices and for their associated legend symbols.
Notice the colors for “Germany” and “Indonesia” are gold and khaki as set in the CSS. Comment the last
statement in the start() method, which is a call to the setSeriesColorStyles() and you will find that the
colors for “Germany” and “Indonesia” will be the same as the colors for “China” and “India.”
*/
public class PieChartExtraColor extends Application {
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
		this.addData(chartData);
		chart.setData(chartData);

		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		scene.getStylesheets().add("chart/resources/additional_series_colors.css");
		stage.setScene(scene);
		stage.setTitle("A Pie Chart with over 8 Slices");
		stage.show();

		// Override the default series color style class-name for slices over 8. 
		// Works only when you set it after the scene is visible
		this.setSeriesColorStyles(chart);
	}

	private void addData(ObservableList<PieChart.Data> data) {
		data.add(new PieChart.Data("Bangladesh", 138));
		data.add(new PieChart.Data("Egypt", 68));
		data.add(new PieChart.Data("France", 59));
		data.add(new PieChart.Data("Germany", 82));
		data.add(new PieChart.Data("Indonesia", 212));
	}

	private void setSeriesColorStyles(PieChart chart) {
		ObservableList<PieChart.Data> chartData = chart.getData();
		int size = chartData.size();		
		for (int i = 8; i < size; i++) {
			String removedStyle = "default-color" + (i % 8);
			String addedStyle = "default-color" + (i % size);

			// Reset the pie slice colors
			Node node = chartData.get(i).getNode();
			node.getStyleClass().remove(removedStyle);
			node.getStyleClass().add(addedStyle);

			// Reser the legend colors
			String styleClass = ".pie-legend-symbol.data" + i + 
			                    ".default-color" + (i % 8);
			Node legendNode = chart.lookup(styleClass);
			if (legendNode != null) {
				legendNode.getStyleClass().remove(removedStyle);
				legendNode.getStyleClass().add(addedStyle);
			}
		}
	}
}
