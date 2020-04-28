package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution51_1 {

    boolean[] col; // 第几列被占用
    boolean[] dial, diar; // 左斜右斜

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> res = new ArrayList<>();
        if(n == 0) return res;
        col = new boolean[n];
        dial = new boolean[2*n-1]; // row + col 从0到2n-1
        diar = new boolean[2*n-1]; // row - col + n - 1 从 0 到 2n-1
        dfs(n, 0, new ArrayList<int[]>(), res);
        return res;
    }

    // 该放第几行的皇后了
    // queens 保存皇后坐标
    private void dfs(int n, int row, ArrayList<int[]> queens, List<List<String>> res) {

        if(row == n) {
            ArrayList<String> subRes = new ArrayList<>();
            for(int[] q: queens) {
                int col = q[1];
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < col; i++) sb.append('.');
                sb.append('Q');
                for(int i = col + 1; i < n; i++) sb.append('.');
                subRes.add(sb.toString());
            }
            res.add(subRes);
            return;
        }
        for(int i = 0; i < n; i++) { // 第几列
            if(!col[i] && !dial[i + row] && !diar[row - i + n - 1]) {
                queens.add(new int[]{row, i});
                col[i] = true; dial[i + row] = true; diar[row - i + n - 1] = true;
                dfs(n, row + 1, queens, res);
                queens.remove(queens.size() - 1);
                col[i] = false; dial[i + row] = false; diar[row - i + n - 1] = false;
            }
        }
    }


}
