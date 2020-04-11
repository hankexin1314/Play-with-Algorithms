package BinaryTree_Recursive;

import java.util.Stack;

// 迭代实现
public class Solution101_2 {

    public boolean isSymmetric(TreeNode root) {

        if(root == null)
            return true;

        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();

        leftStack.push(root);
        rightStack.push(root);
        while(!leftStack.isEmpty() && !rightStack.isEmpty()) {

            TreeNode leftNode = leftStack.pop();
            TreeNode rightNode = rightStack.pop();
            if(leftNode.val != rightNode.val)
                return false;
            if(leftNode.right != null && rightNode.left != null) {
                leftStack.push(leftNode.right);
                rightStack.push(rightNode.left);
            }
            else if(leftNode.right == null && rightNode.left == null);
            else return false;

            if(leftNode.left != null && rightNode.right != null) {
                leftStack.push(leftNode.left);
                rightStack.push(rightNode.right);
            }
            else if(leftNode.left == null && rightNode.right == null);
            else return false;
        }

        return leftStack.isEmpty() && rightStack.isEmpty();
    }
}
