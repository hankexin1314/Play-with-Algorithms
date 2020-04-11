package BinaryTree_Recursive;


// 暴力解法 递归
public class Solution222_1 {

    public int countNodes(TreeNode root) {

        if(root == null)
            return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
