package algorithms.Stack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackTest {

	Stack<String> stack;
	
	@Before
	public void setUp() {
		stack = new StackLL<String>();
	}

	@Test
	public void testSize() {
		
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
	}

	@Test
	public void testPushAndTop() {
		stack.push("1");
		
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());
		assertEquals("1", stack.top());
		
		stack.push("2");
		
		assertEquals(2, stack.size());
		assertEquals("2", stack.top());
	}

	@Test
	public void testPop() {
		stack.push("1");
		stack.push("2");
		
		assertEquals("2", stack.pop());
		assertEquals(1, stack.size());
		assertEquals("1", stack.pop());
		assertEquals(0, stack.size());
	}
	
	@Test
	public void testPopEmptyStack(){
		assertEquals(null, stack.pop());
		assertEquals(0, stack.size());
	}

}
