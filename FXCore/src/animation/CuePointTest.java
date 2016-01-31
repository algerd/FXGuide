
package animation;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Comparator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
You can set up cue points on a timeline. Cue points are named instants on the timeline. An animation
can jump to a cue point using the jumpTo(String cuePoint) method. An animation maintains an
ObservableMap<String,Duration> of cue points. The key in the map is the name of the cue points and the
values are the corresponding duration on the timeline. Use the getCuePoints() method to get the reference
of the cue points map.
There are two ways to add cue points to a timeline.
    • Giving a name to the KeyFrame you add to a timeline that adds a cue point in the cue
    point map
    • Adding name-duration pairs to the map returned by the getCuePoints() method of
    the Animation class

Tip: Every animation has two predefined cue points: “start” and “end.” They are set at the start and end of
the animation. The two cue points do not appears in the map returned by the getCuePoints() method.

The following snippet of code creates a KeyFrame with a name “midway.” When it is added to a timeline,
a cue point named “midway” will added to the timeline automatically. You can jump to this KeyFrame using
jumpTo("midway").
    // Create a KeyFrame with name “midway”
    KeyValue midKeyValue = ...
    KeyFrame midFrame = new KeyFrame(Duration.seconds(5), "midway", midKeyValue);

The following snippet of code adds two cue points directly to the cue point map of a timeline:
    Timeline timeline = ...
    timeline.getCuePoints().put("3 seconds", Duration.seconds(3));
    timeline.getCuePoints().put("7 seconds", Duration.seconds(7));

The program shows how to add and use cue points on a timeline. It adds a KeyFrame
with a “midway” name, which automatically becomes cue point. It adds two cue points, “3 seconds” and
“7 seconds,” directly to the cue point map. The list of available cue points is shown in a ListView on the left
side of the screen. A Text object scrolls with a cycle duration of 10 seconds. 
Select a cue point from the list and the animation will start playing from that point.
*/
public class CuePointTest extends Application {
	Text msg = new Text("JavaFX animation is cool!");
	Pane pane;
	ListView<String> cuePointsListView;
	Timeline timeline;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		msg.setTextOrigin(VPos.TOP);
		msg.setFont(Font.font(24));

		BorderPane root = new BorderPane();
		root.setPrefSize(600, 150);

		cuePointsListView = new ListView<>();
		cuePointsListView.setPrefSize(100, 150);
		pane = new Pane(msg);

		root.setCenter(pane);
		root.setLeft(cuePointsListView);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Cue Points");
		stage.show();

		this.setupAnimation();
		this.addCuePoints();
	}

	private void setupAnimation() {
		double paneWidth = pane.getWidth();
		double msgWidth = msg.getLayoutBounds().getWidth();

		// Create the initial and final key frames
		KeyValue initKeyValue = new KeyValue(msg.translateXProperty(), paneWidth);
		KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);

		// A KeyFrame with a name "midway" that defines a cue point this name
		KeyValue midKeyValue = new KeyValue(msg.translateXProperty(), paneWidth / 2);
		KeyFrame midFrame = new KeyFrame(Duration.seconds(5), "midway", midKeyValue);

		KeyValue endKeyValue = new KeyValue(msg.translateXProperty(), -1.0 * msgWidth);
		KeyFrame endFrame = new KeyFrame(Duration.seconds(10), endKeyValue);

		timeline = new Timeline(initFrame, midFrame, endFrame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	private void addCuePoints() {
		// Add two cue points directly to the map
		timeline.getCuePoints().put("3 seconds", Duration.seconds(3));
		timeline.getCuePoints().put("7 seconds", Duration.seconds(7));

		// Add all cue points from the map to the ListView in the order of their durations
		SortedMap<String, Duration> smap = getSortedCuePoints(timeline.getCuePoints());
		cuePointsListView.getItems().addAll(smap.keySet());

		// Add the special "start" and "end" cue points
		cuePointsListView.getItems().add(0, "Start");
		cuePointsListView.getItems().add("End");

		// Jusp to the cue point when the user selects it
		cuePointsListView.getSelectionModel().selectedItemProperty().addListener(
				(prop, oldValue, newValue) -> {timeline.jumpTo(newValue);}
        );
	}

	// Sort the cue points based on their durations
	private SortedMap<String, Duration> getSortedCuePoints(Map<String, Duration> map) {
		Comparator<String> comparator = (e1, e2) -> map.get(e1).compareTo(map.get(e2));
		SortedMap<String, Duration> smap = new TreeMap<>(comparator);
		smap.putAll(map);
		return smap;
	}
}
