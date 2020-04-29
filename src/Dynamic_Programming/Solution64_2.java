package Dynamic_Programming;


// 递归 记忆化搜索
public class Solution64_2 {

    int m, n;
    Integer[][] memo;
    public int minPathSum(int[][] grid) {

        if(grid.length == 0 || grid[0].length == 0) return 0;
        m = grid.length; n = grid[0].length;
        memo = new Integer[m][n];
        return minPathSum(grid, 0, 0);
    }

    // 从 row col起始到右下角的最短距离
    private int minPathSum(int[][] grid, int row, int col) {

        if(row == m || col == n) return Integer.MAX_VALUE;
        if(row == m - 1 && col == n - 1) return grid[row][col];
        if(memo[row][col] == null)
            memo[row][col] = grid[row][col] + Math.min(minPathSum(grid, row + 1, col), minPathSum(grid, row, col + 1));
        return memo[row][col];
    }
}
