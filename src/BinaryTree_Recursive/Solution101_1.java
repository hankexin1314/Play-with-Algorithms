package BinaryTree_Recursive;

// 递归实现
public class Solution101_1 {

    public boolean isSymmetric(TreeNode root) {

        if(root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }

    // 判断两棵树是否对称
    private boolean isSymmetric(TreeNode left, TreeNode right) {

        if(left == null && right == null)
            return true;
        else if(left != null && right != null) {

            if(left.val == right.val)
                return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
            else
                return false;
        }
        else
            return false;
    }
}
