package BinaryTree_Recursive;

import java.util.Stack;

// 中序遍历，第k个元素即为结果
public class Solution230_1 {
    public int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();
        int count = 0;
        while(!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            count ++;
            if(count == k)
                break;
            root = root.right;
        }
        return root.val;
    }
}
