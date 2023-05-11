package ca.jrvs.practice.codingChallenge;
import  java.util.*;

public class CompareTwoMaps {
    public static void main(String [] args){
        Map<Integer, String> map1 = Map.of(1,"abc");
        Map<Integer, String> map2 = Map.of(1, "axy");
        boolean mapEqual = compareMaps(map1, map2);
        System.out.println(mapEqual);
    }

    public static <k,v> boolean compareMaps(Map<k,v> map1,Map<k,v> map2){
        if(map1 ==map2){
            return true;
        }
        if (map1 ==null || map2 == null || map1.size() != map2.size()){
            return false;
        }
        return map1.equals(map2);
    }
}
