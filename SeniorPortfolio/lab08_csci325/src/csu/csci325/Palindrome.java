package csu.csci325;

/**
 * Created by Kaitlyn on 11/17/2016.
 */
//creates the palindrome class
public class Palindrome<T> extends ReverseCharacter<T> {
    public Palindrome(){

    }

    //creates a new list to store the reversed word
    ReverseCharacter<T> reversed = new ReverseCharacter<T>();

    //checks if the reverse of the word is in fact a palindrome
    public boolean isPalindrome(String check){
        //if no string there returns null
        if(check == null){
            return false;
        }

        //if the reverse is the same as the string it is a palindrome
        if(reversed.Reverse(check).equals(check)){
            return true;
        }

        return false;
    }
}
