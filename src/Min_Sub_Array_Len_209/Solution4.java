package Min_Sub_Array_Len_209;

// 暴力法
// 时间复杂度O(n^2)
// 空间复杂度O(n)


public class Solution4 {

    public int minSubArrayLen(int s, int[] nums) {

        int res = nums.length + 1;
        // sum[i] 表示 [0, i-1] 的和
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;

        for(int i = 1; i < nums.length + 1; i++)
            sum[i] = nums[i - 1] + sum[i - 1];

        for(int i = 0; i < nums.length; i++) {
            int target = s + sum[i];
            int k = lowerBound(sum, target);
            if(k != sum.length) // sum[k] 刚好大于 target，也就是[i...k-1]的和大于s
                res = Math.min(res, k - i);
        }
        if(res == nums.length + 1)
            return 0;
        return res;
    }

    // 在sum[l...r)中，寻找刚好大于等于target的值，如果不存在，则返回sum.length
    private int lowerBound(int[] sum, int target) {

        int l = 0, r = sum.length;
        while(l < r) {

            int mid = l + (r - l) / 2;
            if(sum[mid] == target)
                return mid;
            else if(sum[mid] > target)
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }

    public static void main(String[] args) {

        int[] nums = {5,1,3,5,10,7,4,9,2,8};
        int s = 15;
        int res;
        res = (new Solution4()).minSubArrayLen(s, nums);
    }

}
