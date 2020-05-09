package Dynamic_Programming;

// 动态规划

public class Solution337_3 {

    public int rob(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }

    // 从root节点开始能抢多少钱
    private int[] helper(TreeNode root) {

        if(root == null) return new int[2];

        int[] left = helper(root.left);
        int[] right = helper(root.right);
        int[] res = new int[2];

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];

        return res;
    }



}
