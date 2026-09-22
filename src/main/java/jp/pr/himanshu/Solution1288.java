package jp.pr.himanshu;

import java.util.Arrays;

public class Solution1288 {

    public int removeCoveredIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return b[1] - a[1];
        });
        int res = 1;
        int min = intervals[0][0];
        int max = intervals[0][1];
        //System.out.println(max);
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][1] < max) {
                continue;
            } else {
                min = intervals[i][0];
                max = intervals[i][1];
                res++;
            }
        }
        return res;
    }
}
