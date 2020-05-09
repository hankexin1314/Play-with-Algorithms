package Dynamic_Programming;

// 递归 自顶向下
public class Solution213_1 {

    public int rob(int[] nums) {

        return helper(nums, 0, nums.length - 1);
    }

    // [l, r]范围内所能抢劫到的最大值
    private int helper(int[] nums, int l, int r) {

        if(l > r) return 0;
        return Math.max(Math.max(
                nums[l] + (l == 0 ? helper(nums, l + 2, r - 1) : helper(nums, l + 2, r)), // 选l位置上的值
                l + 1 > r ? 0 : nums[l + 1] + helper(nums, l + 3, r)),// 选l + 1 位置上的值
                l + 2 > r ? 0 : nums[l + 2] + helper(nums, l + 4, r)); // 选l + 2 位置上的值
    }
}
