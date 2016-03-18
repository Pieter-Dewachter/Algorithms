package algorithms.Stack;

/**
 * Basic stack interface
 * 
 * @param <E> type of the elements on the stack
 */
public interface Stack<E> {
	/**
	 * 
	 * @return the number of elements in the stack
	 */
	int size();
	
	/**
	 * 
	 * @return size == 0
	 */
	boolean isEmpty();
	
	/**
	 * Push an element on the stack
	 * @param element to push on the stack
	 */
	void push(E element);
	
	/**
	 * Peek at the top element
	 * @return the top element
	 */
	E top();
	
	/**
	 * Remove the top element of the stack
	 * @return the top element
	 */
	E pop();
	
}
