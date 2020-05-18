package jianzhiOffer;

import java.util.HashSet;

public class Solution3_3 {

    public int findRepeatNumber(int[] nums) {

        if(nums == null || nums.length == 0) return -1;
        for(int i = 0; i < nums.length; i++) {
            while(nums[i] != i) {
                int num = nums[i];
                if(nums[num] == num) return num;
                nums[i] = nums[num];
                nums[num] = num;
            }
        }
        return -1;
    }
}
