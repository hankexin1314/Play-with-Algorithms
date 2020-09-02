package 手撕代码.快速排序;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuickSort2Ways {

    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int l, int r) {

        if(l >= r) return;
        int index = partition(nums, l, r);
        sort(nums, l,index - 1);
        sort(nums, index + 1, r);
    }

    private int partition(int[] nums, int l, int r) {

        int val = nums[l];
        int i = l + 1, j = r;
        while(i < j) {
            while(i <= r && nums[i] < val) i++;
            while(j >= l && nums[j] > val) j--;
            if(i > j) break;
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
        QuickSort2Ways demo = new QuickSort2Ways();
        int[] nums = {5, 3, 11, 23, 0, 1};
        demo.sort(nums);
        System.out.println(Arrays.toString(nums));

    }
}
