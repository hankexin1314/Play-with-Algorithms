package BinaryTree_Recursive;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 迭代实现 广度优先
public class Solution112_2 {

    public boolean hasPathSum(TreeNode root, int sum) {

        if(root == null)
            return false;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, root.val));
        while(!queue.isEmpty()) {

            Pair<TreeNode, Integer> pair = queue.poll();
            int count = pair.getValue();
            TreeNode node = pair.getKey();
            if(node.left == null && node.right == null & count == sum)
                return true;
            if(node.left != null)
                queue.offer(new Pair<>(node.left, count + node.left.val));
            if(node.right != null)
                queue.offer(new Pair<>(node.right, count + node.right.val));
        }

        return false;
    }
}
