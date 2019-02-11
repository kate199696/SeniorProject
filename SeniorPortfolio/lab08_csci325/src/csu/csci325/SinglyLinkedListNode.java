package csu.csci325;

/**
 * Created by Kaitlyn on 11/17/2016.
 */
//creates the node
public class SinglyLinkedListNode<T> {
    //creates variables
    private T mData;
    private SinglyLinkedListNode<T> mNext;

    //initializes the variables
    public SinglyLinkedListNode(T data) {
        mData = data;
    }

    //gets the data
    public T getData() {
        return mData;
    }
    //sets the next in the list
    public void setNext(SinglyLinkedListNode<T> next) {
        mNext = next;
    }
    //gets what the next variable is
    public SinglyLinkedListNode<T> getNext() {
        return mNext;
    }

    //test code
    public static void main(String[] args) {
        SinglyLinkedListNode<Integer> start = new SinglyLinkedListNode<Integer>(10);
        SinglyLinkedListNode<Integer> next = new SinglyLinkedListNode<Integer>(15);
        start.setNext(next);
    }
}
