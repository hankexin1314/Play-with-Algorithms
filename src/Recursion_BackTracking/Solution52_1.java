package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.List;

public class Solution52_1 {

    boolean[] col; // 第几列被占用
    boolean[] dial, diar; // 左斜右斜

    public int totalNQueens(int n) {

        if(n == 0) return 0;
        col = new boolean[n];
        dial = new boolean[2*n-1]; // row + col 从0到2n-1
        diar = new boolean[2*n-1]; // row - col + n - 1 从 0 到 2n-1
        return dfs(n, 0, 0);
    }

    // 该放第几行的皇后了
    // queens 保存皇后坐标
    private int dfs(int n, int row, int res) {
        if(row == n) res++;
        else {
            for(int i = 0; i < n; i++) { // 第几列
                if(!col[i] && !dial[i + row] && !diar[row - i + n - 1]) {
                    col[i] = true; dial[i + row] = true; diar[row - i + n - 1] = true;
                    res = dfs(n, row + 1, res);
                    col[i] = false; dial[i + row] = false; diar[row - i + n - 1] = false;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println((new Solution52_1()).totalNQueens(4));
    }

    public static void func(int a) {

    }
}
