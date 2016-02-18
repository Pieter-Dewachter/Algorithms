package algorithms;

public class List<E> {
	private Node<E> head;
	private int size = 1;
	
	List(E element) {
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
	
	public void prepend(List<E> list) {
		size += list.size;
		Node<E> cursor = list.head();
		while(cursor.next() != null) {
			cursor = cursor.next();
		}
		cursor.setNext(head);
		head = list.head();
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
