package Sort_Colors_75;

public class Solution {

    public void sortColors(int[] nums) {

        int zero = -1; // [0...zero] == 0
        int two = nums.length; // [two...n-1] == 2
        int i = 0; // [zero+1...i-1] == 1

        while(i < two) {
            if(nums[i] == 1)
                i++;
            else if(nums[i] == 0) {
                zero++;
                swap(nums, i, zero);
                i++;
            }
            else {
                two--;
                swap(nums, i, two);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {

        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
