package BinaryTree_Recursive;

import java.util.Stack;

// 迭代实现
public class Solution404_2 {

    public int sumOfLeftLeaves(TreeNode root) {

        if(root == null)
            return 0;
        int ans = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node.right != null) stack.push(node.right);
            if(node.left != null) {
                if(node.left.left == null && node.left.right == null) ans += node.left.val;
                else stack.push(node.left);
            }
        }

        return ans;
    }
}
