package algorithms.PositionalList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedPositionalListTest {

	LinkedPositionalList<String> poslist;
	
	Position<String> first;
	Position<String> last;

	@Before
	public void setUp() {
		poslist = new LinkedPositionalList<String>();
		first = poslist.prepend("first");
		last = poslist.append("last");
	}

	@Test
	public void testConstructor() {
		LinkedPositionalList<String> poslist = new LinkedPositionalList<String>();
		assertTrue(poslist.isEmpty());
		assertEquals(0, poslist.size());
	}

	@Test
	public void testAppendandPrepend() {
		
		assertEquals(2, poslist.size());
		assertFalse(poslist.isEmpty());
		
	}
	
	@Test
	public void testGet(){
		assertEquals("first", first.getElement());
		assertEquals("last", last.getElement());
	}
	
	@Test
	public void testBeforeAndAfter(){
		
		assertEquals(first, poslist.before(last));
		assertEquals(last, poslist.after(first));
		
	}

	@Test
	public void testAddBeforeAndAddAfter(){
				
		Position<String> second = poslist.addAfter(first, "addAfter");
		Position<String> third =poslist.addBefore(last, "addBefore");
		
		assertEquals(second, poslist.after(first));
		assertEquals(third, poslist.before(last));
		
	}
	
	@Test
	public void testSet(){
		assertEquals("first", poslist.set(first, "replaced"));
		assertEquals("replaced", poslist.first().getElement());
	}
	
	@Test
	public void testRemove(){
		assertEquals("last", poslist.remove(last));
		assertEquals(1, poslist.size());
		assertEquals(first, poslist.last());
	}
	
	@Test
	public void testAddBeforeAndAddAfterAtEnd(){
				
		Position<String> before = poslist.addBefore(first, "before");
		Position<String> after =poslist.addAfter(last, "after");
		
		assertEquals(before, poslist.before(first));
		assertEquals(after, poslist.after(last));
	}
	
	@Test
	public void testHasNext(){
		assertTrue(poslist.hasNext(first));
		assertFalse(poslist.hasNext(last));
	}
	
	@Test
	public void testIterator(){
		poslist.addAfter(first, "middle");
		
		StringBuilder str = new StringBuilder();
		
		for(String s : poslist){
			str.append(s);
			str.append(" ");
		}
		
		assertEquals("first middle last ", str.toString());
		
	}
	
	//@Test
	public void testAddBeforeAndAfterSentinels(){
		LinkedPositionalList<String> poslist = new LinkedPositionalList<String>();
		Position<String> header = poslist.last();
		Position<String> trailer = poslist.first();
		Position<String> before = poslist.addBefore(header, "before");
		Position<String> after = poslist.addAfter(trailer, "after");
		
		/*
		 * What do you want to happen?
		 * 1) Undefined behavior: you should not add before or after the respective sentinel
		 * 1.1) You might want to avoid leaking the sentinel returning null in the position method
		 * 2) Throw an exception: check if an illegal add happens and throw an exception
		 * 3) Assume adding before or after the respective sentinel effectively means adding it in first or last position respectively
		 */
		
	}
}
