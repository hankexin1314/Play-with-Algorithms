package Dynamic_Programming;

public class Solution494_1 {
    public int findTargetSumWays(int[] nums, int S) {

        int[] dp = new int[2001];
        dp[nums[0] + 1000] += 1;
        dp[-nums[0] + 1000] += 1;

        for(int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for(int j = 0; j < 2001; j++) { // 目标和
                if(dp[j] > 0) {
                    next[j - nums[i]] += dp[j];
                    next[j + nums[i]] += dp[j];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0: dp[S + 1000];
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 1, 1, 1};
        int ans = (new Solution494_1()).findTargetSumWays(a, 3);
    }
}
