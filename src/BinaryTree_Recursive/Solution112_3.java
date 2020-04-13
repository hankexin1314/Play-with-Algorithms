package BinaryTree_Recursive;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 迭代实现 深度优先
public class Solution112_3 {

    public boolean hasPathSum(TreeNode root, int sum) {

        if(root == null)
            return false;
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, root.val));

        while(!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.pop();
            TreeNode node = pair.getKey();
            int curSum = pair.getValue();
            if(node.left == null & node.right == null && curSum == sum)
                return true;
            if(node.right != null)
                stack.push(new Pair<>(node.right, curSum + node.right.val));
            if(node.left != null)
                stack.push(new Pair<>(node.left, curSum + node.left.val));
        }

        return false;
    }
}
