package algorithms;

import static org.junit.Assert.*;
import org.junit.Test;

public class FibonacciTest {
	final int result [] = {0, 1, 1, 2, 3, 5, 8, 13, 21};
	
	@Test
	public void testFib() {
		for(int i = 0; i < result.length; i++) {
			assertEquals(result[i], Fibonacci.fib(i));
		}
	}
	
	@Test
	public void testFibm() {
		for(int i = 0; i < result.length; i++) {
			assertEquals(result[i], Fibonacci.fibm(i));
		}
	}
}
