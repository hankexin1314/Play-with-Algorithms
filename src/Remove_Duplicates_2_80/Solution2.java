package Remove_Duplicates_2_80;

// 自己的解法
public class Solution2 {

    public int removeDuplicates(int[] nums) {

        int k = 1; // 代表要覆盖的位置
        int count = 1;

        for(int i = 1; i < nums.length; i++) { // i用来遍历数组

            if(nums[i] == nums[i-1]) {
                count++;
                if(count <= 2) {
                    nums[k] = nums[i];
                    k++;
                }
            }
            else {
                count = 1;
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}
