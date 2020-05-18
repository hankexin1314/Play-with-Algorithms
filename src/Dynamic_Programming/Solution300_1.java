package Dynamic_Programming;

// 动态规划
public class Solution300_1 {

    public int lengthOfLIS(int[] nums) {

        if(nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int res = 0;
        for(int i = nums.length - 1; i >= 0; i--) {
            int max = 1;
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[j] > nums[i]) max = Math.max(max, 1 + dp[j]);
            }
            dp[i] = max;
            res = Math.max(res, max);
        }

        return res;
    }
}
