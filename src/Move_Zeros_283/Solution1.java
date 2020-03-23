package Move_Zeros_283;

// 283. Move Zeroes
// https://leetcode.com/problems/move-zeroes/description/
// 时间复杂度: O(n)
// 空间复杂度: O(1)
// 自己的实现，不使用辅助空间
// 优化 循环不变量



public class Solution1 {

    public void moveZeroes(int[] nums) {

//        int i = -1; // i代表最左侧0的索引
        int i = 0; // [0...i) 为非0元素
        for(int j = 0; j < nums.length; j++) {
//            if(nums[j] == 0 && i == -1)
//                i = j;
//            if(nums[j] != 0 && i != -1) {
//                swap(nums, i, j);
//                i++;
//            }
            if(nums[j] != 0) {
                if(i != j) {
                    swap(nums, i, j);
                    i++;
                }
                else
                    i++;
            }
        }
    }


    // 交换nums 中 索引为i和j的两个元素
    public void swap(int[] nums, int i, int j) {

        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
