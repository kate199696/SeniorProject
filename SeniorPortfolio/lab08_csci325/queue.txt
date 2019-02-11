package csu.csci325;

/**
 * Created by Kaitlyn on 11/17/2016.
 */
public interface Queue<T> {
    // adds an Object to the front of the queue.
    void enqueue(T o);
    // removes and returns the first object of the aueue.
    T dequeue();
    // returns the first object of the queue.
    T first();
    // returns how many items are in the queue.
    int size();
    boolean isEmpty();
}
