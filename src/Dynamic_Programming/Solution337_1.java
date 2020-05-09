package Dynamic_Programming;

// 递归
public class Solution337_1 {

    public int rob(TreeNode root) {

        return helper(root, true);
    }

    // flag代表是否可以抢根节点
    private int helper(TreeNode root, boolean flag) {
        if(root == null) return 0;
        if(flag)
            return Math.max(root.val + helper(root.left, false) + helper(root.right, false), // 抢根节点
                    helper(root.left, true) + helper(root.right, true)); // 不抢根节点
        else
            return helper(root.left, true) + helper(root.right, true);
    }

}
