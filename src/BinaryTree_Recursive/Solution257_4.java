package BinaryTree_Recursive;

import java.util.ArrayList;
import java.util.List;

// 递归实现 使用辅助函数避免冗余遍历
// StringBuilder 的优化写法

public class Solution257_4 {

    public List<String> binaryTreePaths(TreeNode root) {

        List<String> res = new ArrayList<>();
        if(root != null) binaryTreePaths(root, new StringBuilder(""), res);
        return res;
    }

    private void binaryTreePaths(TreeNode root, StringBuilder path, List<String> res) {
        int len = path.length();
        if(root.left == null && root.right == null) {
            path.append(root.val);
            res.add(path.toString());
        }
        else {
            if(root.left != null) {
                path.append(root.val);
                path.append("->");
                binaryTreePaths(root.left, path, res);
            }
            path.setLength(len);
            if(root.right != null) {
                path.append(root.val);
                path.append("->");
                binaryTreePaths(root.right, path, res);
            }
        }
    }
}
