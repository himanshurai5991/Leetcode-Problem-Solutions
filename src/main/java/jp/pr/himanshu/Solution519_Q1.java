package jp.pr.himanshu;

public class Solution519_Q1 {

    public int[][] cyclicShift(int n, int[][] grid, int[] rowShift, int[] colShift) {

        for(int i = 0;i< rowShift.length;i++) {
            for(int j = 0;j< rowShift[i]%n;j++) {
                int k = n-2;
                int curr = grid[i][k+1];
                grid[i][k+1] = grid[i][0];
                while(k>=0) {
                    int temp = grid[i][k];
                    grid[i][k] = curr;
                    curr = temp;
                    k--;
                }
            }
        }

//        for (int i = 0;i< n;i++){
//            for(int j = 0;j< n;j++) {
//                System.out.print(grid[i][j] + " ");
//            }
//            System.out.println();
//        }

        for(int i = 0;i< colShift.length;i++) {
            for(int j = 0;j< colShift[i]%n;j++) {
                int k = n-2;
                int curr = grid[k+1][i];
                grid[k+1][i] = grid[0][i];
                while(k>=0) {
                    int temp = grid[k][i];
                    grid[k][i] = curr;
                    curr = temp;
                    k--;
                }
            }
        }


        return grid;

    }
}
