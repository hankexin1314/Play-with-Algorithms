package Dynamic_Programming;

// 记忆化搜索 自顶向下
public class Solution62_2 {

    int[][] memo;
    public int uniquePaths(int m, int n) {

        if(m == 0 || n == 0) return 0;
        memo = new int[m][n];
        return uniquePaths(m, n, 0, 0);
    }

    // 从右下角到指定位置的路径数
    private int uniquePaths(int m, int n, int row, int col) {

        if(row == m - 1 && col == n - 1) return 1;
        if(row == m || col == n) return 0;
        if(memo[row][col] == 0)
            memo[row][col] = uniquePaths(m, n, row + 1, col) + uniquePaths(m, n, row, col + 1);

        return memo[row][col];
    }
}
