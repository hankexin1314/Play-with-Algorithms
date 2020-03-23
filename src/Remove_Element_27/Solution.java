package Remove_Element_27;

// 时间复杂度 O(n)
// 空间复杂度 O(1)
public class Solution {

    public int removeElement(int[] nums, int val) {

        int k = 0; // [0...k) 中不包含val
        for(int i = 0; i < nums.length; i++) {

            if(nums[i] != val) {
                if(i != k) {
                    nums[k] = nums[i];
                    k++;
                }
                else
                    k++;
            }
        }

        return k;
    }
}
