package BinaryTree_Recursive;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution235_1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null) return null;
        HashMap<TreeNode, TreeNode> map = new HashMap<>(); // key为节点 value为父节点（最近，且不为节点本身）
        Queue<TreeNode> queue = new LinkedList<>();
        map.put(root, null);
        queue.offer(root);
        boolean findP = false, findQ = false;
        while(!(findP && findQ)) {

            TreeNode node = queue.poll();
            if(!findP && node.val == p.val)
                findP = true;
            if(!findQ && node.val == q.val)
                findQ = true;
            if(node.left != null) {
                queue.offer(node.left);
                map.put(node.left, node);
            }
            if(node.right != null) {
                queue.offer(node.right);
                map.put(node.right, node);
            }
        }
        HashSet<Integer> set = new HashSet<>();
        set.add(p.val);
        set.add(q.val);
        TreeNode tmp = map.get(p);
        while(tmp != null) {
            if(set.contains(tmp.val))
                return tmp;
            set.add(tmp.val);
            tmp = map.get(tmp);
        }
        tmp = map.get(q);
        while(tmp != null) {
            if(set.contains(tmp.val))
                return tmp;
            set.add(tmp.val);
            tmp = map.get(tmp);
        }
        throw new IllegalStateException("没有可行解");
    }
}
