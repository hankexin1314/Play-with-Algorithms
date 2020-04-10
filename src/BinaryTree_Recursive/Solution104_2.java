package BinaryTree_Recursive;

// 递归
public class Solution104_2 {

    public int maxDepth(TreeNode root) {

        if(root == null)
            return 0;

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }


}
