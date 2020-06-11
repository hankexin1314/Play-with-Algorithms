package 动态规划.p413_等差数列划分;

public class Solution {

    public int numberOfArithmeticSlices(int[] A) {

        if(A == null || A.length == 0) return 0;
        int[] dp = new int[A.length];
        for(int i = 2; i < A.length; i++) {
            if(A[i - 1] - A[i] == A[i - 2] - A[i - 1])
                dp[i] = dp[i - 1] + 1;
        }

        int res = 0;
        for(int num: dp)
            res += num;

        return res;
    }
}
