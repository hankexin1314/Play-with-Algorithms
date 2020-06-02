package jianzhiOffer;

import java.util.LinkedList;
import java.util.Queue;

public class Solution27_2 {

    public TreeNode mirrorTree(TreeNode root) {

        if(root == null) return root;
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }
}
