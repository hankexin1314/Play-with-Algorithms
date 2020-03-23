package Remove_Element_27;

// 时间复杂度 O(n)
// 空间复杂度 O(1)
public class Solution1 {

    public int removeElement(int[] nums, int val) {

        int k = 0; // [0...k) 中不包含val [k...i) 全为val
        for(int i = 0; i < nums.length; i++) {

            if(nums[i] != val) {
                if(i != k) {
                    swap(nums, i, k);
                    k++;
                }
                else
                    k++;
            }
        }

        return k;
    }

    public void swap(int[] nums, int i, int j) {

        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
