package jianzhiOffer.dp;

public class Solution60_1 {

    public double[] twoSum(int n) {

        if(n < 1) return new double[0];
        int[][] dp = new int[12][67];
        for(int i = 1; i <= 6; i++)
            dp[1][i] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = i; j <= 6 * i; j++) {
                for(int k = 1; k <= 6; k++) {
                    if(k >= j) break;
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        double[] res = new double[5 * n + 1];
        double m = Math.pow(6, n);
        for(int i = 0; i < res.length; i++)
            res[i] = dp[n][n + i] / m;

        return res;
    }
}
