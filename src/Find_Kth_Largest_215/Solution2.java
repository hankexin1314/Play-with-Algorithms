package Find_Kth_Largest_215;
// 两路快排
// 空间复杂度 O(1)
// 平均时间复杂度 O(n) 最差为n^2
public class Solution2 {

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

        int val = nums[l]; // 标定点
        int k = l + 1; // [l+1, k) >= v
        int lt = r; // (lt, r] < v
        while(true) {
            while(k <= r && nums[k] > val) k++;
            while(lt >= l && nums[lt] < val) lt--;
            if(k > lt)
                break;
            swap(nums, k, lt);
            k++;
            lt--;
        }
        swap(nums, l, lt);

        return lt;
    }


    private void swap(int[] nums, int i, int j) {

        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
