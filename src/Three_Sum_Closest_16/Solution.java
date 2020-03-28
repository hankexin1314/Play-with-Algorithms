package Three_Sum_Closest_16;

import Length_Of_Longest_Substring_3.Solution1;

import java.util.Arrays;

public class Solution {

    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];

        for(int i = 0; i < nums.length - 2; i++) {
            int l = i + 1, r = nums.length - 1;
            int ans = target - nums[i];
            while(l < r) {

                if(nums[l] + nums[r] == ans)
                    return target;
                else if(nums[l] + nums[r] < ans) {
                    if(Math.abs(nums[l] + nums[r] - ans) < Math.abs(res - target))
                        res = nums[l] + nums[r] + nums[i];
                    l++;
                }
                else {
                    if (Math.abs(nums[l] + nums[r] - ans) < Math.abs(res - target))
                        res = nums[l] + nums[r] + nums[i];
                    r--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int[] nums = {-3, -2, -5, 3, -4};
        int target = -1;
        int a = (new Solution().threeSumClosest(nums, target));
    }
}
