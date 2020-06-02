package jianzhiOffer;

public class Solution21_1 {

    public int[] exchange(int[] nums) {

        if(nums == null || nums.length == 0) return nums;
        int l = 0, r = nums.length - 1;
        while(l < r) {
            while(l < nums.length && nums[l] % 2 == 1) l++;
            while(r >= 0 && nums[r] % 2 == 0) r--;
            if(l < r) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
                l++;
                r--;
            }
        }
        return nums;
    }
}
