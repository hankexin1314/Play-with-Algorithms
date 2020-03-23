package Remove_Duplicates_26;

public class Solution {

    public int removeDuplicates(int[] nums) {

        int k = 0; // [0...k] 没有重复元素
        for(int i = 0; i < nums.length; i++) {

            if(nums[i] != nums[k]) {
                k++;
                if(k != i)
                    nums[k] = nums[i];
            }
        }
        return k + 1;
    }
}
