package jianzhiOffer.dp;

public class Solution47_1 {

    public int maxValue(int[][] grid) {

        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for(int i = 1; i < n; i++) {
            dp[i] = grid[0][i] + dp[i - 1];
        }

        for(int i = 1; i < m; i++) {
            dp[0] = grid[i][0] + dp[0];
            for(int j = 1; j < n; j++) {
                dp[j] = grid[i][j] + Math.max(dp[j - 1], dp[j]);
            }
        }

        return dp[n - 1];
    }
}
