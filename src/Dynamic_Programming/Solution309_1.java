package Dynamic_Programming;

public class Solution309_1 {

    public int maxProfit(int[] prices) {

        int L = prices.length;
        if(L == 0) return 0;
        int[][] memo = new int[L][2]; // 到 第i天所能获得的是否持有
        memo[0][0] = 0; // 第0天不持有
        memo[0][1] = -prices[0]; // 第0天持有
        for(int i = 1; i < L; i++) {
            // 第i天 选择继续不持有  第i天选择sell
            memo[i][0] = Math.max(memo[i-1][0], memo[i-1][1] + prices[i]);
            // 第i天选择继续持有， 第i天选择 buy
            memo[i][1] = Math.max(memo[i-1][1], (i-2 >= 0 ? memo[i-2][0] : 0) - prices[i]);
        }

        return memo[L-1][0];
    }


}
