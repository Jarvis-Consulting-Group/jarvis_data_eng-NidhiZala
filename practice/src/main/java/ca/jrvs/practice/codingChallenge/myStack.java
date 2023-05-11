package ca.jrvs.practice.codingChallenge;

import java.util.*;

public class myStack {

    // initiate 2 queues and a top element for the stack
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;
    private int topElement;
    //assign datastructures to the queues
    public myStack(){
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    public void push(int x){
        queue1.offer(x);
        topElement = x;
    }

    public int pop(){
        while (queue1.size()>1){
            topElement = queue1.poll();
            queue2.offer(topElement);
        }
        int removedElement = queue1.poll();
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
        return removedElement;


    }

    public int top(){
        return topElement;
    }
    public boolean empty(){
        return queue1.isEmpty();
    }

    public static void main(String [] args){
        myStack stack = new myStack();
        stack.push(1);
        stack.push(2);
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.empty());
    }


}
