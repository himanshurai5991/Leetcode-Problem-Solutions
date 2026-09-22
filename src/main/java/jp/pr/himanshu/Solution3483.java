package jp.pr.himanshu;

import java.util.*;

public class Solution3483 {

    Set<Integer> even = new HashSet<>();
    public int totalNumbers(int[] digits) {
        runRecurrsion(digits, new LinkedHashSet<>());
        //System.out.println(even);
        return even.size();
    }
    public void runRecurrsion(int[] arr, Set<Integer> set) {
        //System.out.println(set);
        if(set.size() == 3){
            StringBuilder sb = new StringBuilder();
            for (Integer i : set) {
                sb.append(arr[i]);
            }

            int curr = Integer.parseInt(sb.toString());

            if( curr%2 == 0   && curr /100 >0) {
                even.add(curr);
            }

            return;
        }
        for(int i=0;i<arr.length;i++) {
            if(!set.contains(i)){
                set.add(i);
                runRecurrsion(arr, set) ;
                set.remove(i);
            }
        }
        return;
    }

}
