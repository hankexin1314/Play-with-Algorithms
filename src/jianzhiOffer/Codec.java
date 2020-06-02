package jianzhiOffer;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if(root == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 1; // 用来记录非null元素的个数
        while(!queue.isEmpty()) {

            TreeNode node = queue.poll();
            if(node != null) {
                count --;
                sb.append(node.val);
                queue.offer(node.left);
                count += (node.left == null ? 0 : 1);
                queue.offer(node.right);
                count += (node.right == null ? 0 : 1);
            }
            else sb.append("null");
            if(count == 0) break;
            sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if(data == null || data.equals("") || data.equals("[]")) return null;
        data = data.substring(1, data.length() - 1); // 去掉方括号
        String[] vals = data.split(",");
        if(vals.length == 0) return null;

        int index = 0;
        TreeNode root = strToTreeNode(vals[0]);
        index++;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty() && index < vals.length) {

            TreeNode node = queue.poll();
            if(node == null) continue;
            TreeNode left = strToTreeNode(vals[index]);
            index ++;
            TreeNode right = index < vals.length ? strToTreeNode(vals[index]) : null;
            index ++;
            node.left = left;
            node.right = right;
            queue.offer(left);
            queue.offer(right);
        }

        return root;
    }

    private TreeNode strToTreeNode(String str) {
        if(str.equals("null")) return null;
        int val = Integer.parseInt(str);
        return new TreeNode(val);
    }

    public static void main(String[] args) {
        String str = "[]";
        System.out.println(str.substring(1, str.length() - 1));
    }
}
