package BinaryTree_Recursive;


import javafx.util.Pair;
import java.util.Stack;

// 迭代
public class Solution104_3 {

    public int maxDepth(TreeNode root) {

        if(root == null)
            return 0;
        int depth = 0; // 纪录最大深度
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 1));
        while(!stack.isEmpty()) {

            Pair<TreeNode, Integer> tmpPair = stack.pop();
            TreeNode node = tmpPair.getKey();
            int curDepth = tmpPair.getValue();
            depth = Math.max(curDepth, depth);
            if(node.left != null) stack.push(new Pair<>(node.left, curDepth + 1));
            if(node.right != null) stack.push(new Pair<>(node.right, curDepth + 1));
        }

        return depth;
    }


}
