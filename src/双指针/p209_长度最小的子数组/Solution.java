package 双指针.p209_长度最小的子数组;

public class Solution {

    public int minSubArrayLen(int s, int[] nums) {

        if(nums == null || nums.length == 0) return 0;
        int res = nums.length + 1;
        int l = 0, sum = 0;
        for(int r = 0; r < nums.length; r++) {
            sum += nums[r];
            while(sum >= s) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l];
                l ++;
            }
        }

        return res == nums.length + 1 ? 0: res;
    }
}
