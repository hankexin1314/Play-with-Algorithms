package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.List;

public class Solution78_1 {
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        helper(nums, res, 0, new ArrayList<>());

        return res;
    }

    // level表示 集合处于第几个级别
    private void helper(int[] nums, List<List<Integer>> res, int level, List<Integer> subRes) {
        int L = nums.length;
        if(level == L) {
            res.add(new ArrayList<>(subRes));
            return;
        }
        int num = nums[level];
        helper(nums, res, level + 1, subRes); // 不包含num
        subRes.add(num);
        helper(nums, res, level + 1, subRes); // 包含num
        subRes.remove(subRes.size() - 1); // 状态重置
    }
}
