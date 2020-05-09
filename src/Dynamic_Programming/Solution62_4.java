package Dynamic_Programming;

import java.util.Arrays;

// 动态规划 自底向上 从起点开始
// 优化空间复杂度为n
public class Solution62_4 {


    public int uniquePaths(int m, int n) {

        if(m == 0 || n == 0) return 0;
        int[] memo = new int[n];
        Arrays.fill(memo, 1);
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                memo[j] += memo[j - 1];
            }
        }

        return memo[n - 1];
    }


}
