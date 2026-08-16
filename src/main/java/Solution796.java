import kotlin.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution796 {

    public boolean rotateString(String s, String goal) {


        if(s.length()!=goal.length()) return false;
        for(int i = 0; i<s.length(); i++) {
            if(s.charAt(i) == goal.charAt(0)) {
                if(isPossible(i, s, goal)){
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isPossible(int start, String s, String goal) {
        int j = 0;
        int i = start;
        for(; i<s.length();i++) {
            if(s.charAt(i) == goal.charAt(j)) {
                j++;
            } else {
                return false;
            }
        }
        i = 0;

        while(j< goal.length()) {
            if(s.charAt(i) == goal.charAt(j)) {
                j++;
                i++;
            } else {
                return false;
            }
        }
        return true;
    }
}
