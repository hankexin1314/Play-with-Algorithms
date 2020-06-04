package jianzhiOffer.binarytree;

public class Solution55_1_1 {

    int max;
    public int maxDepth(TreeNode root) {

        max = -1;
        dfs(root, 0);
        return max;
    }

    private void dfs(TreeNode root, int depth) {

        if(root == null)  {
            max = Math.max(depth, max);
            return;
        }
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }
}
