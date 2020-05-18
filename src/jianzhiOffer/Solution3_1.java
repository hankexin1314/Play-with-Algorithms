package jianzhiOffer;

public class Solution3_1 {

    public int findRepeatNumber(int[] nums) {

        if(nums == null || nums.length == 0) return -1;
//        boolean[] used = new boolean[100000];
        boolean[] used = new boolean[nums.length];
        for(int num: nums) {
            if(used[num]) return num;
            used[num] = true;
        }
        return -1;
    }
}
