package jianzhiOffer.dp;

public class Solution49_1 {

    public int nthUglyNumber(int n) {

        int[] dp = new int[1691];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1; // 三个指针
        for(int i = 2; i <= n; i++) {
            int a2 = 2 * dp[p2], a3 = 3 * dp[p3], a5 = 5 * dp[p5];
            int min = Math.min(a2, Math.min(a3, a5));
            dp[i] = min;
            if(min == a2) p2++;
            if(min == a3) p3++;
            if(min == a5) p5++;
        }

        return dp[n];
    }
}
