package jianzhiOffer.search;

public class Solution53_1_1 {

    public int search(int[] nums, int target) {

        int index = search(nums, 0, nums.length - 1, target);
        int count = 0;
        if(index == -1) return count;
        else {
            count ++;
            for(int i = index + 1; i < nums.length; i++) {
                if(nums[i] == target) count ++;
                else break;
            }
            for(int i = index - 1; i >= 0; i--) {
                if(nums[i] == target) count ++;
                else break;
            }
        }
        return count;
    }

    // 在[l, r]寻找， 返回index
    private int search(int[] nums, int l, int r, int target) {
        if(l > r) return -1;
        int mid = (r - l) / 2 + l;
        if(nums[mid] == target) return mid;
        else if(nums[mid] < target) return search(nums, mid + 1, r, target);
        else return search(nums, l, mid - 1, target);
    }
}
