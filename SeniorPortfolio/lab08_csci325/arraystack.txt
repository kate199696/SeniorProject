package csu.csci325;

/**
 * Created by Kaitlyn on 11/17/2016.
 */
//creates array stack class
public class ArrayStack<T> implements Stack<T> {
    //creates variables
    private T[] mArray;
    private int mIndex;

    //initializes the variables
    public ArrayStack() {
        mArray = (T[])(new Object[5]);
        mIndex = 0;
    }

    //pushes onto the stack
    @Override
    public void push(T o) {
        // Increase capacity, if needed.
        if (mIndex >= mArray.length) {
            // Create a new array with double the capacity of the old array.
            T[] newArray = (T[])(new Object[2*mArray.length]);
            // Copy the old array's contents into the new array.
            for (int i = 0; i < mArray.length; i++) {
                newArray[i] = mArray[i];
            }
            // Now we need to
            mArray = newArray;
        }

        // Add to the top of the stack.
        mArray[mIndex] = o;
        mIndex++;
    }

    //removes from the stack
    @Override
    public T pop() {
        if (mIndex <= 0) {
            return null;
        }
        T ret = peek();
        mIndex--;
        mArray[mIndex] = null;
        return ret;
    }

    //looks at the top variable
    @Override
    public T peek() {
        if (mIndex > 0) {
            return mArray[mIndex - 1];
        }

        return null;
    }

    //returns the size of the stack
    @Override
    public int size() {
        return mIndex;
    }

    //returns if the stack is empty
    @Override
    public boolean isEmpty() {
        if(mIndex == 0){
            return true;
        }
        return false;
    }
}
