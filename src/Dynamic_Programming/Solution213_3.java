package Dynamic_Programming;

// 动态规划 自底向上
public class Solution213_3 {

    public int rob(int[] nums) {

        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int p1 = nums[0], p2 = 0; // f(n - 1) f(n - 2)  选择第一家
        for(int i = 1; i < nums.length - 1; i++) {
            int tmp = p1;
            p1 = Math.max(nums[i] + p2, p1);
            p2 = tmp;
        }
        int val = p1;
        p1 = nums[1]; p2 = 0; // 选第二家
        for(int i = 2; i < nums.length; i++) {
            int tmp = p1;
            p1 = Math.max(nums[i] + p2, p1);
            p2 = tmp;
        }

        return Math.max(val, p1);
    }


}
