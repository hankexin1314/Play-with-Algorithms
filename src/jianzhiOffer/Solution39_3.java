package jianzhiOffer;

import java.util.Arrays;

public class Solution39_3 {

    public int majorityElement(int[] nums) {

        int x = 0; // 众数
        int votes = 0; // 票和
        for(int i = 0; i < nums.length; i++) {
            if(votes == 0) x = nums[i];
            votes += (nums[i] == x ? 1 : -1);
        }

        return x;
    }
}
