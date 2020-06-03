package jianzhiOffer.sort;

import java.util.Arrays;

public class Solution51_1 {

    int res;

    public int reversePairs(int[] nums) {

        if(nums == null || nums.length == 0) return 0;
        res = 0;
        sort(nums, 0, nums.length - 1);
        return res;
    }

    // 对[l, r]进行排序
    private void sort(int[] nums, int l, int r) {

        if(l >= r) return;
        int mid = (r - l) / 2 + l;
        sort(nums, l, mid);
        sort(nums, mid + 1, r);
        if(nums[mid] > nums[mid + 1])
            merge(nums, l, mid, r);
    }

    // [l, mid] [mid + 1, r] 进行merge
    private void merge(int[] nums, int l, int mid, int r) {

        int[] aux = Arrays.copyOfRange(nums, l, r + 1);
        int i = l, j = mid + 1;
        for(int k = l; k <= r; k ++) {

            if(i > mid) {
                nums[k] = aux[j];
                j ++;
            }
            else if(j > r) {
                nums[k] = aux[i];
                i ++;
            }
            else if(aux[i] > aux[j]) {
                nums[k] = aux[j];
                j ++;
                res += (mid - i + 1);
            }
            else {
                nums[k] = aux[i];
                i ++;
            }
        }
    }


}
