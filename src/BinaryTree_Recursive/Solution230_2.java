package BinaryTree_Recursive;

import java.util.Stack;

// 中序遍历，第k个元素即为结果 递归
public class Solution230_2 {
    int count = 0;
    int ans;
    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return ans;
    }

    private void inorder(TreeNode root, int k) {
        if(root == null || count > k) return;
        else {
            inorder(root.left, k);
            count ++;
            if(count == k)
                ans = root.val;
            inorder(root.right, k);
        }
    }
}
