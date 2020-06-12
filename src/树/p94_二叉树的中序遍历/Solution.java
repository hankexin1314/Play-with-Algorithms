package 树.p94_二叉树的中序遍历;

import 树.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while(!stack.isEmpty() || root != null) {

            while(root != null) {
                stack.addLast(root);
                root = root.left;
            }
            root = stack.removeLast();
            res.add(root.val);
            root = root.right;
        }

        return res;
    }

}
