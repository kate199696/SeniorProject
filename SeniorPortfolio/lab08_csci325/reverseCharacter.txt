package csu.csci325;

/**
 * Created by Kaitlyn on 11/17/2016.
 */
public class ReverseCharacter<T>  {
    private String reverse;

    public ReverseCharacter(){

    }

    public String Reverse( String word){
        reverse = "";
        ArrayStack<Character> aStack = new ArrayStack<>();

        if(!(word == "")){
            if(!(word == null)){
                for(int i = 0; i < word.length(); i++){
                    aStack.push(word.charAt(i));
                }

                for(int j = 0; j < word.length(); j++){
                    if(!(aStack.peek() == null)){
                        reverse += aStack.pop();
                    }
                }
            }
            else{
                return null;
            }
        }
        else{
            return "";
        }

        return reverse;
    }

}
