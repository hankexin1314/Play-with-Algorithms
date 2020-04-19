package BinaryTree_Recursive;

import java.util.Arrays;

// copyOfRange 消耗大量时间
public class Solution108_1 {
    public TreeNode sortedArrayToBST(int[] nums) {

        int l = 0, r = nums.length - 1;
        if(l > r)
            return null;
        int mid = l + (r - l) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, l, mid));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, r + 1));

        return root;
    }

}
