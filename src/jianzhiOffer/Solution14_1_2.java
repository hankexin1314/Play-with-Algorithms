package jianzhiOffer;


// 记忆化搜索


public class Solution14_1_2 {

    int[] memo;
    public int cuttingRope(int n) {

        memo = new int[n + 1];
        return helper(n);
    }

    // 将一个整数分割 获得最大积
    private int helper(int n) {

        if(n == 1) return 1;
        if(memo[n] != 0) return memo[n];
        int res = -1;
        for(int i = 1; i < n; i++)
            res = Math.max(res, i * Math.max(n - i, helper(n - i)));

        memo[n] = res;
        return memo[n];
    }

    public static void main(String[] args) {
        System.out.println((new Solution14_1_2()).cuttingRope(4));
    }
}
