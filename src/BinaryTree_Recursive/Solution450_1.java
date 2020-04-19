package BinaryTree_Recursive;

public class Solution450_1 {
    public TreeNode deleteNode(TreeNode root, int key) {

        if(root == null) return root;
        if(key < root.val) root.left = deleteNode(root.left, key);
        else if(key > root.val) root.right = deleteNode(root.right, key);
        else { // key == root.val 待删除节点为root
            if(root.left == null) return root.right;
            else if(root.right == null) return root.left;
            else { // 左右都不为null
                TreeNode node = root.left, par = null;
                while(node.right != null) { // 左子树中寻找最大的
                    par = node;
                    node = node.right;
                }
                if(par == null) {
                    node.right = root.right;
                    return node;
                }
                // node只可能是par的右孩子 且node的右孩子为null
                par.right = node.left;
                node.left = root.left;
                node.right = root.right;
                return node;
            }
        }
        return root;
    }
}
