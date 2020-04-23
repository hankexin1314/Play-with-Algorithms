package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.List;

public class Solution78_2 {
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        helper(nums, res, 0, new ArrayList<>());

        return res;
    }

    private void helper(int[] nums, List<List<Integer>> res, int start, List<Integer> subRes) {
        res.add(new ArrayList<>(subRes));
        for(int i = start; i < nums.length; i++) {
            subRes.add(nums[i]);
            helper(nums, res, i + 1, subRes);
            subRes.remove(subRes.size() - 1);
        }
    }
}
