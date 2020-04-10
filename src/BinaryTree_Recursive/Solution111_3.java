package BinaryTree_Recursive;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 迭代实现 层序遍历
public class Solution111_3 {

    public int minDepth(TreeNode root) {

        if(root == null)
            return 0;

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>(); // 存放当前节点深度和节点
        queue.offer(new Pair<>(root, 1));
        while(!queue.isEmpty()) {

            Pair<TreeNode, Integer> tmpPair = queue.poll();
            TreeNode tmpNode = tmpPair.getKey();
            int curDepth = tmpPair.getValue();
            if(tmpNode.left == null && tmpNode.right == null)
                return curDepth;
            if(tmpNode.left != null) queue.offer(new Pair<>(tmpNode.left, curDepth + 1));
            if(tmpNode.right != null) queue.offer(new Pair<>(tmpNode.right, curDepth + 1));
        }

        throw new IllegalStateException("No Solution.");
    }
}
