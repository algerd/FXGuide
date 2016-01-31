
package animation;

import javafx.util.Duration;

/*
The Duration class is in the javafx.util package. It represents a duration of time in milliseconds, seconds,
minutes, and hours. It is an immutable class. A Duration represents the amount of time for each cycle of an
animation. A Duration can represent a positive or negative duration.
You can create a Duration object in three ways.
    • Using the constructor
    • Using factory methods
    • Using the valueOf() method from a duration in String format

The constructor takes the amount of time in milliseconds.
    Duration tenMillis = new Duration(10);

Factory methods create Duration objects for different units of time. They are millis(), seconds(),
minutes(), and hours().
    Duration tenMillis = Duration.millis(10);
    Duration tenSeconds = Duration.seconds(10);
    Duration tenMinutes = Duration.minutes(10);
    Duration tenHours = Duration.hours(10);

The valueOf() static method takes a String argument containing the duration of time and returns a
Duration object. The format of the argument is “number[ms|s|m|h]”, where number is the amount of time,
and ms, s, m, and h denote milliseconds, seconds, minutes, and hours, respectively.
    Duration tenMillis = Duration.valueOf("10.0ms");
    Duration tenMililsNeg = Duration.valueOf("-10.0ms");

You can also represent a duration of an unknown amount of time and an indefinite time using the
UNKNOWN and INDEFINITE constants of the Duration class, respectively. You can use the isIndefinite()
and isUnknown() methods to check if a duration represents an indefinite or unknown amount of time. |
The class declares two more constants, ONE and ZERO, that represent durations of 1 millisecond
and 0 (no time), respectively.

The Duration class provides several methods to manipulate durations (adding a duration to another
duration, dividing and multiplying a duration by a number, comparing two durations, etc.).
*/
public class DurationTest {
    
	public static void main(String[] args) {
        
		Duration d1 = Duration.seconds(30.0);
		Duration d2 = Duration.minutes(1.5);
		Duration d3 = Duration.valueOf("35.25ms");
		System.out.println("d1  = " + d1);
		System.out.println("d2  = " + d2);
		System.out.println("d3  = " + d3);
		
		System.out.println("d1.toMillis() = " + d1.toMillis());
		System.out.println("d1.toSeconds() = " + d1.toSeconds());
		System.out.println("d1.toMinutes() = " + d1.toMinutes());
	 	System.out.println("d1.toHours() = " + d1.toHours());
		
		System.out.println("Negation of d1  = " + d1.negate());
		System.out.println("d1 + d2 = " + d1.add(d2));
		System.out.println("d1 / 2.0 = " + d1.divide(2.0));
	
		Duration inf = Duration.millis(1.0/0.0);
		Duration unknown = Duration.millis(0.0/0.0);
		System.out.println("inf.isIndefinite() = " + inf.isIndefinite());
		System.out.println("unknown.isUnknown() = " + unknown.isUnknown());
	}
}
