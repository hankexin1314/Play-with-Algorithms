package Dynamic_Programming;

// 递归
public class Solution416_1 {

    public boolean canPartition(int[] nums) {

        int sum = 0;
        for(int num: nums) sum += num;
        if(sum % 2 != 0) return false;
        return helper(nums, 0, 0, sum / 2);
    }

    // index 为该判断第几个数字了 num为现在的和为多少
    private boolean helper(int[] nums, int index, int num, int target) {

        if(num == target) return true;
        else if(num > target || index == nums.length) return false;
        else return helper(nums, index + 1, num + nums[index], target) ||
                    helper(nums, index + 1, num, target);
    }
}
