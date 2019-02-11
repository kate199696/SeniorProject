package csu.csci325;

/**
 * Created by Kaitlyn on 11/17/2016.
 */
//creates queue LinkedList class
public class LinkedListQueue<T> implements Queue<T> {
    SinglyLinkedListNode<T> mFront, mBack;
    private int count;

    //initializes the variables
    public LinkedListQueue(){
        mFront = null;
        mBack = null;
        count = 0;
    }

    //pushes variable onto the back of the queue
    @Override
    public void enqueue(T o) {
        SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(o);
        if(mFront == null){
            mFront = mBack = node;
        }
        else{
            mBack.setNext(node);
            mBack = node;
        }
        count++;
    }

    //takes the variable from the front of the queue
    @Override
    public T dequeue() {
        if(mFront == null){
            return null;
        }
        else{
            T object = first();
            count--;
            mFront = mFront.getNext();
            SinglyLinkedListNode<T> current = mFront;
            return object;
        }

    }

    //returns the first variable in the queue
    @Override
    public T first() {
        return mFront.getData();
    }

    //returns the size of the queue
    @Override
    public int size() {
        /*SinglyLinkedListNode<T> rover = mFront;
        int size = 0;
        while (rover != null) {
            size++;
            rover = rover.getNext();
        }
        return size;*/
        return count;
    }

    //returns if the queue is empty
    @Override
    public boolean isEmpty() {
        return (mFront == null);
    }

    //converts the variables to strings
    @Override
    public String toString(){
        return ("" + mFront.getData());
    }


    //test code
    public static void main(String[] args) {
        Queue<String> stack = new LinkedListQueue<>();
        System.out.println("The size is: " + stack.size());
        stack.enqueue("Phil");
        System.out.println("The size is: " + stack.size());
        stack.enqueue("Mark");
        stack.enqueue("Bob");
        System.out.println("The size is: " + stack.size());
        stack.enqueue("Sally");
        stack.enqueue("John");
        stack.enqueue("Lucille");
        System.out.println("The size is: " + stack.size());
        System.out.println("The first in line is: " + stack.first());
        stack.dequeue();
        System.out.println("First in line removed");
        System.out.println("The first in line is: " + stack.first());
        System.out.println(stack.size());
        System.out.println("The size is: " + stack.size());
        System.out.println("Adding new individual");
        stack.enqueue("Nancy");
        System.out.println("The size is: " + stack.size());

    }

}
