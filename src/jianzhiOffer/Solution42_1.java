package jianzhiOffer;

public class Solution42_1 {

    public int maxSubArray(int[] nums) {

        if(nums == null || nums.length == 0) throw new IllegalStateException("无有效结果");
        int max = nums[0], cur = nums[0];
        for(int i = 1; i < nums.length; i++) {
            cur = Math.max(cur, 0) + nums[i];
            max = Math.max(max, cur);
        }

        return max;
    }
}
