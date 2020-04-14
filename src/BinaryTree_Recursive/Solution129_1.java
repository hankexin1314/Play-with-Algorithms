package BinaryTree_Recursive;


import java.util.ArrayList;
import java.util.List;

// 递归 使用String
public class Solution129_1 {
    public int sumNumbers(TreeNode root) {

        int res = 0;
        List<String> path = new ArrayList<>();
        sumNumbers(root, new StringBuilder(""), path);
        for(String s: path)
            res += Integer.parseInt(s);

        return res;
    }

    private void sumNumbers(TreeNode root, StringBuilder path, List<String> res) {

        if(root == null)
            return;
        int len = path.length();
        path.append(root.val);
        if(root.left == null && root.right == null)
            res.add(path.toString());
        else {
            if(root.left != null) sumNumbers(root.left, path, res);
            if(root.right != null) sumNumbers(root.right, path, res);
        }
        path.setLength(len);
    }
}
