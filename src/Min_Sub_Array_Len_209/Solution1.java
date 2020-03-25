package Min_Sub_Array_Len_209;

// 暴力法
// 时间复杂度O(n^3)
// 超出时间限制

public class Solution1 {

    public int minSubArrayLen(int s, int[] nums) {

        int res = 0;
        for(int i = 0; i < nums.length; i++) {
            for(int j = i; j < nums.length; j++) {
                if(sum(nums, i, j) >= s) {
                    if(res == 0)
                        res = j - i + 1;
                    else
                        res = Math.min(res, j - i + 1);
                }
            }
        }

        return res;
    }

    // 求nums中 [i, j]索引之间值的和
    private int sum(int[] nums, int i, int j) {

        int sum = 0;
        for(int k = i; k <= j; k++)
            sum += nums[k];

        return sum;
    }
}
