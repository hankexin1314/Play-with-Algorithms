package jianzhiOffer;

import java.util.Arrays;

public class QuickSort {

    public void sort(int[] nums) {

        sort(nums, 0, nums.length - 1);
    }

    /**
     * 对[l, r]范围内的数字进行排序
     * @param nums
     * @param l
     * @param r
     */
    private void sort(int[] nums, int l, int r) {

        if(l >= r) return;
        int p = partition(nums, l, r);
        sort(nums, l, p - 1);
        sort(nums, p + 1, r);
    }

    /**
     * 两路快排
     * @param nums
     * @param l
     * @param r
     * @return
     */
    private int partition(int[] nums, int l, int r) {

        int val = nums[l];
        int i = l + 1, j = r;
        while(true) {

            while(i <= r && nums[i] < val) i++; // 为什么不是 <=
            while(j >= l + 1 && nums[j] > val) j--; // 为什么不是 >=
            if(i > j) break; // i == j 的情况
            swap(nums, i, j);
            i ++;
            j --;
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {

        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1, 1};
        (new QuickSort()).sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
