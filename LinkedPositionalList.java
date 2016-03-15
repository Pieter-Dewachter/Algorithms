package algorithms;

import java.util.Iterator;


public class LinkedPositionalList<E> implements PositionalList<E>, Iterable<E> {
	private int size = 0;
	private Node<E> header;
	private Node<E> trailer;
	
	/**
	 * Construct an empty linked positional list
	 */
	public LinkedPositionalList(){
		header = new Node<E>(null, null, null);
		trailer = new Node<E>(null, header, null);
		header.setNext(trailer);
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private Position<E> addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> node = new Node<E>(e, predecessor, successor);
		predecessor.setNext(node);
		successor.setPrev(node);
		size++;
		return node;
	}
	
	@Override
	public Position<E> first() {
		return header.getNext();
	}

	@Override
	public Position<E> last() {
		return trailer.getPrev();
	}

	@Override
	public Position<E> prepend(E e) {
		return addBetween(e, header, header.getNext());
	}

	@Override
	public Position<E> append(E e) {
		return addBetween(e, trailer.getPrev(), trailer);
	}
	
	/**
	 * Helper method to convert a position to a node
	 * @param p
	 * @return the corresponding node
	 * @throws IllegalArgumentException if p is not a valid position
	 */
	private Node<E> validate(Position<E> p) throws IllegalArgumentException{
		if(!(p instanceof Node)) throw new IllegalArgumentException("Invalid position");
		Node<E> node = (Node<E>) p;
		return node;
	}
	
	/**
	 * Helper method to convert a node into a position
	 * @param node
	 * @return the corresponding position
	 */
	private Position<E> position(Node<E> node){
		return node;
	}
	
	@Override
	public Position<E> before(Position<E> p) {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

	@Override
	public Position<E> after(Position<E> p) {
		Node<E> node = validate(p);
		return position(node.getNext());
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e) {
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e) {
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}

	@Override
	public E set(Position<E> p, E e) {
		Node<E> node = validate(p);
		E element = node.getElement();
		node.setElement(e);
		return element;
	}

	@Override
	public E remove(Position<E> p) {
		Node<E> node = validate(p);
		E element = node.getElement();
		size--;
		
		Node<E> next = node.getNext();
		Node<E> prev = node.getPrev();
		prev.setNext(next);
		next.setPrev(prev);
		
		node.setNext(null);
		node.setPrev(null);
		node.setElement(null);
		return element;
	}
	
	/**
	 * @return a lazy iterator of all elements in the dynamic array
	 */
	public Iterator<E> iterator(){
		return new LinkedPositionalListIterator();
	}
	
	/**
	 * Check if the position p has a next position
	 * @param p
	 * @return true if not the last position
	 */
	public boolean hasNext(Position<E> p){
		Node<E> node = validate(p);
		return (node.getNext() != trailer);
	}
	
	/**
	 * 
	 * Lazy iterator implementation for the dynamic array class
	 *
	 */
	private class LinkedPositionalListIterator implements Iterator<E> {
		// private, but not static: this class can access the outer class!
		private Node<E> index = header;

		/**
		 * @return true if there is a next element in the iterator
		 */
		public boolean hasNext() {
			return LinkedPositionalList.this.hasNext(index);
			// hasNext method of outer class
		}
		
		/**
		 * @return the next element and updates the next reference
		 */
		public E next() {
			index = index.getNext();
			return index.getElement();
		}
	}
	
	private static class Node<E> implements Position<E> {
		// static means the inner class can't access the outer class!
		private E element;
		private Node<E> prev;
		private Node<E> next;
		
		public Node(E e, Node<E> p, Node<E> n){
			element = e;
			prev = p;
			next = n;
		}
		
		public E getElement(){
			return element;
		}
		
		public void setElement(E e){
			element = e;
		}
		
		public Node<E> getPrev(){
			return prev;
		}
		
		public void setPrev(Node<E> p){
			prev = p;
		}
		
		public Node<E> getNext(){
			return next;
		}
		
		public void setNext(Node<E> n){
			next = n;
		}
	}
}
