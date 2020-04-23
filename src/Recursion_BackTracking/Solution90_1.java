package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution90_1 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        helper(nums, res, new ArrayList<>(), 0);
        return res;
    }

    private void helper(int[] nums, List<List<Integer>> res, List<Integer> subRes, int start) {
        res.add(new ArrayList<>(subRes));
        for(int i = start; i < nums.length; i++) {
            int num = nums[i];
            if(i > start && num == nums[i - 1]) continue;
            subRes.add(num);
            helper(nums, res, subRes, i + 1);
            subRes.remove(subRes.size() - 1);
        }
    }
}
