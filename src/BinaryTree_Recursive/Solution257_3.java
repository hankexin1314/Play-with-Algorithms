package BinaryTree_Recursive;

import java.util.ArrayList;
import java.util.List;

// 递归实现 使用辅助函数避免冗余遍历

public class Solution257_3 {

    public List<String> binaryTreePaths(TreeNode root) {

        List<String> res = new ArrayList<>();
        if(root != null) binaryTreePaths(root, "", res);
        return res;
    }

    private void binaryTreePaths(TreeNode root, String path, List<String> res) {
        if(root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder(path);
            sb.append(root.val);
            res.add(sb.toString());
        }

        else {
            if(root.left != null) {
                StringBuilder sb = new StringBuilder(path);
                sb.append(root.val);
                sb.append("->");
                binaryTreePaths(root.left, sb.toString(), res);
            }
            if(root.right != null) {
                StringBuilder sb = new StringBuilder(path);
                sb.append(root.val);
                sb.append("->");
                binaryTreePaths(root.right, sb.toString(), res);
            }
        }
    }
}
