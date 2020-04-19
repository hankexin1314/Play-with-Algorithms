package BinaryTree_Recursive;

// 递归
public class Solution235_3 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        assert p != null && q != null;
        if(root == null)
            return null;
        if(q.val < root.val && p.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        else if(q.val > root.val && p.val > root.val)
            return lowestCommonAncestor(root.right, p, q);
        else
            return root;
    }
}
