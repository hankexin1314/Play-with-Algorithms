package BinaryTree_Recursive;


import java.util.ArrayList;
import java.util.List;

// 递归 使用int
public class Solution129_3 {
    public int sumNumbers(TreeNode root) {

        return sumNumbers(root, 0);
    }

    private int sumNumbers(TreeNode root, int curSum) {

        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return curSum * 10 + root.val;
        else
            return sumNumbers(root.left, curSum * 10 + root.val) +
                    sumNumbers(root.right, curSum * 10 + root.val);
    }
}
