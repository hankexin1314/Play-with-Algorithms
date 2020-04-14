package BinaryTree_Recursive;

import java.util.ArrayList;
import java.util.List;

// 递归实现 对递归结果进行额外处理
public class Solution257_1 {

    public List<String> binaryTreePaths(TreeNode root) {

        List<String> res = new ArrayList<>();
        if (root == null)
            return res;
        if (root.left == null && root.right == null) {
            res.add(String.valueOf(root.val));
            return res;
        }

        List<String> leftRes = binaryTreePaths(root.left);
        List<String> rightRes = binaryTreePaths(root.right);

        for (String s : leftRes) {
            StringBuilder sb = new StringBuilder(String.valueOf(root.val));
            sb.append("->");
            sb.append(s);
            res.add(sb.toString());
        }

        for (String s : rightRes) {
            StringBuilder sb = new StringBuilder(String.valueOf(root.val));
            sb.append("->");
            sb.append(s);
            res.add(sb.toString());
        }

        return res;
    }
}
