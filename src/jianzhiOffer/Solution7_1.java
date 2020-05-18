package jianzhiOffer;

import sun.reflect.generics.tree.Tree;

import java.util.Arrays;

public class Solution7_1 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if(preorder == null || preorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        int index = 0;
        for(int num: inorder) {
            if(num == preorder[0]) break;
            index++;
        }
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, 1+index),
                Arrays.copyOfRange(inorder, 0, index));
        root.right = buildTree(Arrays.copyOfRange(preorder, index + 1, preorder.length),
                Arrays.copyOfRange(inorder, index + 1, inorder.length));

        return root;
    }


}
