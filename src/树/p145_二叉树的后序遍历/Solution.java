package 树.p145_二叉树的后序遍历;

import 树.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        while(!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.addLast(root);
                root = root.left;
            }
            root = stack.peekLast();
            if(root.right == null || root.right == pre) {
                res.add(root.val);
                pre = root;
                stack.removeLast(); // pop
                root = null;
            }
            else
                root = root.right;
        }
        return res;
    }
}
