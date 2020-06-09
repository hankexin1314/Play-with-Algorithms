package 排序.p215_数组中的第K个最大元素;

import java.util.HashMap;

public class Solution1 {

    public int findKthLargest(int[] nums, int k) {

        int l = 0, r = nums.length - 1;
        int index = partition(nums, l, r);
        while(index != nums.length - k) {
            if(index < nums.length - k) {
                l = index + 1;
                index = partition(nums, l, r);
            }
            else {
                r = index - 1;
                index = partition(nums, l, r);
            }
        }

        return nums[index];
    }

    // 返回index
    private int partition(int[] nums, int l, int r) {

        int rand_index = (int)(Math.random() * (r - l + 1) + l);
        swap(nums, l, rand_index);
        int val = nums[l];
        int i = l + 1, j = r;
        while(true) {
            while(i <= r && nums[i] < val) i++;
            while(j >= l+1 && nums[j] > val) j--;
            if(i > j) break;
            swap(nums, i, j);
            i++;
            j--;
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
