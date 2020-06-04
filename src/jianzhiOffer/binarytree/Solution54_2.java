package jianzhiOffer.binarytree;

import java.util.Deque;
import java.util.LinkedList;

public class Solution54_2 {


    public int kthLargest(TreeNode root, int k) {

        if(root == null) return -1;
        Deque<TreeNode> stack = new LinkedList<>();
        int count = 0;
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            while(cur != null) {
                stack.addFirst(cur);
                cur = cur.right;
            }
            cur = stack.removeFirst();
            count ++;
            if(count == k) {
                return cur.val;
            }
            cur = cur.left;
        }
        return -1;
    }

}
