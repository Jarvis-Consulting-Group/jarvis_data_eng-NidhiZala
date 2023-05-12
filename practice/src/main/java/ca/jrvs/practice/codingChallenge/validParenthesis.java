package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

public class validParenthesis {
    public static boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()){
            if(c == '(' || c == '{' || c == '[' ){
                stack.push(c);
            } else if (stack.isEmpty() || !isMatching(stack.pop(), c)) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static boolean isMatching(char open, char close){
        return  ((open =='(' && close ==')')
            || (open =='{' && close =='}')
            || (open =='[' && close ==']'));
    }

    public static void main (String []  args){
        String s1 ="{]";
        System.out.println(isValid(s1));
    }
}
