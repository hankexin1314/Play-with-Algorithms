package Dynamic_Programming;

public class Solution376_1 {

    public int wiggleMaxLength(int[] nums) {

        if(nums.length < 2) return nums.length;
        int[] up = new int[nums.length]; // 以i为结尾的  且结尾为上升的序列的长度
        int[] down = new int[nums.length];

        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j])
                    up[i] = Math.max(up[i], 1 + down[j]);
                if(nums[i] < nums[j])
                    down[i] = Math.max(down[i], 1 + up[j]);
            }
        }

        return 1 + Math.max(up[nums.length - 1], down[nums.length - 1]);

    }
}
