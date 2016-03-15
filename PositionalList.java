package algorithms;

public interface PositionalList<E> {
	/**
	 * @return the number of elements in the PositionalList
	 */
	int size();
	
	/**
	 * @return size == 0
	 */
	boolean isEmpty();
	
	/**
	 * @return the first position
	 */
	Position<E> first();
	
	/**
	 * @return the last position
	 */
	Position<E> last();
	
	/**
	 * @param p
	 * @return position before p, or null if first
	 */
	Position<E> before(Position<E> p);
	
	/**
	 * @param p
	 * @return position after p, or null if last
	 */
	Position<E> after(Position<E> p);
	
	/**
	 * Adds element e to the front of the PositionalList
	 * @param e 
	 * @return its position
	 */
	Position<E> prepend(E e);
	
	/**
	 * Adds element e at the back of the PositionalList
	 * @param e 
	 * @return its position
	 */
	Position<E> append(E e);
	
	/**
	 * Adds element e before p in the PositionalList
	 * @param p
	 * @param e
	 * @return its position
	 */
	Position<E> addBefore(Position<E> p, E e);
	
	/**
	 * Adds element e after p in the PositionalList
	 * @param p
	 * @param e
	 * @return its position
	 */
	Position<E> addAfter(Position<E> p, E e);
	
	/**
	 * Sets the element at p in the PositionalList
	 * @param p
	 * @param e
	 * @return the old element at p
	 */
	E set(Position<E> p, E e);
	
	/**
	 * Removes the position from the PositionalList
	 * @param p
	 * @return the element at p
	 */
	E remove(Position<E> p);
}
