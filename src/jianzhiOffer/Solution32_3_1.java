package jianzhiOffer;

import java.util.*;

public class Solution32_3_1 {

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 0; // 第几层
        queue.offer(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            int l = queue.size();
            if(level % 2 == 0) {
                for (int i = 0; i < l; i++) {
                    TreeNode node = queue.poll();
                    tmp.addLast(node.val);
                    if(node.left != null) queue.offer(node.left);
                    if(node.right != null) queue.offer(node.right);
                }
            }
            else {
                for (int i = 0; i < l; i++) {
                    TreeNode node = queue.poll();
                    tmp.addFirst(node.val);
                    if(node.left != null) queue.offer(node.left);
                    if(node.right != null) queue.offer(node.right);
                }
            }
            level++;
            res.add(tmp);
        }

        return res;
    }
}
