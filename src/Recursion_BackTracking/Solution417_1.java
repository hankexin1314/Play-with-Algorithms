package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution417_1 {

    private int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int m, n;

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {

        List<List<Integer>> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        m = matrix.length;
        n = matrix[0].length;
        boolean[][] pUsed = new boolean[m][n];
        boolean[][] aUsed = new boolean[m][n];
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        // first col & last col
        for(int i = 0; i < m; i++) {
            pUsed[i][0] = true;
            aUsed[i][n - 1] = true;
            pQueue.offer(new int[]{0, i});
            aQueue.offer(new int[]{n - 1, i});
        }
        // first row & last row
        for(int i = 0; i < n; i++) {
            pUsed[0][i] = true;
            aUsed[m - 1][i] = true;
            pQueue.offer(new int[]{i, 0});
            aQueue.offer(new int[]{i, m - 1});
        }

        bfs(matrix, pQueue, pUsed);
        bfs(matrix, aQueue, aUsed);

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

    private void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] used) {

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for(int i = 0; i < 4; i++) {
                int x = cur[0] + d[i][0];
                int y = cur[1] + d[i][1];
                if(isValid(x, y) && !used[y][x] && matrix[y][x] >= matrix[cur[1]][cur[0]]) {
                    used[y][x] = true;
                    queue.offer(new int[]{x, y});
                }
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
