
package concurrent;

/*
Utility Class.

Utility class to check whether a number is a prime number
*/
public class PrimeUtil {
	public static boolean isPrime(long num) {
		if (num <= 1 || num % 2 == 0) {
			return false;
		}

		int upperDivisor = (int)Math.ceil(Math.sqrt(num));
	 	for (int divisor = 3; divisor <= upperDivisor; divisor += 2) {
			if (num % divisor == 0) {
				return false;
			}
		}
		return true;
	}
}