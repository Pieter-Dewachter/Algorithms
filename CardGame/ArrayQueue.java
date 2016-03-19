package algorithms.CardGame;

public class ArrayQueue<E> implements Queue<E> {
    private int size, first, last;
    private E[] array;

    public ArrayQueue(int size) {
        array = (E[]) new Object[size];
        size = 0;
        first = 0;
        last = -1;
    }

    public void enqueue(E element) throws Exception {
        if (size == array.length)
            throw new Exception("The queue is full");
        size++;

        if (last == array.length-1)
            last = 0;
        else
            last++;

        array[last] = element;
    }

    public E dequeue() throws Exception {
        if (size == 0)
            throw new Exception("The queue is empty");
        size--;

        E cursor = array[first];
        array[first] = null;

        if (first == array.length-1)
            first = 0;
        else
            first++;

        return cursor;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E front() {
        return array[first];
    }
}
