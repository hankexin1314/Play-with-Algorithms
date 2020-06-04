package jianzhiOffer.binarytree;

public class Solution54_1 {

    int count;
    int res;

    public int kthLargest(TreeNode root, int k) {

        if(root == null) return -1;
        count = 0;
        reInorder(root, k);
        return res;
    }

    private void reInorder(TreeNode root, int k) {

        if(root == null) return;
        reInorder(root.right, k);
        count ++;
        if(count == k)  {
            res = root.val;
            return;
        }
        else if(count > k) return;
        reInorder(root.left, k);
    }
}
