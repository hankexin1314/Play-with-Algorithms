package jianzhiOffer;

import java.util.HashSet;

public class Solution3_2 {

    public int findRepeatNumber(int[] nums) {

        if(nums == null || nums.length == 0) return -1;
        HashSet<Integer> set = new HashSet<>();
        for(int num: nums) {
            if(set.contains(num)) return num;
            set.add(num);
        }
        return -1;
    }
}
