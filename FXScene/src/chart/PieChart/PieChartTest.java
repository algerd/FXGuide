
package chart.PieChart;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
A pie chart consists of a circle divided into sectors of different central angles. Typically, a pie is circular.
The sectors are also known as pie pieces or pie slices. Each sector in the circle represents a quantity of some
kind. The central angle of the area of a sector is proportional to the quantity it represents.

An instance of the PieChart class represents a pie chart. The class contains two constructors:
    • PieChart()
    • PieChart(ObservableList<PieChart.Data> data)
The no-args constructor creates a pie chart with no content. You can add the content later using its data
property. The second constructor creates a pie chart with the specified data as its content.
    // Create an empty pie chart
    PieChart chart = new PieChart();

A slice in a pie chart is specified as an instance of the PieChart.Data class. A slice has a name (or a label)
and a pie value represented by the name and pieValue properties of the PieChart.Data class, respectively.

The following statement creates a slice for a pie chart. The slice name is “China,” and the pie value is 1275.
    PieChar.Data chinaSlice = new PieChart.Data("China", 1275);

The content of a pie chart (all slices) is specified in an ObservableList<PieChart.Data>. The following
snippet of code creates an ObservableList<PieChart.Data> and adds three pie slices to it.
    ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();
    chartData.add(new PieChart.Data("China", 1275));
    chartData.add(new PieChart.Data("India", 1017));
    chartData.add(new PieChart.Data("Brazil", 172));

Now, you can use the second constructor to create a pie chart by specifying the chart content:
    // Create a pie chart with content
    PieChart charts = new PieChart(chartData);

The PieChart class contains several properties:
    • data
    • startAngle
    • clockwise
    • labelsVisible
    • labelLineLength

The data property specifies the content for the chart in an ObservableList<PieChart.Data>.

The startAngle property specifies the angle an degrees to start the first pie slice. By default, it is zero
degrees, which corresponds to three o’clock position. A positive startAngle is measured anticlockwise.
For example, a 90-degree startAngle will start at the 12 o’clock position.

The clockwise property specifies whether the slices are placed clockwise starting at the startAngle.
By default, it is true.

The labelsVisible property specifies whether the labels for slices are visible. Labels for slices are
displayed close to the slice and they are placed outside the slices. The label for a slice is specified using the
name property of the PieChart.Data class. In Figure 23-2, “China,” India,” Brazil,, etc., are labels for slices.
Labels and slices are connected through straight lines. The labelLineLength property specifies the
length of those lines. Its default value is 20.0 pixels.

The program uses a pie chart to display the population for five countries in 2000.
The program creates an empty pie chart and sets its title. The legend is placed on the left side. Later,
it sets the data for the chart. The data is generated in the getChartData() method, which returns an
ObservableList<PieChart.Data> containing the name of the countries as the labels for pie slices and their
populations as pie values.
*/
public class PieChartTest extends Application {
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

		stage.setScene(scene);
		stage.setTitle("A Pie Chart");
		stage.show();
	}
}
