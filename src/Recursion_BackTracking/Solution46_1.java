package Recursion_BackTracking;

// 回溯法

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution46_1 {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        helper(nums, res, new ArrayList<>());
        return res;
    }

    private void helper(int[] nums, List<List<Integer>> res, List<Integer> subRes) {
        int L = nums.length;
        if(subRes.size() == L) {
            res.add(new ArrayList<>(subRes));
            return;
        }
        for(int i: nums) {
            if(!subRes.contains(i)) {
                subRes.add(i);
                helper(nums, res, subRes);
                subRes.remove(subRes.size() - 1);
            }
        }
    }

}
