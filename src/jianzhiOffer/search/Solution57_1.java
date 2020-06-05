package jianzhiOffer.search;

public class Solution57_1 {

    public int[] twoSum(int[] nums, int target) {

        if(nums == null || nums.length < 2) return null;
        int l = 0, r = nums.length - 1;
        int[] res = new int[2];
        while(l < r) {
            int sum = nums[l] + nums[r];
            if(sum == target) {
                res[0] = nums[l];
                res[1] = nums[r];
                return res;
            }
            else if(sum < target) l++;
            else r--;
        }
        return null;
    }
}
