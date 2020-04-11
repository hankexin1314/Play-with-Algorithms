package BinaryTree_Recursive;


// 递归 自底向上

public class Solution110_2 {

    public boolean isBalanced(TreeNode root) {

        if(root == null)
            return true;
        return computeHeight(root) != -1;

    }

    // 计算深度
    private int computeHeight(TreeNode root) {

        if(root == null)
            return 0;
        int leftHeight = computeHeight(root.left);
        if(leftHeight == -1) return -1;
        int rightHeight = computeHeight(root.right);
        if(rightHeight == -1) return -1;
        if(Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
