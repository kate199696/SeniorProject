package csu.csci325;

/**
 * Created by Kaitlyn on 11/17/2016.
 */
//creates the class for LinkedListStack
public class LinkedListStack<T> implements Stack<T> {
    SinglyLinkedListNode<T> mTop;
    public LinkedListStack(){

    }

//pushes to the stack
    @Override
    public void push(T object) {
        SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(object);
        if (mTop == null) {
            mTop = node;
        } else {
            node.setNext(mTop);
            mTop = node;
        }
    }

    //pops off of the stack
    @Override
    public T pop() {
        if(mTop != null){
            T ret = peek();
            mTop = mTop.getNext();
            return ret;
        }
        return null;
    }

    //looks at the next variable but does not pull it
    @Override
    public T peek() {
        if(mTop == null){
            return null;
        }
        else{
            return mTop.getData();
        }
    }

    //returns the size of the stack
    @Override
    public int size() {
        SinglyLinkedListNode<T> rover = mTop;
        int size = 0;
        while (rover != null) {
            size++;
            rover = rover.getNext();
        }
        return size;
    }

    //tells that the stack is empty
    @Override
    public boolean isEmpty() {
        return (mTop == null);
    }


    //test code
    public static void main(String[] args) {
        //creates the stack
        Stack<Integer> stack = new LinkedListStack<>();
        //pushes variables to the stack
        stack.push(0);
        System.out.println("size is: " + stack.size());
        stack.push(1);
        stack.push(2);
        System.out.println("size is: " + stack.size());
        stack.push(5);
        stack.push(6);
        System.out.println("size is: " + stack.size());

        //pops from the stack

        System.out.println("The top variable is: " + stack.peek());
        stack.pop();
        System.out.println("The top variable is: " + stack.peek());
        System.out.println("size is: " + stack.size());

        //checks if the stack is empty
        System.out.println("The stack is empty? " + stack.isEmpty());
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println("The stack is empty? " + stack.isEmpty());


    }
}
