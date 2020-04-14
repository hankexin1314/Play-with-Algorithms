package BinaryTree_Recursive;


import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

// 迭代
public class Solution129_4 {
    public int sumNumbers(TreeNode root) {

        if(root == null)
            return 0;
        int res = 0;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        while(!queue.isEmpty()) {

            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            int curSum = pair.getValue();
            if(node.left == null && node.right == null)
                res = res + curSum * 10 + node.val;
            else {
                if(node.left != null)
                    queue.offer(new Pair<>(node.left, curSum * 10 + node.val));
                if(node.right != null)
                    queue.offer(new Pair<>(node.right, curSum * 10 + node.val));
            }
        }

        return res;
    }

}
