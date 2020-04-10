package BinaryTree_Recursive;

import javafx.util.Pair;

import java.util.Stack;

// 迭代实现
public class Solution111_2 {

    public int minDepth(TreeNode root) {

        if(root == null)
            return 0;
        int depth = Integer.MAX_VALUE; // 用来存放最小深度
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>(); // 存放当前节点深度和节点
        stack.push(new Pair<>(root, 1));
        while(!stack.isEmpty()) {

            Pair<TreeNode, Integer> tmpPair = stack.pop();
            TreeNode tmpNode = tmpPair.getKey();
            int curDepth = tmpPair.getValue();
            if(tmpNode.left == null && tmpNode.right == null)
                depth = Math.min(curDepth, depth);
            if(tmpNode.left != null) stack.push(new Pair<>(tmpNode.left, curDepth + 1));
            if(tmpNode.right != null) stack.push(new Pair<>(tmpNode.right, curDepth + 1));
        }

        return depth;
    }
}
