package jianzhiOffer.binarytree;

public class Solution55_2_1 {

    public boolean isBalanced(TreeNode root) {

        if(root == null) return true;
        return Math.abs(computeDepth(root.left) - computeDepth(root.right)) <= 1 &&
                isBalanced(root.left) &&
                isBalanced(root.right);
    }

    private int computeDepth(TreeNode node) {

        if(node == null) return 0;
        return Math.max(computeDepth(node.left), computeDepth(node.right)) + 1;
    }
}
