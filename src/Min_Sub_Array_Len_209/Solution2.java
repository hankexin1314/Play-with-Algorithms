package Min_Sub_Array_Len_209;

// 暴力法
// 时间复杂度O(n^2)
// 空间复杂度O(n)


public class Solution2 {

    public int minSubArrayLen(int s, int[] nums) {

        int res = 0;
        // sum[i] 表示 [0, i-1] 的和
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;

        for(int i = 1; i < nums.length + 1; i++)
            sum[i] = nums[i - 1] + sum[i - 1];

        for(int i = 0; i < nums.length; i++) {
            for(int j = i; j < nums.length; j++) {
                if(sum[j + 1] - sum[i] >= s) {
                    if(res == 0)
                        res = j - i + 1;
                    else
                        res = Math.min(res, j - i + 1);
                    break; // 由于是寻找最短长度，此时可以跳出
                }
            }
        }

        return res;
    }

}
