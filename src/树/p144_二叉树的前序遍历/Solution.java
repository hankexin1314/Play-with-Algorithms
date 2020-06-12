package 树.p144_二叉树的前序遍历;

import 树.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            while(cur != null) {
                res.add(cur.val);
                stack.addLast(cur);
                cur = cur.left;
            }
            cur = stack.removeLast();
            cur = cur.right;
        }

        return res;
    }
}
