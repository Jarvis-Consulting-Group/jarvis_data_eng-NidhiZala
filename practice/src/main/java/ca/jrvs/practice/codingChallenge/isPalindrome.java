package ca.jrvs.practice.codingChallenge;

public class isPalindrome {
    public static boolean checkPalindrome(int x){
        if (x < 0 || (x % 10 == 0 && x !=0) ){
            return false;
        }
        int reversed = 0;
        int org = x;
        while(x>0){
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }
        return org == reversed;
    }

    public static void main (String[] args){
        int a = 121;
        int b = 1122;
        System.out.println(checkPalindrome(a));
        System.out.println(checkPalindrome(b));
    }
}
