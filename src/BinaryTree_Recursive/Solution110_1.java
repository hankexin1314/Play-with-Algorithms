package BinaryTree_Recursive;


import javafx.util.Pair;
import sun.reflect.generics.tree.Tree;

import java.util.Stack;

// 递归 自顶向下

public class Solution110_1 {

    public boolean isBalanced(TreeNode root) {

        if(root == null)
            return true;
        return Math.abs(computeDepth(root.left) - computeDepth(root.right)) <= 1 &&
                isBalanced(root.left) &&
                isBalanced(root.right);

    }

    // 计算深度
    private int computeDepth(TreeNode root) {

        if(root == null)
            return 0;
        else
            return 1 + Math.max(computeDepth(root.right), computeDepth(root.left));
    }
}
