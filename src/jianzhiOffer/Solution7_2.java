package jianzhiOffer;


import java.util.HashMap;

public class Solution7_2 {

    int[] pre;
    int[] in;
    HashMap<Integer, Integer> indexMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if(preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) return null;
        this.pre = preorder;
        this.in = inorder;
        for(int i = 0; i < inorder.length; i++)
            indexMap.put(inorder[i], i);

        return helper(0, pre.length - 1, 0, in.length - 1);
    }

    // 返回前序遍历为  pre[start, end]  中序遍历为[start, end] 的二叉树的根节点
    private TreeNode helper(int preStart, int preEnd, int inStart, int inEnd) {


        if(preStart > preEnd) return null;
        TreeNode root = new TreeNode(pre[preStart]);
        if(preStart == preEnd) return root; // 只有一个点  直接返回节点值

        // 根节点在中序遍历数组中的index
        // 可得 左子树节点个数为 index - inStart  右子树节点个数为 inEnd - index
        int index = indexMap.get(pre[preStart]);
        root.left = helper(preStart + 1, preStart + index - inStart, inStart, index - 1);
        root.right = helper(preEnd - (inEnd - index) + 1, preEnd, index + 1, inEnd);

        return root;
    }

    public static void main(String[] args) {
        int[] in = {9,3,15,20,7};
        int[] pre = {3,9,20,15,7};
        TreeNode res = (new Solution7_2()).buildTree(pre, in);
        System.out.println(res);
    }




}
