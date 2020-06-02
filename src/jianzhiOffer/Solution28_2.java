package jianzhiOffer;

import java.util.Stack;

public class Solution28_2 {

    public boolean isSymmetric(TreeNode root) {

        if(root == null) return true;
        Stack<TreeNode> leftStack = new Stack<>(), rightStack = new Stack<>();
        leftStack.push(root);
        rightStack.push(root);
        while(!leftStack.isEmpty() && !rightStack.isEmpty()) {
            TreeNode leftNode = leftStack.pop();
            TreeNode rightNode = rightStack.pop();

            if(leftNode == null && rightNode == null) continue;
            if(leftNode == null || rightNode == null) return false;
            if(leftNode.val != rightNode.val) return false;
            leftStack.push(leftNode.left);
            rightStack.push(rightNode.right);
            leftStack.push(leftNode.right);
            rightStack.push(rightNode.left);
        }

        return leftStack.isEmpty() && rightStack.isEmpty();
    }

}
