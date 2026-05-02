import java.util.LinkedList;
import java.util.Queue;

class Node {
    int row;
    int col;
    int dis;

    Node(int row, int col, int dis) {
        this.row = row;
        this.col = col;
        this.dis = dis;
    }
}

public class Solution1765 {
    public int[][] highestPeak(int[][] isWater) {
        int rowLength = isWater.length;
        int colLength = isWater[0].length;
        int[][] result = new int[rowLength][colLength];
        Queue<Node> queue = new LinkedList<>();
        boolean [][] visited = new boolean[rowLength][colLength];
        for(int i=0; i<rowLength;i++) {
            for(int j=0;j<colLength;j++) {
                if(isWater[i][j]== 1) {
                    queue.add(new Node(i,j,0));
                    visited[i][j] = true;
                }
            }
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                Node curr = queue.remove();
                int row = curr.row;
                int col = curr.col;
                int dis = curr.dis;
                result[row][col] = dis;

                if(isTravesePossible(rowLength, colLength, visited, row+1, col)) {
                    queue.add(new Node(row+1,col,dis+1));
                    visited[row+1][col] = true;
                }
                if(isTravesePossible(rowLength, colLength, visited, row-1, col)) {
                    queue.add(new Node(row-1,col,dis+1));
                    visited[row-1][col] = true;
                }
                if(isTravesePossible(rowLength, colLength, visited, row, col+1)) {
                    queue.add(new Node(row,col+1,dis+1));
                    visited[row][col+1] = true;
                }
                if(isTravesePossible(rowLength, colLength, visited, row, col-1)) {
                    queue.add(new Node(row,col-1,dis+1));
                    visited[row][col-1] = true;
                }
            }
        }

        return result;
    }

    public Boolean isTravesePossible(int rLength, int cLength, boolean [][] visited, int row, int col) {

        return row >= 0 && row < rLength && col >= 0 && col < cLength && !visited[row][col];
    }
}
