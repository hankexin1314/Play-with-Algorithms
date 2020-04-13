package BinaryTree_Recursive;

// 递归实现
public class Solution112_1 {

    public boolean hasPathSum(TreeNode root, int sum) {

        if(root == null)
            return false;
        if(root.val == sum && root.left == null && root.right == null)
            return true;
        return hasPathSum(root.left, sum - root.val) ||
                hasPathSum(root.right, sum - root.val);
    }
}
