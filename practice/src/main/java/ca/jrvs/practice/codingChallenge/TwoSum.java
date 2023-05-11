package ca.jrvs.practice.codingChallenge;

import java.util.*;

public class TwoSum {
    public int[] twoSum (int[] nums, int target){
        Map<Integer, Integer> subtracter = new HashMap<>();
        for (int i=0; i< nums.length; i++){
            int subtractorVal = target - nums[i];
            if (subtracter.containsKey(subtractorVal)){
                 return new int[] {subtracter.get(subtractorVal), i};
            }
            subtracter.put(nums[i],i);
        }
        return new int[0];
    }
//   Basic code without using hashmap
//    public int[] twoSumSimple(int[] nums, int target) {
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] + nums[j] == target) {
//                    return new int[] { i, j };
//                }
//            }
//        }
//        return new int[0];
//    }

    public static void main (String[] args){
        int[] nums = {1,2,3,4};
        int target = 4;
        TwoSum twoSum = new TwoSum();
        int[] result = twoSum.twoSum(nums, target);
        System.out.println("Sum of Indice- " + result[0] +" Indice- "+ result[1]+ " is our target"+ target);
    }
}
