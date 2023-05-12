package ca.jrvs.practice.codingChallenge;

public class missingNumber {
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = (n * (n + 1)) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        int[] nums = {0,1, 2, 4, 5, 6, 7};
        System.out.println(missingNumber(nums));
    }

}
