package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution40_1 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        helper(candidates, target, -1, res, new ArrayList<>());
        return res;
    }

    // preIndex 记录了上一次加入集合中的元素的index
    private void helper(int[] candidates, int target, int preIndex, List<List<Integer>> res, List<Integer> subRes) {
        if(target == 0) {
            res.add(new ArrayList<>(subRes));
            return;
        }
        int L = candidates.length;
        int pre = -1; // 记录这一轮中，上一次添加进subRes的元素
        for(int i = preIndex + 1; i < L; i++) {
            int num = candidates[i];
            if(num > target) break;
            if(num == pre) continue;
            subRes.add(num);
            pre = num;
            helper(candidates, target - num, i, res, subRes);
            subRes.remove(subRes.size() - 1);
        }
    }
}
