package Dynamic_Programming;

// 动态规划
public class Solution416_3 {

    public boolean canPartition(int[] nums) {

        int sum = 0;
        for(int num: nums) sum += num;
        if(sum % 2 != 0) return false;
        int c = sum/2;
        boolean[] memo = new boolean[c + 1];

        // memo初始化
        for(int i = 0; i <= c; i++) memo[i] = (i == nums[0]);
        for(int i = 1; i < nums.length; i++) {
            for (int j = c; j >= 0; j--) {
                memo[j] = memo[j] || (j - nums[i] >= 0 && memo[j - nums[i]]);
                if (memo[c]) return true;
            }
        }

        return memo[c];
    }


}
