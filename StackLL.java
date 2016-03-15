package algorithms;

public class StackLL<E> implements Stack<E> {
	private List<E> list;
	
	public StackLL(){
		
		list = new List<E>();
	}
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	public void push(E element){
		list.prepend(new Node<E>(element));
	}
	
	public E top(){
		if(list.head() == null) return null;
		return list.head().get();
	}
	
	public E pop(){
		if(top() == null) return null;
		return list.removeHead().get();
	}
}
