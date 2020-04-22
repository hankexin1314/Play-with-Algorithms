package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Solution47_1 {
    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        helper(nums, res, new ArrayList<>(), used);

        return res;
    }

    private void helper(int[] nums, List<List<Integer>> res, List<Integer> subRes, boolean[] used) {
        int L = nums.length;
        if(subRes.size() == L) {
            res.add(new ArrayList<>(subRes));
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < L; i++) {
            int num = nums[i];
            if(!used[i] && !set.contains(num)) {
                used[i] = true;
                set.add(num);
                subRes.add(num);
                helper(nums, res, subRes, used);
                subRes.remove(subRes.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = (new Solution47_1()).permuteUnique(nums);
    }
}
