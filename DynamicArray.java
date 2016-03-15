package algorithms;

import java.util.Iterator;

public class DynamicArray<E> implements IndexList<E>, Iterable<E> {
	private int size = 0;
	private E[] elements;
	private int capacity;

	/**
	 * Constructs a dynamic array with an initialCapacity
	 * @param initialCapacity
	 */
	public DynamicArray(int initialCapacity) {
		capacity = initialCapacity;
		@SuppressWarnings("unchecked")
		E[] temp = (E[]) new Object[capacity];
		elements = temp;		
	}
	
	/**
	 * Constructs a dynamic array with an initial capacity of 2
	 */
	public DynamicArray() {
		this(2);
	}

	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public E set(int index, E element) {
		int times = capacityCheck(index);
		if (times > 0)
			doubleArray(times);
				
		E e = get(index);
		
		if(e == null) size++;
		
		elements[index] = element;
		return e;
	}
	
	
	/**
	 * Indicates how many times the capacity of the array must be double given the index
	 * @param index
	 * @return 0 if it should not be doubled, >1 if more
	 */
	private int capacityCheck(int index){
		if(capacity > index) return 0;
		int result = 1;
		while((capacity * 2 * result) <= index){
			result++;
		}
		return result;
	}
	
	/**
	 * Doubles the array a given times
	 * @param times
	 */
	private void doubleArray(int times) {
		capacity = 2 * times * capacity ;
		@SuppressWarnings("unchecked")
		E[] temp = (E[]) new Object[capacity];

		for (int i = 0; i < size; i++) {
			temp[i] = elements[i];
		}
		elements = temp;
	}

	@Override
	public E get(int index) {
		return elements[index];
	}

	@Override
	public E remove(int index) {
		size--;
		E e = get(index);
		elements[index] = null;
		return e;
	}

	/**
	 * @return a lazy iterator of all elements in the dynamic array
	 */
	public Iterator<E> iterator(){
		return new ArrayIterator();
	}
	
	/**
	 * 
	 * Lazy iterator implementation for the dynamic array class
	 *
	 */
	private class ArrayIterator implements Iterator<E> {
		private int index = 0;

		/**
		 * @return true if there is a next element in the iterator
		 */
		public boolean hasNext() {
			return index < size;
		}
		
		/**
		 * @return the next element and updates the next reference
		 */
		public E next() {
			return elements[index++];
			
			// Basically the same as writing:
			// E element = elements[index];
			// index++;
			// return element;
		}
	}
}