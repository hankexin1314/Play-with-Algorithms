package Min_Sub_Array_Len_209;

// 滑动窗口
// 时间复杂度O(n)
// 空间复杂度O(1)


public class Solution3 {

    public int minSubArrayLen(int s, int[] nums) {

        int res = nums.length + 1;
        int l = 0, r = -1; // [l...r] 滑动窗口
        int sum = 0; // 滑动窗口中的元素和

        while(l < nums.length) {

            if(r + 1 < nums.length && sum < s) {
                r++;
                sum += nums[r];
            }
            else {
                sum -= nums[l];
                l++;
            }
            if(sum >= s)
                res = Math.min(res, r - l + 1);
        }
        if(res == nums.length + 1)
            return 0;

        return res;
    }
}
