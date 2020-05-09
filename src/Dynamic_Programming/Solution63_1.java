package Dynamic_Programming;


// 递归
public class Solution63_1 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
        return helper(obstacleGrid, 0, 0);
    }

    private int helper(int[][] grid, int row, int col) {

        if(row == grid.length || col == grid[0].length || grid[row][col] == 1) return 0;
        if(row == grid.length - 1 && col == grid[0].length - 1) return 1;
        return helper(grid, row + 1, col) + helper(grid, row, col + 1);
    }


}
