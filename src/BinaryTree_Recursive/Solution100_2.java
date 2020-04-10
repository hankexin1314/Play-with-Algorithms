package BinaryTree_Recursive;


import java.util.LinkedList;
import java.util.Queue;

// 迭代实现
public class Solution100_2 {

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if(p == null && q == null) return true;
        if(p == null || q == null) return false;

        Queue<TreeNode> pQueue = new LinkedList<>();
        pQueue.offer(p);
        Queue<TreeNode> qQueue = new LinkedList<>();
        qQueue.offer(q);

        while(!pQueue.isEmpty() && !qQueue.isEmpty()) {

            TreeNode pNode = pQueue.poll();
            TreeNode qNode = qQueue.poll();
            if(pNode.val != qNode.val) return false;
            if(pNode.left != null && qNode.left != null) {
                pQueue.offer(pNode.left);
                qQueue.offer(qNode.left);
            }
            else if(pNode.left == null && qNode.left == null);
            else return false;

            if(pNode.right != null && qNode.right != null) {
                pQueue.offer(pNode.right);
                qQueue.offer(qNode.right);
            }
            else if(pNode.right == null && qNode.right == null);
            else return false;
        }
        return pQueue.isEmpty() && qQueue.isEmpty();
    }
}
