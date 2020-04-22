package Recursion_BackTracking;

// 回溯法
// 使用HashSet
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution46_2 {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        HashSet<Integer> set = new HashSet<>();
        helper(nums, res, new ArrayList<>(), set);
        return res;
    }

    // set用于记录哪些数字使用过，哪些没有使用过
    private void helper(int[] nums, List<List<Integer>> res, List<Integer> subRes, HashSet<Integer> set) {
        int L = nums.length;
        if(subRes.size() == L) {
            res.add(new ArrayList<>(subRes));
            return;
        }
        for(int i: nums) {
            if(!set.contains(i)) {
                subRes.add(i);
                set.add(i);
                helper(nums, res, subRes, set);
                subRes.remove(subRes.size() - 1);
                set.remove(i);
            }
        }
    }

}
