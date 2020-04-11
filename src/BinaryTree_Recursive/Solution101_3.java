package BinaryTree_Recursive;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 迭代实现 简化判断

public class Solution101_3 {

    public boolean isSymmetric(TreeNode root) {

        Queue<TreeNode> leftQueue = new LinkedList<>();
        Queue<TreeNode> rightQueue = new LinkedList<>();
        leftQueue.offer(root);
        rightQueue.offer(root);

        while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
            TreeNode nodeL = leftQueue.poll();
            TreeNode nodeR = rightQueue.poll();

            if (nodeL == null && nodeR == null) continue;
            else if (nodeL == null || nodeR == null) return false;
            else if (nodeL.val != nodeR.val) return false;
            else {
                leftQueue.offer(nodeL.left);
                rightQueue.offer(nodeR.right);
                leftQueue.offer(nodeL.right);
                rightQueue.offer(nodeR.left);
            }
        }

        return leftQueue.isEmpty() && rightQueue.isEmpty();
    }
}
