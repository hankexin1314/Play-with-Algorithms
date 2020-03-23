package Remove_Duplicates_2_80;

// 暴力解法
public class Solution1 {
    public int removeDuplicates(int[] nums) {

        int freq = 1;
        int length = nums.length;
        for(int i = 1; i < length; i++) {

            if(nums[i] == nums[i-1])
                freq++;
            else
                freq = 1;

            if(freq > 2) {
                removeElement(nums, i);
                i--;
                length--;
            }
        }
        return length;
    }

    // 移除第i个元素
    public void removeElement(int[] nums, int n) {

        for(int i = n + 1; i < nums.length; i++)
            nums[i - 1] = nums[i];
    }
}
