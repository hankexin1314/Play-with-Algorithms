package Move_Zeros_283;

import java.util.*;
// 时间复杂度: O(n)
// 空间复杂度: O(n)
// 使用辅助空间
public class Solution2 {

    public void moveZeroes(int[] nums) {

        ArrayList<Integer> nonZeroElements = new ArrayList<Integer>();

        // 将vec中所有非0元素放入nonZeroElements中
        for(int i = 0 ; i < nums.length ; i ++)
            if(nums[i] != 0)
                nonZeroElements.add(nums[i]);

        // 将nonZeroElements中的所有元素依次放入到nums开始的位置
        for(int i = 0 ; i < nonZeroElements.size() ; i ++)
            nums[i] = nonZeroElements.get(i);

        // 将nums剩余的位置放置为0
        for(int i = nonZeroElements.size() ; i < nums.length ; i ++)
            nums[i] = 0;
    }

    public static void main(String args[]){

        int[] arr = {0, 1, 0, 3, 12};

        (new Solution2()).moveZeroes(arr);

        for(int i = 0 ; i < arr.length ; i ++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
