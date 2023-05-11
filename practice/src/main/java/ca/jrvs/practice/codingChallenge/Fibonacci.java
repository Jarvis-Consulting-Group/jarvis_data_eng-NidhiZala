package ca.jrvs.practice.codingChallenge;

import java.util.*;

public class Fibonacci {
//    Recursion
//    public static int fib(int n){
//        if (n<=1)
//        {
//            return n; //base case
//        }
//
//        return fib(n-1)+fib(n-2);
//    }
//    public static void main (String[] args){
//        int n = 12;
//        int answer = fib(n);
//        System.out.println(answer);
//    }

    // Dynamic programming
    public static Map<Integer, Integer> fibo = new HashMap<>();
    public static int storage (int n){
        if(n<=1){
            return n;
        }
        if (fibo.containsKey(n)){
            return fibo.get(n);
        }
        int fibNum = storage(n-1)+storage(n-2);
        fibo.put(n, fibNum);
        System.out.println("For number"+ n +" Fibp num updated" +fibNum);
        return fibNum;
    }

    public static void main(String [] args){
        int n = 10;
        int output = storage(n);
        System.out.println("Fibonacci Number is "+ output);
    }
}
