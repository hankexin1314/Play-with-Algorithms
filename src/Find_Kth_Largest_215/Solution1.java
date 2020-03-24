package Find_Kth_Largest_215;
// 三路快排
// 空间复杂度 O(1)
// 平均时间复杂度 O(n) 最差为n^2
public class Solution1 {

    public int findKthLargest(int[] nums, int k) {

        int l = 0;
        int r = nums.length - 1; // 右边界
        int v = nums[l];
        int lt = l; // [1, lt] > v
        int gt = r + 1; // [gt, r] < v
        int i = l + 1; // [lt+1, i) == v
        while(true) {
            while (i < gt) {
                if (nums[i] > v) {
                    lt++;
                    swap(nums, i, lt);
                    i++;
                } else if (nums[i] < v) {
                    gt--;
                    swap(nums, gt, i);
                } else
                    i++;
            }
            swap(nums, lt, l);
            lt--;
            if (k - 1 > lt && k - 1 < gt)
                return v;
            else if(k - 1 <= lt) {
                r = lt;
                v = nums[l];
                lt = l;
                gt = r + 1;
                i = l + 1;
            }
            else {
                l = gt;
                v = nums[l];
                lt = l;
                gt = r + 1;
                i = l + 1;
            }
        }
    }


    private void swap(int[] nums, int i, int j) {

        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
