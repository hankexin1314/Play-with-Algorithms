package 双指针.p121_买卖股票的最佳时机;

public class Solution {

    public int maxProfit(int[] prices) {

        if(prices == null || prices.length < 2) return 0;
        int res = 0;
        int a = prices[0], b = 0; // 前i天购买的最低成本 前i天售卖的最大值
        for(int i = 1; i < prices.length; i++) {
            b = Math.max(b, prices[i] - a);
            a = Math.min(a, prices[i]);
        }

        return b;
    }
}
