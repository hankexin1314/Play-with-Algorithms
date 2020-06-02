package jianzhiOffer;

import java.util.Arrays;
import java.util.HashMap;

public class Solution39_2 {

    public int majorityElement(int[] nums) {

        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
