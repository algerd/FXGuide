
package concurrent;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import static javafx.concurrent.Worker.State.RUNNING;
import static javafx.concurrent.Worker.State.SCHEDULED;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
The ScheduledService<V> is a Service<V>, which automatically restarts. It can restart when it finishes
successfully or when it fails. Restarting on a failure is configurable. The ScheduledService<V> class inherits
from the Service<V> class. The ScheduledService is suitable for tasks that use polling. For example, you
may use it to refresh the score of a game or the weather report from the Internet after every 10 minutes.

The process of creating a ScheduledService is the same as that of creating a Service. You need to subclass
the ScheduledService<V> class and provide an implementation for the createTask() method.
The following snippet of code creates a ScheduledService that encapsulates a PrimeFinderTask, which
you have created earlier:
    // Create a scheduled service
    ScheduledService<ObservableList<Long>> service = new ScheduledService <ObservableList<Long>>() {
        @Override
        protected Task<ObservableList<Long>> createTask() {
            // Create and return a Task
            return new PrimeFinderTask();
        }
    };

The createTask() method of the service is called when the service is started or restarted manually or
automatically. Note that a ScheduledService is automatically restarted. You can start and restart it manually
by calling the start() and restart() methods.

Tip: Starting, cancelling, resetting, and restarting a ScheduledService work the same way as these
operations on a Service.

The ScheduledService<ScheduledService> class inherits properties from the Service<V> class. It adds the
following properties that can be used to configure the scheduling of the service.
    • lastValue
    • delay
    • period
    • restartOnFailure
    • maximumFailureCount
    • backoffStrategy
    • cumulativePeriod
    • currentFailureCount
    • maximumCumulativePeriod
A ScheduledService<V> is designed to run several times. The current value computed by the service is
not very meaningful. Your class adds a new property lastValue, which is of the type V, and it is the last value
computed by the service.

The delay is a Duration, which specifies a delay between when the service is started and when it begins
running. The service stays in the SCHEDULED state for the specified delay. The delay is honored only when
the service is started manually calling the start() or restart() method. When the service is restarted
automatically, honoring the delay property depends on the current state of the service. For example, if the
service is running behind its periodic schedule, it will rerun immediately, ignoring the delay property. The
default delay is zero.

The period is a Duration, which specifies the minimum amount of time between the last run and the
next run. The default period is zero.

The restartOnFailure specifies whether the service restarts automatically when it fails. By default, it is
set to true.

The currentFailureCount is the number of times the scheduled service has failed. It is reset to zero
when the scheduled service is restarted manually.
The maximumFailureCount specifies the maximum number of times the service can fail before it is
transitioned into the FAILED state and it is not automatically restarted again. Note that you can restart a
scheduled service any time manually. By default, it is set to Integer.MAX_VALUE.

The backoffStrategy is a Callback<ScheduledService<?>,Duration> that computes the Duration
to add to the period on each failure. Typically, if a service fails, you want to slow down before retrying it.
Suppose a service runs every 10 minutes. If it fails for the first time, you may want to restart it after
15 minutes. If it fails for the second time, you want to increase the rerun time to 25 minutes, and so on.
The ScheduledService class provides three built-in backoff strategies as constants.
    • EXPONENTIAL_BACKOFF_STRATEGY
    • LINEAR_BACKOFF_STRATEGY
    • LOGARITHMIC_BACKOFF_STRATEGY

The rerun gaps are computed based on the non-zero period and the current failure count.
The time between consecutive failed runs increases exponentially in the exponential backoffStrategy,
linearly in the linear backoffStrategy, and logarithmically in the logarithmic backoffStrategy.
The LOGARITHMIC_BACKOFF_STRATEGY is the default. When the period is zero, the following formulas are
used. The computed duration is in milliseconds.
    • Exponential: Math.exp(currentFailureCount)
    • Linear: currentFailureCount
    • Logarithmic: Math.log1p(currentFailureCount)

The following formulas are used for the non-null period:
    • Exponential: period + (period * Math.exp(currentFailureCount)
    • Linear: period + (period * currentFailureCount)
    • Logarithmic: period + (period * Math.log1p(currentFailureCount))

The cumulativePeriod is a Duration, which is the time between the current failed run and the next run.
Its value is computed using the backoffStrategy property. It is reset upon a successful run of the scheduled
service. Its value can be capped using the maximumCumulativePeriod property.

The ScheduledService goes through the same transition states as the Service. It goes through the
READY, SCHEDULED, and RUNNING states automatically after a successful run. Depending on how the scheduled
service is configured, it may go through the same state transitions automatically after a failed run.

You can listen to the state transitions and override the transition-related methods (ready(),
running(), failed(), etc.) as you can for a Service. When you override the transition-related methods
in a ScheduledService subclass, make sure to call the super method to keep your ScheduledService
working properly.

Let us use the PrimeFinderTask with a ScheduledService. Once started, the ScheduledService will keep
rerunning forever. If it fails five times, it will quit by transitioning itself to the FAILED state. You can cancel
and restart the service manually any time.
*/
public class PrimeFinderScheduledService extends Application {
    
	Button startBtn = new Button("Start");	
	Button cancelBtn = new Button("Cancel");	
	Button resetBtn = new Button("Reset");
	Button exitBtn = new Button("Exit");
	boolean onceStarted = false;
	
	// Create the scheduled service
	ScheduledService<ObservableList<Long>> service = 
			new ScheduledService<ObservableList<Long>>() {
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
		// Configure the scheduled service
		service.setDelay(Duration.seconds(5));
		service.setPeriod(Duration.seconds(30));
		service.setMaximumFailureCount(5);

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
		stage.setTitle("A Prime Number Finder Scheduled Service");
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
