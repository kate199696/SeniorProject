package csu.csci325;

/**
 * Created by Kaitlyn on 11/17/2016.
 */
public interface Stack<T> {
    // adds an Object to the top of the stack.
    void push(T o);
    // removes and returns the top object of the stack.
    T pop();
    // returns the top object of the stack.
    T peek();
    // returns how many items are in the stack.
    int size();
    boolean isEmpty();
}
