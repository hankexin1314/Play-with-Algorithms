package jianzhiOffer.search;

public class Solution53_2_1 {

    public int missingNumber(int[] nums) {

        if(nums == null || nums.length == 0) return -1;
        return search(nums, 0, nums.length - 1);

    }

    // 返回值为缺失的数字
    private int search(int[] nums, int l, int r) {

        if(l >= r) {
            return nums[l] + (nums[l] == l ? 1: -1);
        }
        int mid = (r - l) / 2 + l;
        if(nums[mid] == mid) return search(nums, mid + 1, r);
        else return search(nums, l, mid - 1);
    }
}
