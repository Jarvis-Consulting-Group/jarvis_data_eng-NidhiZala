package ca.jrvs.practice.codingChallenge;

public class stringRotate {
    public static boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false; // If the lengths are different, it's not possible
        }

        String concatenated = s + s;
        return concatenated.contains(goal);
    }
    public static void main(String [] args){
        String s ="abcde";
        String goal = "bcdea";
        System.out.println(rotateString(s, goal));
    }
}
