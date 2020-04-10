package BinaryTree_Recursive;


import java.util.Stack;

// 迭代
public class Solution226_2 {

    public TreeNode invertTree(TreeNode root) {

        if(root == null)
            return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode tmpNode = stack.pop();
            TreeNode left = tmpNode.left, right = tmpNode.right;
            tmpNode.left = right;
            tmpNode.right = left;
            if(left != null) stack.push(left);
            if(right != null) stack.push(right);
        }

        return root;
    }
}
