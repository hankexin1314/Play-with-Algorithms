package Dynamic_Programming;


// 动态规划
public class Solution63_3 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] memo = new int[m][n];
        if(obstacleGrid[0][0] == 1) return 0;
        memo[0][0] = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(obstacleGrid[i][j] == 1) continue;
                memo[i][j] = memo[i][j] +
                        (i - 1 >= 0 ? memo[i - 1][j] : 0) +
                        (j - 1 >= 0 ? memo[i][j - 1] : 0);
            }
        }

        return memo[m - 1][n - 1];
    }


}
