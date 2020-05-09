package Dynamic_Programming;

// 记忆化搜索

import java.util.Arrays;

public class Solution198_3 {

    int[] memo; // index 表示抢劫 [i, n-1]的最大收益

    public int rob(int[] nums) {

        int L = nums.length;
        if(L == 0) return 0;
        memo = new int[L];
        Arrays.fill(memo, -1);

        return helper(nums, 0);
    }

    // 计算 [index, n-1]的最大收益
    private int helper(int[] nums, int index) {

        if(index >= nums.length) return 0;
        if(memo[index] != -1) return memo[index];

        memo[index] = Math.max(nums[index] + helper(nums, index + 2),
                index + 1 >= nums.length ? 0 : nums[index + 1] + helper(nums, index + 3));
        return memo[index];
    }


}
