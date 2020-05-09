package Dynamic_Programming;

// 动态规划
public class Solution377_1 {

    public int combinationSum4(int[] nums, int target) {

        int[] dp = new int[target + 1];
        for(int i = 1; i <= target; i++) {
            int res = 0;
            for(int num: nums) {
                if(i == num) res++;
                if(i > num) res += dp[i-num];
            }
            dp[i] = res;
        }

        return dp[target];
    }
}
