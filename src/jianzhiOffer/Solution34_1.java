package jianzhiOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution34_1 {

    List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        res = new ArrayList<>();
        if(root == null) return res;
        dfs(root, new ArrayList<>(), sum, 0);
        return res;
    }

    /**
     * 访问这个节点
     * @param root
     * @param path 不包含该节点的路径
     * @param sum 目标和
     * @param curSum 当前和
     */
    private void dfs(TreeNode root, List<Integer> path, int sum, int curSum) {

        path.add(root.val);
        curSum += root.val;
        if(curSum == sum && root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
            return;
        }
        if(root.left != null)  {
            dfs(root.left, path, sum, curSum);
            path.remove(path.size() - 1);
        }
        if(root.right != null)  {
            dfs(root.right, path, sum, curSum);
            path.remove(path.size() - 1);
        }
    }
}
