package 动态规划.p494_目标和;

public class Solution {

    public int findTargetSumWays(int[] nums, int S) {

        int sum = 0;
        for(int num: nums) sum += num;
        S = Math.abs(S);
        if(S > sum) return 0;
        int[][] dp = new int[nums.length][sum + 1];
        if(nums[0] == 0) dp[0][nums[0]] = 2;
        else dp[0][nums[0]] = 1;
        for(int i = 1; i < nums.length; i++) { // 第几个数字
            int num = nums[i];
            for(int j = 0; j < sum + 1; j++) { // 目标为几
                int l = dp[i - 1][Math.abs(j - num)];
                int r = (j + num) <= sum ? dp[i-1][j + num]: 0;
                dp[i][j] = l + r;
            }
        }

        return dp[nums.length - 1][S];
    }
}
