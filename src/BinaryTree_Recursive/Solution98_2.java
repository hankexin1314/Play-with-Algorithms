package BinaryTree_Recursive;

import java.util.Stack;

public class Solution98_2 {

    // 中序遍历

    public boolean isValidBST(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        Integer preNum = null;
        while(!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(preNum != null && root.val <= preNum) return false;
            preNum = root.val;
            root = root.right;
        }
        return true;
    }


}
