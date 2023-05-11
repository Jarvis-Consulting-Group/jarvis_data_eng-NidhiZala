package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

public class climbingStairsProblem {
    public static Map<Integer, Integer> storage = new HashMap<>();
    public static int climbstairs (int n){
        if(n<=2){
            return n;
        }
        if (storage.containsKey(n)){
            return storage.get(n);
        }
        int ways = climbstairs(n-1)+climbstairs(n-2);
        storage.put(n, ways);
        System.out.println("For number"+ n +" Stair updated" +ways);
        return ways;
    }

    public static void main(String [] args){
        int steps = 10;
        int ways = climbstairs(steps);
        System.out.println("For input n "+steps+"ways:"+" "+ways);
    }
}

