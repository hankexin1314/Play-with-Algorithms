package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.List;

public class Solution216_1 {
    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> res = new ArrayList<>();
        helper(k, n, 0, res, new ArrayList<>());
        return res;
    }

    // preNum表示上一轮中加入的数字
    // k表示还可以增加几个数字
    private void helper(int k, int target, int preNum, List<List<Integer>> res, List<Integer> subRes) {
        if(target == 0 && k == 0) {
            res.add(new ArrayList<>(subRes));
            return;
        }
        if(k == 0) return;
        for(int i = preNum + 1; i <= 9; i++) {
            if(i > target) break;
            subRes.add(i);
            helper(k - 1, target - i, i, res, subRes);
            subRes.remove(subRes.size() - 1);
        }
    }
}
