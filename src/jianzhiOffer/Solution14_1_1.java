package jianzhiOffer;


// 动态规划


public class Solution14_1_1 {

    public int cuttingRope(int n) {

        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j < i; j++) { // 切一段多长的下来
                dp[i] = Math.max(dp[i], j * Math.max(i - j, dp[i - j]));
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println((new Solution14_1_1()).cuttingRope(4));
    }
}
