package Remove_Duplicates_2_80;

// 自己的解法
public class Solution {

    public int removeDuplicates(int[] nums) {

        if(nums.length == 0)
            return 0;
        int k = 0; // [0...k) 没有俩个以上的重复元素， 要覆盖的元素的位置
        int freq = 0; // 元素出现的频率
        int val = nums[k]; // 此时的元素

        for(int i = 0; i < nums.length; i++) {

            if(nums[i] == val) {
                freq++;
                if(freq <= 2) {
                    nums[k] = val;
                    k++;
                }
            }
            if(nums[i] != val) {
                val = nums[i];
                freq = 1;
                nums[k] = val;
                k++;
            }
        }
        return k;
    }
}
