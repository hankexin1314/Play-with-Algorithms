package Dynamic_Programming;

// 动态规划 自底向上 从起点开始
public class Solution62_3 {


    public int uniquePaths(int m, int n) {

        if(m == 0 || n == 0) return 0;
        int[][] memo = new int[m][n];
        memo[0][0] = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                memo[i][j] = memo[i][j] +
                        (i - 1 >= 0 ? memo[i - 1][j] : 0) +
                        (j - 1 >= 0 ? memo[i][j - 1] : 0);
            }
        }

        return memo[m - 1][n - 1];
    }


}
