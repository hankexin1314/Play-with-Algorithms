package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 先排序再计算
public class Solution39_2 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 排序
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
            if(num > target) break; // 剪枝
            subRes.add(num);
            helper(candidates, target - num, i, res, subRes);
            subRes.remove(subRes.size() - 1);
        }
    }
}
