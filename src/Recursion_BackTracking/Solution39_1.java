package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.List;

public class Solution39_1 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        helper(candidates, target, 0, res, new ArrayList<>());

        return res;
    }

    // pre 表示之前选择的index
    private void helper(int[] candidates, int target, int pre,  List<List<Integer>> res, List<Integer> subRes) {
        if(target == 0) {
            res.add(new ArrayList<>(subRes));
            return;
        }
        int L = candidates.length;
        for(int i = pre; i < L; i++) {
            int num = candidates[i];
            if(num > target) continue;
            subRes.add(num);
            helper(candidates, target - num, i, res, subRes);
            subRes.remove(subRes.size() - 1);
        }
    }
}
