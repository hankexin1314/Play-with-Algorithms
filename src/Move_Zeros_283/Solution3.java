package Move_Zeros_283;

// 283. Move Zeroes
// https://leetcode.com/problems/move-zeroes/description/
// 时间复杂度: O(n)
// 空间复杂度: O(1)


public class Solution3 {

    // 优化，不使用交换
    public void moveZeroes(int[] nums) {

        int i = 0; // nums中 [0...k) 为非0元素
        for(int j = 0; j < nums.length; j++) {

            if(nums[j] != 0) {
                nums[i] = nums[j];
                i++;
            }
        }
        for(; i < nums.length; i++)
            nums[i] = 0;
    }

}
