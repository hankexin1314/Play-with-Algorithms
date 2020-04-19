package BinaryTree_Recursive;

import sun.reflect.generics.tree.Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
// 迭代
public class Solution236_1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null)
            return null;
        HashMap<TreeNode, TreeNode> map = new HashMap<>(); // key为节点 value为父节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        map.put(root, null);
        TreeNode node;
        boolean findP = false, findQ = false;
        while(!(findP && findQ)) {
            node = queue.poll();
            if(!findP && node.val == p.val)
                findP = true;
            if(!findQ && node.val == q.val)
                findQ = true;
            if(node.left != null) {
                map.put(node.left, node);
                queue.offer(node.left);
            }
            if(node.right != null) {
                map.put(node.right, node);
                queue.offer(node.right);
            }
        }
        HashSet<Integer> set = new HashSet<>();
        set.add(p.val);
        set.add(q.val);
        node = map.get(p);
        while(node != null) {
            if(set.contains(node.val))
                return node;
            set.add(node.val);
            node = map.get(node);
        }
        node = map.get(q);
        while(node != null) {
            if(set.contains(node.val))
                return node;
            set.add(node.val);
            node = map.get(node);
        }
        return null;
    }
}
