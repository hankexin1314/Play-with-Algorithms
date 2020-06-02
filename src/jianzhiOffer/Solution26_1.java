package jianzhiOffer;

public class Solution26_1 {

    public boolean isSubStructure(TreeNode A, TreeNode B) {

        if(B == null || A == null) return false;
        return helper(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);

    }

    // 判断B是否为A的顶层子结构（A B 判断都是从根节点开始）
    private boolean helper(TreeNode A, TreeNode B) {

        if(B == null) return true;
        if(A == null || A.val != B.val) return false;
        return (helper(A.left, B.left) && helper(A.right, B.right)) || helper(A.left, B) || helper(A.right, B);
    }

}
