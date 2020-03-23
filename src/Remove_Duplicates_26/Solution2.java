package Remove_Duplicates_26;

public class Solution2 {

    public int removeDuplicates(int[] nums) {

        int k = 0; // [0...k] 没有重复元素
        for(int i = 0; i < nums.length; i++) {

            if(nums[i] != nums[k]) {
                k++;
                if(k != i)
                    swap(nums, i, k);
            }
        }
        return k + 1;
    }

    public void swap(int[] nums, int i, int j) {

        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
