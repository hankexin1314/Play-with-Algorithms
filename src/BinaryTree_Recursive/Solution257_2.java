package BinaryTree_Recursive;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 迭代实现
public class Solution257_2 {

    public List<String> binaryTreePaths(TreeNode root) {

        List<String> res = new ArrayList<>();
        if(root == null)
            return res;
        Queue<Pair<TreeNode, String>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, String.valueOf(root.val)));
        while(!queue.isEmpty()) {
            Pair<TreeNode, String> pair = queue.poll();
            TreeNode node = pair.getKey();
            String path = pair.getValue();
            if(node.left == null && node.right == null)
                res.add(path);
            else {
                if(node.left != null)
                    queue.offer(new Pair<>(node.left, path + "->" + node.left.val));
                if(node.right != null)
                    queue.offer(new Pair<>(node.right, path + "->" + node.right.val));
            }
        }

        return res;
    }
}
