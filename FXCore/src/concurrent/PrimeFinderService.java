
package concurrent;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import static javafx.concurrent.Worker.State.RUNNING;
import static javafx.concurrent.Worker.State.SCHEDULED;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
The Service<V> class is an implementation of the Worker<V> interface. It encapsulates a Task<V>. It makes
the Task<V> reusable by letting it be started, cancelled, reset, and restarted.

Remember that a Service<V> encapsulates a Task<V>. Therefore, you need a Task<V> to have a Service<V>. The
Service<V> class contains an abstract protected createTask() method that returns a Task<V>. To create a service,
you need to subclass the Service<V> class and provide an implementation for the createTask() method.
The following snippet of code creates a Service that encapsulates a PrimeFinderTask, which you have
created earlier:
    // Create a service
    Service<ObservableList<Long>> service = new Service<ObservableList<Long>>() {
        @Override
        protected Task<ObservableList<Long>> createTask() {
            // Create and return a Task
            return new PrimeFinderTask();
        }
    };
The createTask() method of the service is called whenever the service is started or restarted.

The Service class contains all properties (title, message, state, value, etc.) that represent the internal
state of a Worker. It adds an executor property, which is a java.util.concurrent.Executor. The property is
used to run the Service. If it is not specified, a daemon thread is created to run the Service.

Unlike the Task class, the Service class does not contain updateXxx() methods for updating its
properties. Its properties are bound to the corresponding properties of the underlying Task<V>. When the
Task updates its properties, the changes are reflected automatically to the Service and to the client.

The Service class contains all properties for setting the state transition listeners as contained by the Task
class. It adds an onReady property. The property specifies a state transition event handler, which is called
when the Service transitions to the READY state. Note that the Task class does not contain an onReady
property as a Task is in the READY state when it is created and it never transitions to the READY state again.
However, a Service can be in the READY state multiple times. A Service transitions to the READY state when
it is created, reset, and restarted. The Service class also contains a protected ready() method, which is
intended to be overridden by subclasses. The ready() method is called when the Service transitions to the
READY state.

Use the cancel() methods to cancel a Service: the method sets the state of the Service to CANCELLED.

Calling the start() method of the Service class starts a Service. The method calls the createTask()
method to get a Task instance and runs the Task. The Service must be in the READY state when its start()
method is called.
    Service<ObservableList<Long>> service = create a service
    ...
    // Start the service
    service.start();

Calling the reset() method of the Service class resets the Service. Resetting puts all the Service properties
back to their initial states. The state is set to READY. Resetting a Service is allowed only when the Service
is in one of the finish states: SUCCEEDED, FAILED, CANCELLED, or READY. Calling the reset() method throws a
runtime exception if the Service is in the SCHEDULED or RUNNING state.

Calling the restart() method of the Service class restarts a Service. It cancels the task if it exists, resets the
service, and starts it. It calls the three methods on the Service object in sequence.
    • cancel()
    • reset()
    • start()

The program shows how to use a Service. The Service object is created and stored as an
instance variable. The Service object manages a PrimeFinderTask object, which is a Task to find prime
numbers between two numbers. Four buttons are added: Start/Restart, Cancel, Reset, and Exit. The Start
button is labeled Restart after the Service is started for the first time. The buttons do what their labels
indicate. Buttons are disabled when they are not applicative. 
*/
public class PrimeFinderService  extends Application {
    
	Button startBtn = new Button("Start");	
	Button cancelBtn = new Button("Cancel");	
	Button resetBtn = new Button("Reset");
	Button exitBtn = new Button("Exit");
	boolean onceStarted = false;
	
	// Create the service
	Service<ObservableList<Long>> service = new Service<ObservableList<Long>>() {
		@Override
		protected Task<ObservableList<Long>> createTask() {
			return new PrimeFinderTask();
		}
	};

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Add event handlders to the buttons
		addEventHandlders();

		// Enable disable buttons based on the service state
		bindButtonsState();
			
		GridPane pane = new WorkerStateUI(service);
		HBox buttonBox = new HBox(5, startBtn, cancelBtn, resetBtn, exitBtn);
		BorderPane root = new BorderPane();
		root.setCenter(pane);
		root.setBottom(buttonBox);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("A Prime Number Finder Service");
		stage.show();
	}

	public void addEventHandlders() {
		// Add event handlders to the buttons
		startBtn.setOnAction(e -> {
			if (onceStarted) {
				service.restart();
			} else {
				service.start();
				onceStarted = true;
				startBtn.setText("Restart");
			}
		});

		cancelBtn.setOnAction(e -> service.cancel());
		resetBtn.setOnAction(e -> service.reset()); 
		exitBtn.setOnAction(e -> Platform.exit());
	}
	
	public void bindButtonsState() {
		cancelBtn.disableProperty().bind(service.stateProperty().isNotEqualTo(RUNNING));
		resetBtn.disableProperty().bind(
			Bindings.or(service.stateProperty().isEqualTo(RUNNING),
			            service.stateProperty().isEqualTo(SCHEDULED)));
	}
}
