package 动态规划.p343_整数拆分;

public class Solution {

    public int integerBreak(int n) {

        if(n < 2) return 0;
        int[] dp = new int[n + 1];
        for(int i = 2; i < n + 1; i++) {
            int max = -1;
            for(int j = 1; j <= i / 2; j ++) { // 拆分一个j出来
                max = Math.max(max, j * Math.max(i - j, dp[i - j]));
            }
            dp[i] = max;
        }

        return dp[n];
    }
}
