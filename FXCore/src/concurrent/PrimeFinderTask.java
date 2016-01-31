
package concurrent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/*
An instance of the Task<V> class represents a one-time task. Once the task is completed, cancelled, or failed,
it cannot be restarted. The Task<V> class implements the Worker<V> interface. Therefore, all properties and
methods specified by the Worker<V> interface are available in the Task<V> class.
The Task<V> class inherits from the FutureTask<V> class, which is part of the Java concurrency
framework. The FutureTask<V> implements the Future<V>, RunnableFuture<V>, and Runnable interfaces.
Therefore, a Task<V> also implements all these interfaces.

Creating a Task<V> is easy. You need to subclass the Task<V> class and
provide an implementation for the abstract method call(). The call() method contains the logic to
perform the task. The following snippet of code shows the skeleton of a Task implementation:

    // A Task that produces an ObservableList<Long>
    public class PrimeFinderTask extends Task<ObservableList<Long>> {
        @Override
        protected ObservableList<Long>> call() {
            // Implement the task logic here...
        }
    }

Typically, you would want to update the properties of the task as it progresses. The properties must be
updated and read on the JavaFX Application Thread, so they can be observed safely in a GUI environment.
The Task<V> class provides special methods to update some of its properties.
    • protected void updateMessage(String message)
    • protected void updateProgress(double workDone, double totalWork)
    • protected void updateProgress(long workDone, long totalWork)
    • protected void updateTitle(String title)
    • protected void updateValue(V value)
You provide the values for the workDone and the totalWork properties to the updateProgress()
method. The progress property will be set to workDone/totalWork. The method throws a runtime exception
if the workDone is greater than the totalWork or both are less than -1.0.

Sometimes, you may want to publish partial results of a task in its value property. The updateValue()
method is used for this purpose. The final result of a task is the return value of its call() method.

All updateXxx() methods are executed on the JavaFX application Thread. Their names indicate the
property they update. They are safe to be called from the call() method of the Task. If you want to
update the properties of the Task from the call() method directly, you need to wrap the code inside a
Platform.runLater() call.

The Task class contains the following properties to let you set event handlers for its state transitions:
    • onCancelled
    • onFailed
    • onRunning
    • onScheduled
    • onSucceeded
The following snippet of code adds an onSucceeded event handler, which would be called when the task
transitions to the SUCCEEDED state:
    Task<ObservableList<Long>> task = create a task...
    task.setOnSucceeded(e -> {
        System.out.println("The task finished. Let us party!")
    });


Cancelling a Task:
-------------------
Use one of the following two cancel() methods to cancel a task:
    • public final boolean cancel()
    • public boolean cancel(boolean mayInterruptIfRunning)
The first version removes the task from the execution queue or stops its execution. The second
version lets you specify whether the thread running the task be interrupted. Make sure to handle the
InterruptedException inside the call() method. Once you detect this exception, you need to finish the
call() method quickly. Otherwise, the call to cancel(true) may not cancel the task reliably. The cancel()
method may be called from any thread.

The following methods of the Task are called when it reaches a specific state:
    • protected void scheduled()
    • protected void running()
    • protected void succeeded()
    • protected void cancelled()
    • protected void failed()
Their implementations in the Task class are empty. They are meant to be overridden by the subclasses.


Running a Task:
---------------
A Task is Runnable as well as a FutureTask. To run it, you can use a background thread or an ExecutorService.
    // Schedule the task on a background thread
    Thread backgroundThread = new Thread(task);
    backgroundThread.setDaemon(true);
    backgroundThread.start();
    // Use the executor service to schedule the task
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.submit(task);


The program is an implementation of the Task<ObservableList<Long>>. 
It checks for prime numbers between the specified lowerLimit and upperLimit. 
It returns all the numbers in the range. Notice that the task thread sleeps for a short time before
checking a number for a prime number. This is done to give the user an impression of a long-running task.
It is not needed in a real world application. The call() method handles an InterruptedException and
finishes the task if the task was interrupted as part of a cancellation request.

The call to the method updateValue() needs little explanation.
    updateValue(FXCollections.<Long>unmodifiableObservableList(results));

Every time a prime number is found, the results list is updated. The foregoing statement wraps the
results list in an unmodifiable observable list and publishes it for the client. This gives the client access to the
partial results of the task. This is a quick and dirty way of publishing the partial results. If the call() method
returns a primitive value, it is fine to call the updateValue() method repeatedly.
*/
public class PrimeFinderTask extends Task<ObservableList<Long>> {
    
	private long lowerLimit = 1;
	private long upperLimit = 30;
	private long sleepTimeInMillis = 500;

	public PrimeFinderTask() {
	}

	public PrimeFinderTask(long lowerLimit, long upperLimit) {
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
	}

	public PrimeFinderTask(long lowerLimit, long upperLimit, long sleepTimeInMillis) {
		this(lowerLimit, upperLimit);
		this.sleepTimeInMillis = sleepTimeInMillis;
	}

	// The task implementation
	@Override
	protected ObservableList<Long> call() {
		// An observable list to represent the results
		final ObservableList<Long> results = FXCollections.<Long>observableArrayList();

		// Update the title
		this.updateTitle("Prime Number Finder Task");

		long count = this.upperLimit - this.lowerLimit + 1;
		long counter = 0;

		// Find the prime numbers
		for (long i = lowerLimit; i <= upperLimit; i++) {
			// Check if the task is cancelled
			if (this.isCancelled()) {
				break;
			}

			// Increment the counter
			counter++;

			// Update message
			this.updateMessage("Checking " + i + " for a prime number");

			// Sleep for some time
			try {
				Thread.sleep(this.sleepTimeInMillis);
			}
			catch (InterruptedException e) {
				// Check if the task is cancelled
				if (this.isCancelled()) {
					break;
				}
			}

			// Check if the number is a prime number
			if (PrimeUtil.isPrime(i)) {
				// Add to the list
				results.add(i);

				// Publish the read-only list to give the GUI access to the partial results
				updateValue(FXCollections.<Long>unmodifiableObservableList(results));
			}

			// Update the progress
			updateProgress(counter, count);
		}

		return results;
	}

	@Override
	protected void cancelled() {
		super.cancelled();
		updateMessage("The task was cancelled.");
	}

	@Override
	protected void failed() {
		super.failed();
		updateMessage("The task failed.");
	}

	@Override
	public void succeeded() {
		super.succeeded();
		updateMessage("The task finished successfully.");
	}
}
