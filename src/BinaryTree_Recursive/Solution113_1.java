package BinaryTree_Recursive;

import java.util.ArrayList;
import java.util.List;

// 递归
public class Solution113_1 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        pathSum(root, path, res, sum);
        return res;
    }

    private void pathSum(TreeNode root, List<Integer> path, List<List<Integer>> res, int target) {

        if(root == null)
            return;
        path.add(root.val);
        if(root.left == null && root.right == null && root.val == target)
            res.add(new ArrayList(path));
        else {
            pathSum(root.left, path, res, target - root.val);
            pathSum(root.right, path, res, target - root.val);
        }
        path.remove(path.size() - 1);
    }
}
