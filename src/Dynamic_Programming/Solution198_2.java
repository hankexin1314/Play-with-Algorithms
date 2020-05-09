package Dynamic_Programming;

// 递归

public class Solution198_2 {

    public int rob(int[] nums) {

        return helper(nums, 0);
    }

    // [index, L-1]所能偷取的最大价值
    private int helper(int[] nums, int index) {

        if(index >= nums.length) return 0;
        return Math.max(nums[index] + helper(nums, index + 2), // 选index位置
                index + 1 >= nums.length ? 0 : nums[index + 1] + helper(nums, index + 3)); // 不选index位置
    }
}
