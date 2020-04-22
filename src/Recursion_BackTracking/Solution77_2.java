package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.List;
// 回溯另一种写法
// 比较简便
// 循环部分的优化
public class Solution77_2 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(n < 1 || k > n) return res;
        helper(n, k, 0, res, new ArrayList<>());

        return res;
    }

    // pre表示上一个添加进subRes的数字
    // k表示还需要取几个数
    private void helper(int n, int k, int pre, List<List<Integer>> res, List<Integer> subRes) {
        if(k == 0) {
            res.add(new ArrayList<>(subRes));
            return;
        }
        for(int i = pre + 1; i <= n - k + 1; i++) {
            subRes.add(i);
            helper(n, k - 1, i, res, subRes);
            subRes.remove(subRes.size() - 1);
        }
    }
}
