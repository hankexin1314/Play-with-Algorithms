package Three_Sum_15;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length - 2; i++) {
            if(nums[i] > 0) // 最小值大于0
                break;
            if(i > 0 && nums[i] == nums[i - 1])
                continue;
            int l = i + 1, r = nums.length - 1;
            while(l < r) {

                if(l > i + 1 && nums[l] == nums[l - 1]) {
                    l++;
                    continue;
                }
                if(r < nums.length - 1 && nums[r] == nums[r + 1]) {
                    r--;
                    continue;
                }
                if(nums[l] + nums[r] == - nums[i]) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                }
                else if(nums[l] + nums[r] < - nums[i])
                    l++;
                else
                    r--;
            }
        }

        return res;
    }
}