package jianzhiOffer.binarytree;

public class Solution55_2_2 {

    public boolean isBalanced(TreeNode root) {

        return computeDepth(root) != -1;
    }

    private int computeDepth(TreeNode node) {

        if(node == null) return 0;
        int left = computeDepth(node.left);
        if(left == -1) return -1;
        int right = computeDepth(node.right);
        if(right == -1) return -1;
        return Math.abs(left - right) <= 1 ? Math.max(left, right) + 1: -1;
    }
}
