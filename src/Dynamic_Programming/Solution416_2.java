package Dynamic_Programming;

// 记忆化搜索
public class Solution416_2 {

    int[][] memo;
    public boolean canPartition(int[] nums) {

        int sum = 0;
        for(int num: nums) sum += num;
        if(sum % 2 != 0) return false;
        memo = new int[nums.length][sum/2 + 1]; // 0 表示未计算 1表示true 2表示false

        return helper(nums, nums.length - 1, sum/2);
    }

    // 是否可以用[0, index] 的元素填满target
    private boolean helper(int[] nums, int index, int target) {

        if(target < 0 || index < 0) return false;
        if(target == 0) return true;
        if(memo[index][target] != 0)
            return memo[index][target] == 1;
        memo[index][target] = (helper(nums, index-1, target) ||
                helper(nums, index-1, target-nums[index])) ? 1 : 2;
        return memo[index][target] == 1;
    }

}
