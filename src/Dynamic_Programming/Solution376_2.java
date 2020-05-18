package Dynamic_Programming;

public class Solution376_2 {

    public int wiggleMaxLength(int[] nums) {

        if(nums.length < 2) return nums.length;
        int[] up = new int[nums.length]; // [0, i]上，结尾为上升的序列的长度
        int[] down = new int[nums.length];
        up[0] = 1; down[0] = 1;

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            }
            else if(nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = up[i - 1] + 1;
            }
            else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }

        return Math.max(up[nums.length - 1], down[nums.length - 1]);

    }
}
