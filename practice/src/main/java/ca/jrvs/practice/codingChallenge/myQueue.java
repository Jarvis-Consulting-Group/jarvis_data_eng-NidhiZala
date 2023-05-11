package ca.jrvs.practice.codingChallenge;

import java.util.*;

public class myQueue {

    private Stack<Integer> inStack; //for push
    private Stack<Integer> outStack; //for pop
    public myQueue()
    {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x){
       inStack.push(x);
    }

    public int pop(){
        if(outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }
    public int peek(){
        if(outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.peek();
    }

    public boolean empty(){
        return inStack.isEmpty();
    }

    public static void  main(String [] args){
        myQueue queue = new myQueue();
        queue.push(1);
        queue.push(2);
        queue.push(10);
        queue.push(15);
        System.out.println(queue.pop());
        System.out.println(queue.peek());
        System.out.println(queue.empty());
    }
}
