package BinaryTree_Recursive;


// 递归
public class Solution226_1 {

    public TreeNode invertTree(TreeNode root) {

        if(root == null)
            return null;
        if(root.left == null && root.right == null)
            return root;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);

        return root;
    }
}
