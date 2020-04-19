package BinaryTree_Recursive;

import java.util.Arrays;

// 优化

public class Solution108_2 {
    public TreeNode sortedArrayToBST(int[] nums) {

        int l = 0, r = nums.length - 1;
        return helper(nums, l, r);
    }

    private TreeNode helper(int[] nums, int l, int r) {
        if(l > r)
            return null;
        int mid = l + (r - l) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, l, mid - 1);
        root.right = helper(nums, mid + 1, r);

        return root;
    }

}
