
package media;

import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;

/*
The Media API contains three core classes to play back media:
    • Media
    • MediaPlayer
    • MediaView

An instance of the Media class represents a media resource, which could be an audio or a video. It provides
the information related to the media, for example, the duration, metadata, data, and so forth. If the media is
a video, it provides the width and height of the video. A Media object is immutable. It is created by supplying
a string URL of the media resource, as in the following code:
    // Create a Media
    String mediaUrl = "http://www.jdojo.com/mymusic.wav";
    Media media = new Media(mediaUrl);

The Media class contains the following properties, all (except onError) of which are read-only:
    • duration
    • width
    • height
    • error
    • onError

The duration specifies the duration of the media in seconds. It is a Duration object. If the duration is
unknown, it is Duration.UNKNOWN.

The width and height give the width and height of the source media in pixels, respectively. If the media
does not have width and height, they are set as zero.

The error and onError properties are related. The error property represents the MediaException that
occurs during the loading of the media. The onError is a Runnable object that you can set to get notified
when an error occurs. The run() method of the Runnable is called when an error occurs:
    // When an error occurs in loading the media, print it on the console
    media.setOnError(() -> System.out.println(player.getError().getMessage()));

A MediaPlayer provides the controls, for example, play, pause, stop, seek, play speed, volume adjustment,
for playing the media. The MediaPlayer provides only one constructor that takes a Media object as an
argument:
    // Create a MediaPlayer
    MediaPlayer player = new MediaPlayer(media);
You can get the reference of the media from the MediaPlayer using the getMedia() method of the
MediaPlayer class.

Like the Media class, the MediaPlayer class also contains error and onError properties to report errors.
When an error occurs on the MediaPlayer, the same error is also reported on the Media object.

A MediaView is a node. It provides the view of a media being played by a MediaPlayer. Note that an audio
clip does not have visuals. If you try creating a MediaView for an audio content, it would be empty. To watch a
video, you create a MediaView and add it to a scene graph.

The MediaView class provides two constructors: one no-args constructor and one that takes a
MediaPlayer as an argument:
    • public MediaView()
    • public MediaView(MediaPlayer mediaPlayer)
The no-args constructor creates a MediaView that is attached to any MediaPlayer. You will need to set a
MediaPlayer using the setter for the mediaPlayer property:
    // Create a MediaView with no MediaPlayer
    MediaView mediaView = new MediaView();
    mediaView.setMediaPlayer(player);

The content of a media can be used simultaneously by multiple Media objects. However, one Media object
can be associated with only one media content in its lifetime.

A Media object can be associated with multiple MediaPlayer objects. However, a MediaPlayer is
associated with only one Media in its lifetime.

A MediaView may optionally be associated with a MediaPlayer. Of course, a MediaView that is not
associated with a MediaPlayer does not have any visuals. The MediaPlayer for a MediaView can be changed.
Changing the MediaPlayer for a MediaView is similar to changing the channel on a television. The view
for the MediaView is provided by its current MediaPlayer. You can associate the same MediaPlayer with
multiple MediaViews: Different MediaViews may display different parts of the same media during the
playback.

The program uses a video file Media/chimes.wav. 
You will need to have this file in the CLASSPATH.
*/
public class QuickMediaPlayer extends Application {	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Locate the media content in the CLASSPATH
		String mediaPath = "Media/chimes.wav";
		URL mediaUrl = getClass().getClassLoader().getResource(mediaPath);
		String mediaStringUrl = mediaUrl.toExternalForm();

		// Create a Media
		Media media = new Media(mediaStringUrl);

		// Create a Media Player
		MediaPlayer player = new MediaPlayer(media);

		// Automatically begin the playback
		player.setAutoPlay(true);

		// Create a 400X300 MediaView
		MediaView mediaView = new MediaView(player);
		mediaView.setFitWidth(400);
		mediaView.setFitHeight(300);
		
		// Create Play and Stop player control buttons and add action
		// event handlers to them
		Button playBtn = new Button("Play");
		playBtn.setOnAction(e -> {
			if (player.getStatus() == PLAYING) {
				player.stop();
				player.play();
			} else {
				player.play();
			}
		});

		Button stopBtn = new Button("Stop");
		stopBtn.setOnAction(e -> player.stop());
		
		// Add an error handler
		player.setOnError(() -> System.out.println(player.getError().getMessage()));
		
		HBox controlBox = new HBox(5, playBtn, stopBtn);
		BorderPane root = new BorderPane();

		// Add the MediaView and player controls to the scene graph
		root.setCenter(mediaView);
		root.setBottom(controlBox);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Playing Media");
		stage.show();
	}
}
