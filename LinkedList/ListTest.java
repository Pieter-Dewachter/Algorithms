package algorithms.LinkedList;
import org.junit.*;
import static org.junit.Assert.*;

public class ListTest {
	private List<String> list;
	
	@Before
	public void setUp() throws Exception {
		list = new List<String>("1");
	}

	@Test
	public void testConstructor() {
		assertEquals(1, list.size());
		assertEquals(new Node<String>("1"), list.head());
	}
	
	@Test
	public void testPrependWithNode() {
		Node<String> node = new Node<String>("2");
		list.prepend(node);
		assertEquals(2, list.size());
		assertEquals(node, list.head());
	}
	
	@Test
	public void testPrependAnotherList() {
		List<String> list2 = new List<String>("2");
		Node<String> element3 = new Node<String>("3");
		list2.prepend(element3);
		list.prepend(list2);
		assertEquals(element3, list.head()); // assertEquals(list2.head(), list.head());
		assertEquals(3, list.size());
	}
}
