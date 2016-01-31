
package effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
An effect is a filter that accepts one or more graphical inputs, applies an algorithm on the inputs, and
produces an output.
The Node class contains an effect property that specifies the effect applied to the node. By default, it is
null.
An instance of the Effect class represents an effect. The Effect class is the abstract base for all effect
classes. All effect classes are included in the javafx.scene.effect package.

Tip: An effect applied to a Group is applied to all its children. It is also possible to chain multiple effects
where the output of one effect becomes the input for the next effect in the chain. The layout bounds of a node
are not affected by the effects applied to it. However, the local bounds and bounds in parent are affected by
the effects.

Some effects can be chained with other effects when they are applied in sequence.
Effect classes that allow chaining contain an input property to specify the effect that precedes it. If
the input is null, the effect is applied to the node on which this effect is set instead of being applied to the
preceding input effect. By default, the input is null.
*/
public class EffectTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {		
		Text t1 = new Text("Drop Shadow!");
		t1.setFont(Font.font(24));
		t1.setEffect(new DropShadow());

		Text t2 = new Text("Blur!");
		t2.setFont(Font.font(24));
		t2.setEffect(new BoxBlur());

		Text t3 = new Text("Glow!");
		t3.setFont(Font.font(24));
		t3.setEffect(new Glow());

		Text t4 = new Text("Bloom!");
		t4.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		t4.setFill(Color.WHITE);
		t4.setEffect(new Bloom(0.10));
        
        /*
        A Reflection effect followed by a DropShadow is applied to the text on the left; a
        DropShadow followed by a Reflection effect is applied to the text on the right. Notice the sequence of effects
        makes a difference in the output. The second chain of effects produces a taller output as the reflection also
        includes the shadow.
        */
        // Effect Chain: Text >> Reflection >> Shadow
        DropShadow dsEffect = new DropShadow();
        dsEffect.setInput(new Reflection());
        Text t5 = new Text("Reflection and Shadow");
        t5.setEffect(dsEffect);
        
        // Effect Chain: Text >> Shadow >> Reflection
        Reflection reflection = new Reflection();
        reflection.setInput(new DropShadow());
        Text t6 = new Text("Shadow and Reflection");
        t6.setEffect(reflection);
       
		// Stack the Text node with bloom effect over a Reactangle
		Rectangle rect = new Rectangle(100, 30, Color.GREEN);
		StackPane spane = new StackPane(rect, t4);

		HBox root = new HBox(t1, t2, t3, spane, t5, t6);
		root.setSpacing(20);	
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Applying Effects");
		stage.show();
	}
}
