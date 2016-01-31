
package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
Every JavaFX application class inherits from the Application class. The Application class is in the
javafx.application package. It contains a static launch() method. Its sole purpose is to launch a JavaFX
application. It is an overloaded method with the following two variants:
    • static void launch(Class<? extends Application> appClass, String... args)
    • static void launch(String... args)

Notice that you do not create an object of your JavaFX application class to launch it. The JavaFX runtime
creates an object of your application class when the launch() method is called.

Tip: Your JavaFX application class must have a no-args constructor, otherwise a runtime exception will be
thrown when an attempt is made to launch it.

The first variant of the launch() method is clear. You pass the class reference of your application class
as the first argument, and the launch() method will create an object of that class. The second argument is
comprised of the command-line arguments passed to the application. The following snippet of code shows
how to use the first variant of the launch() method:
    public class MyJavaFXApp extends Application {
        public static void main(String[] args) {
            Application.launch(MyJavaFXApp.class, args);
        }
    }

The class reference passed to the launch() method does not have to be of the same class from which
the method is called. For example, the following snippet of code launches the MyJavaFXApp application class
from the MyAppLauncher class, which does not extend the Application class:
    public class MyAppLauncher {
        public static void main(String[] args) {
            Application.launch(MyJavaFXApp.class, args);
        }
    }

The second variant of the launch() method takes only one argument, which is the command-line
argument passed to the application. Which JavaFX application class does it use to launch the application?
It attempts to find the application class name based on the caller. It checks the class name of the code that
calls it. If the method is called as part of the code for a class that inherits from the Application class, directly
or indirectly, that class is used to launch the JavaFX application. Otherwise, a runtime exception is thrown.
Let’s look at some examples to make this rule clear.

In the following snippet of code, the launch() method detects that it is called from the main() method
of the MyJavaFXApp class. The MyJavaFXApp class inherits from the Application class. Therefore, the
MyJavaFXApp class is used as the application class:
    public class MyJavaFXApp extends Application {
        public static void main(String[] args) {
            Application.launch(args);
        }
    }

In the following snippet of code, the launch() method is called from the main() method of the Test
class. The Test does not inherit from the Application class. Therefore, a runtime exception is thrown, as
shown in the output below the code:
    public class Test {
        public static void main(String[] args) {
            Application.launch(args);
        }
    }

The best practice in launching a JavaFX application: 
limit the code in the main() method to only one statement that launches the application, 
as shown in the following code:
    public class MyJavaFXApp extends Application {
        public static void main(String[] args) {
            Application.launch(args);
            // Do not add any more code in this method
        }
        // More code goes here
    }

Tip: The launch() method of the Application class must be called only once, otherwise, a runtime
exception is thrown. The call to the launch() method blocks until the application is terminated. It is not always
necessary to have a main() method to launch a JavaFX application. A JavaFX packager synthesizes one for
you. For example, when you use the NetBeans IDE, you do not need to have a main() method, and if you have
one, NetBeans ignores it.
---------------------------------------------------------------------------
        
The Life Cycle of a JavaFX Application:

JavaFX runtime creates several threads. At different stages in the application, threads are used to perform
different tasks. In this section, I will only explain those threads that are used to call methods of the
Application class during its life cycle. The JavaFX runtime creates, among other threads, two threads:
    • JavaFX-Launcher
    • JavaFX Application Thread

The launch() method of the Application class create these threads. During the lifetime of a JavaFX
application, the JavaFX runtime calls the following methods of the specified JavaFX Application class in
order:
    • The no-args constructor
    • The init() method
    • The start() method
    • The stop() method

The JavaFX runtime creates an object of the specified Application class on the JavaFX Application
Thread. The JavaFX Launcher Thread calls the init() method of the specified Application class. The
init() method implementation in the Application class is empty. You can override this method in your
application class. It is not allowed to create a Stage or a Scene on the JavaFX Launcher Thread. They must be
created on the JavaFX Application Thread. Therefore, you cannot create a Stage or a Scene inside the init()
method. Attempting to do so throws a runtime exception. It is fine to create UI controls, for example, buttons
or shapes.

The JavaFX Application Thread calls the start(Stage stage) method of the specified Application
class. Note that the start() method in the Application class is declared abstract, and you must override
this method in your application class.

At this point, the launch() method waits for the JavaFX application to finish. When the application
finishes, the JavaFX Application Thread calls the stop() method of the specified Application class. The
default implementation of the stop() method is empty in the Application class. You will have to override
this method in your application class to perform your logic when your application stops.

The code illustrates the life cycle of a JavaFX application. It displays an empty stage.
You will need to close the stage to see the last line of the output.

---------------------------------------------------------------------
Terminating a JavaFX Application:

A JavaFX application may be terminated explicitly or implicitly. You can terminate a JavaFX application
explicitly by calling the Platform.exit() method. When this method is called, after or from within the
start() method, the stop() method of the Application class is called, and then the JavaFX Application
Thread is terminated. At this point, if there are only daemon threads running, the JVM will exit. If this
method is called from the constructor or the init() method of the Application class, the stop() method
may not be called.

Tip: A JavaFX application may be run in web browsers. Calling the Platform.exit() method in web
environments may not have any effect.

A JavaFX application may be terminated implicitly, when the last window is closed. This behavior can
be turned on and turned off using the static setImplicitExit(boolean implicitExit) method of the
Platform class. Passing true to this method turns this behavior on. Passing false to this method turns this
behavior off. By default, this behavior is turned on. This is the reason that in most of the examples so far,
applications were terminated when you closed the windows. When this behavior is turned on, the stop()
method of the Application class is called before terminating the JavaFX Application Thread. Terminating
the JavaFX Application Thread does not always terminate the JVM. The JVM terminates if all running
nondaemon threads terminate. If the implicit terminating behavior of the JavaFX application is turned off,
you must call the exit() method of the Platform class to terminate the application.
*/
public class FXLifeCycleApp extends Application {
	public FXLifeCycleApp() {
		String name = Thread.currentThread().getName();
		System.out.println("FXLifeCycleApp() constructor: " + name);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() {
		String name = Thread.currentThread().getName();
		System.out.println("init() method: " + name);
	}

	@Override
	public void start(Stage stage) {
		String name = Thread.currentThread().getName();
		System.out.println("start() method: " + name);

		Scene scene = new Scene(new Group(), 200, 200);
		stage.setScene(scene);
		stage.setTitle("JavaFX Application Life Cycle");
		stage.show();
	}

	@Override
	public void stop() {
		String name = Thread.currentThread().getName();
		System.out.println("stop() method: " + name);
	}
}
