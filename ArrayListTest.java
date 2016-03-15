package algorithms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {
	ArrayList<String> list; 
	
	@Before
	public void setUp() throws Exception {
		list = new ArrayList<String>();
	}

	@Test
	public void testSize() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		list.set(5, "6");
		list.set(42, "42");
		
		assertEquals(2, list.size());
	}

	@Test
	public void testSet() {
		list.set(0, "1");
		assertEquals("1", list.get(0));
		assertEquals("1", list.set(0, "2"));
		assertEquals("2", list.get(0));
		assertEquals(1, list.size());
	}

	
	@Test
	public void testRemove() {
		list.set(0, "1");
		assertEquals("1", list.remove(0));
		assertEquals(0, list.size());
	}
	
	@Test
	public void testAddMoreElementsThanCapacity() {
		list.set(2, "1");
		assertEquals("1", list.get(2));
	}
	
	@Test
	public void testAddAtIndexLargerThanTwiceTheCapacity() {
		list.set(8, "1");
		assertEquals("1", list.get(8));
	}

	@Test
	public void testIterator(){
		list.set(0, "1");
		list.set(1, "2");
		list.set(2, "3");
		
		StringBuilder result = new StringBuilder();
		
		for(String s : list){
			result.append(s);
		}
		
		assertEquals("123", result.toString());
	}
}