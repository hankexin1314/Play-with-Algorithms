package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// dfs
public class Solution417_2 {

    private int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int m, n;

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {

        List<List<Integer>> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        m = matrix.length;
        n = matrix[0].length;
        boolean[][] pUsed = new boolean[m][n];
        boolean[][] aUsed = new boolean[m][n];
        // first col & last col
        for(int i = 0; i < m; i++) {
            dfs(matrix, i, 0, pUsed);
            dfs(matrix, i, n - 1, aUsed);
        }
        // first row & last row
        for(int i = 0; i < n; i++) {
            dfs(matrix, 0, i, pUsed);
            dfs(matrix, m - 1, i, aUsed);
        }

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                if(pUsed[i][j] && aUsed[i][j]) {
                    ArrayList<Integer> subRes = new ArrayList<>();
                    subRes.add(i);
                    subRes.add(j);
                    res.add(subRes);
                }
        }

        return res;
    }

    private void dfs(int[][] matrix, int row, int col, boolean[][] used) {

        used[row][col] = true;
        for(int i = 0; i < 4; i++) {
            int newRow = row + d[i][0];
            int newCol = col + d[i][1];
            if(isValid(newRow, newCol) && !used[newRow][newCol] && matrix[newRow][newCol] >= matrix[row][col]) {
                dfs(matrix, newRow, newCol, used);
            }
        }
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }
}
