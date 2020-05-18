package jianzhiOffer;

// 迭代
import java.util.HashMap;
import java.util.Stack;

public class Solution7_3 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        int index = 0; // inorder的index
        for (int i = 1; i < preorder.length; i++) {

            if (stack.peek().val != inorder[index]) {
                TreeNode node = new TreeNode(preorder[i]);
                stack.peek().left = node;
                stack.push(node);
            } else {
                TreeNode node = stack.peek();
                while (!stack.isEmpty() && stack.peek().val == inorder[index]) {
                    node = stack.pop();
                    index++;
                }
                node.right = new TreeNode(preorder[i]);
                stack.push(node.right);
            }
        }

        return root;

    }
}

