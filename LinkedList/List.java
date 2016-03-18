package algorithms.LinkedList;

public class List<E> {
	private Node<E> head;
	private int size = 1;
	
	public List(){
		head = null;
		size = 0;
	}
	
	public List(E element) {
		head = new Node<E>(element);
	}
	
	public int size() {
		return size;
	}
	
	public Node<E> head() {
		return head;
	}
	
	public void prepend(Node<E> node) {
		node.setNext(head);
		head = node;
		size++;
	}
	
	public List<E> prepend(List<E> list) {
		size += list.size;
		last(list.head).setNext(head);
		head = list.head();
		
		return this;
	}
	
	private Node<E> last(Node<E> cursor) {
		if(cursor.next() == null)
			return cursor;
		return last(cursor.next());
	}
	
	public Node<E> removeHead(){
		Node <E> oldHead = head;
		head = head.next();
		size--;
		return oldHead;
	}
	
	public boolean contains(E element) {
		Node<E> cursor = head;
		while(cursor.next() != null) {
			if(cursor.get().equals(element)) {
				return true;
			}
			cursor = cursor.next();
		}
		return false;
	}
}
