package BinaryTree_Recursive;

public class Solution104_1 {

    public int maxDepth(TreeNode root) {

        if(root == null)
            return 0;

        return Math.max(maxDepth(root.left, 1), maxDepth(root.right, 1));
    }

    //
    private int maxDepth(TreeNode root, int depth) {

        if(root == null)
            return depth;
        return Math.max(maxDepth(root.left, depth + 1), maxDepth(root.right, depth + 1));
    }

}
