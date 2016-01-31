
package media;

import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/*
An instance of the AudioClip class is used to play a short audio clip with minimal latency. Typically, this is
useful for playing short audio clips, for example, a beep sound when the user makes an error or producing
short sound effects in gaming applications.

The AudioClip class provides only one constructor that takes a URL in string form, which is the URL of
the audio source. The audio clip is immediately loaded into memory in raw, uncompressed form. This is the
reason why you should not use this class for long-playing audio clips. The source URL could use the HTTP,
file, and JAR protocols. This means that you can play an audio clip from the Internet, the local file system,
and a JAR file.

The following snippet of code creates an AudioClip using the HTTP protocol:
    String clipUrl = "http://www.jdojo.com/myaudio.wav";
    AudioClip audioClip = new AudioClip(clipUrl);

When an AudioClip object is created, the audio data are loaded into the memory and they are ready
to be played immediately. Use the play() method to play the audio and the stop() method to stop the
playback:

    // Play the audio
    audioClip.play();
    ...
    // Stop the playback
    audioClip.stop();

The AudioClip class supports setting some audio properties when the clip is played:
• cycleCount
• volume
• rate
• balance
• pan
• priority
All of the above properties, except the cycleCount, can be set on the AudioClip class. Subsequent
calls to the play() method will use them as defaults. The play() method may also override the defaults
for a specific playback. The cycleCount property must be specified on the AudioClip and all subsequent
playbacks will use the same value.

The cycleCount specifies the number of times the clip is played when the play() method is called. It
defaults to 1, which plays the clip only once. You can use one of the following three INDEFINITE constants as
the cycleCount to play the AudioClip loop until stopped:
    • AudioClip.INDEFINITE
    • MediaPlayer.INDEFINITE
    • Animation.INDEFINITE

The following snippet of code shows how to play an audio clip five times and indefinitely:
    // Play five times
    audioClip.setCycleCount(5);
    ...
    // Loop forever
    audioClip.setCycleCount(AudioClip.INDEFINITE);

The volume specifies the relative volume of the playback. The valid range is 0.0 to 1.0. A value of 0.0
represented muted, whereas 1.0 represents full volume.

The rate specifies the relative speed at which the audio is played. The valid range is 0.125 to 8.0. A value
of 0.125 means the clip is played eight times slower, and the value of 8.0 means the clip will play eight times
faster. The rate affects the playtime and the pitch. The default rate is 1.0, which plays the clip at the normal
rate.

The balance specifies the relative volume for the left and right channels. The valid range is -1.0 to 1.0.
A value of -1.0 sets the playback in the left channel at normal volume and mutes the right channel. A value of
1.0 sets the playback in the right channel at normal volume and mutes the left channel. The default value is
0.0, which sets the playback in both channels at normal volume.

The pan specifies distribution of the clip between the left and right channels. The valid range is -1.0 to
1.0. A value of -1.0 shifts the clip entirely to the left channel. A value of 1.0 shifts the clip entirely to the right
channel. The default value is 0.0, which plays the clip normally. Setting the value for pan for a mono clip has
the same effect of setting the balance. You should change the default for this property only for audio clips
using stereo sound.

The priority specifies the priority of the clip relative to other clips. It is used only when the number of
playing clips exceeds the system limits. The playing clips with the lower priority will be stopped. It can be set
to any integer. The default priority is set to zero.

The play() method is overloaded. It has three versions:
    • Void play()
    • void play(double volume)
    • void play(double volume, double balance, double rate, double pan, int priority)
The no-args version of the method uses all of the properties set on the AudioClip. The other two
versions can override the specified properties for a specific playback. Suppose the volume for the AudioClip
is set to 1.0. Calling play() will play the clip at the volume 1.0 and calling play(0.20) will play the clip at
volume 0.20, leaving the volume property for the AudioClip unchanged at 1.0. That is, the play() method
with parameters allows you to override the AudioClip properties on a per-playback basis.

The AudioClip class contains an isPlaying() method to check if the clip is still playing. It returns true
is the clip is playing. Otherwise, it returns false.

The program shows how to play an audio clip using the AudioClip class. It declares an
instance variable to store the AudioClip reference. The AudioClip is created in the init() method to make
sure the clip is ready to be played when the window is shown in the start() method. You could have also
created the AudioClip in the constructor. The start() method adds Start and Stop buttons. Their action
event handlers start and stop the playback, respectively.
*/
public class AudioClipPlayer extends Application {
	private AudioClip audioClip;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void init() {
		URL mediaUrl = this.getClass().getClassLoader().getResource("Media/chimes.wav");
		
		// Create an AudioClip, which loads the audio data synchronously
		audioClip = new AudioClip(mediaUrl.toExternalForm());
	}

	@Override
	public void start(Stage stage) {
		Button playBtn = new Button("Play");
		Button stopBtn = new Button("Stop");

		// Set event handlers for buttons
		playBtn.setOnAction(e -> audioClip.play());
		stopBtn.setOnAction(e -> audioClip.stop());
		
		HBox root = new HBox(5, playBtn, stopBtn);
		root.setStyle("-fx-padding: 10;");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Playing Short Audio Clips");
		stage.show();
	}
}
