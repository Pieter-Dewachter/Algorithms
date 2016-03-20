package algorithms.CardGame;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayQueueTest {
    private ArrayQueue<String> queue;

    public ArrayQueueTest() { queue = new ArrayQueue<String>(3); }

    @Test
    public void testEnqueue() throws Exception {
        assertEquals(0, queue.size());

        queue.enqueue("1");
        assertEquals("1", queue.front());
        assertEquals(1, queue.size());

        queue.enqueue("2");
        assertEquals("1", queue.front());
        assertEquals(2, queue.size());
    }

    @Test
    public void testDequeue() throws Exception {
        queue.enqueue("1");
        assertEquals(1, queue.size());

        queue.dequeue();
        assertTrue(queue.isEmpty());

        queue.enqueue("1");
        assertEquals(1, queue.size());

        queue.enqueue("2");
        assertEquals(2, queue.size());

        queue.dequeue();
        assertEquals(1, queue.size());

        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(0, queue.size());

        queue.enqueue("1");
        assertEquals(1, queue.size());

        queue.enqueue("2");
        assertEquals(2, queue.size());
    }

    @Test
    public void testIsEmpty() throws Exception {
        queue.enqueue("1");
        assertFalse(queue.isEmpty());

        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void front() throws Exception {
        assertEquals(queue.front(), null);

        queue.enqueue("1");
        assertEquals("1", queue.front());

        queue.enqueue("2");
        assertEquals("1", queue.front());
    }

    @Test
    public void testCircularBehaviour() throws Exception {
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");

        queue.dequeue();
        queue.enqueue("4");

        assertEquals("2", queue.front());
    }
}