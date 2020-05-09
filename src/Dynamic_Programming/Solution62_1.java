package Dynamic_Programming;

// 递归 超出时间限制
public class Solution62_1 {

    public int uniquePaths(int m, int n) {

        if(m == 0 || n == 0) return 0;
        return uniquePaths(m, n, 0, 0);
    }

    private int uniquePaths(int m, int n, int row, int col) {

        if(row == m - 1 && col == n - 1) return 1;
        if(row == m || col == n) return 0;
        return uniquePaths(m, n, row + 1, col) + uniquePaths(m, n, row, col + 1);
    }
}
