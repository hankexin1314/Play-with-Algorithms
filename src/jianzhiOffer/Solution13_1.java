package jianzhiOffer;

import java.util.Arrays;
// dfs
public class Solution13_1 {

    boolean[][] used;
    int[] memo = new int[100]; // memo[n] 表示n的位数之和

    public int movingCount(int m, int n, int k) {

        for(int i = 0; i < 100; i++) memo[i] = bSum(i);
        used = new boolean[m][n];
        return dfs(0, 0, k);
    }

    private int dfs(int row, int col, int k) {
        if(row >= used.length || col >= used[0].length || memo[row] + memo[col] > k || used[row][col])
            return 0;
        used[row][col] = true;
        return 1 + dfs(row + 1, col, k) + dfs(row, col + 1, k);
    }

    // 数位之和
    private int bSum(int num) {
        int res = 0;
        while (num != 0) {
            res += num % 10;
            num = num / 10;
        }
        return res;
    }
}
