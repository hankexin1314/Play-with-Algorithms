package BinaryTree_Recursive;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// 迭代
public class Solution235_2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null)
            return null;
        TreeNode node = root;
        while(true) {
            if(p.val == node.val)
                return p;
            else if(q.val == node.val)
                return q;
            else if((p.val < node.val && q.val > node.val) ||
                    (p.val > node.val && q.val < node.val))
                return node;
            else if(p.val < node.val && q.val < node.val)
                node = node.left;
            else
                node = node.right;
        }
    }
}
