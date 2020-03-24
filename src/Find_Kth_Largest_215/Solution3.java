package Find_Kth_Largest_215;
// 两路快排 不需要两个指针
// 空间复杂度 O(1)
// 平均时间复杂度 O(n) 最差为n^2
public class Solution3 {

    public int findKthLargest(int[] nums, int k) {

        int l = 0, r = nums.length - 1;
        int i = partition(nums, l, r);
        while(i != k - 1) {
            if(k - 1 < i)
                r = i - 1;
            else
                l = i + 1;

            i = partition(nums, l, r);
        }
        return nums[i];
    }

    // 对[l, r] 选择nums[l]作为标定点，返回标定点的index
    private int partition(int[] nums, int l, int r) {

        int rand_index = (int)(Math.random() * (r - l + 1) + l);
        swap(nums, l, rand_index);

        int v = nums[l];
        int j = l; // [l+1, j] > v
        for(int i = l; i <= r; i++) {

            if(nums[i] > v) {
                j++;
                swap(nums, i, j);
            }
        }
        swap(nums, j, l);

        return j;
    }


    private void swap(int[] nums, int i, int j) {

        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
