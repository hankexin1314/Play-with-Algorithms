package BinaryTree_Recursive;

public class Solution98_1 {

    // 递归

    public boolean isValidBST(TreeNode root) {
        return helper(null, null, root);
    }

    // 值的范围为(min, max])
    // 使用null 避免溢出问题
    private boolean helper(Integer min, Integer max, TreeNode node) {

        if(node == null)
            return true;
        int val = node.val;
        if(min != null && val <= min) return false;
        if(max != null && val >= max) return false;
        return helper(min, val, node.left) && helper(val, max, node.right);
    }

}
