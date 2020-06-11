package 动态规划.p416_分割等和子集;

import java.util.Arrays;

public class Solution {

    public boolean canPartition(int[] nums) {

        int sum = 0;
        for(int num: nums) sum += num;
        if(sum % 2 != 0) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        Arrays.fill(dp, false);
        if(nums[0] <= target) dp[nums[0]] = true;
        dp[0] = true;
        for(int i = 1; i < nums.length; i++) {
            for(int j = target; j >= 0; j--) {
                dp[j] = dp[j] || (j - nums[i] >= 0 && dp[j - nums[i]]);
                if(dp[target]) return true;
            }
        }

        return false;
    }
}
