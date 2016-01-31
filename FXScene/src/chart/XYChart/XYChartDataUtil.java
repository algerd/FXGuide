
package chart.XYChart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/*
Data in an XYChart represents points in the 2D plane defined by the x-axis and y-axis. A point in a 2D plane
is specified using the x and y coordinates, which are values along the x-axis and y-axis, respectively. The data
in an XYChart is specified as an ObservableList of named series. A series consists of multiple data items,
which are points in the 2D plane. How the points are rendered depends on the chart type. For example, a
scatter chart shows a symbol for a point whereas a bar chart shows a bar for a point.

An instance of the nested static XYChart.Data<X,Y> class represents a data item in a series. The class
defines the following properties:
    • XValue
    • YValue
    • extraValue
    • node
    • The XValue and YValue are the values for the data item along the x-axis and y-axis,
    respectively. Their data types need to match the data type of the x-axis and y-axis for
    the chart. The extraValue is an Object, which can be used to store any additional
    information for the data item. Its use depends of the chart type. If the chart does not
    use this value, you can use it for any other purpose: for example, to store the tooltip
    value for the data item. The node specifies the node to be rendered for the data item in
    the chart. By default, the chart will create a suitable node depending on the chart type.

An instance of the nested static XYChart.Series<X,Y> class represents a series of data items. The class
defines the following properties:
    • name
    • data
    • chart
    • node
The name is the name of the series. The data is an ObservableList of XYChart.Data<X,Y>. The chart is
a read-only reference to the chart to which the series belong. The node is a Node to display for this series. A
default node is automatically created based on the chart type.

The data property of the XYChart class represents the data for the chart. It is an ObservableList
of XYChart.Series class. The following snippet of code creates and adds the data for an XYChart chart
assuming the data series seriesIndia and seriesUSA exists.
    XYChart<Number, Number> chart = ...
    chart.getData().addAll(seriesChina, seriesIndia, seriesUSA);

Listing has code for a utility class. The class consists of two static methods that generate
and return XYChart data. The getCountrySeries() method returns the list of series that plots the years along
the x-axis and the corresponding populations along the y-axis. The getYearSeries() method returns a list of
series that plots the countries along the x-axis and the corresponding populations along the y-axis.
*/
@SuppressWarnings("unchecked")
public class XYChartDataUtil {
	public static ObservableList<XYChart.Series<Number, Number>> getCountrySeries() {
		XYChart.Series<Number, Number> seriesChina = new XYChart.Series<>();
		seriesChina.setName("China");
		seriesChina.getData().addAll(
            new XYChart.Data<>(1950, 555),
            new XYChart.Data<>(2000, 1275),
            new XYChart.Data<>(2050, 1395), 
            new XYChart.Data<>(2100, 1182),
            new XYChart.Data<>(2150, 1149)
        );
		XYChart.Series<Number, Number> seriesIndia = new XYChart.Series<>();
		seriesIndia.setName("India");
		seriesIndia.getData().addAll(
            new XYChart.Data<>(1950, 358),
            new XYChart.Data<>(2000, 1017),
            new XYChart.Data<>(2050, 1531), 
            new XYChart.Data<>(2100, 1458), 
            new XYChart.Data<>(2150, 1308)
        );
		XYChart.Series<Number, Number> seriesUSA = new XYChart.Series<>();
		seriesUSA.setName("USA");
		seriesUSA.getData().addAll(
            new XYChart.Data<>(1950, 158),
            new XYChart.Data<>(2000, 285),
            new XYChart.Data<>(2050, 409), 
            new XYChart.Data<>(2100, 437), 
            new XYChart.Data<>(2150, 453)
        );
		ObservableList<XYChart.Series<Number, Number>> data = FXCollections.<XYChart.Series<Number, Number>>observableArrayList();
		data.addAll(seriesChina, seriesIndia, seriesUSA);
		return data;
	}

	public static ObservableList<XYChart.Series<String, Number>> getYearSeries() {
		XYChart.Series<String, Number> series1950 = new XYChart.Series<>();
		series1950.setName("1950");
		series1950.getData().addAll(
            new XYChart.Data<>("China", 555),
            new XYChart.Data<>("India", 358),
            new XYChart.Data<>("Brazil", 54),
            new XYChart.Data<>("UK", 50),
            new XYChart.Data<>("USA", 158)
        );
		XYChart.Series<String, Number> series2000 = new XYChart.Series<>();
		series2000.setName("2000");
		series2000.getData().addAll(
            new XYChart.Data<>("China", 1275),
            new XYChart.Data<>("India",1017),
            new XYChart.Data<>("Brazil", 172),
            new XYChart.Data<>("UK", 59),
            new XYChart.Data<>("USA", 285)
        );
		XYChart.Series<String, Number> series2050 = new XYChart.Series<>();
		series2050.setName("2050");
		series2050.getData().addAll(
            new XYChart.Data<>("China", 1395),
            new XYChart.Data<>("India",1531),
            new XYChart.Data<>("Brazil", 233),
            new XYChart.Data<>("UK", 66),
            new XYChart.Data<>("USA", 409)
        );
		ObservableList<XYChart.Series<String, Number>> data = FXCollections.<XYChart.Series<String, Number>>observableArrayList();
		data.addAll(series1950, series2000, series2050);
		return data;
	}
}
