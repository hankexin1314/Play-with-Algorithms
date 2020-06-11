package 动态规划;

import java.util.Arrays;

public class p300_最长上升子序列 {

    public int lengthOfLIS(int[] nums) {

        if(nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for(int i = 0; i < nums.length; i++) {
            int max = dp[i];
            for(int j = i - 1; j >= 0; j--) {
                if(nums[j] < nums[i])
                    max = Math.max(max, dp[j] + 1);
            }
            dp[i] = max;
        }

        int res = -1;
        for(int num: dp) res = Math.max(res, num);
        return res;
    }
}
