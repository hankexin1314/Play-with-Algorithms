package BinaryTree_Recursive;


import java.util.LinkedList;
import java.util.Queue;

// 暴力解法 迭代
public class Solution222_2 {

    public int countNodes(TreeNode root) {

        int count = 0;
        if(root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {

            TreeNode node = queue.poll();
            count ++;
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }

        return count;
    }
}
