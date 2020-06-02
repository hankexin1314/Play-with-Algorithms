package jianzhiOffer;

import java.util.LinkedList;
import java.util.Queue;

public class Solution27_1 {

    public TreeNode mirrorTree(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null) queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }

        return root;
    }
}
