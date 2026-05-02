import java.util.ArrayList;
import java.util.Collections;

public class Solution2033 {

    public int minOperations(int[][] grid, int x) {
        int res;
        int row = grid.length;
        int col = grid[0].length;
        int sum = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                sum += grid[i][j];
                list.add(grid[i][j]);
            }
        }
        Collections.sort(list);

        if(list.size() == 1) return 0;
        if(list.size() == 2) return Math.abs(list.get(0) - list.get(1)) % x == 0 ? Math.abs(list.get(0) - list.get(1)) / x : -1;
        int nearest1 = list.get(list.size() / 2);
        int nearest2 = list.get(list.size() / 2 + 1);

//        System.out.println(nearest1);
//        System.out.println(nearest2);

        int result1 = minOperations2(list, x, nearest1);
        int result2 = minOperations2(list, x, nearest2);
        if (result1 == Integer.MAX_VALUE && result2 == Integer.MAX_VALUE) {
            return -1;
        } else  {
            return Math.min(result1, result2);
        }
    }

    public int minOperations2(ArrayList<Integer> al, int x, int y) {
            int res = 0;
            for (int i = 0; i < al.size(); i++) {
                int cal = Math.abs(al.get(i) - y) % x;
                if(cal != 0){
                    return Integer.MAX_VALUE;
                } else {
                    res+= Math.abs(al.get(i) - y) / x;
                }
            }
            return res;
    }
}
