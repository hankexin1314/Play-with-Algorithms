package Dynamic_Programming;


// 动态规划
public class Solution64_3 {

    public int minPathSum(int[][] grid) {

        if(grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n + 1];
        dp[n] = 0;
        // 初始化
        for(int i = n - 1; i >= 0; i--) dp[i] = dp[i + 1] + grid[m-1][i];
        // 到i j的最短路径
        for(int i = m - 2; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(j == n - 1) dp[j] += grid[i][j];
                else dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
            }
        }

        return dp[0];
    }

}
