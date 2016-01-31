
package chart.XYChart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
You have seen how to apply chart-specific CSS styles to customize the appearance of charts. In this section,
you will look at some more ways to customize XYChart plot and axes. The XYChart class contains several
boolean properties to change the chart plot appearance:
    • alternativeColumnFillVisible
    • alternativeRowFillVisible
    • horizontalGridLinesVisible
    • verticalGridLinesVisible
    • horizontalZeroLineVisible
    • verticalZeroLineVisible

The chart area is divided into a grid of columns and rows. Horizontal lines are drawn passing through
major ticks on the y-axis making up rows. Vertical lines are drawn passing through major ticks on the x-axis
making up columns.

Setting Alternate Row/Column Fill:
----------------------------------
The alternativeColumnFillVisible and alternativeRowFillVisible control whether alternate
columns and rows in the grid are filled. By default, alternativeColumnFillVisible is set to false and
alternativeRowFillVisible is set to true.

As of time of this writing, setting the alternativeColumnFillVisible and alternativeRowFillVisible
properties do not have any effects in JavaFX 8, which uses Modena CSS by default. There are two solutions.
You can use the Caspian CSS for your application using the following statement:
Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
The other solution is to include the following styles in your application CSS.
    .chart-alternative-column-fill {
        -fx-fill: #eeeeee;
        -fx-stroke: transparent;
        -fx-stroke-width: 0;
    }
    .chart-alternative-row-fill {
        -fx-fill: #eeeeee;
        -fx-stroke: transparent;
        -fx-stroke-width: 0;
    }
These styles are taken from Caspian CSS. These styles set the fill and stroke properties to null in
Modena CSS.

Showing Zero Line Axes:
-----------------------
The axes for a chart may not include zero lines. Whether zero lines are includes depends on the lower and
upper bounds represented by the axes. The horizontalZeroLineVisible and verticalZeroLineVisible
control whether zero lines should be visible. By default, they are visible. Note that the zero line for an axis
is visible only when the axis has both positive and negative data to plot. If you have negative and positive
values along the y-axis, an additional horizontal axis will appear indicating the zero value along the y-axis.
The same rule applies for values along the x-axis. If the range for an axis is set explicitly using its lower and
upper bounds, the visibility of the zero line depends on whether zero falls in the range.

Showing Grid Lines:
-------------------
The horizontalGridLinesVisible and verticalGridLinesVisible specify whether the horizontal and
vertical grid lines are visible. By default, both are set to true.


Formatting Numeric Tick Labels:
-------------------------------
Sometimes, you may want to format the values displayed on a numeric axis. You want to format the labels for
the numeric axis for different reasons:
    - You want to add prefixes or suffixes to the tick labels. For example, you may want to
    display a number 100 as $100 or 100M.
    - You may be supplying the chart scaled data to get an appropriate scale value for the
    axis. For example, for the actual value 100, you may be supplying 10 to the chart. In
    this case, you would like to display the actual value 100 for the label.

The ValueAxis class contains a tickLabelFormatter property, which is a StringConverter and it is
used to format tick labels. By default, tick labels for a numeric axis are formatted using a default formatter.
The default formatter is an instance of the static inner class NumberAxis.DefaultFormatter.

In our examples of XYChart, you had set the label for the y-axis to “Population (in millions)” to indicate
that the tick values on the axis are in millions. You can use a label formatter to append “M” to the tick values
to indicate the same meaning. The following snippet of code will accomplish this.

NumberAxis yAxis = new NumberAxis();
yAxis.setLabel("Population");
// Use a formatter for tick labels on y-axis to apend
// M (for millioms) to the population value
yAxis.setTickLabelFormatter(new StringConverter<Number>() {
    @Override
    public String toString(Number value) {
        // Append M to the value
        return Math.round(value.doubleValue()) + "M";
    }
    @Override
    public Number fromString(String value) {
        // Strip M from the value
        value = value.replaceAll("M", "");
        return Double.parseDouble(value);
    }
});

The NumberAxis.DefaultFormatter works better for adding a prefix or suffix to tick labels. This
formatter is kept in sync with the autoRanging property for the axis. You can pass a prefix and a suffix to the
constructor. The following snippet of code accomplishes the same thing as the above snippet of code.
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Population");
    yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, null, "M"));

You can customize several visual aspects of an Axis. Please refer to the API documentation for the Axis
class and its subclasses for more details.

The program shows how to customize a line chart.
It formats the tick labels on the y-axis to append “M” to the label value. It hides the grid lines and shows the
alternate column fills.
*/
public class CustomizingCharts extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Set caspian CSS to get alternate column fills until modena CSS is fixed
		Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);

		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Year");

		// CUstomize the x-axis, so points are scattred uniformly
		xAxis.setAutoRanging(false);
		xAxis.setLowerBound(1900);
		xAxis.setUpperBound(2300);
		xAxis.setTickUnit(50);

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Population");

		// Use a formatter for tick labels on y-axis to append M (for millioms) to the population value
		yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, null, "M"));

		LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
		chart.setTitle("Population by Year and Country");

		// Set the data for the chart
		chart.setData(XYChartDataUtil.getCountrySeries());

		// Show alternate column fills
		chart.setAlternativeColumnFillVisible(true);
		chart.setAlternativeRowFillVisible(false);

		// Hide grid lines
		chart.setHorizontalGridLinesVisible(false);
		chart.setVerticalGridLinesVisible(false);

		StackPane root = new StackPane(chart);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Customizing Tick Labels and Chart Plot");
		stage.show();
	}
}
