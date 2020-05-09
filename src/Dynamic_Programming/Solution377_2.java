package Dynamic_Programming;

import java.util.Arrays;

// 记忆化搜索
public class Solution377_2 {

    int[] memo;
    public int combinationSum4(int[] nums, int target) {

        memo = new int[target + 1];
        Arrays.fill(memo, -1);
        return helper(nums, target);
    }

    private int helper(int[] nums, int target) {

        if(target < 0) return 0;
        if(target == 0) return 1;
        if(memo[target] == -1) {
            int res = 0;
            for(int num: nums)
                res += helper(nums, target - num);
            memo[target] = res;
        }
        return memo[target];
    }
}
