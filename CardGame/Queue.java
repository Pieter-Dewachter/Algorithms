package algorithms.CardGame;

public interface Queue<E> {
    void enqueue(E element) throws Exception;
    E dequeue() throws Exception;
    int size();
    boolean isEmpty();
    E front();
}
