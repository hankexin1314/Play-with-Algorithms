package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.List;

public class Solution77_1 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(n < 1 || k > n) return res;
        helper(n, k, 0, res, new ArrayList<>());

        return res;
    }

    // pre表示上一个添加进subRes的数字
    private void helper(int n, int k, int pre, List<List<Integer>> res, List<Integer> subRes) {
        if(subRes.size() == k) {
            res.add(new ArrayList<>(subRes));
            return;
        }
        if(pre >= n) return;
        for(int i = pre + 1; i <= n; i++) {
            subRes.add(i);
            helper(n, k, i, res, subRes);
            subRes.remove(subRes.size() - 1);
        }
    }
}
