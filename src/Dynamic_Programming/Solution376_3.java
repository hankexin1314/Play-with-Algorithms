package Dynamic_Programming;

// 优化存储空间
public class Solution376_3 {

    public int wiggleMaxLength(int[] nums) {

        if(nums.length < 2) return nums.length;
        int up = 1;
        int down = 1;

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i - 1]) up = down + 1;
            else if(nums[i] < nums[i - 1]) down = up + 1;
        }

        return Math.max(up, down);

    }
}
