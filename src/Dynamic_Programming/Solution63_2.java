package Dynamic_Programming;


// 记忆化搜索
public class Solution63_2 {

    int[][] memo;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        memo = new int[m][n];

        return helper(obstacleGrid, 0, 0);
    }

    // 从终点到 指定位置有多少条路径
    private int helper(int[][] grid, int row, int col) {

        if(row == grid.length || col == grid[0].length || grid[row][col] == 1) return 0;
        if(row == grid.length - 1 && col == grid[0].length - 1) return 1;
        if(memo[row][col] == 0)
            memo[row][col] = helper(grid, row + 1, col) + helper(grid, row, col + 1);
        return memo[row][col];
    }


}
